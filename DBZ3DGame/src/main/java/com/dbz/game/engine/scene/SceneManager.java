package com.dbz.game.engine.scene;

import com.dbz.game.engine.input.InputManager;
import com.dbz.game.gameplay.FightScene;

public class SceneManager {

    private FightScene currentScene;

    public void loadInitialScene() {
        currentScene = new FightScene();
    }

    public void update(float dt, InputManager input) {
        if (currentScene != null) {
            currentScene.update(dt, input);
        }
    }

    public FightScene getCurrentScene() {
        return currentScene;
    }

    public void cleanup() {
        if (currentScene != null) {
            currentScene.cleanup();
        }
    }
}
