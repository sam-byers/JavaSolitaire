import java.util.Scanner;

public class UserInputScanner {

    public String[] makeMove(Scanner scanner) {
        System.out.println("What is your move (source destination nCards)? or q to quit, and De to draw 3 cards.");
        System.out.println("Take cards from De, Di, A, B, C, D, E, F, G");
        System.out.println("Place card in A, B, C, D, E, F, G or in Heart, Diam, Spade or Club?");
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            String move[] = new String[3];
            
            String[] moveBuff = userInput.split(" ");
            if (moveBuff.length > 3) {
                System.out.println("Invalid number of arguments, all arguments after the third will be ignored.");
                
            }
            for (int i = 0; i < 3 & i < moveBuff.length; i++) {
                move[i] = moveBuff[i];
            }

            switch (moveBuff.length) {
                case 1:
                    if (move[0].equals("q")) {
                        return move;
                    }
                    if (move[0].equals("de")) {
                        return move;
                    }
                    System.out.println("Invalid number of arguments.");
                    return null;
                case 2:
                    move[2] = "Choose";
                    return move;
                case 3:
                    return move;
                default:
                    System.out.println("Invalid number of arguments.");
                    return null;
            }

        } else {
            System.out.println("No input available.");
            return null;
        }
    }

    
    public int pickIndex(Scanner scanner, Deck deck) {
        System.out.println("Which card do you want to pick? Enter the index:");
        for (Card card : deck.deck) {
            if (card.isShown()) {
                System.out.println(card.getRank() + " of " + card.getSuit() + " at index " + deck.deck.indexOf(card));
            }
        }
        if (scanner.hasNextLine()) {
            int userInput = scanner.nextInt();
            if (!(userInput < deck.deck.size())) {
                System.out.println("Invalid Index.");
                return -1;
            }
            if (!deck.deck.get(userInput).isShown()) {
                System.out.println("Invalid Index.");
                return -1;
            }
            System.out.println(
                    "You chose: " + deck.deck.get(userInput).getRank() + " of " + deck.deck.get(userInput).getSuit());
            return userInput;
        } else {
            System.out.println("No input available.");
            return -1;
        }
    }

    public int loadIndx(String inxString, Deck deck) {

        int userInput = Integer.parseInt(inxString);

        userInput = deck.deck.size() - userInput;

        if (!(userInput < deck.deck.size())) {
            System.out.println("Invalid Index.");
            return -1;
        }
        if (!deck.deck.get(userInput).isShown()) {
            System.out.println("Invalid Index.");
            return -1;
        }
        System.out.println(
                "You chose: " + deck.deck.get(userInput).getRank() + " of " + deck.deck.get(userInput).getSuit());
        return userInput;
    }
}