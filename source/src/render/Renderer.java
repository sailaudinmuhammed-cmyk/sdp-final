package render;

import engine.Scene;

public abstract class Renderer {
    protected final RenderAPI api;

    protected Renderer(RenderAPI api){
        this.api = api;
    }

    public abstract void render(Scene scene);

    public void showMessage(String message) {
        api.drawText(message);
    }
}
