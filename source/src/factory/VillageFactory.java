package factory;

import assets.GameMap;
import assets.maps.Village;

public class VillageFactory implements MapFactory {
    @Override
    public GameMap createMap() {
        return new Village();
    }
}