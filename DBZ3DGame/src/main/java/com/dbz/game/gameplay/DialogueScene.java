package com.dbz.game.gameplay;

import com.dbz.game.engine.input.InputManager;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;

public class DialogueScene {
    private String characterName;
    private String dialogueText;
    private String narrativeText;
    private float textTimer;
    private int revealedChars;
    private boolean isComplete;
    private float blinkTimer;
    
    public DialogueScene(String characterName, String dialogue, String narrative) {
        this.characterName = characterName;
        this.dialogueText = dialogue;
        this.narrativeText = narrative;
        this.textTimer = 0;
        this.revealedChars = 0;
        this.isComplete = false;
        this.blinkTimer = 0;
    }
    
    public void update(float dt, InputManager input) {
        blinkTimer += dt * 5;
        if (!isComplete) {
            textTimer += dt * 30;
            revealedChars = (int)textTimer;
            if (revealedChars >= dialogueText.length()) {
                revealedChars = dialogueText.length();
                isComplete = true;
            }
            if (input.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
                revealedChars = dialogueText.length();
                isComplete = true;
            }
        }
    }
    
    public void render() {
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, 1280, 720, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(0, 0, 0, 0.8f);
        glBegin(GL_QUADS);
        glVertex2f(0, 0); glVertex2f(1280, 0); glVertex2f(1280, 720); glVertex2f(0, 720);
        glEnd();
        glColor4f(0.1f, 0.1f, 0.2f, 0.95f);
        glBegin(GL_QUADS);
        glVertex2f(50, 450); glVertex2f(1230, 450); glVertex2f(1230, 680); glVertex2f(50, 680);
        glEnd();
        glColor3f(1, 0.84f, 0); 
        glBegin(GL_LINE_LOOP);
        glVertex2f(50, 450); glVertex2f(1230, 450); glVertex2f(1230, 680); glVertex2f(50, 680);
        glEnd();
        glDisable(GL_BLEND);
    }
    
    public boolean isComplete() { return isComplete; }
}
