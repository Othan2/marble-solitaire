package cs3500.marblesolitaire.model;

/**
 * This enum represents the two different states a square can have in the MarbleSolitaireImpl.
 * Empty - Not containing a marble, but in the play area
 * Marble - A square containing a marble
 * OutOfBounds - A square outside of the play area
 */
public enum SquareState {
  Empty {
    @Override
    public final String toString() {
      return "_";
    }
  },
  Marble {
    @Override
    public final String toString() {
      return "O";
    }
  },
  OutOfBounds {
    @Override
    public final String toString() {
      return " ";
    }
  }
}
