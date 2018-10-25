package cs3500.marblesolitaire.model;

import java.util.ArrayList;

/**
 * To represent an implementation of the game of triangle marble solitaire.
 */
public final class TriangleSolitaireModelImpl extends AMarbleSolitaireModelImpl {
  /**
   * Zero parameter constructor for the class.
   */
  public TriangleSolitaireModelImpl() {
    this(5,0,0);
  }

  /**
   * Constructor to specify starting marble position for the empty slot in a model with default base
   * thickness a = 5.
   *
   * @param    sRow Starting row for empty spot.
   * @param    sCol Starting column for empty spot.
   * @throws   IllegalArgumentException if sRow and sCol correspond to an invalid starting position
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructor to instantiate a TriangleSolitaireModelImpl with only the base thickness specified.
   *
   * @param    a to represent the base thickness.
   * @throws   IllegalArgumentException if a is not a positive number.18
   */
  public TriangleSolitaireModelImpl(int a) {
    this(a, 0,0);
  }

  /**
   * Zero parameter constructor for the class.
   *
   * @param    a to represent the base thickness.
   * @param    sRow Starting row for empty spot.
   * @param    sCol Starting column for empty spot.
   * @throws   IllegalArgumentException if the base thickness is not a positive number, or the
   *           empty cell position is invalid.
   */
  public TriangleSolitaireModelImpl(int a, int sRow, int sCol) {
    if (a < 1) {
      throw new IllegalArgumentException("Bad base thickness");
    }
    for (int i = 0; i < a; i++) {
      ArrayList<SquareState> rowTemp = new ArrayList<SquareState>();
      for (int j = 0; j < i + 1; j++) {
        rowTemp.add(SquareState.Marble);
      }
      for (int j = i + 1; j < a; j++) {
        rowTemp.add(SquareState.OutOfBounds);
      }
      this.board.add(rowTemp);
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

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // TODO: I could abstract this by only checking if the row and column change are valid for the implementation,
    // TODO contd the rest of this could be kept in the abstract class.
    int size = board.size();
    boolean fromMarble;
    boolean toEmpty;
    boolean jumpedMarble;
    boolean nonNegPosns = (fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0);
    boolean posnsInScope = (fromRow < size && fromCol < size) && (toRow < size && toCol < size);
    boolean sameRowTwocols = fromRow == toRow && Math.abs(fromCol - toCol) == 2;
    boolean twoRowsOnDiagonal = (Math.abs(fromCol - toCol) == 0 || Math.abs(fromCol - toCol) == 2)
            && (Math.abs(fromRow - toRow) == 2);
    try {
      int jumpedRow = (toRow + fromRow) / 2;
      int jumpedCol = (toCol + fromCol) / 2;
      fromMarble = board.get(fromRow).get(fromCol).equals(SquareState.Marble);
      toEmpty = board.get(toRow).get(toCol).equals(SquareState.Empty);
      jumpedMarble = board.get(jumpedRow).get(jumpedCol).equals(SquareState.Marble);
    }
    // todo bad code? Should I be expanding the board width by 1 on each side so that it must jump
    // todo onto OOB square instead of actually out of bounds for the board.
    catch (IndexOutOfBoundsException e) {
      return false;
    }
    return nonNegPosns && posnsInScope && (sameRowTwocols || twoRowsOnDiagonal)
            && fromMarble && toEmpty && jumpedMarble;
  }

  @Override
  public String getGameState() {
    StringBuilder gs = new StringBuilder();
    int size = board.size();
    for (int rowIndex = 0; rowIndex < size; rowIndex++) {
      for (int i = 0; i < size - rowIndex - 1; i++) {
        gs.append(" ");
      }
      for (int colIndex = 0; colIndex < size; colIndex++) {
        SquareState item = board.get(rowIndex).get(colIndex);
        if (!item.equals(SquareState.OutOfBounds)) {
          gs.append(item.toString());
        }
        if (colIndex < rowIndex) {
          gs.append(" ");
        }
      }
      if (rowIndex < size - 1) {
        gs.append("\n");
      }
    }
    return gs.toString();
  }

  @Override
  protected boolean hasValidMove(int row, int col) {
    if (board.get(row).get(col) != SquareState.Marble) {
      throw new IllegalArgumentException("hasValidMove not given marble location");
    }
    return  // dis is fucked up
            // All possible valid moves for a marble in a triangle:
            this.isValidMove(row, col, row, col + 2)
            || this.isValidMove(row, col, row, col - 2)
            || this.isValidMove(row, col, row + 2, col + 2)
            || this.isValidMove(row, col, row - 2, col - 2)
            || this.isValidMove(row, col, row + 2, col)
            || this.isValidMove(row, col, row - 2, col);
  }
}