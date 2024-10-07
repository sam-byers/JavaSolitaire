public class TableDeck extends Deck {
    boolean hasStack = false;
    public TableDeck() { // Constructor for TableDeck class
        super();
    }

    public void initalFill(int nCards, Deck deckIn) {
        for (int i = 0; i < nCards; i++) {
            Card inCard = deckIn.takeCard();
            if (inCard != null) {
                this.deck.add(inCard);
                this.deck.getLast().under();
                this.deck.getLast().hide();
            }
        }
        this.deck.getLast().over();
        this.deck.getLast().show();
    }

    public void revealCardCheck() {
        int count = 0;
        if (!this.deck.isEmpty()) {
            for (Card card : this.deck) {
                card.under();
                if (card.isShown()) {
                    count++;
                }
            }
            this.deck.getLast().over();
            if (!this.deck.getLast().isShown()) {
                this.deck.getLast().show();
            }
            this.hasStack = count > 1;
        }
    }

    public Deck grabStack(int indx) {
        Deck stack = new Deck();
        int deckSize = this.deck.size();
        for (int i = indx; i < deckSize; i++) {
            stack.deck.add(this.deck.get(i));
        }
        for (int i = deckSize - 1; i >= indx; i--) {
            this.deck.remove(i);
        }
        return stack;
    }

    public Deck placeStack(Deck stack) {
        if (stack.deck.isEmpty()){
            return stack;
        }
        if (this.deck.isEmpty() & stack.deck.getFirst().getRank().equals(" K")) {
            for (Card card : stack.deck) {
                this.playCard(card);
            }
            stack.deck.clear();
            return stack;
        }
        else if (this.playCard(stack.deck.getFirst()) == null) {
            for (Card card : stack.deck) {
                this.playCard(card);
            }
            stack.deck.clear();
            return stack;
        }
        else {
            return stack;
        }
    }

    public Card playCard(Card cardIn) {
        if (this.deck.isEmpty() && cardIn.getRank().equals(" K")) {
            this.deck.add(cardIn);
            this.deck.getLast().over();
            this.deck.getLast().show();
            return null;
        } else if (!this.deck.isEmpty() &&(this.deck.getLast().getNumValue() - 1 == cardIn.getNumValue() && (((this.deck.getLast().getSuit().equals("S") || this.deck.getLast().getSuit().equals("C")) && (cardIn.getSuit().equals("H") || cardIn.getSuit().equals("D")))||((this.deck.getLast().getSuit().equals("H") || this.deck.getLast().getSuit().equals("D")) && (cardIn.getSuit().equals("S") || cardIn.getSuit().equals("C")))))) {
            this.deck.getLast().under();
            this.deck.add(cardIn);
            this.deck.getLast().over();
            this.deck.getLast().show();
            return null;
        } else {
            return cardIn;
        }
    }
    
}