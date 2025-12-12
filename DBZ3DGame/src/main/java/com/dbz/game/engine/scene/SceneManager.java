package com.dbz.game.engine.scene;

import com.dbz.game.engine.input.InputManager;
import com.dbz.game.gameplay.*;
import com.dbz.game.network.NetworkManager;
import org.lwjgl.glfw.GLFW;

public class SceneManager {
    private MenuScene menuScene;
    private FightScene localFightScene;
    private StoryMode storyMode;
    private StoryFightScene storyFightScene;
    private DialogueScene dialogueScene;
    private OnlineFightScene onlineFightScene;
    private NetworkManager networkManager;
    private SceneType currentScene;

    public SceneManager() { currentScene = SceneType.MENU; }

    public void loadInitialScene() { menuScene = new MenuScene(); currentScene = SceneType.MENU; }

    public void update(float dt, InputManager input) {
        // Aquí va toda la lógica completa de SceneManager que me enviaste
    }

    public enum SceneType { MENU, LOCAL_FIGHT, STORY_DIALOGUE, STORY_FIGHT, ONLINE_FIGHT }
}
