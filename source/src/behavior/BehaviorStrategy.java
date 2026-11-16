package behavior;

import assets.character.GameCharacter;

public interface BehaviorStrategy {
    void execute(GameCharacter character);
}
