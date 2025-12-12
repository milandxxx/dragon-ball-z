package com.dbz.game.engine.render;

import static org.lwjgl.opengl.GL11.*;
import com.dbz.game.gameplay.FightScene;

public class Renderer {
    
    public void render(Object scene) {
        if (scene instanceof FightScene) {
            renderFightScene((FightScene) scene);
        }
    }

    private void renderFightScene(FightScene scene) {
        glClearColor(0.5f, 0.7f, 1.0f, 1.0f);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        float aspect = 1280f / 720f;
        float fov = 60.0f;
        float near = 0.1f;
        float far = 1000.0f;
        perspective(fov, aspect, near, far);
        
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
        glTranslatef(0, -5, -30);
        glRotatef(15, 1, 0, 0);
        
        scene.render();
    }

    private void perspective(float fov, float aspect, float near, float far) {
        float f = (float) (1.0 / Math.tan(Math.toRadians(fov) / 2));
        glFrustum(-near * aspect / f, near * aspect / f, -near / f, near / f, near, far);
    }

    public void cleanup() {}
}
