package factory;

import assets.GameMap;
import assets.maps.Forest;

public class ForestFactory implements MapFactory {
    @Override
    public GameMap createMap() {
        return new Forest();
    }
}