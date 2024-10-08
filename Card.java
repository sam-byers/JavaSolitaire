/**
 * Card class which represents a given card, this is never extended on but is stored in array lists in each deck class
 * and is used to represent a card in the game of solitaire.
 * */
public class Card {
    private final String rank; // String to store the rank of the card, FINAL because it should not be changed
    private final String suit; // String to store the suit of the card, FINAL because it should not be changed
    private final int numValue;  // Integer to store the numerical value of the card, FINAL because it should not be changed
    private Boolean isShown; // Boolean to check if the card is shown
    private Boolean isUnder; // Boolean to check if the card is under another card

    /**
     * Constructor for the Card class.
     *
     * @param rank The rank of the card.
     * @param suit The suit of the card.
     */
    public Card(String rank, String suit) {
        this.rank = rank; // Set the rank of the card
        this.suit = suit; // Set the suit of the card
        this.numValue = switch (rank) { // Switch statement to set the numerical value of the card
            case " A" -> 1;
            case " 2" -> 2;
            case " 3" -> 3;
            case " 4" -> 4;
            case " 5" -> 5;
            case " 6" -> 6;
            case " 7" -> 7;
            case " 8" -> 8;
            case " 9" -> 9;
            case "10" -> 10;
            case " J" -> 11;
            case " Q" -> 12;
            case " K" -> 13;
            default -> 0;
        };
        this.isShown = false; // Set the card to be hidden by default
        this.isUnder = true; // Set the card to be under another card by default
    }


    /**
     * Getter for the rank of the card.
     * @return The rank of the card.
     */
    public String getRank() {
        return rank;
    }
    /**
     * Getter for the suit of the card.
     * @return The suit of the card.
     */
    public String getSuit() {
        return suit;
    }
    /**
     * Getter for the numerical value of the card.
     * @return The numerical value of the card.
     */
    public int getNumValue() {
        return numValue;
    }

    /**
     * Getter for the isShown boolean. This is used to check if the card is shown or not, 
     * which determines how it is rendered as well as game logic for what card can be moved.
     * @return The isShown boolean.
     */
    public Boolean isShown() {
        return isShown;
    }

    /**
     * Getter for the isUnder boolean. This is used to check if the card is under another card or not, 
     * which determines how it is rendered.
     * @return The isUnder boolean.
     */
    public Boolean isUnder() {
        return isUnder;
    }

    /**
     * Setter for the isShown boolean. This only sets true for code readability. This is used to set the card to be shown, 
     * which determines how it is rendered as well as game logic for what card can be moved.
     * @param isShown The boolean to set isShown to.
     */
    public void show() {
        isShown = true;
    }

    /**
     * Setter for the isShown boolean. This only sets false for code readability. This is used to set the card to be not shown,
     * which determines how it is rendered as well as game logic for what card can be moved.
     */
    public void hide() {
        isShown = false;
    } 
    
    /**
     * Setter for the isUnder boolean. This only sets false for code readability. This is used to set the card to be over another.
     */
    public void over() {
        isUnder = false;
    }
    
    /**
     * Setter for the isUnder boolean. This only sets true for code readability. This is used to set the card to be under another.
     */
    public void under() {
        isUnder = true;
    }

}