package com.dbz.game.gameplay;

import com.dbz.game.entities.Fighter;
import com.dbz.game.engine.input.InputManager;

public class FightScene {
    private Fighter player1;
    private Fighter player2;
    private AIController aiController;

    public void setupFighters(int char1, int char2, int mode) {
        String[] names = {""GOKU"", ""VEGETA"", ""GOHAN"", ""PICCOLO""};
        player1 = new Fighter(-10, 0, 0, true);
        player1.setCharacterType(names[char1]);
        player2 = new Fighter(10, 0, 0, false);
        player2.setCharacterType(names[char2]);
    }

    public void update(float dt, InputManager input) {}
    public void render() {}
    public void cleanup() {}

    public Fighter getPlayer1() { return player1; }
    public Fighter getPlayer2() { return player2; }
}
