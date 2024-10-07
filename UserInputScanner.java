import java.util.Scanner;

public class UserInputScanner {
    public String pickCard(Scanner scanner) {
        System.out.println("Take cards from De, Di, A, B, C, D, E, F, G");
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            System.out.println("You chose: " + userInput);
            return userInput;
        } else {
            System.out.println("No input available.");
            return null;
        }
    }
    public String placeCard(Scanner scanner, boolean isStack) {
        if (isStack) {
            System.out.println("Place cards in A, B, C, D, E, F, G");
        } else {
            System.out.println("Place card in A, B, C, D, E, F, G or in Heart, Diam, Spade or Club?");
        }

        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            while (userInput.equals("\n") | userInput.equals("")){
                userInput = scanner.nextLine();
            }
            System.out.println("You chose: " + userInput);
            return userInput;
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
            if (!(userInput < deck.deck.size())){
                System.out.println("Invalid Index.");
                return -1;
            }
            if(!deck.deck.get(userInput).isShown()) {
                System.out.println("Invalid Index.");
                return -1;
            }
            System.out.println("You chose: " + deck.deck.get(userInput).getRank() + " of " + deck.deck.get(userInput).getSuit());
            return userInput;
        } else {
            System.out.println("No input available.");
            return -1;
        }
    }
}