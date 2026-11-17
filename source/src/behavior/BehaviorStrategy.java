package behavior;

import assets.GameMap;
import assets.character.GameCharacter;

public interface BehaviorStrategy {
    String execute(GameCharacter actor, GameCharacter target, GameMap map);
}