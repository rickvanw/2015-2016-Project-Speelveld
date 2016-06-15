package nl.saxion.groep2.speelveld.kamertjeverhuren.model;

import android.graphics.Color;
import android.view.ViewGroup;

import java.util.ArrayList;

import nl.saxion.groep2.speelveld.kamertjeverhuren.view.BoxView;
import nl.saxion.groep2.speelveld.kamertjeverhuren.view.LineView;

public class GameModel {

    private static GameModel ourInstance = new GameModel();
    private int gameBoardSize, amountOfBoxesInRow, gameBoardMargin;

    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<BoxView> boxViews = new ArrayList<>();
    private ArrayList<LineView> lineViews = new ArrayList<>();
    private String endTime;

    private Player player1, player2, currentPlayer;

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

    // Setters
    public void initNewGame() {
        // Remove all previous lineViews and boxviews from the parent view so they won't interfere with the new ones
        for (int i = 0; i < lineViews.size(); i++) {
            LineView lineView = lineViews.get(i);
            ((ViewGroup) lineView.getParent()).removeView(lineView);
        }
        for (int i = 0; i < boxViews.size(); i++) {
            BoxView boxView = boxViews.get(i);
            ((ViewGroup) boxView.getParent()).removeView(boxView);
        }
        lineViews = new ArrayList<>();
        lines = new ArrayList<>();
        boxes = new ArrayList<>();
        boxViews = new ArrayList<>();
        player1.resetCurrentScore();
        player2.resetCurrentScore();
        player1.resetPowerUpTakeBox();
        player2.resetPowerUpTakeBox();
        player1.setPowerUpTakeBoxActive(false);
        player2.setPowerUpTakeBoxActive(false);
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

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    // Getters
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

    public String getEndTime() {
        return endTime;
    }
}