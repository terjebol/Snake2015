package org.nardogames.rattlesnake.domain;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.nardogames.rattlesnake.common.content.IScene;
import org.nardogames.rattlesnake.common.keyboard.AbstractKeyEventListener;
import org.nardogames.rattlesnake.common.keyboard.KeyboardController;
import org.nardogames.rattlesnake.common.util.FPS;
import org.nardogames.rattlesnake.common.util.FPSListener;
import org.nardogames.rattlesnake.common.util.LoopTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.opengl.GL11.*;

public class RattleSnake implements FPSListener {
    private static final Logger log = LoggerFactory.getLogger(RattleSnake.class);
    private LoopTime loopTime;
    private boolean requestedShutdown;
    private DisplayMode displayMode = new DisplayMode(1280, 720); //Display.getDesktopDisplayMode();
    private IScene currentScene;
    private KeyboardController keyboardController = new KeyboardController();

    private static RattleSnake instance;

    public static RattleSnake getInstance() {
        if(instance == null) {
            instance = new RattleSnake();
        }
        return instance;
    }

    public KeyboardController getKeyboardController() {
        return keyboardController;
    }

    public IScene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(IScene scene) {
        currentScene = scene;
    }

    public final void start() {
        getKeyboardController().addListener(new AbstractKeyEventListener() {
            @Override
            public boolean listensToKey(int key) {
                return key == Keyboard.KEY_ESCAPE;
            }

            @Override
            public void notifyKeyReleased(int key, long timeDown) {
                requestedShutdown = true;
            }
        });

        loopTime = new LoopTime();
        createAndShowWindow();
        initializeGL();
        requestedShutdown = false;
        FPS.addFPSListener(this);
        while (!isWaitingOnShutdown()) {
            sleepAndSync();
            update((float)loopTime.getDeltaTime());
            render();
        }
        beforeExit();
        Display.destroy();
    }

    public int getDisplayWidth() {
        //return  1280;
        return displayMode.getWidth();
    }

    public int getDisplayHeight() {
        //return 720;
        return displayMode.getHeight();
    }

    private void beforeExit() {

    }

    private void createAndShowWindow() {
        try {
            Display.setTitle("RattleSnake");
            Display.setDisplayMode(displayMode);
            Display.setFullscreen(false);
            Display.create();
        } catch (LWJGLException e) {
            throw new RuntimeException("Could not create window", e);
        }
    }

    private void initializeGL() {
        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_DEPTH_FUNC);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, getDisplayWidth(), 0, getDisplayHeight(),  -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glViewport(0, 0, getDisplayWidth(), getDisplayHeight());
        glClearColor(0.0f,0.0f,0.0f,1f);
    }

    private void update(float deltaTime) {
        if(!currentScene.isInitialized()) {
            currentScene.initialize();
        }

        getKeyboardController().update(deltaTime);
        getCurrentScene().update(deltaTime);
        FPS.increment(deltaTime);
    }


    private void render() {
        // Clear the screen and depth buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        getCurrentScene().render();
    }

    private void sleepAndSync() {
        Display.update();
        Display.sync(60);
    }


    private boolean isWaitingOnShutdown() {
        return Display.isCloseRequested() || requestedShutdown;
    }

    @Override
    public void updateFPS(int frames) {
        Display.setTitle("FPS: "+frames);
    }

}
