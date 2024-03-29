//********************************************************************************
// PANTHERID:  5202729
// CLASS: COP 2210 – 2019 Spring
// ASSIGNMENT # 3
// DATE: November 4th, 2019
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//********************************************************************************

import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
public class TowerOfTerror {
    
    //Naadir: Create Backpack
     Set<String> Backpack = new HashSet<String> ();
    private boolean salvation = false;// Checks if they have basement key
     
    //Stores the player's name.
    private String namePC;
    //Stores the index in the backpack array for the item being held.
    private int backpackIndex = 0;
    //int to indicate which shower is being called
    //Master Bathroom = 2
    //Bedroom bathroom = 1
    //Living room bathroom = 0 (default) 
    //only matters in the backpack function, for showing which image.
    private int bathroomShower = 0;
    
    //boolean to indicate if the mirror is in the livingroom bathroom
    //or in the bedroom bathroom.
    //only matters in the backpack function, for showing which image.
    private boolean livingroomMirror = true;
    
    /*
    Tiff: These booleans check if the player is still alive and if the player
    has escaped.
    player starts off as Alive.
    player has yet to escape, so false.
    */
    private boolean playerAlive = true;
    private boolean playerEscaped = false;
    
    /*
    Tiff: This boolean array is checked in the elevator function.
    If all elements are true, then the player dies, as they have explored
    every room.
    Java intializes booleans to false. 
    */
    private boolean[] floorExplored = new boolean[13];
    
    /**
     * Eric: This boolean checks whether the elevator method was called, which
     * is used to break the while loop in the room methods
     */
    private boolean elevatorCalled = false;
    
    /*
    playerStart: If the player has just entered
    the Tower of Terror, they start at the Front Door.
    */
    private boolean playerStart = true;
    
    /*
    If true, program terminates.
    Happens when player hits x or cancel.
    */
    private boolean XorCancel = false;

    
    
    
    //The only method in the tester class, prompts for player's
    //name which is used throughout the house.
    public void Enter(){
        /**
         * This function is initially played to find the user's name
         * then calls Elevator and starts the loop.
         * @author Tiffany Reyes
         */
       ImageIcon iconName = new ImageIcon("src/main/java/img/name.png");
       ImageIcon iconSweaty = new ImageIcon("src/main/java/img/sweaty.gif");
       
       //I wanted the name no-named bugcat to be shown if no name was entered.
       //Therefore nametempvalue holds the return temporarily to be checked
       //by null, since checking a string to be null is complicated.
       Object nametempvalue = JOptionPane.showInputDialog(null, "Welcome...\n"
            + "What is your name?","The Tower of Terror Begins",JOptionPane.INFORMATION_MESSAGE,iconName, null,"No-named BugCat");
       //If nametempvalue is null, or namePC is empty, change to No-named Bugcat.
       namePC = (String) nametempvalue;
       if(nametempvalue == null || namePC.isEmpty()){
            namePC = "No-named BugCat";
         }
       
       int cancelCheck = JOptionPane.showConfirmDialog(null,"Welcome to the Tower of Terror, "+namePC+".\n"
         + "Let us enter..."
               + "\n\n[Hit Cancel at anytime to stop the game!]", "Let's go!", 2, JOptionPane.INFORMATION_MESSAGE, iconSweaty);
    
    //Calling the Elevator here.
    if(cancelCheck != JOptionPane.CANCEL_OPTION){Elevator();}
    }
    
    /**
     * Tiffany Reyes: Elevator case.
     */
    public void Elevator(){ 
        /**
         * The main loop of the project.
         * All room functions return here and are called from here in the Elevator.
         * Keeps looping if the player is alive and has not escaped.
         * @author Tiffany Reyes
         */
      elevatorCalled = true;
      
      /*Array of options to be offered in the elevator. Used with 
      Chosen Destination function to create a switch case.
      */
    String[] elevatorFloors = {"Floor 0","Floor 1","Floor 2","Floor 3",  
        "Floor 4","Floor 5","Floor 6","Floor 7","Floor 8","Floor 9","Floor 10",
        "Floor 11","Floor 12"};
    
        /*Intialized to 0. For checking if all the rooms have been explored.*/
        int allFloorsExplored=0;
        
        
        ImageIcon iconElevator = new ImageIcon("src/main/java/img/ELEVATOR.gif");
          ImageIcon iconSweaty = new ImageIcon("src/main/java/img/sweaty.gif");
       do{
            //First, if the player is just starting, they must start
            // at the FrontDoor.
           if(playerStart){               
               FrontDoor();
               playerStart = false;
               //The player has always explored at least one room.
               floorExplored[1]=true;
           }
           else{
               /*First, check if the player has explored
               all the rooms. This loop checks that.
               This All rooms explored int will increment
               if a room has been explored.
               */
             
               for(int i = 0; i < floorExplored.length; i++){
                   if(floorExplored[i]==true){allFloorsExplored++;
                   }
               }
               //If all 13 floors were explored, player dies.
               if(allFloorsExplored == 13){playerAlive=false;
               JOptionPane.showConfirmDialog(null,"You're still here, "+namePC
                + "?\nYou've explored every room and unlike you, we can't be here all day.\n\n"+namePC+" dies in the elevator.\n"
                        + "Later, when they find your remains, they take a peek in your Backpack:\n"+Backpack+"\nThey'll take good care of it.", 
                       "Ripperoni, Pepperoni: Game Over", 2, JOptionPane.INFORMATION_MESSAGE, iconSweaty);
               }
               else{
                   allFloorsExplored = 0;// if not, reset counter for next loop.
               
               /*Now offer the elevator options.*/
                    String elevatorMessage =("You stand in the creaky and cold elevator."
                       + "\nThe buttons on the side panel flickers in the dim light."
                       + "\nYou press...");
                    String inputElevator = (String) JOptionPane.showInputDialog(null, elevatorMessage,
                    "Which floor would you like?",
                    JOptionPane.QUESTION_MESSAGE, iconElevator  , elevatorFloors, elevatorFloors[0]);
               
                    int floorChosen = chosenDestination(elevatorFloors, inputElevator);
                    switch(floorChosen){
                        case 0: 
                             Basement();
                            break;
                        case 1:
                             FrontDoor();
                            break;
                        case 2: 
                             LivingRoom();
                            break;
                        case 3:
                            Bathroom1();
                            break;
                        case 4: 
                            Kitchen();
                            break;
                        case 5: 
                             Pantry();
                            break;
                        case 6:
                             DiningRoom();
                            break;
                        case 7:
                             firstBedroom();
                            break;
                        case 8:
                             Bathroom2();
                            break;
                        case 9:
                             secondBedroom();
                            break;
                        case 10:
                             MasterBathroom();
                            break;
                        case 11:
                             MasterBedroom();
                            break;
                        case 12:
                             Attic();
                            break;
                        case -1:
                            System.out.println("Program terminated.");
                            XorCancel=true;
                            break;
                        default:
                            System.out.println("Program terminated.");
                            XorCancel=true;
                            break;
                    }
                    
                        //If the player has not explored this room yet,
                        //they have now.
                    if(!floorExplored[floorChosen]) {floorExplored[floorChosen] = true;}
                }    
           }
       }while((playerAlive==true)&&(playerEscaped==false)&&(XorCancel==false));
               /*if the player is alive
               and the player has not escaped,
               the game is not over. 
               */
    }
    
    //***FLOOR 0***//
    public void Basement(){
        /**
         * Basement, called at case 1 of Floor 1.
         * Offers Boiler and Storage room. The only method
         * that offers two rooms and calls functions.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */
        elevatorCalled = false;
        //I show a map here to provide some clarity on the options.
        ImageIcon iconBASE = new ImageIcon("src/main/java/img/0-BASEMENT.gif");
        String[] choicesBasement = {"The [Door] to your right.",
            "The [Door] to your left.","Choose another floor in the [Elevator]."
            ,"Check " + namePC+"'s [Backpack]."};
            
        while(elevatorCalled == false && playerAlive == true){
              //JOptionPane with String conversion from object type.
            String inputBasement = (String) JOptionPane.showInputDialog(null,"You are at the bottom floor of the Tower, the Basement."+
                "\nThe elevator door opens, and you realize there are two [Doors].\n"+
                "Which one do you choose?",
                "Floor 0: The Basement",
                JOptionPane.QUESTION_MESSAGE,
                iconBASE,
                choicesBasement,
                choicesBasement[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBasement, inputBasement);
            switch(nextActionIndex){
                case 0: //Bedroom 1
                    BoilerRoom();
                    break;
                case 1:             
                    StorageRoom();
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Ends game entirely.
                    elevatorCalled = true; ///Just to break this loop.
                    break;
                }
        } 
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the front
     * door or up the stairs. Replaced it with going to the elevator.
     */
    //***FLOOR 1***//
    public void FrontDoor(){
          /**
           * Front door method, case 1, called from the Elevator,
         * Loop breaks if player dies or if the elevator is called.
         * Also checks if the player has escaped with the wincases,
         * The keys from the attic and the basement.
         * @author Eric Vilella, Ivan Reyes, Naadir Kirlew, Tiffany Reyes
         */
        elevatorCalled = false;
        //Loop while the player has not escaped, not called elevator and is still alive.
        while((playerAlive)&&(!playerEscaped)&&(!elevatorCalled)){
            
            String[] choices = {"Enter the [Elevator].", "Check " + namePC+"'s [Backpack]."};
           //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconFD = new ImageIcon("src/main/java/img/1-FRONTDOOR.gif");

           //JOptionPane with String conversion from object type.
            String inputFD = (String) JOptionPane.showInputDialog(null, "You stand at the front door, in front of an [Elevator].\nThe door opens as you move forward.\n"
                    + "Where do you choose to go?",
                "Floor 1: The Front Door",
                JOptionPane.QUESTION_MESSAGE,
                iconFD,
                choices,
                choices[0]);


            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choices, inputFD);
            switch(nextActionIndex){
                case 0:
                    elevatorCalled = true;
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the Living 
     * room. Replaced it with going to the elevator.
     */
    //***FLOOR 2***//
    public void LivingRoom(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconLR = new ImageIcon("src/main/java/img/2-LIVINGROOM.gif");

        /**
         * Floor2, case 2 from the Elevator. Offers only a chest,
         * or calling the elevator and checking the backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
           String[] choices = {"Open the [Chest].", "Go back into the [Elevator].","Check " + namePC+"'s [Backpack]."};

           //JOptionPane with String conversion from object type.
            String inputFD = (String) JOptionPane.showInputDialog(null, "Welcome to the Living Room, " + namePC +
                ". \nSadly not much to welcome you here... The room looks pretty bare."
                +"\nAside from a [Chest] in the corner. You hear the [Elevator] creak.",
                "Floor 2: The Living Room",
                JOptionPane.QUESTION_MESSAGE,
                iconLR,
                choices,
                choices[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choices, inputFD);
            switch(nextActionIndex){
                case 0: //BackpackIndex = chest
                    backpackIndex = 1;
                    Backpack(backpackIndex);
                    break;
                case 1:
                    elevatorCalled = true;
                    break;
                case 2:
                JOptionPane.showMessageDialog(null, "You Have " + Backpack 
             + " in your Backpack.");
                break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Split the bathroom into 2 separate bathrooms, and removed being able to 
     * go to another room from it, replacing it with the elevator.
     */    
    //**FLOOR 3**//
    public void Bathroom1(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconLRBATH = new ImageIcon("src/main/java/img/3-LRBATHROOM.gif");

        /**
         * Living room bathroom, Floor 3. Offers the Mirror,
         * Shower, Elevator and Checking the Backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesB1 = {"Turn to the [Mirror]...", 
                "Maybe something is behind the [Shower] curtain...?", 
                "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputB1stFloor = (String) JOptionPane.showInputDialog(null, "Welcome to the Living Room Bathroom, " + namePC +
                ". \nYou wonder if living rooms often have bathrooms..." +
                "\nThere is a [Mirror] and a [Shower]. Couldn't have a bathroom without those."
                        + "\nYou contemplate the privacy a direct [Elevator] to a bathroom provides.",
                "Floor 3: The Living Room's Bathroom",
                JOptionPane.QUESTION_MESSAGE,
                iconLRBATH,
                choicesB1,
                choicesB1[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndexB1 = chosenDestination(choicesB1, inputB1stFloor);
            switch(nextActionIndexB1){
                case 0:
                    //Backpackindex = Mirror;
                    backpackIndex = 7;
                    Backpack(backpackIndex);
                    break;
                case 1:
                    //Backpackindex = shower
                    backpackIndex = 8;
                    Backpack(backpackIndex);
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the kitchen.
     * Replaced it with going to the elevator.
     */
    //***FLOOR 4***//
    public void Kitchen(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconKIT = new ImageIcon("src/main/java/img/4-KITCHEN.gif");

        /**
         * Floor 4, Case 4 of the Elevator. Offers Refridgerator,
         * Cabinet, Elevator and Checking the backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
           String[] choicesKIT = {"It's rude to open someone else's [Refridgerator].",
               "You decide to take a little peek into the [Cabinet]...",
               "Go back into the [Elevator].","Check " + namePC+"'s [Backpack]."
            };

           //JOptionPane with String conversion from object type.
           String inputKIT = (String) JOptionPane.showInputDialog(null, "Welcome to the Kitchen, " + namePC +
               ". \nIt smells awful in here.\n"
                + "Maybe it's coming from the [Refridgerator] or the [Cabinet]?"
                + "\nWho even cares, really? Maybe just get back in the [Elevator].",
               "Floor 4: The Kitchen",
               JOptionPane.QUESTION_MESSAGE,
               iconKIT,
               choicesKIT,
               choicesKIT[0]);
           //Calls chosenDestination, which returns the index of the chosen location
           //after being passed in the returned string from JOptionPane,
           //and the string Array of choices
           int nextActionIndex = chosenDestination(choicesKIT, inputKIT);
           switch(nextActionIndex){
               case 0:
               //Backpackindex = Refridgerator
                   backpackIndex = 3;
                   Backpack(backpackIndex);
                   break;
               case 1: //Backpackindex = cabinet 
                   backpackIndex = 4;
                   Backpack(backpackIndex);
                   break;
               case 2:
                    elevatorCalled = true;
                   break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
               default:
                   System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Removed case where user could backtrack from the pantry
     * . Replaced it with going to the elevator.
     */
    //**FLOOR 5***//
    public void Pantry(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconPAN = new ImageIcon("src/main/java/img/5-PANTRY.gif");

        /**
         * Case 5 of the Elevator, offers pantry, broom,
         * elevator and checking the backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesPAN = {"Ah, maybe some secrets in this [Dusty Recipe Box].",
                "Welp. Might as well do some light cleaning with the [Broom].",
                "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputPAN = (String) JOptionPane.showInputDialog(null, "Welcome to the Pantry, " + namePC +
                ". \nIt's a little dusty and cramped in here.\n"
                + "On the shelves, you see a [Dusty recipe box],"
                + "\nand in the corner, a [Broom]. The [Elevator] could use some cleaning...",
                "Floor 5: The Pantry",
                JOptionPane.QUESTION_MESSAGE,
                iconPAN,
                choicesPAN,
                choicesPAN[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesPAN, inputPAN);
            switch(nextActionIndex){
                case 0: //BackpackIndex = Dusty recipe box
                    backpackIndex =5;
                    Backpack(backpackIndex);
                    break;
                case 1:
                //Backpackindex = Broom
                    backpackIndex = 6;
                    Backpack(backpackIndex);
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the dining
     * room. Replaced it with going to the elevator.
     */
    //***FLOOR 6***//
    public void DiningRoom(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconDR = new ImageIcon("src/main/java/img/6-DININGROOM.gif");

        /**
         * Case 6 of the Elevator, offers the Candelabra,
         * Elevator and checking the backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesDR = {"You reach out to the flickering [Candelabra]...",
                "Go back into the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputDR = (String) JOptionPane.showInputDialog(null, "Welcome to the Dining Room, " + namePC +
                ". \nWhat do ghosts even eat?\n"
                        + "\nA [Candelabra] sits on the table. Oooh, shiny..."
                        + "Behind you, the [Elevator] looms.",
                "Floor 6: The Dining Room",
                JOptionPane.QUESTION_MESSAGE,
                iconDR,
                choicesDR,
                choicesDR[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesDR, inputDR);
            switch(nextActionIndex){
                case 0:
                //Backpackindex = candelabra
                    backpackIndex = 2;
                    Backpack(backpackIndex);
                    break;
                case 1:
                    elevatorCalled = true;
                    break;
                case 2:
                JOptionPane.showMessageDialog(null, "You Have " + Backpack 
             + " in your Backpack.");
                break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
     /**
     * Eric: Removed cases where user could go to rooms or stairs connected to 
     * the first bedroom. Replaced it with going to the elevator. Removed location
     * ,fromStairs, and if statement from method due to it no longer being needed.
     */
    //***FLOOR 7***//
    public void firstBedroom(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconBED1 = new ImageIcon("src/main/java/img/7-BEDROOM1.gif");

        /**
         * Case 7, floor 7 of the elevator. Offers Rocking chair, window,
         * Backpack and Elevator.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            String[] choicesBed1FromStairs = {
                "Wow, a [Rocking Chair] is so fun, just take a lil seat...", 
                "Sweet, you've never seen a [Window] before!",
                "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputBed1FromStairs = (String) JOptionPane.showInputDialog(null,"Welcome to the First Bedroom"
                +namePC+
                ". \nThe room is mostly barren, aside from a few pieces of furniture,\n"+
                "including a [Rocking Chair] by the [Window]."
                + "\n",
                "Floor 7: The First Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                iconBED1,
                choicesBed1FromStairs,
                choicesBed1FromStairs[0]);

                //Calls chosenDestination, which returns the index of the chosen location
                //after being passed in the returned string from JOptionPane,
                //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed1FromStairs, inputBed1FromStairs);
            switch(nextActionIndex){
                case 0://Backpack Index = rocking chair
                    backpackIndex = 9;
                    Backpack(backpackIndex);
                    break;
                case 1://Backpack Index = Window
                    backpackIndex = 10;
                    Backpack(backpackIndex);
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    //***FLOOR 8***//
    public void Bathroom2(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconBDBATH = new ImageIcon("src/main/java/img/8-BDBATHROOM.gif");

        /**
         * Case 8 of the Elevator,
         * Offers mirror, shower, elevator and check backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesB2 = {"Turn to the [Mirror]...", 
                "Maybe something is behind the [Shower] curtain...?",
                "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputB1stFloor = (String) JOptionPane.showInputDialog(null, "Welcome to the Shared Bedroom Bathroom, " + namePC +
                ". \nAnother bathroom with it's own floor?" +
                "\nThere is a [Mirror] and a [Shower]. Couldn't have a bathroom without those."
                        + "\nBehind you, the [Elevator] watches intently, interested in your bathroom business.",
                "Floor 8: The Shared Bedroom Bathroom",
                JOptionPane.QUESTION_MESSAGE,
                iconBDBATH,
                choicesB2,
                choicesB2[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndexB1 = chosenDestination(choicesB2, inputB1stFloor);
            switch(nextActionIndexB1){
                case 0:
                    //Backpackindex = Mirror;
                    backpackIndex = 7;
                    Backpack(backpackIndex);
                    break;
                case 1:
                    //Backpackindex = shower
                    backpackIndex = 8;
                    Backpack(backpackIndex);
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
                    break;
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms or stairs connected to 
     * the second bedroom. Replaced it with going to the elevator. Removed location
     * ,fromStairs, and if statement from method due to it no longer being needed.
     */
    //***FLOOR 9***//
    public void secondBedroom(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconBED2 = new ImageIcon("src/main/java/img/9-BEDROOM2.gif");

        /**
         * Case 9 of the Elevator.
         * Offers Dresser, Doll house, elevator and check backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            String[] choicesBed2FromStairs = {
                "Let's check out this totally inconspicuous [Doll House]!", 
                "Let's see what's in this [Dresser].",
                "Go back to the [Elevator].",
                "Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputBed2FromStairs = (String) JOptionPane.showInputDialog(null,"Welcome to the Second Bedroom"
                +namePC+
                ".\nYou find yourself in another, cold dark room.\n"+
                "A [Doll House] sits at the center, a barely standing [Dresser] against the wall."
                + "\nOr do you miss the smell of the [Elevator] already?",
                "Floor 9: The Second Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                iconBED2,
                choicesBed2FromStairs,
                choicesBed2FromStairs[0]);

            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed2FromStairs, inputBed2FromStairs);
            switch(nextActionIndex){
                case 0://Backpack Index = Doll House
                    backpackIndex = 11;
                    Backpack(backpackIndex);
                    break;
                case 1://Backpack Index = Dresser
                    backpackIndex = 12;
                    Backpack(backpackIndex);
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Removed case where user could backtrack from master bathroom. Replaced it with going to the elevator.
     */
    //***FLOOR 10***//
    public void MasterBathroom(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconMBATH = new ImageIcon("src/main/java/img/10-MASTERBATH.gif");

        /**
         * Case 10 of the Elevator, offers oil diffusser, shower,
         * elevator, and check backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesMBath = {"An [Intricate Oil Lamp]? ...Like an oil diffuser?", 
                "Master Bathrooms must have pretty cool [Showers], right?", 
                "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputMBath = (String) JOptionPane.showInputDialog(null, "Welcome to the Master Bathroom, " + namePC +
                ". \nThe bathroom is spacious and freezing.\n"
                        + "On the counter, a [Intricate Oil Lamp]."
                        + "\nIn the corner, the bathroom staple, a [Shower]. Bless."
                        + "\nWho has direct [Elevator] access to a bathroom, though?",
                    "Floor 10: The Master Bathroom",
                    JOptionPane.QUESTION_MESSAGE,
                    iconMBATH,
                choicesMBath,
                choicesMBath[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesMBath, inputMBath);
            switch(nextActionIndex){
                case 0:
                //Backpackindex = Intricate Oil Lamp
                    backpackIndex = 14;
                    Backpack(backpackIndex);
                    break;
                case 1://Backpackindex = Shower
                    backpackIndex = 8;
                    bathroomShower = 2;
                    Backpack(backpackIndex);
                    break;
                case 2: 
                    elevatorCalled = true;
                    break;
                case 3:
                JOptionPane.showMessageDialog(null, "You Have " + Backpack 
             + " in your Backpack.");
                break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms or the stairs 
     * connected to the master bedroom. Replaced it with going to the elevator.
     */
    //***FLOOR 11***//

    public void MasterBedroom(){   
        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconMBED = new ImageIcon("src/main/java/img/11-MASTERBED.gif");
        /**
         * Case 11 of the Elevator, offers jewelry box, elevator
         * and check backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Eric Vilella
         */

        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesMBed = {"Anything good to pawn in the [Jewelry Box]?", "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};

            //JOptionPane with String conversion from object type.
            String inputMBed = (String) JOptionPane.showInputDialog(null, "Welcome to the Master Bedroom, " + namePC +
                ". \nA large bed stands in the middle of the room. It's tacky.\n"
                + "You see the real goods, a [Jewelry Box] in the corner."
                + "\n But maybe you miss the [Elevator] already.",
                "Floor 11: The Master Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                iconMBED,
                choicesMBed,
                choicesMBed[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesMBed, inputMBed);
            switch(nextActionIndex){
                case 0:
                //Backpackindex = Jewelry Box
                    backpackIndex = 13;
                    Backpack(backpackIndex);
                    break;
                case 1:
                    elevatorCalled = true;
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                    + " in your Backpack.");
                    break;
                default:
                    System.out.println("Program terminated.");
                    XorCancel = true; //Break elevator loop if X or Cancel was hit.
                    elevatorCalled = true; //Just to break this loop.
            }
        }
    }      
    /*Yasmani, edited by Tiff*/
    //***FLOOR 12***//
    public void Attic(){

        //ImageIcon object for the JOptionPane with image file location. 
            ImageIcon iconATTIC = new ImageIcon("src/main/java/img/12-ATTIC.gif");

        /**
         * Case 12, from the Elevator, offers
         * Chest with win key in it, Elevator, Check Backpack.
         * Loop breaks if player dies or if the elevator is called.
         * @author Yasmani Valdez, Tiffany Reyes, Naadir Kirlew, Ivan Reyes
         */

       elevatorCalled = false;
       //String array of choices for the JOptionPane
       String[] choicesAttic = {"Try to open the [Chest].",
            "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."};
       //JOptionPane with String conversion from object type.
       while(playerAlive&&(!elevatorCalled)){
           String inputAttic = (String) JOptionPane.showInputDialog(null, "You are in the Attic, " + namePC +
               ".\nAround the room, many objects look old and covered in dust.\n"
               + "But in the corner, you see an old rusty [Chest]."
               + "\nLook in the [Chest] or just go back to the [Elevator]?",
               "Floor 12: The Attic",
               JOptionPane.QUESTION_MESSAGE,
               iconATTIC,
               choicesAttic,
               choicesAttic[0]);
           //Calls chosenDestination, which returns the index of the chosen location
           //after being passed in the returned string from JOptionPane,
           //and the string Array of choices
           int nextActionIndex = chosenDestination(choicesAttic, inputAttic);
           switch(nextActionIndex){
               case 0:
                  if(salvation == true){
                      JOptionPane.showMessageDialog(null, "You open the chest and"
                          + " a ray of light surrounds you!\n You close your "
                          + " eyes and when you open them you find yourself"
                          + " at home under your blanket with a warm cup of"
                          + " eggnog.\nSanta is reading you bedtime stories."
                          + "\nSweet Dreams.");
                      playerEscaped =true;
                  }
                  else{
                      JOptionPane.showMessageDialog(null,"It seems you"
                          + " are missing something very important. Better"
                          + " find it before you forget who you are.");
                  }
                  break;
              case 1:
                  elevatorCalled = true;
                  break;
              case 2:
                  JOptionPane.showMessageDialog(null, "You Have " + Backpack 
                  + " in your Backpack.");
                  break;
              default:
                  System.out.println("Program terminated.");
                  XorCancel = true;
                  elevatorCalled = true; //Just to break this loop.
                  break;
              }
       }
   }
    //***ON FLOOR 0: BASEMENT***//
    public void BoilerRoom(){
      /**
         * Case 0 from the Elevator, to Basement, to BoilerRoom
         * Just an empty room. Offers Backpack and Elevator.
         * Loop breaks if player dies or if the elevator is called.
         * @author Yasmani Valdez, Tiffany Reyes
         */
    elevatorCalled = false;
     //String array of choices for the JOptionPane
        String[] choicesBoilerRoom = {"Go through the Elevator to the [Storage Room].",
            "Go back to the [Elevator].","Check " + namePC+"'s [Backpack]."  
        };
        //JOptionPane with String conversion from object type.
        while(elevatorCalled == false && playerAlive == true){
            String inputBoilerRoom = (String) JOptionPane.showInputDialog(null, "You are in the Boiler Room, " + namePC +
            ". \nIt's an empty room with nothing interesting.\n"
                    + "\nMaybe you should checkout the other [Room] or go back to the [Elevator]?",
                    "Floor 0: The Basement's Boiler Room",
            JOptionPane.QUESTION_MESSAGE,
            null,
           choicesBoilerRoom,
            choicesBoilerRoom[0]);
        //Calls chosenDestination, which returns the index of the chosen location
        //after being passed in the returned string from JOptionPane,
        //and the string Array of choices
        int nextActionIndex = chosenDestination(choicesBoilerRoom, inputBoilerRoom);
        switch(nextActionIndex){
            case 0:
                StorageRoom();
                break;
            case 1:
                elevatorCalled = true;
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "You Have " + Backpack 
             + " in your Backpack.");
                break;
            default:
                elevatorCalled = true; //Just to break this loop.
                System.out.println("Program terminated.");
                XorCancel = true; //Ends game entirely.
                break;
               }
        }
}
    //***ON FLOOR 0: BASEMENT***//
    public void StorageRoom(){
        
         /**
         * Case 0 from the Elevator, to Basement, to StorageRoom/
         * Offers taking key from Chest for Attic, Backpack and Elevator.
         * Loop breaks if player dies or if the elevator is called.
         * @author Yasmani Valdez, Tiffany Reyes, Ivan Reyes, Naadir Kirlew
         */
     //String array of choices for the JOptionPane
     elevatorCalled = false;
     
        String[] choicesStorageRoom = {"Check out the [Chest].","Go to the [Boiler Room] through the Elevator.",
            "Go to the [Elevator]","Check " + namePC+"'s [Backpack]."};
        //OptionPane with String conversion from object type.  
        while(elevatorCalled == false && playerAlive == true){
            String inputStorageRoom = (String) JOptionPane.showInputDialog(null, "You are in the Storage Room, " + namePC +
            ". \nIt's eerily quiet. Amongst the clutter, you see a [Chest].\n"
                + "\nTake a lil peek inside the [Chest] or maybe go through the other Basement [Door], through the Elevator?\n"
                + "Or just go back to pushing buttons and see where it takes you in the [Elevator].",
                "Floor 0: The Basement's Storage Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesStorageRoom,
                choicesStorageRoom[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesStorageRoom, inputStorageRoom);
            switch(nextActionIndex){
            case 0:
                backpackIndex = 15;
                Backpack(backpackIndex);
                salvation = true;
                break;
            case 1:
            //Backpackindex = Refridgerator
                BoilerRoom();
                break;
            case 2:
                elevatorCalled = true;
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "You Have " + Backpack 
             + " in your Backpack.");
                break;
            default:
                System.out.println("Program terminated.");
                XorCancel = true; //Ends Game entirely.
                elevatorCalled = true; //Just to break this loop.
                break;
               }
        }
}
    // Modified by Naadir Kirlew
    public String Backpack(int backpackIndex){
            /**
             * Outputs message depending on item chosen in rooms.
             * Returns nothing, just goes back to calling point to continue loops.
             * Changed to the hashset.
         * @author Naadir Kirlew, Tiffany Reyes, Ivan Reyes
         */
        String itemMessage= "";
        ImageIcon lastLocation = new ImageIcon("src/main/java/img/bcaClapCheer.gif"); 
        switch(backpackIndex){
                    //Chest case
            case 1: itemMessage = "A Ghost escapes from the Chest and scares you to death."
                    + "\nYou die.\nF.";
                    lastLocation = new ImageIcon("src/main/java/img/2death.gif");
                    Backpack.add("Chest");
                    playerAlive = false;
                    break;
                    //Candelabra case
            case 2: itemMessage = "Suddenly the Candelabra flares! You see a death shadow."
                    + "\nThen you die.";
                    lastLocation = new ImageIcon("src/main/java/img/6death.gif");
                    Backpack.add("Candelabra");
                    playerAlive = false;
                    break;
                    //Refridgerator case
            case 3: itemMessage = "You open the refridgerator and see someone's head.\n"
                    + "The jar slowly unfogs... it's yours.";
                    lastLocation = new ImageIcon("src/main/java/img/4death.gif");
                    Backpack.add("Jar from the Refridgerator");
                    playerAlive = false;
                    break;
                    //Cabinet case
            case 4: itemMessage = "The dishes and glasses fly out of the cabinet and hit you in the face."
                    + "\n You die.";
                    lastLocation = new ImageIcon("src/main/java/img/4death.gif");
                    Backpack.add("Broken Glass from the Cabinet");
                    playerAlive = false;
                    break;
                    //Dusty recipe box case
            case 5: itemMessage = "You open the box and find some pretty decent recipes.\n"
                    + "You don't die. But you will eventually.";
                    lastLocation = new ImageIcon("src/main/java/img/5item.gif");
                    Backpack.add("Recipes from the Dusty Recipe Box");
                    break;
                    //Broom case
            case 6: itemMessage = "As soon as you grasp the broom, the room explodes.\n"
                    + "RIP.";
                    lastLocation = new ImageIcon("src/main/java/img/5death.gif");
                    Backpack.add("Haunted Broom");
                    playerAlive = false;
                    break;
                    //Mirror case
            case 7: itemMessage = "You look in the mirror. You look fantastic.\n"
                    + "Keep doing you. Great job man.";
                    if(livingroomMirror){
                        lastLocation = new ImageIcon("src/main/java/img/3mirror.gif");
                        Backpack.add("Living Room Bathroom's Mirror");
                        }else{lastLocation = new ImageIcon("src/main/java/img/9mirror.gif");
                        Backpack.add("Bedroom Bathroom's Mirror");}
                    break;
                    //Shower case
            case 8: itemMessage = "The shower suddenly turns on. You get drenched.\n"
                    + "You kinda needed it. How considerate.";
                    Backpack.add("Spooky Water from the Shower");
                    switch(bathroomShower){
                        case 0: lastLocation = new ImageIcon("src/main/java/img/butt.gif");
                            break;
                        case 1: lastLocation = new ImageIcon("src/main/java/img/butt.gif");
                            break;
                        case 2: lastLocation = new ImageIcon("src/main/java/img/butt.gif");
                            break;
                    }
                    break;
                    //Rocking chair case
            case 9: itemMessage = "You sit in the Rocking Chair. It breaks suddenly.\n"
                    + "You fall to the floor. Then through the floor.\n"
                    + "Through the ground. Straight to hell.";
                    lastLocation = new ImageIcon("src/main/java/img/angry.gif");
                    Backpack.add("Wood Splinters from the Rocking Chair");
                    playerAlive = false;
                    break;
                    //Window
            case 10: itemMessage = "You fall through the Window. Who couldve seen this coming?";
                    lastLocation = new ImageIcon("src/main/java/img/angry.gif");
                    Backpack.add("Broken Glass from the Window");
                    playerAlive = false;
                    break;
                    //Doll House
            case 11: itemMessage = "You lose track of a few hours playing with the dolls.\n"
                    + "Really sends you back. Such nostalgia. What a good time.";
                    lastLocation = new ImageIcon("src/main/java/img/yay.gif");
                    Backpack.add("Haunted Dolls");
                    break;
                    //Dresser
            case 12: itemMessage = "A ghost flies out of the dresser. It's dressed great.\n"
                    + "It still kills you though.";
                    lastLocation = new ImageIcon("src/main/java/img/cry.gif");
                    Backpack.add("Pet Ghost (?)");
                    playerAlive = false;
                    break;
                    //Jewelry Box case
            case 13: itemMessage = "You've opened Pandora's box. The end of the world is neigh.\n"
                    + "No one blames you, just capitalism. Justly.";
                    lastLocation = new ImageIcon("src/main/java/img/nopls.gif");
                    Backpack.add("Pandora's Now Empty Box");
                    break;
                    //intricate oil lamp case
            case 14: itemMessage = "You're right. It's an oil diffuser.\n"
                    + "In this dark corner of the Haunted House,\nyou find true relaxation "
                    + "in the scent \"Lemon Grass\"."+"\nNice.";
                    lastLocation = new ImageIcon("src/main/java/img/yay.gif");
                    Backpack.add("Oil Diffuser");
                    break;
            case 15: itemMessage = "You found an eerily irresistible key, "
                    + "/n it calls you!! You must have it!!!";
                    lastLocation = new ImageIcon("src/main/java/img/party.gif");
                    Backpack.add("Precious Key");
                    break;
            default: 
                  System.out.println("Empty. Shouldn't be called. Program terminates.");
                  
        }
        
        String backpackMessage="";
        if(!playerAlive){
        backpackMessage = "\nIt's the end of the line for you, "+namePC+".\n"
            + "Later they find your remains, whatever it looks like, and your backpack.\n"
            + "It holds:\n" +Backpack +".\nWas it worth it?";}
        JOptionPane.showConfirmDialog(null,itemMessage+"\n"+backpackMessage,
                    "Game Over", 2, JOptionPane.ERROR_MESSAGE, lastLocation);   
        return backpackMessage;
    }
    public int chosenDestination(String[] choices, String chosenLocation){
            /**
         * Called in every method.
         * Just for case purposes and simplicity, rather than comparing strings,
         * allows utilization of a switch statement.
         * @param choices String array, usually of the JOptionPane choices for a window.
         * @param chosenLocation String, usually what was chosen from the JOptionPane
         * @return Returns the index of the string in the passed in array.
         * @author Tiffany Reyes
         */
         /*For loop to find index of chosen destination for switch case*/
         //intiialze to 0
        int choicesIndex = 0;
        //if chosenLocation is a null pointer pointing to nothing, returns error
        if(chosenLocation == null){
            choicesIndex = -1;}
        else{ //if not, return the same as the index.
            for(int i = 0; i < choices.length; i++) {
                if(choices[i].equals(chosenLocation)) {
                    choicesIndex = i;
                    break;
                } 
            }
        }
        return choicesIndex;
    }
}