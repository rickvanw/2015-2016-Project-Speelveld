package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.graphics.Color;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.BoxView;

/**
 * Created by Robert on 20-5-2016.
 */
public class GameModel {

    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow, gameBoardMargin;

    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<BoxView> boxViews = new ArrayList<>();

    private Player player1, player2;
    private Player currentPlayer;

    public static GameModel getInstance() {
        return ourInstance;
    }

    private GameModel() {
        this.amountOfBoxesInRow = 6;
        this.gameBoardMargin = 40;

        player1 = new Player(1, Color.RED, Color.parseColor("#ff6666"));
        player2 = new Player(2, Color.BLUE, Color.parseColor("#6666ff"));
        currentPlayer = player1;
    }

    public void initNewGame()
    {
        lines = new ArrayList<>();
        boxes = new ArrayList<>();
        boxViews = new ArrayList<>();
        player1.resetCurrentScore();
        player2.resetCurrentScore();
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    // setters

    /**
     * This method sets the GameBoard size based on the smallest side of the screen
     *
     * @param gameBoardSize
     */
    public void setGameBoardSize(int gameBoardSize) {
        this.gameBoardSize = gameBoardSize;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addBoxView(BoxView boxView) {
        boxViews.add(boxView);
    }

    // getters

    /**
     * This method returns the GameBoard size based on the smallest side of the screen
     *
     * @return gameBoardSize
     */
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public int getAmountOfBoxesInRow() {
        return amountOfBoxesInRow;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public int getGameBoardMargin() {
        return gameBoardMargin;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<BoxView> getBoxViews() {
        return boxViews;
    }
}