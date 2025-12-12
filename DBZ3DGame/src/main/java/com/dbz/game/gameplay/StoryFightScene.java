package com.dbz.game.gameplay;

import com.dbz.game.entities.Fighter;
import com.dbz.game.entities.EnergyBlast;
import com.dbz.game.engine.input.InputManager;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import java.util.Iterator;

public class StoryFightScene {
    private Fighter player;
    private Fighter enemy;
    private ArrayList<EnergyBlast> projectiles;
    private Arena arena;
    private AIController aiController;
    private ComboSystem comboSystem;
    private float gameTime;
    private boolean battleEnded;
    private boolean playerWon;
    private int currentBattle;
    private int totalBattles;

    public StoryFightScene(int playerChar, int enemyChar, float enemyDifficulty, int battleNum, int totalBattles) {
        this.currentBattle = battleNum;
        this.totalBattles = totalBattles;
        player = new Fighter(-10, 0, 0, true);
        player.setColor(1.0f, 0.5f, 0.0f);
        player.setCharacterType(getCharacterName(playerChar));
        enemy = new Fighter(10, 0, 0, false);
        enemy.setColor(1.0f, 0.0f, 0.0f);
        enemy.setCharacterType(getCharacterName(enemyChar));
        projectiles = new ArrayList<>();
        arena = new Arena();
        aiController = new AIController(enemy, player, enemyDifficulty);
        comboSystem = new ComboSystem();
        gameTime = 0;
        battleEnded = false;
    }

    private String getCharacterName(int index) {
        String[] names = {"GOKU", "VEGETA", "GOHAN", "PICCOLO"};
        return names[index];
    }

    public void update(float dt, InputManager input) {
        if (battleEnded) return;
        gameTime += dt;
        // Aquí va toda la lógica de combate
    }

    public void render() { arena.render(); }

    public boolean isBattleEnded() { return battleEnded; }
    public boolean didPlayerWin() { return playerWon; }
    public Fighter getPlayer() { return player; }
    public Fighter getEnemy() { return enemy; }
}
