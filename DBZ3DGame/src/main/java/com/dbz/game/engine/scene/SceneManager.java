package com.dbz.game.engine.scene;

import com.dbz.game.engine.input.InputManager;

public class SceneManager {

    private Object currentScene;

    public void loadInitialScene() {
        currentScene = new Object(); // cambiar luego
    }

    public void update(float dt, InputManager input) {
        // update escena
    }

    public Object getCurrentScene() {
        return currentScene;
    }

    public void cleanup() {}
}
