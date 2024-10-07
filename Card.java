public class Card {
    private final String rank;
    private final String suit;
    private final int numValue;
    private Boolean isShown;
    private Boolean isUnder;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        this.numValue = switch (rank) {
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
        this.isShown = false;
        this.isUnder = true;
    }


    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getNumValue() {
        return numValue;
    }

    public Boolean isShown() {
        return isShown;
    }

    public Boolean isUnder() {
        return isUnder;
    }


    public void show() {
        isShown = true;
    }

    public void hide() {
        isShown = false;
    } 
    
    public void over() {
        isUnder = false;
    }
    
    public void under() {
        isUnder = true;
    }

}