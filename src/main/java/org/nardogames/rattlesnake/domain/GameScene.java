package org.nardogames.rattlesnake.domain;

import org.lwjgl.opengl.GL11;
import org.nardogames.rattlesnake.common.content.IScene;
import org.nardogames.rattlesnake.common.gl.TextureCoord2f;
import org.nardogames.rattlesnake.common.gl.VBO;
import org.nardogames.rattlesnake.common.particles.ParticleSystem;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.common.util.Vertex2f;
import org.nardogames.rattlesnake.common.util.VertexUtils;
import org.nardogames.rattlesnake.domain.enemies.CometProvider;
import org.nardogames.rattlesnake.domain.enemies.IProvideEnemies;
import org.nardogames.rattlesnake.domain.enemies.SolarWhipProvider;
import org.nardogames.rattlesnake.domain.food.EnergyCloudProvider;
import org.nardogames.rattlesnake.domain.food.IProvideFood;
import org.nardogames.rattlesnake.domain.player.Player;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;

public class GameScene implements IScene {
    private boolean isInitialized;
    private List<IProvideFood> foodProviders;
    private Player player;
    private Texture backgroundTexture;
    private VBO vbo;
    private List<IProvideEnemies> enemyProviders;

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void initialize() {
        player = new Player();
        foodProviders = new ArrayList<>();
        foodProviders.add(new EnergyCloudProvider());
        enemyProviders = new ArrayList<>();
        enemyProviders.add(new CometProvider());
        enemyProviders.add(new SolarWhipProvider());
        backgroundTexture = TextureUtils.getTexture("textures/background_pow2.png");
        vbo = new VBO(1);

        float w = RattleSnake.getInstance().getDisplayWidth();
        float h = RattleSnake.getInstance().getDisplayHeight();

        Vertex2f[] vertices = VertexUtils.createDefaultQuad(
               w *0.5f, h * 0.5f, w, h);


        vbo.resetData();
        for(Vertex2f vertex : vertices) {
            System.out.println(vertex.getValues()[0]+","+vertex.getValues()[1]);
            vbo.putVertex(vertex);
            vbo.putColor( new float[] { 1f,1f,1f,1f });
        }
        TextureCoord2f txt = new TextureCoord2f();
        vbo.putTextureCoords( txt );
        isInitialized = true;
    }

    @Override
    public void update(float deltaTime) {
        updateFood(deltaTime);
        updateEnemies(deltaTime);
        player.update(deltaTime);
        ParticleSystem.globalInstance().update(deltaTime);
    }

    private void updateFood(float deltaTime) {
        for(IProvideFood provider : foodProviders) {
            provider.update(deltaTime, player);
        }
        addAndRemoveFoodProviders();
    }

    private void updateEnemies(float deltaTime) {
        for(IProvideEnemies provider: enemyProviders) {
            provider.update(deltaTime, player);
        }
        addAndRemoveEnemyProviders();
    }


//    private void checkFoodCollisionsWithSnake(IProvideFood provider) {
//        List<? extends IAmFood> currentFood = provider.getCurrentEntities();
//        Snake snake = player.getSnake();
//        for(IAmFood food : currentFood) {
//            if(food.collidesWithSnake(snake)) {
//                snake.eatFood(food);
//                food.notifyEaten();
//            }
//        }
//    }

    private void addAndRemoveFoodProviders() {
        for(int i = foodProviders.size()-1; i>=0; i--) {
            IProvideFood provider = foodProviders.get(i);
            if (provider.isInactive()) {
                foodProviders.remove(i);
                provider.dispose();
            }
        }
    }

    private void addAndRemoveEnemyProviders() {
        for(int i = enemyProviders.size()-1; i>=0; i--) {
            IProvideEnemies provider = enemyProviders.get(i);
            if(provider.isInactive()) {
                enemyProviders.remove(i);
                provider.dispose();
            }
        }
    }

    @Override
    public void render() {
        if (!isInitialized()) {
            return;
        }

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        backgroundTexture.bind();
        vbo.render();
        ParticleSystem.globalInstance().render();
        player.renderScore();

        GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }
}
