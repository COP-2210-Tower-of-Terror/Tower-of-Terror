//********************************************************************************
// PANTHERID:  5202729
// CLASS: COP 2210 – 2019 Spring
// ASSIGNMENT # 3
// DATE: November 4th, 2019
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else.
//********************************************************************************

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
public class hauntedHouse {
    private String[] backpacklist = {"Empty","Chest","Candelabra","Refrigerator","Cabinet","Dusty recipe box",
            "Broom","Mirror","Shower","Rocking Chair","Window","Doll House",
            "Dresser","Jewelry Box","Intricate Oil Lamp"};
    
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

    
    
    
    //The only method in the tester class, prompts for player's
    //name which is used throughout the house.
    public void Enter(){
        
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
       
       JOptionPane.showConfirmDialog(null,"Welcome to the Tower of Terror, "+namePC+".\n"
         + "Let us enter...", "Let's go!", 2, JOptionPane.INFORMATION_MESSAGE, iconSweaty);

    
    //Calling the Elevator here.
    Elevator();
    }
    
    /**
     * Tiffany Reyes: Elevator case.
     */
    
    public void Elevator(){ 
      elevatorCalled = true;
      
      /*Array of options to be offered in the elevator. Used with 
      Chosen Destination function to create a switch case.
      */
    String[] elevatorFloors = {"Floor 0","Floor 1","Floor 2","Floor 3",  
        "Floor 4","Floor 5","Floor 6","Floor 7","Floor 8","Floor 9","Floor 10",
        "Floor 11","Floor 12"};
    
        /*Intialized to 0. For checking if all the rooms have been explored.*/
        int allFloorsExplored=0;
        
        
        ImageIcon iconSweaty = new ImageIcon("src/main/java/img/sweaty.gif");
       do{
            //First, if the player is just starting, they must start
            // at the FrontDoor.
           if(playerStart){
               playerStart = false;
               FrontDoor();
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
               JOptionPane.showConfirmDialog(null,"You're still here, "+namePC+"."
                + "?\nYou've explored every room and unlike you, we can't be here all day.\n\nYou die in the elevator.", 
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
                    JOptionPane.QUESTION_MESSAGE, null, elevatorFloors, elevatorFloors[0]);
               
                    int floorChosen = chosenDestination(elevatorFloors, inputElevator);
                    switch(floorChosen){
                        case 0: 
                             //Basement();
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
                             //Attic();
                            break;
                        }
                    
                        //If the player has not explored this room yet,
                        //they have now.
                        if(!floorExplored[floorChosen]) {floorExplored[floorChosen] = true;}
                } 
               
               
           }
       }while((playerAlive==true)&&(playerEscaped==false));
               /*if the player is alive
               and the player has not escaped,
               the game is not over. 
               */
        
    
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the front
     * door or up the stairs. Replaced it with going to the elevator.
     */
    public void FrontDoor(){
        
        //If the player has not explored this room yet,
        //they have now.
        
        
        //String array of choices for the JOptionPane
       String[] choices = {"Go into the [Elevator]"};
       
       //ImageIcon object for the JOptionPane with image file location. 
       ImageIcon iconFD = new ImageIcon("src/main/java/img/bugcatmap1.png");
       
       //JOptionPane with String conversion from object type.
        String inputFD = (String) JOptionPane.showInputDialog(null, "You stand at the front door, in front of an elevator.\nThe door opens as you move forward.\n"
                + "Where do you choose to go?",
            "The Front Door",
            JOptionPane.QUESTION_MESSAGE,
            iconFD,
            choices,
            choices[0]);
    
        //Testing case.
        //System.out.println(inputFD)
        
        //Calls chosenDestination, which returns the index of the chosen location
        //after being passed in the returned string from JOptionPane,
        //and the string Array of choices
        int nextActionIndex = chosenDestination(choices, inputFD);
        switch(nextActionIndex){
            case 0:
                //Elevator();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminates.");
                playerAlive = false;
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the Living 
     * room. Replaced it with going to the elevator.
     */
    public void LivingRoom(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
           String[] choices = {"Open the [Chest].", "Go back into the [Elevator]."};

           //JOptionPane with String conversion from object type.
            String inputFD = (String) JOptionPane.showInputDialog(null, "Welcome to the Living Room, " + namePC +
                    ". \nSadly not much to welcome you here... The room looks pretty bare." +
                    "\nAside from a [Chest] in the corner, you see another [Door]...",
                "The Living Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choices, inputFD);
            switch(nextActionIndex){
                case 0: //BackpackIndex = chest
                    backpackIndex = 1;
                    Backpack();
                    break;
                case 1:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the dining
     * room. Replaced it with going to the elevator.
     */
    public void DiningRoom(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesDR = {"You reach out to the flickering [Candelabra]...", "Go back into the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputDR = (String) JOptionPane.showInputDialog(null, "Welcome to the Dining Room, " + namePC +
                ". \nWhat do ghosts even eat?\n"
                        + "Might as well raid the [Kitchen] and find out."
                        + "\nOr check out the [Candelabra]. Oooh, shiny...",
                "The Dining Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
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
                    Backpack();
                    break;
                case 1:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms connected to the kitchen.
     * Replaced it with going to the elevator.
     */    
    public void Kitchen(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
           String[] choicesKIT = {"It's rude to open someone else's [Refridgerator].",
               "You decide to take a little peek into the [Cabinet]...",
               "Go back into the [Elevator]"
           };

           //JOptionPane with String conversion from object type.
           String inputKIT = (String) JOptionPane.showInputDialog(null, "Welcome to the Kitchen, " + namePC +
               ". \nIt smells awful in here.\n"
                       + "Maybe it's coming from the [Refridgerator] or the [Cabinet]?"
                       + "\nOr should we just check out what hides behind the next [Door]...",
               "The Kitchen",
               JOptionPane.QUESTION_MESSAGE,
               null,
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
                   Backpack();
                   break;
               case 1: //Backpackindex = cabinet 
                   backpackIndex = 4;
                   Backpack();
                   break;
               case 2:
                    elevatorCalled = true;
                   break;
               default:
                   System.out.println("null value. Did you close the window? Program terminated.");
                   playerAlive = false;
           }
        }
    }
    /**
     * Eric: Removed case where user could backtrack from the pantry
     * . Replaced it with going to the elevator.
     */
    public void Pantry(){
            elevatorCalled = false; 
            while(playerAlive == true && elevatorCalled == false){
             //String array of choices for the JOptionPane
            String[] choicesPAN = {"Ah, maybe some secrets in this [Dusty Recipe Box].",
                "Welp. Might as well do some light cleaning with the [Broom].",
                "Go back to the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputPAN = (String) JOptionPane.showInputDialog(null, "Welcome to the Pantry, " + namePC +
                ". \nIt's a little dusty and cramped in here.\n"
                        + "On the shelves, you see a [Dusty recipe box],"
                        + "\nand in the corner, a [Broom].",
                "The Pantry",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesPAN,
                choicesPAN[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesPAN, inputPAN);
            switch(nextActionIndex){
                case 0: //BackpackIndex = Dusty recipe box
                    backpackIndex =5;
                    Backpack();
                    break;
                case 1:
                //Backpackindex = Broom
                    backpackIndex = 6;
                    Backpack();
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    /**
     * Eric: Removed stairs. Replaced with elevator.
     */
    /**
     * Eric: Removed cases where user could go to rooms or the stairs 
     * connected to the master bedroom. Replaced it with going to the elevator.
     */
    public void MasterBedroom(){   
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesMBed = {"Anything good to pawn in the [Jewelry Box]?", "Go back to the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputMBed = (String) JOptionPane.showInputDialog(null, "Welcome to the Master Bedroom, " + namePC +
                ". \nA large bed stands in the middle of the room. It's tacky.\n"
                        + "You see the real goods, a [Jewelry Box] in the corner."
                        + "\nAnother [Door], shut, beckons from the far corner.",
                    "The Master Bedroom",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
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
                    Backpack();
                    break;
                case 1:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    /**
     * Eric: Removed case where user could backtrack from master bathroom. Replaced it with going to the elevator.
     */
    public void MasterBathroom(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesMBath = {"An [Intricate Oil Lamp]? ...Like an oil diffuser?", 
                "Master Bathrooms must have pretty cool [Showers], right?", "Go back to the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputMBath = (String) JOptionPane.showInputDialog(null, "Welcome to the Master Bathroom, " + namePC +
                ". \nThe bathroom is spacious and freezing.\n"
                        + "On the counter, a [Intricate Oil Lamp]."
                        + "\nIn the corner, the bathroom staple, a [Shower]. Bless.",
                    "The Master Bathroom",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
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
                    Backpack();
                    break;
                case 1://Backpackindex = Shower
                    backpackIndex = 8;
                    bathroomShower = 2;
                    Backpack();
                    break;
                case 2: 
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    /**
     * Eric: Removed cases where user could go to rooms or stairs connected to 
     * the first bedroom. Replaced it with going to the elevator. Removed location
     * ,fromStairs, and if statement from method due to it no longer being needed.
     */    
    public void firstBedroom(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            String[] choicesBed1FromStairs = {
                "Wow, a [Rocking Chair] is so fun, just take a lil seat...", 
                "Sweet, you've never seen a [Window] before!",
                "Go back to the [Elevator]."};

                  //JOptionPane with String conversion from object type.
            String inputBed1FromStairs = (String) JOptionPane.showInputDialog(null,"Welcome to the First 2nd-Floor Bedroom "
                    +namePC+
                    ". \nThe room is mostly barren, aside from a few pieces of furniture,\n"+
                    "including a [Rocking Chair] by the [Window]."
                    + "\nA [Door] in the room creaks.",
                "The First 2nd Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesBed1FromStairs,
                choicesBed1FromStairs[0]);

                //Calls chosenDestination, which returns the index of the chosen location
                //after being passed in the returned string from JOptionPane,
                //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed1FromStairs, inputBed1FromStairs);
            switch(nextActionIndex){
                case 0://Backpack Index = rocking chair
                    backpackIndex = 9;
                    Backpack();
                    break;
                case 1://Backpack Index = Window
                    backpackIndex = 10;
                    Backpack();
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    
    /**
     * Eric: Removed cases where user could go to rooms or stairs connected to 
     * the second bedroom. Replaced it with going to the elevator. Removed location
     * ,fromStairs, and if statement from method due to it no longer being needed.
     */    
    public void secondBedroom(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            String[] choicesBed2FromStairs = {
                "Let's check out this totally inconspicuous [Doll House]!", 
                "Let's see what's in this [Dresser].",
                "Go back to the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputBed2FromStairs = (String) JOptionPane.showInputDialog(null,"Welcome to the Second 2nd-Floor Bedroom "
                +namePC+
                ". \nYou find yourself in another, cold dark room.\n"+
                "A [Doll House] sits at the center, a barely standing [Dresser] against the wall."
                + "\nA [Door] in the room creaks.",
                "The Second 2nd Floor Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesBed2FromStairs,
                choicesBed2FromStairs[0]);

            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed2FromStairs, inputBed2FromStairs);
            switch(nextActionIndex){
                case 0://Backpack Index = Doll House
                    backpackIndex = 11;
                    Backpack();
                    break;
                case 1://Backpack Index = Dresser
                    backpackIndex = 12;
                    Backpack();
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    
    /**
     * Eric: Split the bathroom into 2 separate bathrooms, and removed being able to 
     * go to another room from it, replacing it with the elevator.
     */    
    public void Bathroom1(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesB1 = {"Turn to the [Mirror]...", "Maybe something is behind the [Shower] curtain...?", "Go back to the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputB1stFloor = (String) JOptionPane.showInputDialog(null, "Welcome to the Living Room Bathroom, " + namePC +
                ". \nYou wonder if living rooms often have bathrooms..." +
                "\nThere is a [Mirror] and a [Shower]. Couldn't have a bathroom without those.",
                "The Living Room's Bathroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
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
                    Backpack();
                    break;
                case 1:
                    //Backpackindex = shower
                    backpackIndex = 8;
                    Backpack();
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    public void Bathroom2(){
        elevatorCalled = false; 
        while(playerAlive == true && elevatorCalled == false){
            //String array of choices for the JOptionPane
            String[] choicesB2 = {"Turn to the [Mirror]...", "Maybe something is behind the [Shower] curtain...?", "Go back to the [Elevator]."};

            //JOptionPane with String conversion from object type.
            String inputB1stFloor = (String) JOptionPane.showInputDialog(null, "Welcome to the Living Room Bathroom, " + namePC +
                ". \nYou wonder if living rooms often have bathrooms..." +
                "\nThere is a [Mirror] and a [Shower]. Couldn't have a bathroom without those.",
                "The Living Room's Bathroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
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
                    Backpack();
                    break;
                case 1:
                    //Backpackindex = shower
                    backpackIndex = 8;
                    Backpack();
                    break;
                case 2:
                    elevatorCalled = true;
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
                    playerAlive = false;
            }
        }
    }
    
    
    public void Backpack(){
        String backpackMessage = "\nThe "+backpacklist[backpackIndex]+" rests in your backpack..."
                + "\nCya, "+namePC+"!";
        String itemMessage= "";
        ImageIcon lastLocation = new ImageIcon("src/main/java/img/bcaClapCheer.gif"); 

        switch(backpackIndex){
                    //Chest case
            case 1: itemMessage = "A Ghost escapes from the Chest and scares you to death."
                    + "\nYou die.\nF.";
                    lastLocation = new ImageIcon("src/main/java/img/livingroom.gif");
                    break;
                    //Candelabra case
            case 2: itemMessage = "Suddenly the Candelabra flares! You see a death shadow."
                    + "\nThen you die.";
                    lastLocation = new ImageIcon("src/main/java/img/diningroom.gif");
                    break;
                    //Refridgerator case
            case 3: itemMessage = "You open the refridgerator and see someone's head.\n"
                    + "The jar slowly unfogs... it's yours.";
                    lastLocation = new ImageIcon("src/main/java/img/kitchen.gif");
                    break;
                    //Cabinet case
            case 4: itemMessage = "The dishes and glasses fly out of the cabinet and hit you in the face."
                    + "\n You die.";
                    lastLocation = new ImageIcon("src/main/java/img/kitchen.gif");
                    break;
                    //Dusty recipe box case
            case 5: itemMessage = "You open the box and find some pretty decent recipes.\n"
                    + "You don't die. But you will eventually.";
                    lastLocation = new ImageIcon("src/main/java/img/pantry1.gif");
                    break;
                    //Broom case
            case 6: itemMessage = "As soon as you grasp the broom, the room explodes.\n"
                    + "RIP.";
                    lastLocation = new ImageIcon("src/main/java/img/pantry2.gif");
                    break;
                    //Mirror case
            case 7: itemMessage = "You look in the mirror. You look fantastic.\n"
                    + "Keep doing you. Great job man.";
                    if(livingroomMirror){
                        lastLocation = new ImageIcon("src/main/java/img/bathroommirror.gif");
                        }else{lastLocation = new ImageIcon("src/main/java/img/bathroommirror-bed.gif");}
                    break;
                    //Shower case
            case 8: itemMessage = "The shower suddenly turns on. You get drenched.\n"
                    + "You kinda needed it. How considerate.";
                    switch(bathroomShower){
                        case 0: lastLocation = new ImageIcon("src/main/java/img/shower.gif");
                            break;
                        case 1: lastLocation = new ImageIcon("src/main/java/img/shower-bedbath.gif");
                            break;
                        case 2: lastLocation = new ImageIcon("src/main/java/img/shower-master.gif");
                            break;
                    }
                    break;
                    //Rocking chair case
            case 9: itemMessage = "You sit in the Rocking Chair. It breaks suddenly.\n"
                    + "You fall to the floor. Then through the floor.\n"
                    + "Through the ground. Straight to hell.";
                    lastLocation = new ImageIcon("src/main/java/img/bed1.gif");
                    break;
                    //Window
            case 10: itemMessage = "You fall through the Window. Who couldve seen this coming?";
                    lastLocation = new ImageIcon("src/main/java/img/bed1-1.gif");
                    break;
                    //Doll House
            case 11: itemMessage = "You lose track of a few hours playing with the dolls.\n"
                    + "Really sends you back. Such nostalgia. What a good time.";
                    lastLocation = new ImageIcon("src/main/java/img/bed2-1.gif");
                    break;
                    //Dresser
            case 12: itemMessage = "A ghost flies out of the dresser. It's dressed great.\n"
                    + "It still kills you though.";
                    lastLocation = new ImageIcon("src/main/java/img/dresser.gif");
                    break;
                    //Jewelry Box case
            case 13: itemMessage = "You've opened Pandora's box. The end of the world is neigh.\n"
                    + "No one blames you, just capitalism. Justly.";
                    lastLocation = new ImageIcon("src/main/java/img/masterbedroom.gif");
                    break;
                    //intricate oil lamp case
            case 14: itemMessage = "You're right. It's an oil diffuser.\n"
                    + "In this dark corner of the Haunted House,\nyou find true relaxation "
                    + "in the scent \"Lemon Grass\"."+"\nNice.";
                    lastLocation = new ImageIcon("src/main/java/img/oillamp.gif");
                    break;
            default: 
                  System.out.println("Empty. Shouldn't be called. Program terminates.");
        }
        
            JOptionPane.showConfirmDialog(null,itemMessage+"\n"+backpackMessage,
                    "Game Over", 2, JOptionPane.ERROR_MESSAGE, lastLocation);     
    }
    

    public int chosenDestination(String[] choices, String chosenLocation){
         /*For loop to find index of chosen destination for switch case*/
         //intiialze to 0
        int choicesIndex = 0;
        //if chosenLocation is a null pointer pointing to nothing, returns error
        if(chosenLocation == null){
            choicesIndex = -1;
        }else{ //if not, return the same as the index.
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