package com.dbz.game.engine.input;

import org.lwjgl.glfw.GLFW;
import com.dbz.game.engine.window.Window;

public class InputManager {
    private Window window;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    public InputManager(Window window) { this.window = window; }

    public boolean isKeyPressed(int key) { return keys[key]; }
    public boolean isKeyDown(int key) { return keys[key]; }
}
