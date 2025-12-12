package com.dbz.game.gameplay;

import com.dbz.game.engine.input.InputManager;
import com.dbz.game.entities.Fighter;
import com.dbz.game.entities.EnergyBlast;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.Iterator;

public class FightScene {
    private Fighter player1;
    private Fighter player2;
    private ArrayList<EnergyBlast> projectiles;
    private Arena arena;

    public FightScene() {
        player1 = new Fighter(-10, 0, 0, true);
        player1.setColor(1.0f, 0.5f, 0.0f);
        
        player2 = new Fighter(10, 0, 0, false);
        player2.setColor(0.2f, 0.4f, 1.0f);
        
        projectiles = new ArrayList<>();
        arena = new Arena();
    }

    public void update(float dt, InputManager input) {
        handlePlayer1Input(input, dt);
        handlePlayer2Input(input, dt);
        
        player1.update(dt);
        player2.update(dt);
        
        Iterator<EnergyBlast> it = projectiles.iterator();
        while (it.hasNext()) {
            EnergyBlast blast = it.next();
            blast.update(dt);
            
            if (Math.abs(blast.getX()) > 50) {
                it.remove();
                continue;
            }
            
            Fighter target = blast.getOwner() == player1 ? player2 : player1;
            if (blast.collidesWith(target)) {
                target.takeDamage(15);
                it.remove();
            }
        }
        
        if (player1.isPunching() && player1.inRangeOf(player2)) {
            if (!player2.isBlocking()) player2.takeDamage(5);
        }
        
        if (player2.isPunching() && player2.inRangeOf(player1)) {
            if (!player1.isBlocking()) player1.takeDamage(5);
        }
    }

    private void handlePlayer1Input(InputManager input, float dt) {
        if (input.isKeyDown(GLFW.GLFW_KEY_A)) player1.moveLeft(dt);
        if (input.isKeyDown(GLFW.GLFW_KEY_D)) player1.moveRight(dt);
        if (input.isKeyPressed(GLFW.GLFW_KEY_W)) player1.jump();
        if (input.isKeyPressed(GLFW.GLFW_KEY_F)) player1.punch();
        if (input.isKeyDown(GLFW.GLFW_KEY_H)) player1.block();
        else player1.unblock();
        
        if (input.isKeyPressed(GLFW.GLFW_KEY_G)) {
            if (player1.canShoot()) projectiles.add(player1.shoot());
        }
    }

    private void handlePlayer2Input(InputManager input, float dt) {
        if (input.isKeyDown(GLFW.GLFW_KEY_LEFT)) player2.moveLeft(dt);
        if (input.isKeyDown(GLFW.GLFW_KEY_RIGHT)) player2.moveRight(dt);
        if (input.isKeyPressed(GLFW.GLFW_KEY_UP)) player2.jump();
        if (input.isKeyPressed(GLFW.GLFW_KEY_J)) player2.punch();
        if (input.isKeyDown(GLFW.GLFW_KEY_L)) player2.block();
        else player2.unblock();
        
        if (input.isKeyPressed(GLFW.GLFW_KEY_K)) {
            if (player2.canShoot()) projectiles.add(player2.shoot());
        }
    }

    public void render() {
        arena.render();
        for (EnergyBlast blast : projectiles) blast.render();
        player1.render();
        player2.render();
        renderHUD();
    }

    private void renderHUD() {
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, 1280, 720, 0, -1, 1);
        
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
        
        glDisable(GL_DEPTH_TEST);
        
        drawHealthBar(50, 30, player1.getHealth());
        drawHealthBar(1280 - 250, 30, player2.getHealth());
        
        drawEnergyBar(50, 60, player1.getEnergy());
        drawEnergyBar(1280 - 250, 60, player2.getEnergy());
        
        glEnable(GL_DEPTH_TEST);
        
        glPopMatrix();
        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
    }

    private void drawHealthBar(float x, float y, float health) {
        glColor3f(0, 0, 0);
        glBegin(GL_QUADS);
        glVertex2f(x, y); glVertex2f(x + 200, y);
        glVertex2f(x + 200, y + 20); glVertex2f(x, y + 20);
        glEnd();
        
        float ratio = health / 100f;
        if (ratio > 0.5f) glColor3f(0, 1, 0);
        else if (ratio > 0.25f) glColor3f(1, 1, 0);
        else glColor3f(1, 0, 0);
        
        glBegin(GL_QUADS);
        glVertex2f(x + 2, y + 2);
        glVertex2f(x + 2 + 196 * ratio, y + 2);
        glVertex2f(x + 2 + 196 * ratio, y + 18);
        glVertex2f(x + 2, y + 18);
        glEnd();
    }

    private void drawEnergyBar(float x, float y, float energy) {
        glColor3f(0, 0, 0);
        glBegin(GL_QUADS);
        glVertex2f(x, y); glVertex2f(x + 200, y);
        glVertex2f(x + 200, y + 15); glVertex2f(x, y + 15);
        glEnd();
        
        float ratio = energy / 100f;
        glColor3f(0, 0.75f, 1);
        glBegin(GL_QUADS);
        glVertex2f(x + 2, y + 2);
        glVertex2f(x + 2 + 196 * ratio, y + 2);
        glVertex2f(x + 2 + 196 * ratio, y + 13);
        glVertex2f(x + 2, y + 13);
        glEnd();
    }

    public void cleanup() {}
}
