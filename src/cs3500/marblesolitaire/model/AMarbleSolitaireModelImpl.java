package cs3500.marblesolitaire.model;

import cs3500.marblesolitaire.model.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.SquareState;

import java.util.ArrayList;

/**
 * To represent implementations of a MarbleSolitaireModel for any game of marble solitaire.
 */
public abstract class AMarbleSolitaireModelImpl implements MarbleSolitaireModel {

  protected ArrayList<ArrayList<SquareState>> board = new ArrayList<ArrayList<SquareState>>();

  // Contained entirely here
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException(String.format("Invalid move:\nFrom row %d, column %d\n" +
                      "To row %d, column %d.",
              fromRow + 1, fromCol + 1, toRow + 1, toCol + 1));
    }
    int jumpedRow = (fromRow + toRow) / 2;
    int jumpedCol = (fromCol + toCol) / 2;
    board.get(fromRow).set(fromCol, SquareState.Empty);
    board.get(jumpedRow).set(jumpedCol, SquareState.Empty);
    this.addMarble(toRow, toCol);
  }

  // Contained entirely here
  @Override
  public boolean isGameOver() {
    int size = board.size();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (board.get(i).get(j).equals(SquareState.Marble) && this.hasValidMove(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  // Overrode in Triangle
  @Override
  public String getGameState() {
    StringBuilder gs = new StringBuilder();
    int size = board.size();
    for (int rowIndex = 0; rowIndex < size; rowIndex++) {
      for (int colIndex = 0; colIndex < size; colIndex++) {
        SquareState item = board.get(rowIndex).get(colIndex);
        if (colIndex > 0 && !(item == SquareState.OutOfBounds && colIndex > size / 2)) {
          gs.append(" ");
        }
        if (!(item == SquareState.OutOfBounds && colIndex > (2 * size / 3 - 1))) {
          gs.append(item.toString());
        }
      }
      if (rowIndex < size - 1) {
        gs.append("\n");
      }
    }
    return gs.toString();
  }

  // Contained entirely here
  @Override
  public int getScore() {
    int size = board.size();
    int score = 0;
    for (int rowIndex = 0; rowIndex < size; rowIndex++) {
      for (int colIndex = 0; colIndex < size; colIndex++) {
        SquareState item = board.get(rowIndex).get(colIndex);
        if (item == SquareState.Marble) {
          score++;
        }
      }
    }
    return score;
  }

  /**
   * Changes SquareState at desired row and column from Empty to Marble.
   * @param    row To represent the row of the SquareState to change.
   * @param    col To represent the column of the SquareState to change.
   *
   * @throws   IllegalArgumentException if the SquareState at the given row and column is not
   *           an Empty.
   */
  protected void addMarble(int row, int col) {
    if (board.get(row).get(col) != SquareState.Empty) {
      throw new IllegalArgumentException("addMarble not given empty location");
    }
    board.get(row).set(col, SquareState.Marble);
  }

  /**
   * Determines whether a given marble has a valid move to make.
   *
   * @param    row To represent the row of the marble to check.
   * @param    col To represent the column of the marble to check.
   * @throws   IllegalArgumentException if the row and column provided do not correspond to a
   *           marble.
   */
  protected boolean hasValidMove(int row, int col) {
    int size = board.size();
    if (board.get(row).get(col) != SquareState.Marble) {
      throw new IllegalArgumentException("hasValidMove not given marble location");
    }
    return row < (size - 1) && this.isValidMove(row, col, row + 2, col)
            || row > 1 && this.isValidMove(row, col, row - 2, col)
            || col < (size - 1) && this.isValidMove(row, col, row, col + 2)
            || col > 1 && this.isValidMove(row, col, row, col - 2);
  }

  /**
   * Determines whether a given move is valid according to the rules of marble solitaire.
   *
   * @param    fromRow To represent the row of the marble to move.
   * @param    fromCol To represent the column of the marble to move.
   * @param    toRow To represent the row to move the marble to.
   * @param    toCol To represent the column to move the marble to.
   * @return   True if the move is valid, otherwise false.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int jumpedRow = (fromRow + toRow) / 2;
    int jumpedCol = (fromCol + toCol) / 2;
    int size = this.board.size();
    return ((fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0))
            && ((fromRow < size && fromCol < size) && (toRow < size && toCol < size))
            && (board.get(fromRow).get(fromCol) == SquareState.Marble)
            && ((Math.abs(fromRow - toRow) == 2 && fromCol - toCol == 0)
            || (Math.abs(fromCol - toCol) == 2 && fromRow - toRow == 0))
            && (board.get(jumpedRow).get(jumpedCol) == SquareState.Marble)
            && (board.get(toRow).get(toCol) == SquareState.Empty);
  }

}
