package com.dbz.game.engine.input;

import com.dbz.game.engine.window.Window;
import org.lwjgl.glfw.GLFW;

public class InputManager {

    private Window window;

    public InputManager(Window window) {
        this.window = window;
    }

    public void poll() {
        // Aquí vienen teclas, combos, etc.
    }
}
