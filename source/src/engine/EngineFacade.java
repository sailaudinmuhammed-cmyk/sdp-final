
package engine;

import render.AsciRenderAPI;
import assets.character.CharacterBuilder;
import assets.character.GameCharacter;
import assets.character.Role;
import behavior.AttackBehavior;
import behavior.HideBehavior;
import command.AddCharacterCommand;
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
import assets.character.GameCharacter;
import assets.character.Role;
import behavior.AttackBehavior;
import behavior.HideBehavior;

import java.util.List;

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
                    addCharacter();
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


    private void debug() {
        while (true) {
            renderer.showMessage(
                    "\n===== DEBUG MODE =====" +
                            "\n1. Show detailed scene information" +
                            "\n2. Show ASCII scene" +
                            "\nb. Back"
            );

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1":
                    showDebugInfo();
                    break;

                case "2":
                    Renderer asciiRenderer =
                            new SceneRenderer(new AsciRenderAPI());

                    asciiRenderer.render(scene);
                    break;

                case "b":
                    return;

                default:
                    renderer.showMessage("Invalid option.");
            }
        }
    }

    private void showDebugInfo() {
        renderer.showMessage("\n===== SCENE DEBUG INFO =====");

        GameMap map = scene.getMap();

        if (map == null) {
            renderer.showMessage("Map: NULL");
        } else {
            renderer.showMessage(
                    "Map: " + map.getName() +
                            "\nAttribute: " + map.getAttribute() +
                            "\nDamage Modifier: " + map.getDamageMod() +
                            "\nHide Modifier: " + map.getHideMod()
            );
        }

        List<GameCharacter> characters = scene.getCharacterList();

        renderer.showMessage(
                "\nCharacter count: " + characters.size()
        );

        int index = 1;

        for (GameCharacter character : characters) {
            renderer.showMessage(
                    "\n--- Character #" + index + " ---" +
                            "\nName: " + character.getName() +
                            "\nRole: " + character.getRole() +
                            "\nHP: " + character.getHp() +
                            "\nDamage: " + character.getDamage() +
                            "\nHide Probability: " + character.getHideProb() +
                            "\nStrategy: " + character.getStrategyName() +
                            "\nHidden: " + character.isHidden() +
                            "\nAlive: " + character.isAlive()
            );

            index++;
        }
    }



    private void addCharacter() {
        renderer.showMessage("\n===== CREATE CHARACTER =====");

        renderer.showMessage("Enter character name:");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            renderer.showMessage("Name cannot be empty.");
            return;
        }

        int hp = readInt("Enter HP:", 1, 1000);
        int damage = readInt("Enter damage:", 0, 1000);
        int hideProb = readInt("Enter hiding probability (0-100):", 0, 100);

        renderer.showMessage("Choose role:\n1. Player\n2. Enemy");
        String roleInput = scanner.nextLine().trim();

        Role role;

        switch (roleInput) {
            case "1":
                role = Role.PLAYER;
                break;
            case "2":
                role = Role.ENEMY;
                break;
            default:
                renderer.showMessage("Invalid role.");
                return;
        }

        renderer.showMessage("Choose behavior:\n1. Attack\n2. Hide");
        String behaviorInput = scanner.nextLine().trim();

        CharacterBuilder builder = new CharacterBuilder();

        builder.setName(name);
        builder.setHp(hp);
        builder.setDamage(damage);
        builder.setHideProb(hideProb);
        builder.setRole(role);

        switch (behaviorInput) {
            case "1":
                builder.setBehaviorStrategy(new AttackBehavior());
                break;
            case "2":
                builder.setBehaviorStrategy(new HideBehavior());
                break;
            default:
                renderer.showMessage("Invalid behavior.");
                return;
        }

        try {
            GameCharacter character = builder.build();

            history.executeCommand(
                    new AddCharacterCommand(scene, character)
            );

            renderer.showMessage("Character created successfully!");
            renderer.showMessage(character.toString());

        } catch (IllegalArgumentException | IllegalStateException e) {
            renderer.showMessage("Error: " + e.getMessage());
        }
    }

    private int readInt(String message, int min, int max) {
        while (true) {
            renderer.showMessage(message);

            try {
                int number = Integer.parseInt(scanner.nextLine().trim());

                if (number >= min && number <= max) {
                    return number;
                }

                renderer.showMessage(
                        "Enter a number between " + min + " and " + max
                );

            } catch (NumberFormatException e) {
                renderer.showMessage("Invalid number. Try again.");
            }
        }
    }

    private void run() {
        if (scene.getMap() == null) {
            renderer.showMessage("Please select a map in Edit Mode first.");
            return;
        }

        List<GameCharacter> characters = scene.getCharacterList();

        if (findFirstAlive(characters, Role.PLAYER) == null ||
                findFirstAlive(characters, Role.ENEMY) == null) {
            renderer.showMessage(
                    "Run Mode requires at least one living Player and one living Enemy."
            );
            return;
        }

        int round = 1;

        renderer.showMessage("===== RUN MODE STARTED =====");
        renderer.render(scene);

        while (true) {
            GameCharacter player = findFirstAlive(characters, Role.PLAYER);

            renderer.showMessage("\n===== ROUND " + round + " =====");

            renderer.showMessage(
                    "Choose behavior for " + player.getName() + ":\n" +
                            "1. Attack\n" +
                            "2. Hide\n" +
                            "3. Keep current strategy\n" +
                            "q. Exit Run Mode"
            );

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "1":
                    player.setBehaviorStrategy(new AttackBehavior());
                    renderer.showMessage(player.getName() + " chose Attack.");
                    break;

                case "2":
                    player.setBehaviorStrategy(new HideBehavior());
                    renderer.showMessage(player.getName() + " chose Hide.");
                    break;

                case "3":
                    renderer.showMessage("Current strategy preserved.");
                    break;

                case "q":
                    renderer.showMessage("Leaving Run Mode.");
                    return;

                default:
                    renderer.showMessage("Invalid option. Try again.");
                    continue;
            }

            for (GameCharacter actor : characters) {
                if (!actor.isAlive()) {
                    continue;
                }

                GameCharacter target;

                if (actor.getRole() == Role.PLAYER) {
                    target = findFirstAlive(characters, Role.ENEMY);
                } else {
                    target = findFirstAlive(characters, Role.PLAYER);
                }

                if (target == null) {
                    break;
                }

                String result = actor.performAction(target, scene.getMap());
                renderer.showMessage(result);
            }

            renderer.showMessage("\n===== ROUND RESULT =====");
            renderer.render(scene);

            if (findFirstAlive(characters, Role.ENEMY) == null) {
                renderer.showMessage("PLAYERS WIN!");
                return;
            }

            if (findFirstAlive(characters, Role.PLAYER) == null) {
                renderer.showMessage("ENEMIES WIN!");
                return;
            }

            round++;
        }
    }

    private GameCharacter findFirstAlive(
            List<GameCharacter> characters,
            Role role) {

        for (GameCharacter character : characters) {
            if (character.isAlive() && character.getRole() == role) {
                return character;
            }
        }

        return null;
    }


}
