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
    
    
    //The only method in the tester class, prompts for player's
    //name which is used throughout the house.
    public void Enter(){
        
       ImageIcon iconName = new ImageIcon("src/main/java/img/name.png");
       ImageIcon iconSweaty = new ImageIcon("src/main/java/img/sweaty.gif");
       
       //I wanted the name no-named bugcat to be shown if no name was entered.
       //Therefore nametempvalue holds the return temporarily to be checked
       //by null, since checking a string to be null is complicated.
       Object nametempvalue = JOptionPane.showInputDialog(null, "Welcome...\n"
            + "What is your name?","The Haunted House Begins...",JOptionPane.INFORMATION_MESSAGE,iconName, null,"No-named BugCat");
       //If nametempvalue is null, or namePC is empty, change to No-named Bugcat.
       namePC = (String) nametempvalue;
       if(nametempvalue == null || namePC.isEmpty()){
            namePC = "No-named BugCat";
         }
       
       JOptionPane.showConfirmDialog(null,"Welcome to the Haunted House, "+namePC+".\n"
         + "Let us enter...", "Let's go!", 2, JOptionPane.INFORMATION_MESSAGE, iconSweaty);

    
    //Testing case.
    //System.out.println(namePC);
    FrontDoor();
    }
    
    public void FrontDoor(){
        
        //String array of choices for the JOptionPane
       String[] choices = {"Go up the [Stairs]...", "Down the hall, to the [Dining Room]...","The [Living Room], to the right..."};
       
       //ImageIcon object for the JOptionPane with image file location. 
       ImageIcon iconFD = new ImageIcon("src/main/java/img/bugcatmap1.png");
       
       //JOptionPane with String conversion from object type.
        String inputFD = (String) JOptionPane.showInputDialog(null, "You stand at the front door, at the bottom of the stairs.\nThe path opens 3 ways before you...\n"
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
                Stairs(0);
                break;
            case 1:
                DiningRoom();
                break;
            case 2: 
                LivingRoom();  
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminates.");   
        }
    }
    
    public void LivingRoom(){
        //String array of choices for the JOptionPane
       String[] choices = {"Oooh, I love [Doors]!", "Open the [Chest]."};
       
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
            case 0:
               Bathroom(0);
                break;
            case 1: //BackpackIndex = chest
                backpackIndex = 1;
                Backpack();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminated.");   
        }
       
    }
    
    public void DiningRoom(){
          
        //String array of choices for the JOptionPane
        String[] choicesDR = {"Wander into the [Kitchen].", "You reach out to the flickering [Candelabra]..."};

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
                Kitchen();
                break;
            case 1:
            //Backpackindex = candelabra
                backpackIndex = 2;
                Backpack();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminated.");
        }
    }
        
    public void Kitchen(){
     //String array of choices for the JOptionPane
        String[] choicesKIT = {"Welp, you're outta here. Through the [Door] you go.",
            "It's rude to open someone else's [Refridgerator].",
            "You decide to take a little peek into the [Cabinet]..."
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
                Pantry();
                break;
            case 1:
            //Backpackindex = Refridgerator
                backpackIndex = 3;
                Backpack();
                break;
            case 2: //Backpackindex = cabinet 
                backpackIndex = 4;
                Backpack();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminated.");    }
        }
    
    public void Pantry(){
         //String array of choices for the JOptionPane
        String[] choicesPAN = {"Ah, maybe some secrets in this [Dusty Recipe Box].",
            "Welp. Might as well do some light cleaning with the [Broom]."};

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
            default:
                System.out.println("null value. Did you close the window? Program terminated.");
        }
    }
    
    
    //One stairs function for 3 different cases,
    //One for coming from downstairs
    //One entering the stairs area from Bedroom1
    //One entering the stairs area from Bedroom2
    public void Stairs(int Location){
        int entryPoint = Location;
        if(entryPoint == 0){ //If calling stairs from the Front Door (Going up stairs)
              //String array of choices for the JOptionPane
              
              //I show a map here to provide some clarity on the options.
            ImageIcon mapStairsUp = new ImageIcon("src/main/java/img/stairs-top.gif");
            String[] choicesStairsUp = {"The only [Door] not facing you, off to your side.",
                "The [Door] closest to you, directly infront of you.",
                "The [Door] next to the door infront of you."};

              //JOptionPane with String conversion from object type.
            String inputStairsUp = (String) JOptionPane.showInputDialog(null,namePC + " stands at the top of the stairs."+
                "\nThe hallway is dark and empty, but you make out three different [Doors.]\n"+
                "Which one do you choose?",
                "The Hallway by the Stairs",
                JOptionPane.QUESTION_MESSAGE,
                mapStairsUp,
                choicesStairsUp,
                choicesStairsUp[0]);
            //Calls chosenDestination, which returns the index of the chosen location
             //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesStairsUp, inputStairsUp);
            switch(nextActionIndex){
                case 0: //Bedroom 1
                    MasterBedroom();
                    break;
                case 1:
                    firstBedroom(true);
                    break;
                case 2:
                    secondBedroom(true);
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
        }else if(entryPoint == 1){  //If calling stairs from the first bedroom (Going downstairs)
                  //String array of choices for the JOptionPane
                  
                  //I show a map here for clarity on the options.
            ImageIcon mapBed1Stairs = new ImageIcon("src/main/java/img/stairs-1st.gif");
            String[] choicesBed1Stairs = {"You enter the [Door] farthest from you, on the opposite wall.",
            "You go back down the [Stairs]...", "You go back through the [Door] you entered before. Loopy."};

              //JOptionPane with String conversion from object type.
            String inputBed1Stairs = (String) JOptionPane.showInputDialog(null,namePC + " re-enters the dark hallway."+
                "\nYou see the [Stairs] you climbed earlier in front of you...\n"+
                "As well as the [Door] you entered earlier"
                + " and another across from it.",
                "The Hallway by the Stairs from the First Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                mapBed1Stairs,
                choicesBed1Stairs,
                choicesBed1Stairs[0]);
                 //Calls chosenDestination, which returns the index of the chosen location
                //after being passed in the returned string from JOptionPane,
                //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed1Stairs, inputBed1Stairs);
            switch(nextActionIndex){
                case 0: //MasterBedroom
                    MasterBedroom();
                    break;
                case 1://Go down the stairs, calling Front Door
                    FrontDoor();
                    break;
                case 2://Go to the other bedroom, to the left
                    secondBedroom(false);
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
            
        }else if(entryPoint == 2){ //If calling stairs from the second bedroom 
            
                //I show a map here for clarity on the options.
            ImageIcon mapBed2Stairs = new ImageIcon("src/main/java/img/stairs-2nd.gif");
             String[] choicesBed2Stairs = {"You enter the [Door] in front of you.",
                "You go back down the [Stairs]...", 
                "You go through [Door] you entered earlier, maybe you missed something?"};

              //JOptionPane with String conversion from object type.
            String inputBed2Stairs = (String) JOptionPane.showInputDialog(null,namePC + " re-enters the dark hallway."+
                "\nYou see the [Stairs] you climbed earlier...\n"+
                "The [Door] you ignored before and the [Door] you entered earlier.",
                "The Hallway by the Stairs from the Second Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                mapBed2Stairs,
                choicesBed2Stairs,
                choicesBed2Stairs[0]);
                 //Calls chosenDestination, which returns the index of the chosen location
                //after being passed in the returned string from JOptionPane,
                //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed2Stairs, inputBed2Stairs);
            switch(nextActionIndex){
                case 0: //MasterBedroom
                    MasterBedroom();
                    break;
                case 1://Go down the stairs, calling Front Door
                    FrontDoor();
                    break;
                case 2://Go to the other bedroom, to the left
                    firstBedroom(false);
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
        }else{System.out.println("Error. Default else case. Program terminates."); }
    }
    
    public void MasterBedroom(){   
        //String array of choices for the JOptionPane
        String[] choicesMBed = {"Check out the [Door] in the corner...", "Anything good to pawn in the [Jewelry Box]?"};

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
                MasterBathroom();
                break;
            case 1:
            //Backpackindex = Jewelry Box
                backpackIndex = 13;
                Backpack();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminated.");}
        }
    
    public void MasterBathroom(){//String array of choices for the JOptionPane
        String[] choicesMBath = {"An [Intricate Oil Lamp]? ...Like an oil diffuser?", 
            "Master Bathrooms must have pretty cool [Showers], right?"};

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
            default:
                System.out.println("null value. Did you close the window? Program terminated.");}
    }
        
    public void firstBedroom(boolean Location){
        boolean fromStairs = Location;
        if(fromStairs){ //If you enter Bedroom 1 from the stairs.
          String[] choicesBed1FromStairs = {"Oooh, a [Door]...",
            "Wow, a [Rocking Chair] is so fun, just take a lil seat...", 
            "Sweet, you've never seen a [Window] before!"};

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
                case 0: //Bathroom with argument 1 to indicate from Bedroom 1
                    Bathroom(1);
                    break;
                case 1://Backpack Index = rocking chair
                    backpackIndex = 9;
                    Backpack();
                    break;
                case 2://Backpack Index = Window
                    backpackIndex = 10;
                    Backpack();
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
        }else{ //When entering Bedroom1 from the Bathroom, presumably from Bedroom 2 earlier.
                String[] choicesBed1FromBathroom = {"Maybe you should check out the other [Doors] you passed...",
                "Oh boy, a [Rocking Chair]!.", 
                "Wonder what the view from the [Window] is like?"};

              //JOptionPane with String conversion from object type.
            String inputBed1FromBathroom = (String) JOptionPane.showInputDialog(null,"Welcome to the First 2nd-Floor Bedroom "
                    +namePC+
                    ". \nThe room is mostly barren, aside from a few pieces of furniture,\n"+
                    "including a [Rocking Chair] by the [Window]."
                    + "\nA [Door] leading to the hallway sways.",
                "The First 2nd Bedroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesBed1FromBathroom,
                choicesBed1FromBathroom[0]);
                 //Calls chosenDestination, which returns the index of the chosen location
                //after being passed in the returned string from JOptionPane,
                //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed1FromBathroom, inputBed1FromBathroom);
            switch(nextActionIndex){
                case 0: //Call stairs with argument 1 to show entering Stairs from Bedroom1
                    Stairs(1);
                    break;
                case 1://Backpack Index = rocking chair
                    backpackIndex = 9;
                    Backpack();
                    break;
                case 2://Backpack Index = Window
                    backpackIndex = 10;
                    Backpack();
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
        }
    }
    
    
    public void secondBedroom(boolean Location){
        boolean fromStairs = Location;
        if(fromStairs){ //If you enter Bedroom 2 from the stairs.
          String[] choicesBed2FromStairs = {"What's behind [Door] numba 32549783...",
            "Let's check out this totally inconspicuous [Doll House]!", 
            "Let's see what's in this [Dresser]."};

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
                case 0: //Bathroom with argument 2 to indicate from Bedroom 2
                    Bathroom(2);
                    break;
                case 1://Backpack Index = Doll House
                    backpackIndex = 11;
                    Backpack();
                    break;
                case 2://Backpack Index = Dresser
                    backpackIndex = 12;
                    Backpack();
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
        }else{ //When entering Bedroom2 from the Bathroom, presumably from Bedroom 1 earlier.
               String[] choicesBed2FromStairs = {"Let's go see the other [Doors] by the stairs...",
                 "Let's check out this totally inconspicuous [Doll House]!", 
                    "Let's see what's in this [Dresser]."};

              //JOptionPane with String conversion from object type.
            String inputBed2FromStairs = (String) JOptionPane.showInputDialog(null,"Welcome to the Second 2nd-Floor Bedroom "
                +namePC+
                ". \nYou find yourself in another, cold dark room.\n"+
                "A [Doll House] sits at the center, a barely standing [Dresser] against the wall."
                + "\nA [Door] leading to the hallway sways.",
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
                case 0: //Bathroom with argument 2 to indicate from Bedroom 2
                    Stairs(2);
                    break;
                case 1://Backpack Index = Doll House
                    backpackIndex = 11;
                    Backpack();
                    break;
                case 2://Backpack Index = Dresser
                    backpackIndex = 12;
                    Backpack();
                    break;
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
        }
    }
    
    //One bathroom function for the bathroom on the first and second floor,
    // with a location argument to signify if this is the bathroom
    // on the first floor, on the 2nd floor from the 1st bedroom or the 2nd floor from the 2nd bathroom
    public void Bathroom(int Location){
    
        int entryPoint = Location;
        
        if(entryPoint == 0){ //Bathroom on the 1st floor, Living Room.
              //String array of choices for the JOptionPane
            String[] choicesB1 = {"Turn to the [Mirror]...", "Maybe something is behind the [Shower] curtain...?"};
       
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
                default:
                    System.out.println("null value. Did you close the window? Program terminated.");
            }
            
            
        }else if(entryPoint == 1){//Bathroom on the second floor from the 1st bedroom
        
            //String array of choices for the JOptionPane
            String[] choicesBed1Bathroom = {"Go through the mysterious [Door]...", "Look to the [Mirror] and admire your reflection...",
                "Feeling a bit sweaty, consider a [Shower]..."};

            //JOptionPane with String conversion from object type.
            String inputBed1Bathroom = (String) JOptionPane.showInputDialog(null, "Welcome to the 2nd Floor Bathroom, " + namePC +
                ". \nThere's not even toilet paper in here. What horror!\n"
                        + "At least there's a [Mirror] and a [Shower]."
                        + "\nAcross the bathroom, another [Door]... but to where?",
                "The Second Floor Shared Bathroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesBed1Bathroom,
                choicesBed1Bathroom[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed1Bathroom, inputBed1Bathroom);
            switch(nextActionIndex){
                case 0: //Enter the second bedroom from the bathroom, using the false argument
                 secondBedroom(false);
                 break;
            case 1:
                //Backpackindex = Mirror
                backpackIndex = 7;
                livingroomMirror = false;
                Backpack();
                 break;
            case 2: 
                //Backpackindex = Shower
                backpackIndex = 8;
                 bathroomShower = 1;
                Backpack();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminated.");
            }
            
            
        }else if(entryPoint == 2){//Bathroom on the second floor from the 2nd bedroom
            
            //String array of choices for the JOptionPane
            String[] choicesBed2Bathroom = {"Go through the mysterious [Door]...", "Look to the [Mirror] and contemplate...",
                "Good time to take a tour of the [Shower]..."};

            //JOptionPane with String conversion from object type.
            String inputBed2Bathroom = (String) JOptionPane.showInputDialog(null, "Welcome to the 2nd Floor Bathroom, " + namePC +
                ". \nThere's not even toilet paper in here... or a toilet?\n"
                        + "Only a [Mirror] and a [Shower]. Better than nothing."
                        + "\nA [Door] across from you, where could it lead?",
                "The Second Floor Shared Bathroom",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesBed2Bathroom,
                choicesBed2Bathroom[0]);
            //Calls chosenDestination, which returns the index of the chosen location
            //after being passed in the returned string from JOptionPane,
            //and the string Array of choices
            int nextActionIndex = chosenDestination(choicesBed2Bathroom, inputBed2Bathroom);
            switch(nextActionIndex){
                case 0: //Enter the first bedroom from the bathroom, using the false argument
                 firstBedroom(false);
                 break;
            case 1:
                //Backpackindex = Mirror
                backpackIndex = 7;
                livingroomMirror = false;
                Backpack();
                 break;
            case 2: 
                //Backpackindex = Shower
                backpackIndex = 8;
                 bathroomShower = 1;
                Backpack();
                break;
            default:
                System.out.println("null value. Did you close the window? Program terminated.");
            }
        }else{System.out.println("Error. Default else case. Program terminates.");}
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
