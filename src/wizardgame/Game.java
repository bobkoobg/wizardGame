/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wizardgame;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Boyko
 */
public class Game
{

    Control control;
    Scanner scan = new Scanner(System.in);
    Room currentRoom;
    Room[] rooms = new Room[10];
    private int removedRooms;
    private boolean operationdone = false;
    Player thePlayer;
    private boolean shield = false;
    private int usedShields;
    private int roundsWithShield;

    public void GamePlayer()
    {
        System.out.println("Your name,bebz:");
        String name = scan.nextLine();
        thePlayer = new Player(name, 100);
        rooms = roomCreationLogic();

        System.out.println("Welcome to Mandfred The Wizard.. blah,blah.. game!"
                + "\n\tYou are " + thePlayer.getName() + " with " + thePlayer.getHealth() + "HP"
                + "\n\tType 'Start' to start."
                + "\n\tType 'Help' after you 'Start' for Helping commands.");
        String input = scan.nextLine();
        if (!input.equalsIgnoreCase("start"))
        {
            boolean bobo = false;
            while (bobo == false)
            {
                System.out.println("Wrong input.");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("start"))
                {
                    bobo = true;
                }
            }
        }
        if (input.equalsIgnoreCase("start"))
        {
            currentRoom = rooms[0];
            while (currentRoom != rooms[9] && thePlayer.getHealth() > 0)
            {
                System.out.println("You are in :" + currentRoom.getName());
                System.out.println("\tYour way:");
                input = scan.nextLine();
                roomLogic(input, rooms);
            }

            if (thePlayer.getHealth() == 0)
            {
                System.out.println("\nYou are dead as hell... :)");
            }
            
            if (currentRoom == rooms[9])
            {
                rooms[9].getDescription();
            }
        }

    }

    private Room roomLogic(String input, Room[] arrayOfRooms)
    {
        control = new Control();
        if (input.equalsIgnoreCase("ShiftM"))
        {
            mapLogic();
        } else if (input.equalsIgnoreCase("Help"))
        {
            System.out.println("\nYou can move using 'East','West,'North','South',"
                    + "\n'Shiftm' will show you the surrounding rooms."
                    + "\n'RemoveRoom' will remove the currentroom and will bring you back to starting one."
                    + "\n'MageShield' will shield you from damage attacks."
                    + "\n'FindExit' will help you.. find it.. haha!\n");
        }
        else if (input.equalsIgnoreCase("RemoveRoom"))
        {
            if (removedRooms < 3 && currentRoom != rooms[0] && deletingRoomLogic(arrayOfRooms, currentRoom)) //hacked
            {
                removedRooms++;
                currentRoom = arrayOfRooms[0];
                System.out.println("You deleted " + removedRooms + " room(s).");
            } else if (removedRooms >= 3)
            {
                System.out.println("You deleted 3 rooms already.");
            } else
            {
                System.out.println("Error.");
            }
            System.out.println("The room has been removed. You start from the beggining");

        } else if (input.equalsIgnoreCase("MageShield"))
        {
            usedShields++;
            if (usedShields < 3)
            {
                shield = true;
            } else
            {
                System.out.println("You cannot use shields anymore.");
            }
        } else if (input.equalsIgnoreCase("FindExit"))
        {
            findExit();
        } else if (input.equalsIgnoreCase("East") || input.equalsIgnoreCase("West")
                || input.equalsIgnoreCase("North") || input.equalsIgnoreCase("South"))
        {
            directionLogic(input);
        } else
        {
            System.out.println("Wrong input,babe! ...ops... ahh.." + thePlayer.getName());
        }

        return currentRoom;
    }

    private Room[] roomCreationLogic()
    {
        Room room1 = new Room("Start", "StartmeUP", 0);
        Room room2 = new Room("2", "2", 10);
        Room room3 = new Room("3", "3", 5);
        Room room4 = new Room("4", "4", 15);
        Room room5 = new Room("5", "5", 20);
        Room room6 = new Room("6", "6", 10);
        Room room7 = new Room("7", "7", 5);
        Room room8 = new Room("8", "8", 10);
        Room room9 = new Room("9", "9", 6);
        Room room10 = new Room("Win!", "Winning room! You won the game!", 0);
        Room room11 = new Room("11", "11", 10);
        Room room12 = new Room("12", "12", 15);
        Room room13 = new Room("13", "13", 5);
        Room room14 = new Room("14", "14", 20);
        Room room15 = new Room("INSTAHHH", " IINSTAA KILLLL", 1000);

        TreeMap tree = new TreeMap();
        //tree.put(0, room1); <<<if (tree.get(chosenRoom) != null && chosenRoom != 9 && chosenRoom != 0)
        tree.put(1, room2);
        tree.put(2, room3);
        tree.put(3, room4);
        tree.put(4, room5);
        tree.put(5, room6);
        tree.put(6, room7);
        tree.put(7, room8);
        tree.put(8, room9);
        //tree.put(9, room10); <<<if (tree.get(chosenRoom) != null && chosenRoom != 9 && chosenRoom != 0)
        tree.put(10, room11);
        tree.put(11, room12);
        tree.put(12, room13);
        tree.put(13, room14);
        tree.put(14, room15);

        Random rand = new Random();
        int chosenRoom;
        operationdone = false;
        int index = 1;
        chosenRoom = rand.nextInt(15);
        while (operationdone == false)
        {
            if (tree.get(chosenRoom) != null)
            {
                rooms[index] = (Room) tree.get(chosenRoom);
                System.out.println("CHOSENROOM : " + rooms[index].getName());
                index++;
                tree.remove(chosenRoom);
                if (index == 9)
                {
                    operationdone = true;
                }
            }
            chosenRoom = rand.nextInt(15);
        }
        rooms[0] = room1;
        rooms[9] = room10;

        //1
        rooms[0].setEast(rooms[1]);
        rooms[0].setWest(null);
        rooms[0].setNorth(null);
        rooms[0].setSouth(rooms[3]);
        //2
        rooms[1].setEast(rooms[2]);
        rooms[1].setWest(rooms[0]);
        rooms[1].setNorth(null);
        rooms[1].setSouth(rooms[4]);
        //3
        rooms[2].setEast(null);
        rooms[2].setWest(rooms[1]);
        rooms[2].setNorth(null);
        rooms[2].setSouth(rooms[5]);
        //4
        rooms[3].setEast(rooms[4]);
        rooms[3].setWest(null);
        rooms[3].setNorth(rooms[0]);
        rooms[3].setSouth(rooms[6]);
        //5
        rooms[4].setEast(rooms[5]);
        rooms[4].setWest(rooms[3]);
        rooms[4].setNorth(rooms[1]);
        rooms[4].setSouth(rooms[7]);
        //6
        rooms[5].setEast(null);
        rooms[5].setWest(rooms[4]);
        rooms[5].setNorth(rooms[2]);
        rooms[5].setSouth(rooms[8]);
        //7
        rooms[6].setEast(rooms[7]);
        rooms[6].setWest(null);
        rooms[6].setNorth(rooms[3]);
        rooms[6].setSouth(null);
        //8
        rooms[7].setEast(rooms[8]);
        rooms[7].setWest(rooms[6]);
        rooms[7].setNorth(rooms[4]);
        rooms[7].setSouth(null);
        //9
        rooms[8].setEast(null);
        rooms[8].setWest(rooms[7]);
        rooms[8].setNorth(rooms[5]);
        rooms[8].setSouth(rooms[9]); //winning room

        return rooms;
    }

    private boolean deletingRoomLogic(Room[] rooms, Room tobeDeleted)
    {
        operationdone = false;
        for (int i = 0; i < rooms.length; i++)
        {
            if (rooms[i].getEast() != null && rooms[i].getEast().equals(tobeDeleted))
            {
                rooms[i].setEast(null);
            }
            if (rooms[i].getWest() != null && rooms[i].getWest().equals(tobeDeleted))
            {
                rooms[i].setWest(null);
            }
            if (rooms[i].getNorth() != null && rooms[i].getNorth().equals(tobeDeleted))
            {
                rooms[i].setNorth(null);
            }
            if (rooms[i].getSouth() != null && rooms[i].getSouth().equals(tobeDeleted))
            {
                rooms[i].setSouth(null);
            }
            operationdone = true;
        }
        return operationdone;
    }

    private void mapLogic()
    {
        System.out.println("\nYou are in : " + currentRoom.getName());
        if (currentRoom.getEast() == null || currentRoom.getName().equals(currentRoom.getEast().getName()))
        {
            System.out.println("\tEast door : ***LOCKED***");
        } else
        {
            System.out.println("\tEast door : " + currentRoom.getEast().getName());
        }
        if (currentRoom.getWest() == null || currentRoom.getName().equals(currentRoom.getWest().getName()))
        {
            System.out.println("\tWest door : ***LOCKED***");
        } else
        {
            System.out.println("\tWest door : " + currentRoom.getWest().getName());
        }
        if (currentRoom.getNorth() == null || currentRoom.getName().equals(currentRoom.getNorth().getName()))
        {
            System.out.println("\tNorth door : ***LOCKED***");
        } else
        {
            System.out.println("\tNorth door : " + currentRoom.getNorth().getName());
        }
        if (currentRoom.getSouth() == null || currentRoom.getName().equals(currentRoom.getSouth().getName()))
        {
            System.out.println("\tSouth door : ***LOCKED***");
        } else
        {
            System.out.println("\tSouth door : " + currentRoom.getSouth().getName());
        }

    }

    private void directionLogic(String input)
    {
        int chance = control.findNextRoom();
        if (chance == 0) //rolls from 0-5 therefore it's 16,6% chance ;)
        {
            System.out.println("*****Clockwise shift*****"); //i do not think it's working properly

            switch (input.toLowerCase())
            {
                case "east":
                    if (currentRoom.getSouth() == null )
                    {
                        System.out.println("East door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getSouth();
                        shieldLogic();

                    }
                    break;
                case "west":
                    if (currentRoom.getNorth() == null )
                    {
                        System.out.println("West door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getNorth();
                        shieldLogic();
                    }
                    break;
                case "north":
                    if (currentRoom.getEast() == null )
                    {
                        System.out.println("North door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getEast();
                        shieldLogic();
                    }
                    break;
                case "south":
                    if (currentRoom.getWest() == null )
                    {
                        System.out.println("South door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getWest();
                        shieldLogic();
                    }
                    break;

            }
        } else //if it's from 1 to 5 haha
        {
            switch (input.toLowerCase())
            {
                case "east":
                    if (currentRoom.getEast() == null )
                    {
                        System.out.println("East door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getEast();
                        shieldLogic();
                    }
                    break;
                case "west":
                    if (currentRoom.getWest() == null )
                    {
                        System.out.println("West door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getWest();
                        shieldLogic();
                    }
                    break;
                case "north":
                    if (currentRoom.getNorth() == null )
                    {
                        System.out.println("North door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getNorth();
                        shieldLogic();
                    }
                    break;
                case "south":
                    if (currentRoom.getSouth() == null )
                    {
                        System.out.println("South door : ***LOCKED***");
                    } else
                    {
                        currentRoom = currentRoom.getSouth();
                        shieldLogic();
                    }
                    break;
            }
        }
    }

    public void findExit()
    {
        Room temp;
        temp = currentRoom;
        String result = "Your way ";
        boolean south = true;
        boolean east = true;
        while (east)
        {
            if (temp.getEast() != null && temp.getEast() != temp)
            {
                result += "- east ";
                temp = temp.getEast();
            } else
            {
                east = false;
            }
        }
        while (south)
        {
            if (temp.getSouth() != null && temp.getSouth() != temp)
            {
                result += "- south  ";
                temp = temp.getSouth();
            } else
            {
                south = false;
            }
        }
        System.out.println(result);
        //No path to Manfred's lab found! AINT WORKING IN MY CASE, I AINT LOOSING SHIT!
    }

    private void shieldLogic()
    {

        if (roundsWithShield >= 2)
        {
            System.out.println("Your shield had expired.");
            shield = false;
        }

        if (shield == true)
        {
            System.out.println("***DAMAGE ABSORBED*** (" + roundsWithShield + " round with shield.)");
            roundsWithShield++;
        } else
        {
            thePlayer.takeDamage(thePlayer.getHealth() - currentRoom.getDamage());
            System.out.println("That room took you: " + currentRoom.getDamage());
            System.out.println("Your health now is: " + thePlayer.getHealth());
        }
    }
}
