package org.nardogames.rattlesnake.domain;

import org.lwjgl.opengl.GL11;
import org.nardogames.rattlesnake.common.content.AbstractSprite;
import org.nardogames.rattlesnake.common.content.IController;
import org.nardogames.rattlesnake.common.content.IScene;
import org.nardogames.rattlesnake.common.gl.TextureCoord2f;
import org.nardogames.rattlesnake.common.gl.VBO;
import org.nardogames.rattlesnake.common.particles.*;
import org.nardogames.rattlesnake.common.util.TextureUtils;
import org.nardogames.rattlesnake.common.util.Vertex2f;
import org.nardogames.rattlesnake.common.util.VertexUtils;
import org.nardogames.rattlesnake.domain.enemies.FireEnemy;
import org.nardogames.rattlesnake.domain.food.EnergyCloudProvider;
import org.nardogames.rattlesnake.domain.food.IProvideFood;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScene implements IScene {
    private Random randomizer;
    private boolean isInitialized;
    private List<Enemy> currentEnemies;
    private List<IProvideFood> foodProviders;
    private Player player;
    private Texture backgroundTexture;
    private VBO vbo;

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void initialize() {
        player = new Player();
        currentEnemies = new ArrayList<>();
        foodProviders = new ArrayList<>();
        foodProviders.add(new EnergyCloudProvider());
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
        //txt = txt.top(1.2f).right(1.2f);
        vbo.putTextureCoords( txt );
        isInitialized = true;
        randomizer = new Random();
    }

    @Override
    public <T extends AbstractSprite> IController<T> getController(ControllerType controllerType) {
        return null;
    }

    @Override
    public void update(float deltaTime) {
        updateFood(deltaTime);
        spawnEnemyIfPossible();
        updateEnemies(deltaTime);
        player.update(deltaTime);
        ParticleSystem.globalInstance().update(deltaTime);
    }

    private void updateFood(float deltaTime) {
        Snake snake = player.getSnake();
        for(IProvideFood provider : foodProviders) {
            provider.update(deltaTime, snake);
            checkFoodCollisionsWithSnake(provider);
        }
        addAndRemoveFoodProviders();
    }

    private void checkFoodCollisionsWithSnake(IProvideFood provider) {
        List<? extends IAmFood> currentFood = provider.getCurrentFood();
        Snake snake = player.getSnake();
        for(IAmFood food : currentFood) {
            if(food.collidesWithSnake(snake)) {
                snake.notifyAteFood(food);
                food.notifyEaten();
            }
        }
    }

    private void addAndRemoveFoodProviders() {
        for(int i = foodProviders.size()-1; i>=0; i--) {
            IProvideFood provider = foodProviders.get(i);
            if (provider.isInactive()) {
                foodProviders.remove(i);
                provider.dispose();
            }
        }
    }

    private void updateEnemies(float delta) {
        for(Enemy e: currentEnemies) {
            e.update(delta);
            if(!e.isActive()) {
                e.dispose();
            }
        }
        checkEnemyCollisionsWithSnake();
    }

    private void spawnEnemyIfPossible() {
        if(randomizer.nextFloat() > 0.98f) {
            currentEnemies.add(FireEnemy.createNewEnemy());
        }
    }

    private void checkEnemyCollisionsWithSnake() {
        Snake snake = player.getSnake();
        for(Enemy enemy : currentEnemies) {
            if(enemy.collidesWithSnake(snake)) {
                snake.notifyHitByEnemy(enemy);
                enemy.notifyHitSnake();
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
        backgroundTexture.bind();
        vbo.render();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        ParticleSystem.globalInstance().render();
        GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }
}
