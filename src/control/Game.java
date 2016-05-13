package control;

import entity.Player;
import entity.Room;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Game {

    //Interact with player
    private Scanner scan = new Scanner( System.in );

    //Game world components
    private Player thePlayer;
    private Room[] rooms;

    //Current position
    private Room currentRoom;

    //Gaming helper components
    private boolean wasRoomDeleted = false;
    private boolean wasShieldUsed = false;
    private boolean shieldStatus = false;
    private int roundsWithShield = 0;

    //Utilities
    Random rand = new Random();

    public void startWizardGame() {
        //Interact with player
        System.out.println( "Introduce yourself, Traveler!" );
        String name = scan.nextLine();

        //Init player
        thePlayer = new Player( name, 100 );
        //Init rooms
        rooms = roomCreationLogic();

        System.out.println( "\nWelcome to this dark side, " + thePlayer.getName()
                + " (" + thePlayer.getHealth() + " HP) "
                + "\n\tYou are in the Cube now - your soul purpose is to escape! "
                + "\n\tWatch your health and ... good hunting, may the odds be ever "
                + " in your favour!"
                + "\n\tType 'Start' to enter your journey" );

        //Interact with player
        String input = scan.nextLine();
        while ( !input.equalsIgnoreCase( "start" ) ) {
            System.out.println( "Trust me, I can get you out of here, Unbeliever, "
                    + "type 'Start'!" );
            input = scan.nextLine();
        }

        //Set current room to start room
        currentRoom = rooms[ 0 ];

        //Interact with player - core gaming logic
        while ( currentRoom != rooms[ 9 ] && thePlayer.getHealth() > 0 ) {
            System.out.println( "\nYou just entered \"" + currentRoom.getName() + "\""
                    + "\n\t\"" + currentRoom.getDescription() + "\"" );
            System.out.println( "\tChoose your direction, " + thePlayer.getName() + "." );

            input = scan.nextLine();
            roomLogic( input, rooms );
        }

        if ( thePlayer.getHealth() == 0 ) {
            System.out.println( "\nYou are dead as hell... :)" );
        }

        if ( currentRoom == rooms[ 9 ] ) {
            System.out.println( "\nYou just entered \"" + currentRoom.getName() + "\""
                    + "\n\t\"" + currentRoom.getDescription() + "\"" );
        }

        System.out.println( "\nThank you for playing!"
                + "\n\tCreated by Bobkoo"
                + "\n\tDeveloped during 2013 and updated during 2016"
                + "\n\tGithub : \"https://github.com/bobkoobg\""
                + "\n\tbye bye" );
    }

    /*
     * This method returns an array of rooms, which are mapped to each other,
     * therefore the rooms will act like a puzzle.
     */
    private Room[] roomCreationLogic() {
        Room room1 = new Room( "The Entrance Hall of the Cube", "Welcome, lost "
                               + "soul! Don't fear, I can give give you some survival "
                               + "hints - type 'Help'", 0 );
        Room room10 = new Room( "Room of Redemption", "You escaped this madness "
                                + "and won, congratulations! ...now you should watch "
                                + "the movie!", 0 );

        TreeMap eligibleRoomsTree = new TreeMap();
        eligibleRoomsTree.put( 1, new Room( "Room of Silence", "Schhh, just get out silently or else...", 0 ) );
        eligibleRoomsTree.put( 2, new Room( "Room of Despare", "Search, search!", 5 ) );
        eligibleRoomsTree.put( 3, new Room( "Crossroads", "You got lost, Traveler?", 15 ) );
        eligibleRoomsTree.put( 4, new Room( "Cylon Basestar", "Spin up the FTL drive and get the FRAK out of here!", 20 ) );
        eligibleRoomsTree.put( 5, new Room( "Snakes-s-s-s", "They bite, get away!", 10 ) );
        eligibleRoomsTree.put( 6, new Room( "Downfall", "You fell on the ground... luckily the room is empty.", 5 ) );
        eligibleRoomsTree.put( 7, new Room( "Tears of the lost souls", "You are drowning, get out, get out!!", 10 ) );
        eligibleRoomsTree.put( 8, new Room( "The Six slaps", "You just entered a bathroom with six naked girls. They slapped you.", 6 ) );
        eligibleRoomsTree.put( 10, new Room( "Winterfell", "(SPOILER ALERT) You entered Winterfell and you touched the flames...", 10 ) );
        eligibleRoomsTree.put( 11, new Room( "Copenhagen Business Academy", "You have an assignment and the teachers do not seem to care about you, so you are just getting rekt...", 15 ) );
        eligibleRoomsTree.put( 12, new Room( "Room of Madness", "You started hitting your head in the wall, cause of despare", 5 ) );
        eligibleRoomsTree.put( 13, new Room( "Daenerys's Dragons", "You are burning into the flames of Drogon, Rhaegal and Viserion", 20 ) );
        eligibleRoomsTree.put( 14, new Room( "Room of Doom", " I-I-INSTAA KILLLL-IL-IL", 1000 ) );

        Random rand = new Random();
        int chosenRoomIndex, chosenRoomsCounter = 1;

        rooms = new Room[ 10 ];

        boolean isChosingRooms = true;
        while ( isChosingRooms ) {

            chosenRoomIndex = rand.nextInt( eligibleRoomsTree.size() + 1 );

            if ( eligibleRoomsTree.get( chosenRoomIndex ) != null ) {

                rooms[ chosenRoomsCounter ] = ( Room ) eligibleRoomsTree.get( chosenRoomIndex );
                eligibleRoomsTree.remove( chosenRoomIndex );

                chosenRoomsCounter++;
                if ( chosenRoomsCounter == 9 ) {
                    isChosingRooms = false;
                }
            }

        }
        //Enter room
        rooms[ 0 ] = room1;
        //Exit room
        rooms[ 9 ] = room10;

        //Randomized rooms
        rooms[ 0 ].setEast( rooms[ 1 ] );
        rooms[ 0 ].setWest( null );
        rooms[ 0 ].setNorth( null );
        rooms[ 0 ].setSouth( rooms[ 3 ] );

        rooms[ 1 ].setEast( rooms[ 2 ] );
        rooms[ 1 ].setWest( rooms[ 0 ] );
        rooms[ 1 ].setNorth( null );
        rooms[ 1 ].setSouth( rooms[ 4 ] );

        rooms[ 2 ].setEast( null );
        rooms[ 2 ].setWest( rooms[ 1 ] );
        rooms[ 2 ].setNorth( null );
        rooms[ 2 ].setSouth( rooms[ 5 ] );

        rooms[ 3 ].setEast( rooms[ 4 ] );
        rooms[ 3 ].setWest( null );
        rooms[ 3 ].setNorth( rooms[ 0 ] );
        rooms[ 3 ].setSouth( rooms[ 6 ] );

        rooms[ 4 ].setEast( rooms[ 5 ] );
        rooms[ 4 ].setWest( rooms[ 3 ] );
        rooms[ 4 ].setNorth( rooms[ 1 ] );
        rooms[ 4 ].setSouth( rooms[ 7 ] );

        rooms[ 5 ].setEast( null );
        rooms[ 5 ].setWest( rooms[ 4 ] );
        rooms[ 5 ].setNorth( rooms[ 2 ] );
        rooms[ 5 ].setSouth( rooms[ 8 ] );

        rooms[ 6 ].setEast( rooms[ 7 ] );
        rooms[ 6 ].setWest( null );
        rooms[ 6 ].setNorth( rooms[ 3 ] );
        rooms[ 6 ].setSouth( null );

        rooms[ 7 ].setEast( rooms[ 8 ] );
        rooms[ 7 ].setWest( rooms[ 6 ] );
        rooms[ 7 ].setNorth( rooms[ 4 ] );
        rooms[ 7 ].setSouth( null );

        rooms[ 8 ].setEast( null );
        rooms[ 8 ].setWest( rooms[ 7 ] );
        rooms[ 8 ].setNorth( rooms[ 5 ] );
        rooms[ 8 ].setSouth( rooms[ 9 ] );

        return rooms;
    }

    /*
     * This method has the purpose of checking the player's input for keywords.
     * If a keyword is used, then a specific functionality will be triggered.
     */
    private Room roomLogic( String input, Room[] rooms ) {

        if ( input.equalsIgnoreCase( "help" ) ) {

            System.out.println( "\nYou can move to different rooms using 'East', "
                    + "'West,'North' or 'South'."
                    + "\n'Map' will show you the surrounding rooms."
                    + "\n'RemoveRoom' will remove the Current Room and will bring "
                    + "you back the save starting zone (can be used once)"
                    + "\n'MageShield' will shield you from damage attacks for three "
                    + "rooms (can be used once)."
                    + "\n'FindExit' will show you the path to the exit of this "
                    + "madness (easy mode)!\n" );
        } else if ( input.equalsIgnoreCase( "map" ) ) {

            mapLogic();
        } else if ( input.equalsIgnoreCase( "removeroom" ) ) {

            if ( !wasRoomDeleted ) {
                if ( currentRoom != rooms[ 0 ] ) {
                    System.out.println( "\nYou deleted \"" + currentRoom.getName() + "\"" );
                    deletingRoomLogic( rooms, currentRoom );
                    currentRoom = rooms[ 0 ];
                    wasRoomDeleted = true;
                    System.out.println( "The room has been removed. You start from the beggining" );
                } else {
                    System.out.println( "You cannot delete this room." );
                }
            } else {
                System.out.println( "You already deleted a room." );
            }

        } else if ( input.equalsIgnoreCase( "mageshield" ) ) {

            if ( !wasShieldUsed ) {
                wasShieldUsed = true;
                shieldStatus = true;
                System.out.println( "Your mage shield has been activated. You will "
                        + " have protecting for the following three rooms." );
            } else {
                System.out.println( "You cannot use shields anymore, brace yourself!" );
            }

        } else if ( input.equalsIgnoreCase( "findexit" ) ) {
            findExit();
        } else if ( input.equalsIgnoreCase( "East" ) || input.equalsIgnoreCase( "West" )
                || input.equalsIgnoreCase( "North" ) || input.equalsIgnoreCase( "South" ) ) {
            directionLogic( input );
        } else {
            System.out.println( "Concentrate, " + thePlayer.getName() + ", or you "
                    + "will lose your life!" );
        }

        return currentRoom;
    }

    /*
     * This method has the purpose of removing the pointers to a specific room,
     * which essentially deletes it from the map.
     */
    private void deletingRoomLogic( Room[] rooms, Room tobeDeleted ) {
        for ( int i = 0; i < rooms.length; i++ ) {
            if ( rooms[ i ].getEast() != null && rooms[ i ].getEast().equals( tobeDeleted ) ) {
                rooms[ i ].setEast( null );
            }
            if ( rooms[ i ].getWest() != null && rooms[ i ].getWest().equals( tobeDeleted ) ) {
                rooms[ i ].setWest( null );
            }
            if ( rooms[ i ].getNorth() != null && rooms[ i ].getNorth().equals( tobeDeleted ) ) {
                rooms[ i ].setNorth( null );
            }
            if ( rooms[ i ].getSouth() != null && rooms[ i ].getSouth().equals( tobeDeleted ) ) {
                rooms[ i ].setSouth( null );
            }
        }
    }

    /*
     * This method has the purpose of investigating the possible paths for the player
     * by finding the four rooms surrounding the current room in which the player
     * is located.
     */
    private void mapLogic() {
        System.out.println( "\nYou are currently in the \"" + currentRoom.getName() + "\"" );
        if ( currentRoom.getEast() == null ) {
            System.out.println( "\tEast door : ***LOCKED***" );
        } else {
            System.out.println( "\tEast door : " + currentRoom.getEast().getName() );
        }
        if ( currentRoom.getWest() == null ) {
            System.out.println( "\tWest door : ***LOCKED***" );
        } else {
            System.out.println( "\tWest door : " + currentRoom.getWest().getName() );
        }
        if ( currentRoom.getNorth() == null ) {
            System.out.println( "\tNorth door : ***LOCKED***" );
        } else {
            System.out.println( "\tNorth door : " + currentRoom.getNorth().getName() );
        }
        if ( currentRoom.getSouth() == null ) {
            System.out.println( "\tSouth door : ***LOCKED***" );
        } else {
            System.out.println( "\tSouth door : " + currentRoom.getSouth().getName() );
        }
    }

    /*
     * This method find the correct way to the exit room (easy mode for lazy players)
     */
    public void findExit() {
        Room temp;
        temp = currentRoom;
        String result = "Your way ";
        boolean south = true;
        boolean east = true;
        while ( east ) {
            if ( temp.getEast() != null && temp.getEast() != temp ) {
                result += "- east ";
                temp = temp.getEast();
            } else {
                east = false;
            }
        }
        while ( south ) {
            if ( temp.getSouth() != null && temp.getSouth() != temp ) {
                result += "- south  ";
                temp = temp.getSouth();
            } else {
                south = false;
            }
        }
        System.out.println( result );
    }

    /*
     * This method contains the East,West,North,South logic. It contains
     * functionality to change the current room, based on the user desire.
     * However, it also provides additional functionality to enter another
     * room (clockwise) based on a chance.
     */
    private void directionLogic( String input ) {

        int roomShiftChance = rand.nextInt( (3) + 1 );
        boolean isShiftIncluded = roomShiftChance == 1 ? true : false;

        if ( isShiftIncluded ) {
            System.out.println( "*****Clockwise shift*****" );
        }

        switch ( input.toLowerCase() ) {
            case "east":
                if ( isShiftIncluded ) {
                    if ( currentRoom.getSouth() == null ) {
                        System.out.println( "South door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getSouth();
                        damageOrShieldLogic();
                    }
                } else {
                    if ( currentRoom.getEast() == null ) {
                        System.out.println( "East door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getEast();
                        damageOrShieldLogic();
                    }
                }
                break;
            case "west":
                if ( isShiftIncluded ) {
                    if ( currentRoom.getNorth() == null ) {
                        System.out.println( "North door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getNorth();
                        damageOrShieldLogic();
                    }
                } else {
                    if ( currentRoom.getWest() == null ) {
                        System.out.println( "West door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getWest();
                        damageOrShieldLogic();
                    }
                }
                break;
            case "north":
                if ( isShiftIncluded ) {
                    if ( currentRoom.getEast() == null ) {
                        System.out.println( "East door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getEast();
                        damageOrShieldLogic();
                    }
                } else {
                    if ( currentRoom.getNorth() == null ) {
                        System.out.println( "North door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getNorth();
                        damageOrShieldLogic();
                    }
                }
                break;
            case "south":
                if ( isShiftIncluded ) {
                    if ( currentRoom.getWest() == null ) {
                        System.out.println( "West door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getWest();
                        damageOrShieldLogic();
                    }
                } else {
                    if ( currentRoom.getSouth() == null ) {
                        System.out.println( "South door : ***LOCKED***" );
                    } else {
                        currentRoom = currentRoom.getSouth();
                        damageOrShieldLogic();
                    }
                }
                break;
        }
    }

    /*
     * This method has the functionality to evaluate the consequences of entering
     * a specific room. It includes additional functionality to absorb any damage
     * effect based on the user's shield status.
     */
    private void damageOrShieldLogic() {

        if ( roundsWithShield >= 3 ) {
            System.out.println( "\nYour shield had expired." );
            shieldStatus = false;
        }

        if ( shieldStatus ) {
            roundsWithShield++;
            System.out.println( "\n***DAMAGE ABSORBED*** (" + roundsWithShield + " round(s) with shield.)" );
        } else {
            thePlayer.takeDamage( thePlayer.getHealth() - currentRoom.getDamage() );
            System.out.println( "\nThis room damaged you for " + currentRoom.getDamage() + "HP." );
            System.out.println( "Your health now is " + thePlayer.getHealth() );
        }
    }
}
