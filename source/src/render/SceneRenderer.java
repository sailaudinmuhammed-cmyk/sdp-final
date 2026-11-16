package render;

import assets.GameMap;
import assets.character.GameCharacter;
import engine.Scene;

import java.util.ArrayList;
import java.util.List;

public class SceneRenderer extends Renderer{
    public SceneRenderer(RenderAPI api) {
        super(api);
    }

    @Override
    public void render(Scene scene) {
        GameMap map = scene.getMap();
        List<GameCharacter> characters =scene.getCharacterList();
        if (map == null){
            api.drawText("Oops, no map was found in scene");
        }
        else {
            api.drawText(map.toString());
        }
        for (GameCharacter i : characters){
            api.drawText(i.toString());
        }
    }
}
