
package engine;

import assets.GameMap;
import command.CommandHistory;
import command.SetMapCommand;
import factory.MapFactory;
import factory.ForestFactory;
import factory.CliffFactory;
import factory.VillageFactory;
import render.ConsoleRenderAPI;
import render.Renderer;
import render.SceneRenderer;

import java.util.Scanner;

public class EngineFacade {
    private final Scene scene;
    private final Renderer renderer;
    private final CommandHistory history;
    private final Scanner scanner;

    public EngineFacade() {
        scene = new Scene();
        renderer = new SceneRenderer(new ConsoleRenderAPI());
        history = new CommandHistory();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            renderer.showMessage(
                    "\n" + "=".repeat(40) +
                            "\n       GAME ENGINE FROM DIMASH\n" +
                            "=".repeat(40) +
                            "\n1. Edit Mode" +
                            "\n2. Run Mode" +
                            "\n3. Debug Mode" +
                            "\nq. Exit" +
                            "\nChoose an option:"
            );

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1":
                    edit();
                    break;

                case "2":
                    run();
                    break;

                case "3":
                    debug();
                    break;

                case "q":
                    renderer.showMessage("Goodbye!");
                    return;

                default:
                    renderer.showMessage("Invalid option. Try again.");
            }
        }
    }

    private void edit() {
        while (true) {
            renderer.showMessage(
                    "\n===== EDIT MODE =====" +
                            "\n1. Change Map" +
                            "\n2. Add Character" +
                            "\n3. Show Scene" +
                            "\n4. Undo" +
                            "\n5. Redo" +
                            "\nb. Back"
            );

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1":
                    changeMap();
                    break;

                case "2":
                    renderer.showMessage("Character creation is not implemented yet.");
                    break;

                case "3":
                    renderer.render(scene);
                    break;

                case "4":
                    if (history.undo()) {
                        renderer.showMessage("Undo successful.");
                    } else {
                        renderer.showMessage("Nothing to undo.");
                    }
                    break;

                case "5":
                    if (history.redo()) {
                        renderer.showMessage("Redo successful.");
                    } else {
                        renderer.showMessage("Nothing to redo.");
                    }
                    break;

                case "b":
                    return;

                default:
                    renderer.showMessage("Invalid option.");
            }
        }
    }

    private void changeMap() {
        renderer.showMessage(
                "\nChoose a map:" +
                        "\n1. Forest" +
                        "\n2. Cliff" +
                        "\n3. Village"
        );

        String input = scanner.nextLine().trim();

        MapFactory factory;

        switch (input) {
            case "1":
                factory = new ForestFactory();
                break;

            case "2":
                factory = new CliffFactory();
                break;

            case "3":
                factory = new VillageFactory();
                break;

            default:
                renderer.showMessage("Invalid map.");
                return;
        }

        GameMap map = factory.createMap();

        history.executeCommand(new SetMapCommand(scene, map));

        renderer.showMessage("Map changed to " + map.getName());
    }

    private void run() {
        renderer.showMessage("Run Mode is not implemented yet.");
    }

    private void debug() {
        renderer.showMessage("Debug Mode is not implemented yet.");
    }
}
