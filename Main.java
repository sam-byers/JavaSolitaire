// Sam Byers - 2024
// JAVA Solitaire Game
// This main class is the main driver for the game. All game logic is handled here.

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ScoreSystem score = new ScoreSystem(); // ScoreSystem object to handle the scoring of the game
        Card heldCard; // The card that is currently being held by the player
        var deck = new Deck(); // The main deck that cards are drawn from
        var bufferDeck = new Deck(); // A temporary deck to store cards that are being moved as a stack
        var discardPileBuffer = new Deck(); // A temporary deck to store the discard pile's top 3 cards for display when the discard pile has more than 3 cards
        var discardPile = new DiscardDeck(); // The discard pile where cards are placed after being drawn from the deck
        var spadeBuild = new BuildDeck("S"); // The build deck for spades
        var clubBuild = new BuildDeck("C"); // The build deck for clubs
        var heartBuild = new BuildDeck("H"); // The build deck for hearts
        var diamondBuild = new BuildDeck("D"); // The build deck for diamonds
        Scanner scanner = new Scanner(System.in); // Scanner object to get user input that is passed to the UserInputScanner class
        UserInputScanner uInput = new UserInputScanner(); // UserInputScanner object to handle user input
        String displayRowString; // String to store the row of cards to display
        String sourceString = ""; // String to store the source of the card being moved, so that they can be placed back if the move is invalid
        String[] moveInput; // Array to store the user input, source, destination, number of cards
        int indxVal; // Integer to store the index of the card to grab the stack from when moving a stack of cards

        ArrayList<TableDeck> tableDecks = new ArrayList<>(); // Array list to store the table decks, the 7 decks where cards are moved to and from when playing the game

        deck.defineCards(); // Define the cards in the deck, 52 cards in total are defined here in deck
        deck.shuffleDeck(); // Shuffle the deck so that the cards are in a random order
        for (int i = 0; i < 7; i++) { // Loop to create the 7 table decks
            tableDecks.add(new TableDeck());
            tableDecks.get(i).initalFill(i + 1, deck); // Fill the table decks with the appropriate number of cards from the main deck
        }

        while (spadeBuild.getDeckSize() + clubBuild.getDeckSize() + heartBuild.getDeckSize() + diamondBuild.getDeckSize() < 52) { // Main game loop starts here, this code runs until the game is quit or won
            for (TableDeck tableDeck : tableDecks) { // Loop through the table decks at the start of a turn
                tableDeck.revealCardCheck(); // Reveal the top card of the deck and check if there is a stack of cards
            }
            System.out.println("\n Time elapsed: " + score.elapedTime() + " Score: " + score.getScore() + "\n"); // Print the time elapsed since the game started
            System.out.println("\n\n-DECK-|-SPADE-|-CLUB-|-HEART|-DIAM|  "); // Print an interface element to show the player where the build decks are
            for (int i = 0; i < 3; i++) { // 3 lines to print a top card for the decks that print in top card mode

                displayRowString = ""; // String to store the row of cards to display is set to empty
                displayRowString += deck.printTopCard(i); // Add the top card of the deck to the display row string
                displayRowString += spadeBuild.printTopCard(i); // Add the top card of the spade build deck to the display row string
                displayRowString += clubBuild.printTopCard(i); // Add the top card of the club build deck to the display row string
                displayRowString += heartBuild.printTopCard(i); // Add the top card of the heart build deck to the display row string
                displayRowString += diamondBuild.printTopCard(i); // Add the top card of the diamond build deck to the display row string

                System.out.println(displayRowString); // Print the display row string

            }
            System.out.println("DISCARD|--A--||--B--||--C--||--D--||--E--||--F--||--G--|"); // Print an interface element to show the player where the table decks are
            for (int i = 0; i < 72; i++) { // Loop to print the table decks, this game supports up to 24 cards in a given row
                displayRowString = ""; // String to store the row of cards to display is set to empty
                if (discardPile.deck.size() < 3) { // If the discard pile has less than 3 cards
                    displayRowString += discardPile.printDeck(i); // We can print the discard pile as normal
                } else { // If the discard pile has more than 3 cards
                    discardPileBuffer.deck.clear(); // Clear the discard pile buffer
                    discardPileBuffer.addCard(discardPile.deck.get(discardPile.deck.size() - 3)); // Add the 3rd last card to the buffer
                    discardPileBuffer.addCard(discardPile.deck.get(discardPile.deck.size() - 2)); // Add the 2nd last card to the buffer
                    discardPileBuffer.addCard(discardPile.deck.get(discardPile.deck.size() - 1)); // Add the last card to the buffer
                    displayRowString += discardPileBuffer.printDeck(i); // Print the buffer instead of the discard pile
                }

                for (int j = 0; j < 7; j++) { // Loop through the table decks
                    displayRowString += tableDecks.get(j).printDeck(i); // Print the line from each of the table decks
                }
                if (!displayRowString.equals("                                                        ")) { // If a row is not empty, if it is empty we don't need to print it to save screen space
                    System.out.println(displayRowString); // Print the row
                }
            }

            heldCard = null; // Set the held card to null so that we do not accidentally override a move stack operation
            bufferDeck.deck.clear(); // Clear the buffer deck so that we do not accidentally override a move card operation
            scanner.reset(); // Reset the scanner so that we can get new input (Likely not needed)
            moveInput = null; // Set the move input to null so that we can get new input
            while (moveInput == null) { // Loop to get the user input for the move
                moveInput = uInput.makeMove(scanner); // Get the user input for the move
            }
            switch (moveInput[0]) { // Switch statement to handle the user inputs first term, the source of the card to move
                case "q" -> { // If the user input is 'q', quit the game
                    System.out.println("You quit! Goodbye!"); // Print a quit message
                    System.out.println("Final Score: " + score.getScore()); // Print the final score
                    System.out.println("Cards left: " + (52-(spadeBuild.getDeckSize() + clubBuild.getDeckSize() + heartBuild.getDeckSize() + diamondBuild.getDeckSize()))); // Print the number of cards left
                    System.out.println("Time Spent: " + score.elapedTime()); // Print the time spent
                    return;
                }
                case "de" -> { // If the user input is 'de', draw 3 cards from the deck
                    if (deck.deck.isEmpty()) { // If the deck is empty
                        deck.refillDeck(discardPile); // Refill the deck from the discard pile
                        discardPile.deck.clear(); // Clear the discard pile
                    } else { // If the deck is not empty
                        discardPile.addCards(deck); // Add 3 cards to the discard pile from the deck
                    }
                    heldCard = null; // Set the held card to null as we have not drawn a card
                }
                case "di" -> { // if the user input is 'di', take the top card from the discard pile
                    heldCard = discardPile.takeCard(); // Take the top card from the discard pile
                    sourceString = "Discard"; // Set the source string to 'Discard'
                }
                case "a" -> { // If the user input is 'a', take the top card from table deck A
                    if (tableDecks.get(0).hasStack) { // If table deck A has a stack of cards
                        if (moveInput[2].equals("Choose")) { // If the user input is 'Choose', ie the user has not specifed the number of cards to move, prompt the user to choose the number of cards
                            indxVal = uInput.pickIndex(scanner, tableDecks.get(0)); // Get the index of the card to grab the stack from, from the user
                            if (indxVal == -1) { // If the index is -1, the user has cancelled the move or entered an invalid index
                                break;  // Break out of the switch statement
                            }
                            bufferDeck = tableDecks.get(0).grabStack(indxVal); // Grab the stack of cards from the table deck
                        } else { // If the user has specified the number of cards to move
                            indxVal = uInput.loadIndx(moveInput[2], tableDecks.get(0));  // Get the index of the card to grab the stack from, from the previously obtained user input
                            if (indxVal == -1) { // If the index is -1, the user has cancelled the move or entered an invalid index
                                break; // Break out of the switch statement
                            }
                            bufferDeck = tableDecks.get(0).grabStack(indxVal); // Grab the stack of cards from the table deck
                        }
                        if (bufferDeck.deck.size() == 1) { // If the stack of cards is only 1 card
                            heldCard = bufferDeck.takeCard(); // Take the card from the stack so that we can process it as a single card
                            bufferDeck.deck.clear(); // thus we can clear the buffer deck
                        } else { // If the stack of cards is more than 1 card
                            heldCard = null; // Set the held card to null so that we do not accidentally override a move stack operation
                        }
                    } else { // If table deck A does not have a stack of cards
                        heldCard = tableDecks.get(0).takeCard(); // Take the top card from table deck A
                        bufferDeck.deck.clear(); // Clear the buffer deck so that we do not accidentally override a move card operation
                    }
                    sourceString = "Table A"; // Set the source string to 'Table A'
                }
                // This code is repeated for each of the table decks, A-G
                case "b" -> {
                    if (tableDecks.get(1).hasStack) {
                        if (moveInput[2].equals("Choose")) {
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
                        if (moveInput[2].equals("Choose")) {
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
                        if (moveInput[2].equals("Choose")) {
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
                        if (moveInput[2].equals("Choose")) {
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
                        if (moveInput[2].equals("Choose")) {
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
                        if (moveInput[2].equals("Choose")) {
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
                    System.out.println("Invalid input"); // If the user input is not valid, print an error message
            }
            if (heldCard != null && bufferDeck.deck.isEmpty()) { // If the held card is not null and the buffer deck is empty, we are moving a single card
                System.out.println("Held card: " + heldCard.getRank() + heldCard.getSuit()); // Print the held card
                scanner.reset(); // Reset the scanner so that we can get new input (Likely not needed)
                switch (moveInput[1]) { // Switch statement to handle the user inputs second term, the destination of the card we are moving
                    case "heart" -> { // If the user input is 'heart', move the card to the heart build deck
                        heldCard = heartBuild.playCard(heldCard); // Play the card to the heart build deck
                        if (heldCard != null) { // If the card is not placed in the heart build deck, ie the move is invalid
                            System.out.println("Invalid move. Card not placed in Hearts."); // Print an error message
                            switch (sourceString) { // Switch statement to handle the source of the card we are moving
                                case "Discard" -> // If the source is the discard pile
                                    discardPile.addCard(heldCard); // Add the card back to the discard pile
                                case "Table A" -> // If the source is table deck A
                                    tableDecks.get(0).addCard(heldCard);  // Add the card back to table deck A
                                case "Table B" -> // If the source is table deck B
                                    tableDecks.get(1).addCard(heldCard); // Add the card back to table deck B
                                case "Table C" -> // If the source is table deck C
                                    tableDecks.get(2).addCard(heldCard); // Add the card back to table deck C
                                case "Table D" -> // If the source is table deck D 
                                    tableDecks.get(3).addCard(heldCard); // Add the card back to table deck D
                                case "Table E" -> // If the source is table deck E 
                                    tableDecks.get(4).addCard(heldCard); // Add the card back to table deck E
                                case "Table F" -> // If the source is table deck F
                                    tableDecks.get(5).addCard(heldCard); // Add the card back to table deck F
                                case "Table G" -> // If the source is table deck G 
                                    tableDecks.get(6).addCard(heldCard); // Add the card back to table deck G
                            }
                        } else {
                            if (sourceString.equals("Discard")) { // If the source is the discard pile
                                score.scoreFromDiscard(); // Score the move from the discard pile
                            } else {
                                score.scoreFromTable(); // Score the move from a table deck
                            }
                        }
                    }
                    case "diam" -> { // If the user input is 'diam', move the card to the diamond build deck
                        heldCard = diamondBuild.playCard(heldCard); // Play the card to the diamond build deck
                        if (heldCard != null) { // If the card is not placed in the diamond build deck, ie the move is invalid
                            System.out.println("Invalid move. Card not placed in Diamonds."); // Print an error message
                            switch (sourceString) { // Switch statement to handle the source of the card we are moving, same as before
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
                        } else {
                            if (sourceString.equals("Discard")) { // If the source is the discard pile
                                score.scoreFromDiscard(); // Score the move from the discard pile
                            } else {
                                score.scoreFromTable(); // Score the move from a table deck
                            }
                        }
                    }
                    // This code is repeated for each of the build decks, Hearts, Diamonds, Spades, Clubs and the table decks A-G
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
                        } else {
                            if (sourceString.equals("Discard")) { // If the source is the discard pile
                                score.scoreFromDiscard(); // Score the move from the discard pile
                            } else {
                                score.scoreFromTable(); // Score the move from a table deck
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
                        } else {
                            if (sourceString.equals("Discard")) { // If the source is the discard pile
                                score.scoreFromDiscard(); // Score the move from the discard pile
                            } else {
                                score.scoreFromTable(); // Score the move from a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
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
                        } else {
                            score.toTableScore(); // Score the move to a table deck
                        }
                    }
                    default -> { // If the user input is not valid, we print an error message and return the card to the source as before
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
            } else if (!bufferDeck.deck.isEmpty() & heldCard == null) { // If the buffer deck is not empty and the held card is null, we are moving a stack of cards
                System.out.println("Held stack: " + bufferDeck.deck.getFirst().getRank() + bufferDeck.deck.getFirst().getSuit() + " to " + bufferDeck.deck.getLast().getRank() + bufferDeck.deck.getLast().getSuit()); // Print the stack of cards
                score.setMultipleBuffer(bufferDeck.deck.size()); // Set the score multiplier to the number of cards in the stack
                switch (moveInput[1]) { // Switch statement to handle the user inputs second term, the destination of the card we are moving as before
                    case "a" -> { // If the user input is 'a', move the stack of cards to table deck A, we need not need check if the cards are being moved to a build deck as stacks of cards cannot be moved to build decks
                        bufferDeck = tableDecks.get(0).placeStack(bufferDeck); // Place the stack of cards in table deck A
                        if (!bufferDeck.deck.isEmpty()) { // If the stack of cards is not placed in table deck A, ie the move is invalid
                            System.out.println("Invalid move. Card not placed in Table A."); // Print an error message
                            score.clearMultipleBuffer(); // Clear the score multiplier as the move was invalid
                            switch (sourceString) { // Switch statement to handle the source of the card we are moving, same as before
                                case "Table A" -> { // If the source is table deck A
                                    tableDecks.get(0).addCard(bufferDeck.deck.getFirst()); // Add the first card of the stack back to table deck A, this is done to force the place stack to work correctly as otherwise it would check the face down card below where the stack was taken from
                                    bufferDeck.deck.removeFirst(); // Remove the first card from the stack
                                    tableDecks.get(0).placeStack(bufferDeck); // Place the stack of cards back in table deck A, which will now succeed given that the stack was valid before
                                }
                                // Again, this code is repeated for each of the table decks, A-G
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
                                case "Table D" -> {
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
                        } else { // If the stack of cards is placed in table deck A
                            score.scoreFromTableMultiple(); // Score the move from a table deck
                        }
                    }
                    // and again, this code is repeated for each of the table decks, A-G
                    case "b" -> {
                        bufferDeck = tableDecks.get(1).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table B.");
                            score.clearMultipleBuffer();
                            switch (sourceString) {
                                case "Table A" -> {
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
                                case "Table D" -> {
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
                        } else {
                            score.scoreFromTableMultiple();
                        }

                    }
                    case "c" -> {
                        bufferDeck = tableDecks.get(2).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table C.");
                            score.clearMultipleBuffer();
                            switch (sourceString) {
                                case "Table A" -> {
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
                                case "Table D" -> {
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
                        } else {
                            score.scoreFromTableMultiple();
                        }

                    }
                    case "d" -> {
                        bufferDeck = tableDecks.get(3).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table D.");
                            score.clearMultipleBuffer();
                            switch (sourceString) {
                                case "Table A" -> {
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
                                case "Table D" -> {
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
                        } else {
                            score.scoreFromTableMultiple();
                        }

                    }
                    case "e" -> {
                        bufferDeck = tableDecks.get(4).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table E.");
                            score.clearMultipleBuffer();
                            switch (sourceString) {
                                case "Table A" -> {
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
                                case "Table D" -> {
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
                        } else {
                            score.scoreFromTableMultiple();
                        }

                    }
                    case "f" -> {
                        bufferDeck = tableDecks.get(5).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table F.");
                            score.clearMultipleBuffer();
                            switch (sourceString) {
                                case "Table A" -> {
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
                                case "Table D" -> {
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
                        } else {
                            score.scoreFromTableMultiple();
                        }

                    }
                    case "g" -> {
                        bufferDeck = tableDecks.get(6).placeStack(bufferDeck);
                        if (!bufferDeck.deck.isEmpty()) {
                            System.out.println("Invalid move. Card not placed in Table G.");
                            score.clearMultipleBuffer();
                            switch (sourceString) {
                                case "Table A" -> {
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
                                case "Table D" -> {
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
                        } else {
                            score.scoreFromTableMultiple();
                        }

                    }
                    default -> { // If the user input is not valid, we print an error message and return the stack of cards to the source as before
                        score.clearMultipleBuffer(); // Clear the score multiplier as the move was invalid
                        switch (sourceString) {
                            case "Table A" -> {
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
                            case "Table D" -> {
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
                        System.out.println("Invalid input");
                    }
                }
            } else { // if the held card is null, we have just drawn 3 cards to the discard pile and thus are not holding anything
                System.out.println("Held card: None"); // Print that we are not holding anything
            }
        }
        if (spadeBuild.getDeckSize() + clubBuild.getDeckSize() + heartBuild.getDeckSize() + diamondBuild.getDeckSize() == 52) { // If all the build decks are full, the game is won
            System.out.println("You win!"); // Print a win message
            System.out.println("Score: " + score.getScore()); // Print the final score
            System.out.println("Time Spent: " + score.elapedTime()); // Print the time spent
        }
    }
}
