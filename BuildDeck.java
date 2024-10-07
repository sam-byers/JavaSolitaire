public class BuildDeck extends Deck {

    String assosiatedSuit;

    public BuildDeck(String suite) { // Constructor for BuildDeck class
        super();
        this.assosiatedSuit = suite;
    }

    public Card playCard(Card cardIn) {
        String currentRank;
        if (this.deck.isEmpty()) {
            currentRank = "EMPTY";
        } else {
            currentRank = this.deck.getLast().getRank();
        }
        if (cardIn.getSuit().equals(this.assosiatedSuit)) {
            switch (currentRank) {
                case "EMPTY" -> {
                    if (cardIn.getRank().equals(" A")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " A" -> {
                    if (cardIn.getRank().equals(" 2")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 2" -> {
                    if (cardIn.getRank().equals(" 3")) {
                        this.deck.add(cardIn);
                        return null;
                    }
                    break;
                }
                case " 3" -> {
                    if (cardIn.getRank().equals(" 4")) {
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
                    return cardIn;
                }
            }
        }
        return cardIn;
    }

    public int getDeckSize() {
        return this.deck.size();
    }

}
