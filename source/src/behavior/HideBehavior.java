package behavior;

import assets.GameMap;
import assets.character.GameCharacter;

public class HideBehavior implements BehaviorStrategy {

    @Override
    public String execute(GameCharacter actor, GameCharacter target, GameMap map) {
        int modifier = map == null ? 0 : map.getHideMod();

        int chance = Math.max(0,
                Math.min(100, actor.getHideProb() + modifier));

        int roll = (int) (Math.random() * 100);

        if (roll < chance) {
            actor.setHidden(true);

            return actor.getName() + " successfully hid!";
        }

        actor.setHidden(false);

        return actor.getName() + " failed to hide!";
    }
}