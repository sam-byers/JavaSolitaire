
public class ScoreSystem {
    private int score; // Integer to store the score
    private final long startTime; // long final to store the start time of the game
    private int multipleBuffer;

    /**
     * Constructor for the ScoreSystem class.
     */
    public ScoreSystem() {
        this.score = 0; // Set the score to 0
        this.startTime = System.currentTimeMillis(); // Set the start time to the current time
        this.multipleBuffer = 0;
    }

    /**
     * Getter to return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Method to return the elapsed time since the start of the game.
     * @return The elapsed time in seconds.
     */
    public int elapedTime() {
        return (int)((System.currentTimeMillis() - startTime) / 1000);
    }

    /**
     * Method to update the score when a card is moved from the discard pile to the build pile.
     */
    public void scoreFromDiscard() {
        score += 10;
    }

    /**
     * Method to update the score when a card is moved from the table to the build pile.
     */
    public void scoreFromTable() {
        score += 20;
    }

    /**
     * Method to update the score when a card is moved from table pile to table pile, or from discard pile to table pile.
     */
    public void toTableScore() {
        score += 5;
    }

    /**
     * Method to set the number of cards being moved, which can be used to update the score if sucessful.
     */
    public void setMultipleBuffer(int multiple) {
        multipleBuffer = multiple;
    }

    /**
     * Method to update the score when multiple cards are moved from the table to table.
     */
    public void scoreFromTableMultiple() {
        score += 5 * multipleBuffer;
        multipleBuffer = 0;
    }

    /**
     * Method to clear the multiple buffer if the move is not sucessful.
     */
    public void clearMultipleBuffer() {
        multipleBuffer = 0;
    }


}
