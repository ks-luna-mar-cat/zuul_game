package Assignments.A4;

/**
 *  This class is the main class of the "Escape the ship" application. 
 *  "Escape the ship" is a very simple, text based adventure game. 
 *  The scenario is - The big storrm is coming, so the user should find 
 *  emergency exit within 5 minuites.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes and Kate Son
 *
 * Modifications:
 * - add look and eat method
 * - add a case for the look and eat command in the processCommand method
 * - update code to show commands in the printHelp method
 * - create items and add to rooms
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room deck, medical, guest, hall, emergency, dining, engine, captain, swimpool;
      
        // create the rooms
        deck = new Room("outside the ship, deck area.");
        medical = new Room("in the medical service.");
        guest = new Room("in guest rooms area.");
        hall = new Room("in the access hall.");
        emergency = new Room("in the emergency exit!!! You find it finally!!");
        dining = new Room("in the dining room.");
        engine = new Room("in the engine room.");
        captain = new Room("in the captain's cabin.");
        swimpool = new Room("in swimming pool area.");
         
        // initialise room exits
        deck.setExit("east", medical);
        deck.setExit("south", hall);
        deck.setExit("up", swimpool); //add new direction up
        
        swimpool.setExit("down", deck); //add new direction down
         
        medical.setExit("west", deck);
        
        guest.setExit("east", hall);
        guest.setExit("south", emergency);
        
        hall.setExit("north", deck);
        hall.setExit("south", dining);
        hall.setExit("west", guest);
        
        dining.setExit("north", hall);
        dining.setExit("east", engine);
        dining.setExit("south", captain);
        dining.setExit("west", emergency);
        
        engine.setExit("west", dining);
        
        captain.setExit("north", dining);

        // create the items and add to rooms
        Item rope, bouquet, fishStatue, teaPot, compass ;
        rope = new Item("a long rope", 33);
        bouquet = new Item("a red rose bouquet", 17);
        fishStatue = new Item("a small bronze fish status", 80);
        teaPot = new Item("a gold tea pot", 10);
        compass = new Item("a big compass", 7);
        deck.setItem(rope);
        guest.setItem(bouquet);
        hall.setItem(fishStatue);
        dining.setItem(teaPot);
        captain.setItem(compass);

        currentRoom = hall;  // start game at hall
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        
        while (! finished && !currentRoom.getExitString().equals("")) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the Escape the ship!!");
        System.out.println("Big storm is coming. We have to escape in five minuite!! Find the emergency exit!!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the ship.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command input command
     */
    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param command input command
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Print information about the current location
     */
    private void look() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Print information about eat command
     */
    private void eat() {
        System.out.println("You have eaten now and you are not hungry any more.");
    }

}
