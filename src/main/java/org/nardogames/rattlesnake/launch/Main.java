package org.nardogames.rattlesnake.launch;

import org.nardogames.rattlesnake.domain.GameScene;
import org.nardogames.rattlesnake.domain.RattleSnake;

public class Main {
    public static void main(String[] args) {
        loadLibraries();
        RattleSnake instance = RattleSnake.getInstance();
        instance.setCurrentScene( new GameScene());
        instance.start();
    }

    private static void loadLibraries() {
        LibLoader.loadAll(
                "lwjgl64.dll",
                "OpenAL64.dll",
                "jinput-dx8_64.dll"
        );
    }
}
