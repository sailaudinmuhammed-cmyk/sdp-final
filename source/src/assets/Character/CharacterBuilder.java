package assets.Character;

import Behavior.AttackBehavior;
import Behavior.BehaviorStrategy;

public class CharacterBuilder implements Builder {
    // обязательные поля (значений по умолчанию нет)
    private String name;
    private int hp;
    private Role role;

    // необязательные поля (значения по умолчанию)
    private int damage = 10;
    private int hideProb = 0;
    private BehaviorStrategy behaviorStrategy = new AttackBehavior();

    @Override
    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Builder setHp(int hp) {
        this.hp = hp;
        return this;
    }

    @Override
    public Builder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public Builder setHideProb(int hideProb) {
        if (hideProb < 0 || hideProb > 100) {
            throw new IllegalArgumentException("hideProb must be 0-100");
        }
        this.hideProb = hideProb;
        return this;
    }

    @Override
    public Builder setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public Builder setBehaviorStrategy(BehaviorStrategy behaviorStrategy) {
        this.behaviorStrategy = behaviorStrategy;
        return this;
    }

    @Override
    public GameCharacter build() {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Character name is required");
        }
        if (hp <= 0) {
            throw new IllegalStateException("HP must be positive");
        }
        if (role == null) {
            throw new IllegalStateException("Role is required");
        }
        return new GameCharacter(name, hp, damage, hideProb, role, behaviorStrategy);
    }
}