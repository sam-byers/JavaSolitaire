
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    ArrayList<Card> deck = new ArrayList<>();

    public void defineCards() {
        for (String suit : new String[]{"H", "D", "C", "S"}) {
            for (String rank : new String[]{" 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", " J", " Q", " K", " A"}) {
                deck.add(new Card(rank, suit)); // Add a new card to the deck
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
        deck.getLast().over();
    }

    
    public Card takeCard() {
        if (!deck.isEmpty()) {
            Card topCard = deck.getLast();
            deck.removeLast();
            if (!deck.isEmpty()) {
                deck.getLast().over();
            }
            return topCard;
        } else {
            return null;
        }
    }

    public void addCard(Card cardIn) {
        if (!deck.isEmpty()) {
            deck.getLast().under();
        }
        deck.add(cardIn);
        deck.getLast().over();
    }

    public void refillDeck(Deck deckIn) {
            for (Card card : deckIn.deck) {
            card.under();
            card.hide();
            this.deck.add(card);
        }
        Collections.reverse(this.deck);

        this.deck.getLast().over();
    }

    public String printDeck(int line) {
        String output = "";
        if (deck.size() > line / 3) {
            Card card = deck.get(line / 3);
            if (card.isShown()) {
                if (card.isUnder()) {
                    switch (line % 3) {
                        case 0 ->
                            output = "+-----+";
                        case 1 ->
                            output = "|-" + card.getRank() + card.getSuit() + "-|";
                        case 2 ->
                            output = "       ";
                    }
                } else {
                    switch (line % 3) {
                        case 0 ->
                            output = "+-----+";
                        case 1 ->
                            output = "|-" + card.getRank() + card.getSuit() + "-|";
                        case 2 ->
                            output = "+-----+";
                    }
                }
            } else {
                if (card.isUnder()) {
                    switch (line % 3) {
                        case 0 ->
                            output = "+-----+";
                        case 1 ->
                            output = "|-xxx-|";
                        case 2 ->
                            output = "       ";
                    }
                } else {
                    switch (line % 3) {
                        case 0 ->
                            output = "+-----+";
                        case 1 ->
                            output = "|-xxx-|";
                        case 2 ->
                            output = "+-----+";
                    }
                }
            }
        } else {
            output = "       ";
        }
        return output;
    }

    public String printTopCard(int line) {
        String output = "";
        if (deck.isEmpty()) {
            output = "       ";
            return output;
        }
        Card card = deck.getLast();
        if (line < 3) {
            if (card.isShown()) {
                if (card.isUnder()) {
                    switch (line) {
                        case 0 -> {
                            output = "+-----+";
                        }
                        case 1 -> {
                            output = "|-" + card.getRank() + card.getSuit() + "-|";
                        }
                        case 2 -> {
                            output = "";
                        }
                    }
                } else {
                    switch (line) {
                        case 0 -> {
                            output = "+-----+";
                        }
                        case 1 -> {
                            output = "|-" + card.getRank() + card.getSuit() + "-|";
                        }
                        case 2 -> {
                            output = "+-----+";
                        }
                    }
                }
            } else {
                if (card.isUnder()) {
                    switch (line) {
                        case 0 -> {
                            output = "+-----+";
                        }
                        case 1 -> {
                            output = "|-xxx-|";
                        }
                        case 2 -> {
                            output = "";
                        }
                    }
                } else {
                    switch (line) {
                        case 0 -> {
                            output = "+-----+";
                        }
                        case 1 -> {
                            output = "|-xxx-|";
                        }
                        case 2 -> {
                            output = "+-----+";
                        }
                    }
                }
            }
        } else {
            output = "";
        }

        return output;
    }
}
