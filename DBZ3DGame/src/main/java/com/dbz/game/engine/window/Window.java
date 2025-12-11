package com.dbz.game.engine.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class Window {
    private long id;
    private int width, height;
    private String title;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void create() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("No se pudo inicializar GLFW");

        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        id = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (id == 0)
            throw new RuntimeException("No se pudo crear la ventana");
    }

    public void update() {
        GLFW.glfwSwapBuffers(id);
        GLFW.glfwPollEvents();
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(id);
    }

    public void destroy() {
        GLFW.glfwDestroyWindow(id);
    }

    public long getId() { return id; }
}
