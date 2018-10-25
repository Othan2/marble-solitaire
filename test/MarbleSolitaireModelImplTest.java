import cs3500.marblesolitaire.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class holds tests for the marble solitaire model implementation.
 */
public class MarbleSolitaireModelImplTest {
  MarbleSolitaireModelImpl nullCons;
  MarbleSolitaireModelImpl size_3;


  MarbleSolitaireModelImpl start_0_3;
  MarbleSolitaireModelImpl start_3_3;
  MarbleSolitaireModelImpl start_2_2;
  MarbleSolitaireModelImpl start_neg1_3;
  MarbleSolitaireModelImpl start_1_neg3;
  MarbleSolitaireModelImpl start_4_4;

  MarbleSolitaireModelImpl thickness_1;
  MarbleSolitaireModelImpl thickness_neg3;
  MarbleSolitaireModelImpl thickness_4;
  MarbleSolitaireModelImpl thickness_0;

  MarbleSolitaireModelImpl a_3_row_3_col_4;
  MarbleSolitaireModelImpl a_3_row_2_col_2;
  MarbleSolitaireModelImpl a_2_row_3_col_4;
  MarbleSolitaireModelImpl a_3_row_9_col_4;
  MarbleSolitaireModelImpl a_6_row_9_col_4;

  /**
   * Initializes values used for testing.
   */
  @Before
  public void setUp() {
    nullCons = new MarbleSolitaireModelImpl();

    size_3 = new MarbleSolitaireModelImpl(3);

    start_0_3 = new MarbleSolitaireModelImpl(0, 3);
    start_3_3 = new MarbleSolitaireModelImpl(3, 3);
    start_4_4 = new MarbleSolitaireModelImpl(4, 4);

    thickness_1 = new MarbleSolitaireModelImpl(1);

    a_3_row_3_col_4 = new MarbleSolitaireModelImpl(3,3,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarbleOffBoard() {
    start_2_2 = new MarbleSolitaireModelImpl(2,100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMarbleRow() {
    start_neg1_3 = new MarbleSolitaireModelImpl(-1,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMarbleCol() {
    start_1_neg3 = new MarbleSolitaireModelImpl(1,-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegThickness() {
    thickness_neg3 = new MarbleSolitaireModelImpl(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenThickness() {
    thickness_4 = new MarbleSolitaireModelImpl(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroThickness() {
    thickness_0 = new MarbleSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGoodThicknessBadMarble() {
    a_3_row_2_col_2 = new MarbleSolitaireModelImpl(3,100,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadThicknessGoodMarble() {
    a_2_row_3_col_4 = new MarbleSolitaireModelImpl(2,3,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGoodThicknessOffBoardMarble() {
    a_3_row_9_col_4 = new MarbleSolitaireModelImpl(3,9,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadThicknessBadMarble() {
    a_6_row_9_col_4 = new MarbleSolitaireModelImpl(6,9,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidMoveTooFar() {
    nullCons.move(3,3,3,6);
  }

  @Test
  public void testMove() {
    this.setUp();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", nullCons.getGameState());
    nullCons.move(3,1, 3,3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", nullCons.getGameState());
    nullCons.move(5,2,3,2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", nullCons.getGameState());
    nullCons.move(2,2,4,2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    _ O O\n" +
            "    O O O", nullCons.getGameState());
    nullCons.move(2,4,2,2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O _ _ O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    _ O O\n" +
            "    O O O", nullCons.getGameState());
  }

  @Test
  public void testIsGameOver() {
    this.setUp();
    /*
                O O O
                O O O
            O O O O O O O
            O O O _ O O O
            O O O O O O O
                O O O
                O O O
     */
    // fixme pls
    // Are there any marbles with any valid moves left?
    // Make board with arm thickness 3 and a few moves left
    assertEquals(true, thickness_1.isGameOver());
    assertEquals(false, nullCons.isGameOver());
    assertEquals(false, start_0_3.isGameOver());
    start_0_3.move(1,3,3,3);
    assertEquals(false, start_0_3.isGameOver());
    // Sequence of moves to end game
    nullCons.move(2,4,4,4);
    nullCons.move(3,2,3,4);
    nullCons.move(5,3,3,3);
    nullCons.move(2,3,4,3);
    nullCons.move(0,3,2,3);
    nullCons.move(5,1,5,3);
    nullCons.move(5,3,3,3);
    nullCons.move(3,3,1,3);
    nullCons.move(3,0,3,2);
    nullCons.move(3,2,5,2);
    nullCons.move(5,5,5,3);
    nullCons.move(5,3,5,1);
    nullCons.move(7,3,5,3);
    nullCons.move(6,5,6,3);
    nullCons.move(8,5,6,5);
    nullCons.move(5,7,5,5);
    nullCons.move(5,5,7,5);
    nullCons.move(8,3,8,5);
    nullCons.move(8,5,6,5);
    nullCons.move(3,6,5,6);
    nullCons.move(3,8,3,6);
    nullCons.move(3,5,3,7);
    nullCons.move(5,8,3,8);
    nullCons.move(3,8,3,6);
    nullCons.move(5,0,5,2);
    nullCons.move(5,2,5,4);
    nullCons.move(0,5,0,3);
    nullCons.move(0,3,2,3);
    nullCons.move(1,5,1,3);
    nullCons.move(1,3,3,3);
    nullCons.move(3,3,3,5);
    nullCons.move(3,5,3,7);
    nullCons.move(3,7,5,7);
    nullCons.move(5,7,5,5);
    nullCons.move(5,5,5,3);
    nullCons.move(5,3,7,3);
    nullCons.move(7,3,7,5);
    nullCons.move(7,5,5,5);
    nullCons.move(5,5,3,5);
    nullCons.move(2,5,4,5);
    nullCons.move(4,5,4,3);
    assertEquals(false, nullCons.isGameOver());
    nullCons.move(4,0,4,2);
    assertEquals(false, nullCons.isGameOver());
    nullCons.move(4,2,4,4);
    assertEquals(true, nullCons.isGameOver());
    System.out.print(nullCons.getGameState());
  }

  @Test
  public void testGetGameState() {
    this.setUp();
    assertEquals("_",thickness_1.getGameState());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", size_3.getGameState());
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O",nullCons.getGameState());
  }

  @Test
  public void testGetScore() {
    this.setUp();
    assertEquals(32, nullCons.getScore());
    nullCons.move(3,1, 3,3);
    assertEquals(31, nullCons.getScore());
    assertEquals(0, thickness_1.getScore());
    assertEquals(32, start_0_3.getScore());
  }
}