package engine.menu;

import static org.lwjgl.opengl.GL11.*;

public class MenuScene {

    private void drawChar(char c) {
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(16, 0);
        glVertex2f(16, 16);
        glVertex2f(0, 16);
        glEnd();
    }
}
