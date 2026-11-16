package render;

public class AsciRenderAPI implements RenderAPI{
    @Override
    public void drawText(String text) {
        int length = text.length();
        System.out.println("+" + "-".repeat(length+2) + "+\n"
                + text +
                "\n+" + "-".repeat(length+2) + "+");
    }
}
