package command;

import engine.Scene;
import assets.character.GameCharacter;

public class AddCharacterCommand implements Command {
    private final Scene scene;
    private final GameCharacter character;

    public AddCharacterCommand(Scene scene, GameCharacter character) {
        this.scene = scene;
        this.character = character;
    }

    @Override
    public void execute() {
        scene.addCharacter(character);
    }

    @Override
    public void undo() {
        scene.removeCharacter(character);
    }
}