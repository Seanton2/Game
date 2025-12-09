package ProfRoyale;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameUI {
    private int choice;

    public void setChoice(int choice){
        this.choice = choice;
    }
    public int getChoice(){
        return choice;
    }

    private final Scanner scan = new Scanner(System.in);

    public void Mainmenu(){
        displayBanner();
        displayMenuOptions();

        int num = 0;
        do{    
            try{
                num = scan.nextInt();
                switch(num) {
                    case 1 :
                        clearScreen();
                        displayBanner();
                        System.out.println();
                        System.out.print("        You have selected Player Vs Player               \n\n");
                        pressAnyKey();
                        clearScreen();
                        displayCharacterSelection();
                        startPvP();
                    break;
                    
                    
                    case 2 :
                        clearScreen();
                        displayBanner();
                        System.out.println();
                        System.out.print("       You have selected Player Vs Computer               \n\n");
                    
                        pressAnyKey();
                        clearScreen();
                        displayCharacterSelection();
                        startPvC();
                    break;
                    
                    case 3 :
                        clearScreen();
                        displayBanner();
                        about();
                    break;
                    
                    case 4  :
                        clearScreen();
                        System.out.println();
                        System.out.print("               Thank you for Playing!\n\n\n");
                        exitProgram();
                        System.exit(0);
                    break;
                    default : System.out.println("Input must be 1 - 4. Please try again.");
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 - 4.");
                scan.nextLine(); 
                num = 0;        
            }    
        }while(num < 1 || num > 4);
    }


    public void exitProgram() {
        System.exit(0);
    }

    public void clearScreen() {
        for(int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void pressAnyKey() {
        System.out.println("\n            Press Enter to continue...");
        try {
            scan.nextLine();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printLine(int width, char c) {
        for (int i = 0; i < width; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    private void printEmptyBlock(int width, int lines) {
        for (int rep = 0; rep < lines; rep++) {
            for (int i = 0; i <= width; i++) {
                if (i == 0) System.out.print("|");
                else if (i == width - 2) System.out.print("|");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void displayBanner() {
        int width = 50;

        printLine(width, '*');
        printEmptyBlock(width, 2);
        System.out.println("|               Professors Royale               |");
        printEmptyBlock(width, 2);
        printLine(width, '*');
    }

    public void displayMenuOptions() {
        System.out.println();
        System.out.println("               [1] Player vs Player");
        System.out.println("             [2] Player vs Computer");
        System.out.println("                    [3] About");
        System.out.println("                    [4] Quit");
    }

    public void displayCharacterSelection() {
        System.out.println();
        System.out.println("*******************  CHARACTER SELECT  *******************");
        System.out.println();
        System.out.println("   ┌────────────────────────┬────────────────────────┐");
        System.out.println("   │      Player Heroes     │     Rival Heroes       │");
        System.out.println("   ├────────────────────────┼────────────────────────┤");
        System.out.println("   │  [1] Lance             │  [5] Manolito          │");
        System.out.println("   │  [2] Khai              │  [6] Sarrah            │");
        System.out.println("   │  [3] Judy              │  [7] Sean              │");
        System.out.println("   │  [4] Cheryl            │  [8] Charles           │");
        System.out.println("   └────────────────────────┴────────────────────────┘");
        System.out.println();
    }

    public int setCharacterChoice(){
        try{
            choice = scan.nextInt();
            if (choice == 0) {
                System.out.println("Exiting the battle...");
                System.exit(0);
            }
            setChoice(choice);
            scan.nextLine();
        }catch(InputMismatchException e){
            System.out.println("Input must be a number from 1 - 8. Please try again.");
            scan.nextLine();
        }
        return choice;
    }

    public Characters chooseCharacter(int choice2) {
        try {

        if(choice2 < 1 || choice2 > 8) {
            System.out.println("Invalid choice! Please select a number from 1 to 8.");
            return null;
        }

        return switch(choice2) {
            case 1 -> new Lance();
            
            case 2 -> new Khai();
            case 3 -> new Judy();
            case 4 -> new Cheryl();
            case 5 -> new Manolito();
            case 6 -> new Sarrah();
            case 7 -> new Sean();
            case 8 -> new Charles();
            default -> null;
        };

    } catch(InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number (1-8).");
        return null;
    }
    }
    
    public void startPvP() {
        Characters player1 = null;
        Characters player2 = null;
    do{
        try{
            System.out.print("Player 1, choose your character (1-8): ");
            int choice = setCharacterChoice();
            player1 = chooseCharacter(choice);
            
            
        } catch(InputMismatchException e){
            System.out.println("Input must be a number from 1 - 8. Please try again.");
            scan.nextLine();
        }
    }while(player1 == null);

    do{
        try{
            System.out.print("Player 2, choose your character (1-8): ");
            int choice = setCharacterChoice();
            player2 = chooseCharacter(choice);
        } catch (InputMismatchException e){
            System.out.println("Input must be a number from 1 - 8. Please try again.");
            scan.nextLine();
        }
    } while (player2 == null);
    
        clearScreen();
        System.out.println("               " + player1.getName() + " Vs " + player2.getName() + "\n");

        pressAnyKey();
        clearScreen();

        Battle battle = new Battle(player1, player2);
        battle.start();
    }

    public void startPvC() {
        Characters player1 = null;
        do{
        try{
            System.out.print("Player 1, choose your character (1-8): ");
            int choice = setCharacterChoice();
            player1 = chooseCharacter(choice);
            
        } catch(InputMismatchException e){
            System.out.println("Input must be a number from 1 - 8. Please try again.");
            scan.nextLine();
        }
    }while(player1 == null);

        int aiChoice = (int)(Math.random() * 6) + 1;
        Characters player2 = chooseCharacter(aiChoice);
        player2.setComputer(true);
        clearScreen();

        System.out.println("        You've selected " + player1.getName());
        System.out.println("        Computer has selected " + player2.getName());

        pressAnyKey();
        clearScreen();

        System.out.println("               " + player1.getName() + " Vs " + player2.getName() + "\n");

        pressAnyKey();
        clearScreen();

        Battle battle = new Battle(player1, player2);
        battle.start();
    }

    public void about() {
        System.out.println("Professors Royale is a turn-based battle game where players select characters with unique skills to compete against each other or the computer.");
        System.out.println("Each character has different abilities, health, and shields that influence the outcome of battles.");
        System.out.println("Choose your character wisely and strategize your moves to emerge victorious!\n\n");
        

        System.out.println("\n                          Developed by:");
        System.out.println("                    Charles Brayden P. Sanchez");
        System.out.println("                     Sean Anthony P. Sanchez");
        System.out.println("                         Eleonora Sayson");
        System.out.println("                         3Clarence Espejon");
        System.out.println("                         Ralph Empeynado");


        pressAnyKey();
        Mainmenu();
    }
}
