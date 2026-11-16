package engine;

import assets.GameMap;
import assets.character.GameCharacter;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private GameMap map = null;
    private List<GameCharacter> characters = new ArrayList<>();

    public void setMap(GameMap map) {
        this.map = map;
    }

    public GameMap getMap() {
        return map;
    }

    public List<GameCharacter> getCharacterList() {
        ArrayList<GameCharacter> copy = new ArrayList<>(characters);
        return copy;
    }

    public void addCharacter(GameCharacter character){
        if (character == null) {
            throw new IllegalArgumentException("Character  is required");
        }
        characters.add(character);
    }

    public void removeCharacter(GameCharacter character){
        if (character == null) {
            throw new IllegalArgumentException("Character  is required");
        }
        characters.remove(character);
    }
}
