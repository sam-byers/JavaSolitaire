/**
 * DiscardDeck class extends Deck and represents the 'draw' pile 
 */
public class DiscardDeck extends Deck{
    
    /**
     * Constructor for DiscardDeck class
     */
    public DiscardDeck() {
        super();
    }

    /**
     * addCards method to add cards to the discard deck from the draw pile.
     * @param deckIn The deck object to take the cards from.
     */
    public void addCards(Deck deckIn) { // Method to add cards to the discard deck
        if (!this.deck.isEmpty()) { // If the deck is not empty
            this.deck.getLast().under(); // Set the last card to be under
        }
        for(int i = 0; i < 3 && !deckIn.deck.isEmpty(); i++){ // Loop to add 3 cards to the discard deck
            Card inCard = deckIn.takeCard(); // Take a card from the deck 
            if (inCard != null) { // If the card is not null
                this.deck.add(inCard); // Add the card to the discard deck
                this.deck.getLast().show(); // Show the card
                this.deck.getLast().under(); // Set the card to be under
            }
        }
        this.deck.getLast().over(); // Set the last card to be over
    }
}
