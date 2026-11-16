package assets.Character;

import Behavior.BehaviorStrategy;
import assets.Asset;

public class GameCharacter extends Asset {
    private int hp;
    private final int damage;
    private final int hideProb;
    private final Role role;
    private BehaviorStrategy behaviorStrategy;

    protected GameCharacter(String name, int hp, int damage, int hideProb, Role role, BehaviorStrategy behaviorStrategy) {
        super(name);
        this.hp = hp;
        this.damage = damage;
        this.hideProb = hideProb;
        this.role = role;
        this.behaviorStrategy = behaviorStrategy;
    }

    public void setBehaviorStrategy(BehaviorStrategy behaviorStrategy) {
        this.behaviorStrategy = behaviorStrategy;
    }
}
