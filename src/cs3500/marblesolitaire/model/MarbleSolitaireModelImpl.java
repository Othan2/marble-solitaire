package cs3500.marblesolitaire.model;

import java.util.ArrayList;

/**
 * This class represents a concrete implementation of the marble solitaire model, representing a
 * game of marble solitaire.
 */
public class MarbleSolitaireModelImpl
        extends AMarbleSolitaireModelImpl {


  /**
   * Zero parameter constructor for the class.
   */
  public MarbleSolitaireModelImpl() {
    this(3,3,3);
  }

  /**
   * Constructor to specify starting marble position for the marble in a model with default arm
   * thickness a = 3.
   *
   * @param    sRow Starting row for marble.
   * @param    sCol Starting column for marble.
   * @throws   IllegalArgumentException if sRow and sCol correspond to an invalid starting position
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor to instantiate a MarbleSolitaireModelImpl with only the arm thickness specified.
   *
   * @param    a to represent the arm thickness.
   * @throws   IllegalArgumentException if a is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int a) {
    this(a, 3 * a / 2 - 1, 3 * a / 2 - 1);
  }

  /**
   * Zero parameter constructor for the class.
   *
   * @param    a to represent the arm thickness.
   * @param    sRow Starting row for marble.
   * @param    sCol Starting column for marble.
   * @throws   IllegalArgumentException if the arm thickness is not a positive odd number, or the
   *           empty cell position is invalid.
   */
  public MarbleSolitaireModelImpl(int a, int sRow, int sCol) {
    if (a < 1 || a % 2 == 0) {
      throw new IllegalArgumentException("Bad Thickness");
    }
    for (int rowIndex = 0; rowIndex < (3 * a) - 2; rowIndex++) {
      ArrayList<SquareState> rowTemp = new ArrayList<SquareState>();
      for (int colIndex = 0; colIndex < (3 * a) - 2; colIndex++) {
        if ((a - 2 < rowIndex && rowIndex < 2 * a - 1) ||
            (a - 2 < colIndex && colIndex < 2 * a - 1)) {
          rowTemp.add(SquareState.Marble);
        }
        else {
          rowTemp.add(SquareState.OutOfBounds);
        }
      }
      board.add(rowTemp);
    }
    try {
      if (board.get(sRow).get(sCol).equals(SquareState.Marble)) {
        board.get(sRow).set(sCol, SquareState.Empty);
      }
      else {
        // If empty slot is in board, but not at a marble,
        throw new IndexOutOfBoundsException("Bad marble");
      }
    }
    catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Empty slot not at a valid position");
    }
  }
}
