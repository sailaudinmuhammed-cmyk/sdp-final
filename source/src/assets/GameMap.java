package assets;

public abstract class GameMap extends Asset{
    private final String name = "Map";
    private final String attribute;
    private final int damageMod;
    private final int hideMod;

    protected GameMap(String name, String attribute, int damageMod, int hideMod) {
        super(name);
        this.attribute = attribute;
        this.damageMod = damageMod;
        this.hideMod = hideMod;
    }


    public String getAttribute() {
        return attribute;
    }

    public int getDamageMod() {
        return damageMod;
    }

    public int getHideMod() {
        return hideMod;
    }

    public String getDescription(){
        return  "The " + this.getName() + " sure is " + attribute;
    }
}
