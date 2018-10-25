import cs3500.marblesolitaire.model.EuropeanSolitaireModelImpl;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class EuropeanSolitaireModelImplTest {
  EuropeanSolitaireModelImpl defaultCons1;
  EuropeanSolitaireModelImpl defaultCons2;
  EuropeanSolitaireModelImpl defaultCons3;
  EuropeanSolitaireModelImpl defaultCons4;
  EuropeanSolitaireModelImpl size_5;
  EuropeanSolitaireModelImpl size_9;
  EuropeanSolitaireModelImpl hole_0_2;
  EuropeanSolitaireModelImpl size_5_hole_4_3;

  /**
   * Sets up objects to be tested.
   */
  @Before
  public void setUp() {
    defaultCons1 = new EuropeanSolitaireModelImpl();
    defaultCons2 = new EuropeanSolitaireModelImpl();
    defaultCons3 = new EuropeanSolitaireModelImpl();
    defaultCons4 = new EuropeanSolitaireModelImpl();
    size_5 = new EuropeanSolitaireModelImpl(5);
    size_9 = new EuropeanSolitaireModelImpl(9);
    hole_0_2 = new EuropeanSolitaireModelImpl(0,2);
    size_5_hole_4_3 = new EuropeanSolitaireModelImpl(4,3);
  }

  @Test
  public void testConstructorsAndGetGameState() {
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", defaultCons1.getGameState());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", size_5.getGameState());
    assertEquals("    _ O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", hole_0_2.getGameState());
    assertEquals("                O O O O O O O O O\n" +
            "              O O O O O O O O O O O\n" +
            "            O O O O O O O O O O O O O\n" +
            "          O O O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O _ O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O O O O O O O\n" +
            "          O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O O O O O O O\n" +
            "              O O O O O O O O O O O\n" +
            "                O O O O O O O O O", size_9.getGameState());
  }

  @Test
  public void move() {
    defaultCons1.move(3,1,3,3);
    defaultCons2.move(3,5,3,3);
    defaultCons3.move(1,3,3,3);
    defaultCons4.move(5,3,3,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", defaultCons1.getGameState());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", defaultCons2.getGameState());
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", defaultCons3.getGameState());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O", defaultCons4.getGameState());
  }

  @Test
  public void badMoves() {
    int[][] moves = {
            {1,1,1,-1},
            {0,2,0,4},
            {1,5,3,3},
            {3,3,3,5},
            {1,2,1,0},
            {2,2,2,4},
            {1,3,-1,3},
            {3,5,3,7},
            {5,3,7,3}
    };

    for (int i = 0; i < moves.length; i++) {
      try {
        defaultCons1.move(moves[i][0],moves[i][1],moves[i][2],moves[i][3]);
        fail(String.format("Failed on test %d", i + 1));
      }
      catch (IllegalArgumentException e) { /*Makes sure that move fails*/ }
    }
  }

  @Test
  public void isGameOver() {
    // assertEquals(false, hole_0_2.isGameOver());
    assertEquals(36, hole_0_2.getScore());
    hole_0_2.move(0,4,0,2);
    assertEquals(35, hole_0_2.getScore());
    hole_0_2.move(2,4,0,4);
    assertEquals(34, hole_0_2.getScore());
    hole_0_2.move(2,3,0,3);
    hole_0_2.move(2,1,2,3);
    hole_0_2.move(4,2,2,2);
    hole_0_2.move(2,2,2,4);
    hole_0_2.move(4,1,2,1);
    hole_0_2.move(2,0,2,2);
    hole_0_2.move(1,2,3,2);
    hole_0_2.move(4,0,2,0);
    hole_0_2.move(3,3,3,1);
    hole_0_2.move(2,5,2,3);
    hole_0_2.move(3,5,3,3);
    hole_0_2.move(4,4,4,2);
    hole_0_2.move(5,2,3,2);
    hole_0_2.move(3,2,3,4);
    hole_0_2.move(6,3,4,3);
    hole_0_2.move(5,5,5,3);
    hole_0_2.move(5,3,3,3);
    hole_0_2.move(3,3,3,5);
    assertEquals(false, hole_0_2.isGameOver());
    hole_0_2.move(3,5,5,5);
    assertEquals(true, hole_0_2.isGameOver());
    assertEquals(15, hole_0_2.getScore());
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ O _ _ O\n" +
            "_ O _ _ _ _ O\n" +
            "_ _ _ _ _ _ O\n" +
            "  O _ _ _ O\n" +
            "    O _ O", hole_0_2.getGameState());
  }

  @Test
  public void getScore() {
    assertEquals(36, defaultCons1.getScore());
    assertEquals(36, hole_0_2.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryNegSize() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void trySize4() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void trySize2() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryBadHole1() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryBadHole2() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryBadHole3() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(5,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryBadHole4() {
    EuropeanSolitaireModelImpl bad_model = new EuropeanSolitaireModelImpl(5,0);
  }
}