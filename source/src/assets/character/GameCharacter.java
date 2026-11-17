package assets.character;

import behavior.BehaviorStrategy;
import assets.Asset;
import assets.GameMap;

public class GameCharacter extends Asset {
    private int hp;
    private final int damage;
    private final int hideProb;
    private final Role role;
    private BehaviorStrategy behaviorStrategy;
    private boolean hidden = false;

    protected GameCharacter(String name, int hp, int damage,
                            int hideProb, Role role,
                            BehaviorStrategy behaviorStrategy) {
        super(name);
        this.hp = hp;
        this.damage = damage;
        this.hideProb = hideProb;
        this.role = role;
        this.behaviorStrategy = behaviorStrategy;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getHideProb() {
        return hideProb;
    }

    public Role getRole() {
        return role;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        hp = Math.max(0, hp - amount);
    }

    public void setBehaviorStrategy(BehaviorStrategy behaviorStrategy) {
        if (behaviorStrategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }

        this.behaviorStrategy = behaviorStrategy;
    }

    public String performAction(GameCharacter target, GameMap map) {
        if (!isAlive()) {
            return getName() + " cannot act because they are defeated.";
        }

        return behaviorStrategy.execute(this, target, map);
    }

    @Override
    public String toString() {
        return "| NAME: " + getName()
                + " | ROLE: " + role
                + " | HP: " + hp
                + " | DAMAGE: " + damage
                + " | HIDING PROBABILITY: " + hideProb
                + " | HIDDEN: " + hidden + " |";
    }
}