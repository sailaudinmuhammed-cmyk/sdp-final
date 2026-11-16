package factory;

import assets.GameMap;
import assets.maps.Cliff;

public class CliffFactory implements MapFactory {
    @Override
    public GameMap createMap() {
        return new Cliff();
    }
}