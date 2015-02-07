package org.nardogames.rattlesnake.common.keyboard;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.input.Keyboard.*;

public class GameKeys {

    private static Map<Integer, GameKeys> gameKeysMap;

    static {
        gameKeysMap = new HashMap<>();
        gameKeysMap.put(1, new GameKeys(
                KEY_UP, KEY_DOWN, KEY_LEFT, KEY_RIGHT, KEY_RETURN, KEY_RCONTROL
        ));
        gameKeysMap.put(2, new GameKeys(
                KEY_W, KEY_S, KEY_A, KEY_D, KEY_LCONTROL, KEY_TAB
        ));
    }

    private final int[] keys;
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;
    public final static int FIRE = 4;
    public final static int SWITCH_TANK = 5;

    private GameKeys(int... keys) {
        this.keys = keys;
    }

    /**
     *
     * @param playerId (Typically 1 or 2, indicating player 1 or player 2)
     * @return GameKeys for that player
     */
    public static GameKeys getKeysForPlayer(int playerId) {
        return gameKeysMap.get(playerId);
    }


    public boolean contains(int key) {
        for(int i = 0; i < keys.length; i++) {
            if(keys[i] == key) {
                return true;
            }
        }
        return false;
    }

    public int fire() {
        return keys[FIRE];
    }
    public int switchTank() {
        return keys[SWITCH_TANK];
    }
    public int weaponUp() {
        return keys[UP];
    }
    public int weaponDown() {
        return keys[DOWN];
    }
}
