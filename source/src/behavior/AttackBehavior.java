package behavior;

import assets.GameMap;
import assets.character.GameCharacter;

public class AttackBehavior implements BehaviorStrategy {

    @Override
    public String execute(GameCharacter actor, GameCharacter target, GameMap map) {
        if (target == null) {
            return actor.getName() + " has no target.";
        }

        if (!target.isAlive()) {
            return target.getName() + " is already defeated.";
        }

        if (actor.getRole() == target.getRole()) {
            return actor.getName() + " cannot attack an ally.";
        }

        actor.setHidden(false);

        if (target.isHidden()) {
            target.setHidden(false);
            return target.getName() + " avoided the attack by hiding!";
        }

        int modifier = map == null ? 0 : map.getDamageMod();

        int totalDamage = Math.max(0, actor.getDamage() + modifier);

        target.takeDamage(totalDamage);

        return actor.getName() + " attacked "
                + target.getName()
                + " and dealt " + totalDamage + " damage!";
    }
}