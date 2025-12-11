package engine.ai;

public class AIController {

    private float attackCooldown = 0;

    public void update(float dt) {
        if (attackCooldown > 0)
            attackCooldown -= dt;
    }

    public boolean wantsToAttack() {
        if (attackCooldown <= 0) {
            attackCooldown = 1.5f;
            return true;
        }
        return false;
    }
}
