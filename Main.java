
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Card heldCard;
        var deck = new Deck();
        var bufferDeck = new Deck();
        var discardPileBuffer = new Deck();
        var discardPile = new DiscardDeck();
        var spadeBuild = new BuildDeck("S");
        var clubBuild = new BuildDeck("C");
        var heartBuild = new BuildDeck("H");
        var diamondBuild = new BuildDeck("D");
        Scanner scanner = new Scanner(System.in);
        UserInputScanner uInput = new UserInputScanner();
        String displayRowString;
        String sourceString = "";
        String [] moveInput;
        int indxVal;

        ArrayList<TableDeck> tableDecks = new ArrayList<>();

        deck.defineCards();
        deck.shuffleDeck();
        for (int i = 0; i < 7; i++) {
            tableDecks.add(new TableDeck());
            tableDecks.get(i).initalFill(i + 1, deck);
        }

        while (true) {
            for (TableDeck tableDeck : tableDecks) {
                tableDeck.revealCardCheck();
            }

            System.out.println("\n\n-DECK-|-SPADE-|-CLUB-|-HEART|-DIAM|  ");
            for (int i = 0; i < 3; i++) {

                displayRowString = "";
                displayRowString += deck.printTopCard(i);
                displayRowString += spadeBuild.printTopCard(i);
                displayRowString += clubBuild.printTopCard(i);
                displayRowString += heartBuild.printTopCard(i);
                displayRowString += diamondBuild.printTopCard(i);

                System.out.println(displayRowString);

            }
            System.out.println("DISCARD|--A--||--B--||--C--||--D--||--E--||--F--||--G--|");
            for (int i = 0; i < 72; i++) {
                displayRowString = "";
                if(discardPile.deck.size() < 3) {
                    displayRowString += discardPile.printDeck(i);
                } else {
                    discardPileBuffer.deck.clear();
                    discardPileBuffer.addCard(discardPile.deck.get(discardPile.deck.size()-3));
                    discardPileBuffer.addCard(discardPile.deck.get(discardPile.deck.size()-2));
                    discardPileBuffer.addCard(discardPile.deck.get(discardPile.deck.size()-1));
                    displayRowString += discardPileBuffer.printDeck(i);
                }
                
                for (int j = 0; j < 7; j++) {
                    displayRowString += tableDecks.get(j).printDeck(i);
                }
                if (!displayRowString.equals("                                                        ")) {
                    System.out.println(displayRowString);
                }
            }

            heldCard = null;
            bufferDeck.deck.clear();
            scanner.reset();
            moveInput = null;
            while (moveInput == null) {
                moveInput = uInput.makeMove(scanner);
            }
            switch (moveInput[0]) {
                case "q" -> {
                    System.out.println("Quitting game.");
                    return;
                }
                case "de" -> {
                    if(deck.deck.isEmpty()){
                        deck.refillDeck(discardPile);
                        discardPile.deck.clear();
                    } else {
                        discardPile.addCards(deck);
                    }
                    heldCard = null;
                }
                case "di" -> {
                    heldCard = discardPile.takeCard();
                    sourceString = "Discard";
                }
                case "a" -> {
                    if (tableDecks.get(0).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(0));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(0).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(0));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(0).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                    } else {
                        heldCard = tableDecks.get(0).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table A";
                }
                case "b" -> {
                    if (tableDecks.get(1).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(1));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(1).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(1));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(1).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                    } else {
                        heldCard = tableDecks.get(1).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table B";
                }
                case "c" -> {
                    if (tableDecks.get(2).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(2));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(2).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(2));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(2).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                    } else {
                        heldCard = tableDecks.get(2).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table C";
                }
                case "d" -> {
                    if (tableDecks.get(3).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(3));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(3).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(3));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(3).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                    } else {
                        heldCard = tableDecks.get(3).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table D";
                }
                case "e" -> {
                    if (tableDecks.get(4).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(4));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(4).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(4));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(4).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                        
                    } else {
                        heldCard = tableDecks.get(4).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table E";
                }
                case "f" -> {
                    if (tableDecks.get(5).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(5));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(5).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(5));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(5).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                    } else {
                        heldCard = tableDecks.get(5).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table F";
                }
                case "g" -> {
                    if (tableDecks.get(6).hasStack) {
                        if (moveInput[2].equals("Choose")){
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(6));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(6).grabStack(indxVal);
                        } else {
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(6));
                            if (indxVal == -1) {
                                break;
                            }
                            bufferDeck = tableDecks.get(6).grabStack(indxVal);
                        }
                        if (bufferDeck.deck.size() == 1) {
                            heldCard = bufferDeck.takeCard();
                            bufferDeck.deck.clear();
                        } else {
                            heldCard = null;
                        }
                    } else {
                        heldCard = tableDecks.get(6).takeCard();
                        bufferDeck.deck.clear();
                    }
                    sourceString = "Table G";
                }
                default ->
                    System.out.println("Invalid input");
            }
            if (heldCard != null && bufferDeck.deck.isEmpty()) {
                System.out.println("Held card: " + heldCard.getRank() + heldCard.getSuit());
                scanner.reset();
                switch (moveInput[1]) {
                    case "heart" -> {
                        heldCard = heartBuild.playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Hearts.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "diam" -> {
                        heldCard = diamondBuild.playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Diamonds.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "spade" -> {
                        heldCard = spadeBuild.playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Spades.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "club" -> {
                        heldCard = clubBuild.playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Clubs.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "a" -> {
                        heldCard = tableDecks.get(0).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table A.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "b" -> {
                        heldCard = tableDecks.get(1).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table B.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "c" -> {
                        heldCard = tableDecks.get(2).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table C.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "d" -> {
                        heldCard = tableDecks.get(3).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table D.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "e" -> {
                        heldCard = tableDecks.get(4).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table E.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "f" -> {
                        heldCard = tableDecks.get(5).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table F.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    case "g" -> {
                        heldCard = tableDecks.get(6).playCard(heldCard);
                        if (heldCard != null) {
                            System.out.println("Invalid move. Card not placed in Table G.");
                            switch (sourceString) {
                                case "Discard" ->
                                    discardPile.addCard(heldCard);
                                case "Table A" ->
                                    tableDecks.get(0).addCard(heldCard);
                                case "Table B" ->
                                    tableDecks.get(1).addCard(heldCard);
                                case "Table C" ->
                                    tableDecks.get(2).addCard(heldCard);
                                case "Table D" ->
                                    tableDecks.get(3).addCard(heldCard);
                                case "Table E" ->
                                    tableDecks.get(4).addCard(heldCard);
                                case "Table F" ->
                                    tableDecks.get(5).addCard(heldCard);
                                case "Table G" ->
                                    tableDecks.get(6).addCard(heldCard);
                            }
                        }
                    }
                    default -> {
                        switch (sourceString) {
                            case "Discard" ->
                                discardPile.addCard(heldCard);
                            case "Table A" ->
                                tableDecks.get(0).addCard(heldCard);
                            case "Table B" ->
                                tableDecks.get(1).addCard(heldCard);
                            case "Table C" ->
                                tableDecks.get(2).addCard(heldCard);
                            case "Table D" ->
                                tableDecks.get(3).addCard(heldCard);
                            case "Table E" ->
                                tableDecks.get(4).addCard(heldCard);
                            case "Table F" ->
                                tableDecks.get(5).addCard(heldCard);
                            case "Table G" ->
                                tableDecks.get(6).addCard(heldCard);
                        }
                        System.out.println("Invalid input");
                    }
                        
                }
            } else if(!bufferDeck.deck.isEmpty() & heldCard == null) {
                switch (moveInput[1]) {
                    case "a" -> {
                        bufferDeck = tableDecks.get(0).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table A.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }
                    }
                    case "b" -> {
                        bufferDeck = tableDecks.get(1).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table B.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }
                        
                    }
                    case "c" -> {
                        bufferDeck = tableDecks.get(2).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table C.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }

                        
                    }
                    case "d" -> {
                        bufferDeck = tableDecks.get(3).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table D.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }
                        
                    }
                    case "e" -> {
                        bufferDeck = tableDecks.get(4).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table E.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }
                        
                    }
                    case "f" -> {
                        bufferDeck = tableDecks.get(5).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table F.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }
                        
                    }
                    case "g" -> {
                        bufferDeck = tableDecks.get(6).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table G.");
                            switch (sourceString) {
                                case "Table A" ->{
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(0).placeStack(bufferDeck);
                                }
                                case "Table B" -> {
                                    tableDecks.get(1).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(1).placeStack(bufferDeck);
                                }
                                case "Table C" -> {
                                    tableDecks.get(2).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(2).placeStack(bufferDeck);
                                }
                                case "Table D" ->{
                                    tableDecks.get(3).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(3).placeStack(bufferDeck);
                                }
                                case "Table E" -> {
                                    tableDecks.get(4).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(4).placeStack(bufferDeck);
                                }
                                case "Table F" -> {
                                    tableDecks.get(5).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(5).placeStack(bufferDeck);
                                }
                                case "Table G" -> {
                                    tableDecks.get(6).addCard(bufferDeck.deck.getFirst());
                                    bufferDeck.deck.removeFirst();
                                    tableDecks.get(6).placeStack(bufferDeck);
                                }
                            }
                        }
                        
                    }
                    default ->
                        System.out.println("Invalid input");
                }
            }
            else {
                System.out.println("Held card: None");
            }
        }
    }
}
