public class DiscardDeck extends Deck{
    
    public DiscardDeck() { // Constructor for DiscardDeck class
        super();
    }

    public void addCards(Deck deckIn) { // Method to add cards to the discard deck
        if (!this.deck.isEmpty()) {
            this.deck.getLast().under();
        }
        for(int i = 0; i < 3 && !deckIn.deck.isEmpty(); i++){
            Card inCard = deckIn.takeCard();
            if (inCard != null) {
                this.deck.add(inCard);
                this.deck.getLast().show();
                this.deck.getLast().under();
            }
        }
        this.deck.getLast().over();
    }
}
