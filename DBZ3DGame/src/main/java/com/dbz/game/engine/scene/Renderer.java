package com.dbz.game.engine;

import com.dbz.game.engine.scene.SceneManager;
import com.dbz.game.gameplay.*;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    private SceneManager sceneManager;

    public Renderer(SceneManager manager) { sceneManager = manager; }

    public void render(Object scene) {
        SceneManager.SceneType type = sceneManager.getCurrentSceneType();
        switch (type) {
            case MENU: ((MenuScene)scene).render(); break;
            case LOCAL_FIGHT: ((FightScene)scene).render(); break;
            case STORY_DIALOGUE: ((DialogueScene)scene).render(); break;
            case STORY_FIGHT: ((StoryFightScene)scene).render(); break;
            case ONLINE_FIGHT: ((OnlineFightScene)scene).render(); break;
        }
    }
}
