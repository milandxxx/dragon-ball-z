package com.dbz.game.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

import com.dbz.game.engine.render.Renderer;
import com.dbz.game.engine.window.Window;
import com.dbz.game.engine.input.InputManager;
import com.dbz.game.engine.scene.SceneManager;
import com.dbz.game.engine.audio.AudioEngine;

public class GameEngine {

    private boolean running = false;

    private Window window;
    private Renderer renderer;
    private InputManager input;
    private SceneManager scenes;
    private AudioEngine audio;

    private final int TARGET_FPS = 60;
    private final double FRAME_TIME = 1.0 / TARGET_FPS;

    public void start() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        window = new Window("DBZ 3D Game Engine", 1280, 720);
        window.create();

        GLFW.glfwMakeContextCurrent(window.getId());
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);

        renderer = new Renderer();
        input = new InputManager(window);
        scenes = new SceneManager();
        audio = new AudioEngine();

        scenes.loadInitialScene();

        running = true;
    }

    private void loop() {
        double lastTime = GLFW.glfwGetTime();
        double accumulator = 0;

        while (!window.shouldClose()) {

            double currentTime = GLFW.glfwGetTime();
            double delta = currentTime - lastTime;
            lastTime = currentTime;
            accumulator += delta;

            input.poll();

            while (accumulator >= FRAME_TIME) {
                update((float) FRAME_TIME);
                accumulator -= FRAME_TIME;
            }

            render();
            window.update();
        }
    }

    private void update(float dt) {
        scenes.update(dt, input);
        audio.update();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        renderer.render(scenes.getCurrentScene());
    }

    private void cleanup() {
        scenes.cleanup();
        renderer.cleanup();
        audio.cleanup();
        window.destroy();
        GLFW.glfwTerminate();
    }
}
