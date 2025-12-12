package com.dbz.game.gameplay;

import com.dbz.game.network.NetworkManager;
import com.dbz.game.engine.input.InputManager;

public class OnlineFightScene {
    private NetworkManager networkManager;
    private boolean isHost;
    private int char1, char2;

    public OnlineFightScene(NetworkManager nm, boolean host, int c1, int c2) {
        networkManager = nm; isHost = host; char1 = c1; char2 = c2;
    }

    public void update(float dt, InputManager input) {}
    public void render() {}
    public void cleanup() {}
}
