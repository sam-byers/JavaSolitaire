/**
 *  Table Deck class extends Deck and represents one of the 7 piles of cards on the playtable
 */
public class TableDeck extends Deck {

    boolean hasStack = false; // Boolean to check if the deck has a stack of cards


    /**
     * Constructor for TableDeck class
     */
    public TableDeck() {
        super();
    }

    /**
     * Method to fill the deck with a number of cards from a deck object.
     * @param nCards The number of cards to fill the deck with.
     * @param deckIn The deck object to take the cards from.
     */
    public void initalFill(int nCards, Deck deckIn) {
        for (int i = 0; i < nCards; i++) { // Loop to fill the deck with the specified number of cards
            Card inCard = deckIn.takeCard(); // Take a card from the deck
            if (inCard != null) { // If the card is not null
                this.deck.add(inCard); // Add the card to the deck
                this.deck.getLast().under(); // Set the card to be under
                this.deck.getLast().hide(); // Hide the card
            }
        }
        this.deck.getLast().over(); // Set the last card to be over
        this.deck.getLast().show(); // Show the last card
    }

    /**
     * Method to reveal the top card of the deck and check if there is a stack of cards.
     */
    public void revealCardCheck() {
        int count = 0; // Integer to count the number of cards shown
        if (!this.deck.isEmpty()) { // If the deck is not empty
            for (Card card : this.deck) { // Loop through the deck
                card.under(); // Set the card to be under
                if (card.isShown()) { // If the card is shown
                    count++; // Increment the count
                }
            }
            this.deck.getLast().over(); // Set the last card to be over
            if (!this.deck.getLast().isShown()) { // If the last card is not shown
                this.deck.getLast().show(); // Show the last card, as this should always be the case
            }
            this.hasStack = count > 1; // Set the hasStack boolean to true if the count is greater than 1
        }
    }

    /**
     * Method to grab a stack of cards from the deck.
     * @param indx The index of the card to grab the stack from.
     * @return The stack of cards as a Deck object.
     */
    public Deck grabStack(int indx) {
        Deck stack = new Deck(); // Create a new deck object to store the stack
        int deckSize = this.deck.size(); // Get the size of the deck
        for (int i = indx; i < deckSize; i++) { // Loop through the deck from the index to the end
            stack.deck.add(this.deck.get(i)); // Add the card to the stack
        }
        for (int i = deckSize - 1; i >= indx; i--) { // Loop through the deck from the end to the index
            this.deck.remove(i); // Remove the card from the deck
        }
        return stack; // Return the stack
    }

    /**
     * Method to place a stack of cards onto the deck.
     * @param stack The stack of cards to place.
     * @return The stack of cards.
     */
    public Deck placeStack(Deck stack) {
        if (stack.deck.isEmpty()) { // If the stack is empty
            return stack; // Return the stack (faliure misrepresneted as success but no effect)
        }
        if (this.deck.isEmpty() & stack.deck.getFirst().getRank().equals(" K")) { // If the deck is empty and the stack starts with a King
            for (Card card : stack.deck) { // Loop through the stack
                this.playCard(card); // Play the card (Placing the cards while checking order and isShown status)
            }
            stack.deck.clear(); // Clear the stack (success)
            return stack; // Return the stack
        } else if (this.playCard(stack.deck.getFirst()) == null) { // If the card can be played
            for (Card card : stack.deck) { // Loop through the stack
                this.playCard(card); // Play the card (Placing the cards while checking order and isShown status)
            }
            stack.deck.clear(); // Clear the stack (success)
            return stack; // Return the stack
        } else {
            return stack; // Return the stack (failure) 
        }
    }

    /**
     * Method to play a card onto the deck.
     * @param cardIn The card to play.
     * @return The card played, or null if the card was successfully added to the deck.
     */
    public Card playCard(Card cardIn) {
        if (this.deck.isEmpty() && cardIn.getRank().equals(" K")) { // If the deck is empty and the card is a King
            this.deck.add(cardIn); // Add the card to the deck
            this.deck.getLast().over(); // Set the card to be over
            this.deck.getLast().show(); // Show the card
            return null; // Return null (success)
        } else if (!this.deck.isEmpty() && (this.deck.getLast().getNumValue() - 1 == cardIn.getNumValue() && (((this.deck.getLast().getSuit().equals("S") || this.deck.getLast().getSuit().equals("C")) && (cardIn.getSuit().equals("H") || cardIn.getSuit().equals("D"))) || ((this.deck.getLast().getSuit().equals("H") || this.deck.getLast().getSuit().equals("D")) && (cardIn.getSuit().equals("S") || cardIn.getSuit().equals("C")))))) { // If the card follows the correct order and the suits are alternating
            this.deck.getLast().under(); // Set the last card to be under
            this.deck.add(cardIn); // Add the card to the deck
            this.deck.getLast().over();  // Set the card to be over
            this.deck.getLast().show(); // Show the card
            return null; // Return null (success)
        } else {  // If the card does not follow the correct order
            return cardIn; // Return the card (failure)
        }
    }

}
