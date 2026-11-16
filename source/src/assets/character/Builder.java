package assets.character;

import behavior.BehaviorStrategy;

public interface Builder {
    Builder setName(String name);
    Builder setHp(int hp);
    Builder setDamage(int damage);
    Builder setHideProb(int hideProb);
    Builder setRole(Role role);
    Builder setBehaviorStrategy(BehaviorStrategy behaviorStrategy);

    GameCharacter build();
}
