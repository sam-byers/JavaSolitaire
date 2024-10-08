
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck class represents a deck of cards. It contains an ArrayList of Card
 * objects and methods to shuffle the deck, take a card from the deck, add a card
 * to the deck, and refill the deck. All piles in the game are represented by supers of this class or this class itself.
 */
public class Deck {

    ArrayList<Card> deck = new ArrayList<>(); // ArrayList to store the cards in the deck

    /**
     * A constructor for the Deck class that creates a deck with all 52 cards.
     */
    public void defineCards() {
        for (String suit : new String[]{"H", "D", "C", "S"}) { // Loop through the suits
            for (String rank : new String[]{" 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", " J", " Q", " K", " A"}) { // Loop through the ranks
                deck.add(new Card(rank, suit)); // Add a new card to the deck
            }
        }
    }

    /**
     * Method to shuffle the deck.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck); // Shuffle the deck using the Collections java utility
        deck.getLast().over(); // Set the last card to be over
    }

    
    /**
     * Method to take the last (top) card from the deck.
     * @return The card taken from the deck.
     */
    public Card takeCard() {
        if (!deck.isEmpty()) { // If the deck is not empty
            Card topCard = deck.getLast(); // Get the last card in the deck
            deck.removeLast(); // Remove the last card from the deck
            if (!deck.isEmpty()) { // If the deck is not empty
                deck.getLast().over(); // Set the last card to be over
            }
            return topCard; // Return the last card
        } else {
            return null; // Return null if the deck is empty
        }
    }

    /**
     * Method to add a card to the top of the deck.
     * @param cardIn The card to add to the deck.
     */
    public void addCard(Card cardIn) { 
        if (!deck.isEmpty()) { // If the deck is not empty
            deck.getLast().under(); // Set the last card to be under
        }
        deck.add(cardIn); // Add the card to the deck
        deck.getLast().over(); // Set the card to be over
    }

    /**
     * Method to refill the deck with the cards from another deck.
     * This is used to refill the draw pile from the discard pile.
     * @param deckIn The deck to take the cards from.
     */
    public void refillDeck(Deck deckIn) {
            for (Card card : deckIn.deck) { // For each card in the deck
            card.under(); // Set the card to be under
            card.hide(); // Hide the card
            this.deck.add(card); // Add the card to the deck
        }
        Collections.reverse(this.deck); // Reverse the deck using the Collections java utility
        this.deck.getLast().over(); // Set the last card to be over
    }

    /**
     * Method to print the deck. This takes a line number as an argument and returns the string to be printed.
     * It prints the cards as nCards*3 lines, which can be selected by the line number. Therefore, by calling this method, and indexing the line number
     * a sufficient number of times, the entire deck can be printed.
     * @param line The line number to print.
     * @return A string representation of one of the lines making up one of the cards in the deck
     */
    public String printDeck(int line) {
        String output = ""; // String to store the output
        if (deck.size() > line / 3) { // If the deck has more cards than the line number has selected
            Card card = deck.get(line / 3); // Get the card at the index of the line number divided by 3
            if (card.isShown()) { // If the card is shown
                if (card.isUnder()) { // If the card is under
                    switch (line % 3) { // Switch statement to print the card by using modulo 3, devide the card into 3 lines
                        case 0 -> // If the line number is 0
                            output = "+-----+"; // Print the top of the card
                        case 1 -> // If the line number is 1
                            output = "|-" + card.getRank() + card.getSuit() + "-|"; // Print the rank and suit of the card
                        case 2 -> // If the line number is 2
                            output = "       "; // Print an empty line as a card is 'over' this card as is 'under'
                    }
                } else { // If the card is not under
                    switch (line % 3) { // Switch statement to print the card by using modulo 3, devide the card into 3 lines
                        case 0 -> // If the line number is 0
                            output = "+-----+"; // Print the top of the card
                        case 1 -> // If the line number is 1
                            output = "|-" + card.getRank() + card.getSuit() + "-|"; // Print the rank and suit of the card
                        case 2 -> // If the line number is 2
                            output = "+-----+"; // Print the bottom of the card
                    }
                }
            } else { // If the card is not shown and is under
                if (card.isUnder()) { // If the card is under
                    switch (line % 3) { 
                        case 0 -> 
                            output = "+-----+"; // Print the top of the card
                        case 1 ->
                            output = "|-xxx-|"; // Print the card as 'xxx' as its the back of the card
                        case 2 ->
                            output = "       "; // Print an empty line as a card is 'over' this card as is 'under'
                    }
                } else { // If the card is not under but is not shown
                    switch (line % 3) {
                        case 0 ->
                            output = "+-----+"; // Print the top of the card
                        case 1 ->
                            output = "|-xxx-|"; // Print the card as 'xxx' as its the back of the card
                        case 2 ->
                            output = "+-----+"; // Print the bottom of the card
                    }
                }
            }
        } else {
            output = "       "; // If we have run out of cards to print, print an empty line to keep the formatting
        }
        return output; // Return the output
    }

    /**
     * Method to print the top card of the deck. This takes a line number as an argument and returns the string to be printed.
     * It prints the top card as 3 lines, which can be selected by the line number. This is used to render the top card of the deck in the game for the build piles.
     * @param line The line number to print.
     * @return A string representation of one of the lines making up the top card in the deck.
     */
    public String printTopCard(int line) {
        String output = ""; // String to store the output
        if (deck.isEmpty()) { // If the deck is empty
            output = "       "; // Print an empty line as this will always be the case for an empty deck
            return output; // Return the output
        }
        Card card = deck.getLast(); // Get the last card in the deck
        if (line < 3) { // If the line number is less than 3
            if (card.isShown()) { // If the card is shown
                if (card.isUnder()) { // If the card is under
                    switch (line) { // Switch statement to print the card by using the line number
                        case 0 -> {
                            output = "+-----+"; // Print the top of the card
                        }
                        case 1 -> {
                            output = "|-" + card.getRank() + card.getSuit() + "-|"; // Print the rank and suit of the card
                        }
                        case 2 -> {
                            output = ""; // Print an empty line as the card is 'under', this should never happen but is included for visual identification of a hypothetical bug
                        }
                    }
                } else { // If the card is not under (Should always be the case)
                    switch (line) { // Switch statement to print the card by using the line number
                        case 0 -> {
                            output = "+-----+"; // Print the top of the card
                        }
                        case 1 -> {
                            output = "|-" + card.getRank() + card.getSuit() + "-|"; // Print the rank and suit of the card
                        }
                        case 2 -> {
                            output = "+-----+"; // Print the bottom of the card
                        }
                    }
                }
            } else { // If the card is not shown
                if (card.isUnder()) {  // If the card is under (Should never be the case)
                    switch (line) {
                        case 0 -> {
                            output = "+-----+"; // Print the top of the card
                        }
                        case 1 -> {
                            output = "|-xxx-|"; // Print the card as 'xxx' as its the back of the card
                        }
                        case 2 -> {
                            output = ""; // Print an empty line as the card is 'under', this should never happen but is included for visual identification of a hypothetical bug
                        }
                    }
                } else { // If the card is not under but is not shown (Should always be the case if the card is not shown)
                    switch (line) { 
                        case 0 -> {
                            output = "+-----+"; // Print the top of the card
                        }
                        case 1 -> {
                            output = "|-xxx-|"; // Print the card as 'xxx' as its the back of the card
                        }
                        case 2 -> {
                            output = "+-----+"; // Print the bottom of the card
                        }
                    }
                }
            }
        } else { // If the line number is greater than 3
            output = "";  // Print an empty line so that the formatting is maintained
        }

        return output; // Return the output
    }
}
