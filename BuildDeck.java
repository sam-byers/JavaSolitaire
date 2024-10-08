
/**
 * The BuildDeck class extends the Deck class and represents one of the 4 'build' decks that the player can place cards on.
 */
public class BuildDeck extends Deck {

    // String to store the suit of the build deck
    String assosiatedSuit;

    /**
     * Constructor for the BuildDeck class.
     *
     * @param suite The suit associated with the deck.
     */
    public BuildDeck(String suite) {
        super();
        this.assosiatedSuit = suite;
    }

    /**
     * Plays a card into the deck if it matches the associated suit and follows
     * the correct rank order.
     *
     * @param cardIn The card to be played.
     * @return The card played, or null if the card was successfully added to
     * the deck.
     */
    public Card playCard(Card cardIn) {
        String currentRank;
        if (this.deck.isEmpty()) {
            currentRank = "EMPTY"; // If the deck is empty, the current rank is set to "EMPTY"
        } else {
            currentRank = this.deck.getLast().getRank(); // Otherwise, the current rank is set to the rank of the last card in the deck
        }
        if (cardIn.getSuit().equals(this.assosiatedSuit)) { // If the card's suit matches the associated suit of the deck
            switch (currentRank) { // Switch statement to check if the card's rank follows the correct order
                case "EMPTY" -> {
                    if (cardIn.getRank().equals(" A")) { // If the deck is empty, the card must be an Ace
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " A" -> {
                    if (cardIn.getRank().equals(" 2")) { // If the current card is an Ace, the next card must be a 2
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 2" -> {
                    if (cardIn.getRank().equals(" 3")) { // If the current card is a 2, the next card must be a 3
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 3" -> {
                    if (cardIn.getRank().equals(" 4")) { //... and so on
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 4" -> {
                    if (cardIn.getRank().equals(" 5")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 5" -> {
                    if (cardIn.getRank().equals(" 6")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 6" -> {
                    if (cardIn.getRank().equals(" 7")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 7" -> {
                    if (cardIn.getRank().equals(" 8")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 8" -> {
                    if (cardIn.getRank().equals(" 9")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 9" -> {
                    if (cardIn.getRank().equals("10")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case "10" -> {
                    if (cardIn.getRank().equals(" J")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " J" -> {
                    if (cardIn.getRank().equals(" Q")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " Q" -> {
                    if (cardIn.getRank().equals(" K")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                default -> {
                    return cardIn; // If the card does not follow the correct order, the card is returned
                }
            }
        }
        return cardIn; // If the card does not match the associated suit, the card is returned
    }

    /**
     * Method to get the size of the deck.
     *
     * @return The size of the deck.
     */
    public int getDeckSize() { // Method to get the size of the deck
        return this.deck.size();
    }

}
