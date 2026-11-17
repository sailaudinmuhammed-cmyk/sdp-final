package command;

import assets.GameMap;
import engine.Scene;

public class SetMapCommand implements Command {
    private final Scene scene;
    private final GameMap newMap;
    private GameMap previousMap;

    public SetMapCommand(Scene scene, GameMap newMap) {
        this.scene = scene;
        this.newMap = newMap;
    }

    @Override
    public void execute() {
        previousMap = scene.getMap();
        scene.setMap(newMap);
    }

    @Override
    public void undo() {
        scene.setMap(previousMap);
    }
}