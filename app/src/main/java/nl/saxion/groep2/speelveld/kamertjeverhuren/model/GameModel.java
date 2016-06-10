package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.graphics.Color;
import android.view.ViewGroup;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.BoxView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;

/**
 * Created by Robert on 20-5-2016.
 */
public class GameModel {

    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow, gameBoardMargin;

    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<BoxView> boxViews = new ArrayList<>();
    private ArrayList<LineView> lineViews = new ArrayList<>();

    private Player player1, player2;
    private Player currentPlayer;

    public static GameModel getInstance() {
        return ourInstance;
    }

    private GameModel() {
        this.amountOfBoxesInRow = 2;
        this.gameBoardMargin = 40;
        player1 = new Player(1, Color.RED, Color.parseColor("#ff6666"));
        player2 = new Player(2, Color.BLUE, Color.parseColor("#6666ff"));
        currentPlayer = player1;
    }

    public void initNewGame() {
        // Remove all previous lineViews from the parent view so they won't interfere with the new ones
        for (int i = 0; i < lineViews.size(); i++) {
            LineView lineView = lineViews.get(i);
            ((ViewGroup) lineView.getParent()).removeView(lineView);
        }

        lineViews = new ArrayList<>();
        lines = new ArrayList<>();
        boxes = new ArrayList<>();
        boxViews = new ArrayList<>();
        player1.resetCurrentScore();
        player2.resetCurrentScore();
    }

    // This method sets the GameBoard size based on the smallest side of the screen
    public void setGameBoardSize(int gameBoardSize) {
        this.gameBoardSize = gameBoardSize;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setAmountOfBoxesInRow(int amountOfBoxesInRow) {
        this.amountOfBoxesInRow = amountOfBoxesInRow;
    }

    public void addBoxView(BoxView boxView) {
        boxViews.add(boxView);
    }

    public void addLineView(LineView lineView) {
        lineViews.add(lineView);
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    // getters
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public int getGameBoardMargin() {
        return gameBoardMargin;
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

    public ArrayList<BoxView> getBoxViews() {
        return boxViews;
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
}