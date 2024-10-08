
import java.util.Scanner;

/**
 *  User Input Scanner class handles all user inputs into the program
 */
public class UserInputScanner {

    /**
     * Method to get the user input for the move. The user can input the source
     * stack, destination stack and the number of cards to move.
     *
     * @param scanner The scanner object to get the user input.
     * @return The user input as a string array.
     */
    public String[] makeMove(Scanner scanner) {
        System.out.println("What is your move (source destination nCards)? or q to quit, and De to draw 3 cards.");
        System.out.println("Take cards from De, Di, A, B, C, D, E, F, G");
        System.out.println("Place card in A, B, C, D, E, F, G or in Heart, Diam, Spade or Club?");
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            while (userInput.isEmpty()) { // If the user input is empty, ask for input again
                userInput = scanner.nextLine();
            }
            userInput = userInput.toLowerCase(); // Convert the user input to lowercase 
            String move[] = new String[3]; // Create a string array to store the move

            String[] moveBuff = userInput.split(" "); // Split the user input into an array of strings
            if (moveBuff.length > 3) { // If the user input has more than 3 arguments, print an error message
                System.out.println("Invalid number of arguments, all arguments after the third will be ignored.");

            }
            for (int i = 0; i < 3 & i < moveBuff.length; i++) { // Copy the first 3 arguments into the move array
                move[i] = moveBuff[i];
            }

            switch (moveBuff.length) { // Check the number of arguments in the user input
                case 1 -> {
                    // If there is only one argument
                    if (move[0].equals("q")) { // If the user input is 'q', return the move array
                        return move;
                    }
                    if (move[0].equals("de")) { // if the user input is 'de', no other arguments are needed, return the move array
                        return move;
                    }
                    System.out.println("Invalid number of arguments."); // If the user input is not 'q' or 'de', print an error message
                    return null; // Return null
                }
                case 2 -> {
                    // If there are two arguments
                    move[2] = "Choose"; // Set the third argument to "Choose", this will be used to prompt the user to choose the number of cards to move
                    return move; // Return the move array
                }
                case 3 -> {
                    // If there are three arguments
                    return move; // Return the move array
                }
                default -> {
                    // If there are more than three arguments
                    System.out.println("Invalid number of arguments."); // Print an error message
                    return null; // Return null
                }
            }
            // Check the number of arguments in the user input
            
        } else { // If there is no user input
            System.out.println("No input available."); // Print an error message
            return null; // Return null
        }
    }

    /**
     * Method to get the user input for the number of cards to move (index).
     *
     * @param scanner The scanner object to get the user input.
     * @param deck The deck object from which the cards are to be moved from.
     * @return The number of cards to move as an integer.
     *
     */
    public int pickIndex(Scanner scanner, Deck deck) {
        System.out.println("Which card do you want to pick? Enter the index:");
        for (Card card : deck.deck) { // Loop through the deck
            if (card.isShown()) { // If the card is shown (face up)
                System.out.println(card.getRank() + " of " + card.getSuit() + " at index " + deck.deck.indexOf(card)); // Print the card's rank, suit and index
            }
        }
        if (scanner.hasNextLine()) { // If there is user input
            int userInput = scanner.nextInt(); // Get the user input as an integer
            if (!(userInput < deck.deck.size())) { // If the user input is greater than the size of the deck
                System.out.println("Invalid Index."); // Print an error message
                return -1;
            }
            if (!deck.deck.get(userInput).isShown()) { // If the card at the user input index is not shown
                System.out.println("Invalid Index."); // Print an error message
                return -1;
            }
            // otherwise, print the card's rank and suit
            System.out.println("You chose: " + deck.deck.get(userInput).getRank() + " of " + deck.deck.get(userInput).getSuit()); 
            return userInput; // Return the user input
        } else {
            System.out.println("No input available."); // If there is no user input, print an error message
            return -1;
        }
    }

    /**
     * Method to use the previous user input to check and return the index of the card that the user wants to move.
     *
     * @param inxString The user input as a string. (Number of cards to move)
     * @param deck The deck object from which the card is to be loaded from.
     * @return The index of the card to load as an integer.
     */
    public int loadIndx(String inxString, Deck deck) {

        int userInput = Integer.parseInt(inxString); // Convert the user input to an integer

        userInput = deck.deck.size() - userInput; // Subtract the user input from the size of the deck

        if (!(userInput < deck.deck.size())) { // If the user input is greater than the size of the deck
            System.out.println("Invalid Index."); // Print an error message
            return -1;
        }
        if (!deck.deck.get(userInput).isShown()) { // If the card at the user input index is not shown
            System.out.println("Invalid Index."); // Print an error message
            return -1;
        }
        // otherwise, print the card's rank and suit
        System.out.println(
                "You chose: " + deck.deck.get(userInput).getRank() + " of " + deck.deck.get(userInput).getSuit());
        return userInput; // Return the user input
    }
}
