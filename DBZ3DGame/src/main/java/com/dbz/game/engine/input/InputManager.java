package com.dbz.game.engine.input;

import org.lwjgl.glfw.GLFW;
import com.dbz.game.engine.window.Window;

public class InputManager {
    private Window window;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] keysPressed = new boolean[GLFW.GLFW_KEY_LAST];

    public InputManager(Window window) {
        this.window = window;
        setupCallbacks();
    }

    private void setupCallbacks() {
        GLFW.glfwSetKeyCallback(window.getId(), (win, key, scancode, action, mods) -> {
            if (key >= 0 && key < keys.length) {
                if (action == GLFW.GLFW_PRESS) {
                    keys[key] = true;
                    keysPressed[key] = true;
                } else if (action == GLFW.GLFW_RELEASE) {
                    keys[key] = false;
                }
            }
        });
    }

    public void poll() {
        for (int i = 0; i < keysPressed.length; i++) {
            keysPressed[i] = false;
        }
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }

    public boolean isKeyPressed(int key) {
        return keysPressed[key];
    }
}
