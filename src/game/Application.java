package game;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.HumanoidFigure;
import game.actors.Player;
import game.grounds.*;
import game.items.*;
import game.plants.Sapling;
import game.plants.Sprout;
import game.spawners.AlienBugSpawner;
import game.spawners.HuntsmanSpiderSpawner;
import game.spawners.SuspiciousAstronautSpawner;

/**
 * The main class to start the game.
 * It sets up the game world, including the map, Player, Grounds, and Items, and executes the game loop.
 */

public class Application {

    /**
     * The main method to start the game.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

        List<String> polymorphiaMap = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".............##_##.........~~~",
                ".................~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        List<String> factoryMap = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#___#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");

        List<String> refactorioMap = Arrays.asList(
                "..........................~~~~",
                "..........................~~~~",
                "..........................~~~~",
                "~..........................~..",
                "~~...........#####............",
                "~~~..........#___#............",
                "~~~..........#___#............",
                "~~~..........##_##............",
                "~~~..................~~.......",
                "~~~~................~~~~......",
                "~~~~...............~~~~~......",
                "..~................~~~~.......",
                "....................~~........",
                ".............~~...............",
                "............~~~~..............");

        GameMap polymorphiaGameMap = new GameMap(groundFactory, polymorphiaMap);
        world.addGameMap(polymorphiaGameMap);

        GameMap factoryGameMap = new GameMap(groundFactory, factoryMap);
        world.addGameMap(factoryGameMap);

        GameMap refactorioGameMap = new GameMap(groundFactory, refactorioMap);
        world.addGameMap(refactorioGameMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Intern", '@', 4);
        world.addPlayer(player, polymorphiaGameMap.at(15, 6));

        // Add Saplings to the game map
        polymorphiaGameMap.at(5, 8).setGround(new Flora(new Sapling()));
        polymorphiaGameMap.at(23, 2).setGround(new Flora(new Sapling()));
        polymorphiaGameMap.at(24, 11).setGround(new Flora(new Sapling()));

        // Add Craters that spawn Huntsman Spiders to the game map
        polymorphiaGameMap.at(5,5).setGround(new Crater(new HuntsmanSpiderSpawner()));
        polymorphiaGameMap.at(21, 7).setGround(new Crater(new HuntsmanSpiderSpawner()));
        polymorphiaGameMap.at(8, 10).setGround(new Crater(new HuntsmanSpiderSpawner()));

        // Add Craters that spawn Alien Bugs to the game map
        polymorphiaGameMap.at(3, 2).setGround(new Crater(new AlienBugSpawner(player)));
        polymorphiaGameMap.at(18, 4).setGround(new Crater(new AlienBugSpawner(player)));
        polymorphiaGameMap.at(22, 13).setGround(new Crater(new AlienBugSpawner(player)));

        // Add Craters that spawn Suspicious Astronauts to the game map
        polymorphiaGameMap.at(2, 8).setGround(new Crater(new SuspiciousAstronautSpawner()));
        polymorphiaGameMap.at(10, 3).setGround(new Crater(new SuspiciousAstronautSpawner()));
        polymorphiaGameMap.at(16, 11).setGround(new Crater(new SuspiciousAstronautSpawner()));

        polymorphiaGameMap.at(15, 9).addItem(new LargeBolt());
        polymorphiaGameMap.at(12, 7).addItem(new LargeBolt());
        polymorphiaGameMap.at(6, 14).addItem(new LargeBolt());

        polymorphiaGameMap.at(5, 4).addItem(new MetalSheet());
        polymorphiaGameMap.at(8, 13).addItem(new MetalSheet());
        polymorphiaGameMap.at(11, 2).addItem(new MetalSheet());

        polymorphiaGameMap.at(14, 9).addItem(new MetalPipe());

        // Add Jars of Pickles to the game map
        polymorphiaGameMap.at(3, 5).addItem(new JarOfPickles());
        polymorphiaGameMap.at(20, 10).addItem(new JarOfPickles());
        polymorphiaGameMap.at(22, 3).addItem(new JarOfPickles());

        // Add Pots of Gold to the game map
        polymorphiaGameMap.at(4, 10).addItem(new PotOfGold());
        polymorphiaGameMap.at(18, 6).addItem(new PotOfGold());
        polymorphiaGameMap.at(23, 12).addItem(new PotOfGold());

        player.addBalance(2500);

        // Add the travel locations for the Computer Terminal
        Map<String, Location> travelLocations = new LinkedHashMap<>();
        travelLocations.put("Polymorphia", polymorphiaGameMap.at(15, 6));
        travelLocations.put("Factory's Spaceship Parking Lot", factoryGameMap.at(3, 3));
        travelLocations.put("Refactorio", refactorioGameMap.at(15, 6));

        // Add a Computer Terminal to each game map
        polymorphiaGameMap.at(15, 5).setGround(new ComputerTerminal(travelLocations));
        factoryGameMap.at(3, 2).setGround(new ComputerTerminal(travelLocations));
        refactorioGameMap.at(15, 5).setGround(new ComputerTerminal(travelLocations));

        // Add a Humanoid Figure to the game map
        factoryGameMap.at(3, 6).addActor(new HumanoidFigure());

        // Add Sprouts to the game map
        refactorioGameMap.at(5, 8).setGround(new Flora(new Sprout()));
        refactorioGameMap.at(23, 2).setGround(new Flora(new Sprout()));
        refactorioGameMap.at(24, 11).setGround(new Flora(new Sprout()));

        world.run();
    }
}
