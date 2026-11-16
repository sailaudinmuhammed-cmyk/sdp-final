package render;

public class ConsoleRenderAPI implements RenderAPI{
    @Override
    public void drawText(String text) {
        System.out.println(text);
    }
}
