package cs3500.marblesolitaire.model;

import java.util.ArrayList;

public final class EuropeanSolitaireModelImpl extends AMarbleSolitaireModelImpl {
  /**
   * Zero parameter constructor for the class.
   */
  public EuropeanSolitaireModelImpl() {
    this(3, 3, 3);
  }

  /**
   * Constructor to specify starting marble position for the empty slot in a model with default arm
   * thickness a = 5.
   *
   * @param    sRow Starting row for empty spot.
   * @param    sCol Starting column for empty spot.
   * @throws   IllegalArgumentException if sRow and sCol correspond to an invalid starting position
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor to instantiate a EuropeanSolitaireModelImpl with only the arm thickness specified.
   *
   * @param    a to represent the arm thickness.
   * @throws   IllegalArgumentException if a is not a positive odd number.
   */
  public EuropeanSolitaireModelImpl(int a) {
    this(a, (3 * a) / 2 - 1, (3 * a) / 2 - 1);
  }

  /**
   * Zero parameter constructor for the class.
   *
   * @param    a to represent the arm thickness.
   * @param    sRow Starting row for empty spot.
   * @param    sCol Starting column for empty spot.
   * @throws   IllegalArgumentException if the arm thickness is not a positive odd number, or the
   *           empty cell position is invalid.
   */
  public EuropeanSolitaireModelImpl(int a, int sRow, int sCol) {
    if (a < 1 || a % 2 == 0) {
      throw new IllegalArgumentException(String.format("Bad Thickness: %d", a));
    }
    for (int rowIndex = 0; rowIndex < (3 * a) - 2; rowIndex++) {
      ArrayList<SquareState> rowTemp = new ArrayList<SquareState>();
      // Approach: Bound the left and right halves of the board between upper and lower lines
      // For the left half of the board:
      for (int colIndex = 0; colIndex < ((3 * a) - 2) / 2; colIndex++) {
        if (rowIndex >= a - 1 - colIndex && rowIndex <= 2 * a - 2 + colIndex) {
          rowTemp.add(SquareState.Marble);
        } // todo how would i do this without ifs
        else {
          rowTemp.add(SquareState.OutOfBounds);
        }
      }
      // For the right half of the board:
      for (int colIndex = ((3 * a) - 2) / 2; colIndex < (3 * a) - 2; colIndex++) {
        if (rowIndex >= - 2 * a + 2 + colIndex && rowIndex <= 5 * a - 5 - colIndex) {
          rowTemp.add(SquareState.Marble);
        }
        else {
          rowTemp.add(SquareState.OutOfBounds);
        }
      }
      board.add(rowTemp);
    }
    if (!board.get(sRow).get(sCol).equals(SquareState.Marble)) {
      throw new IllegalArgumentException("Bad Starting position given");
    }
    board.get(sRow).set(sCol, SquareState.Empty);
  }
}