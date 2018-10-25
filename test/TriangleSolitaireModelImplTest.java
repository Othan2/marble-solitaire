import cs3500.marblesolitaire.model.TriangleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TriangleSolitaireModelImplTest {
  TriangleSolitaireModelImpl defaultCons1;
  TriangleSolitaireModelImpl hole_2_2;
  TriangleSolitaireModelImpl hole_4_2;
  TriangleSolitaireModelImpl hole_4_0_no1;
  TriangleSolitaireModelImpl hole_4_0_no2;
  TriangleSolitaireModelImpl hole_4_0_size_7;


  /**
   * Sets up objects to be tested.
   */
  @Before
  public void setUp() throws Exception {
    defaultCons1 = new TriangleSolitaireModelImpl();
    hole_2_2 = new TriangleSolitaireModelImpl(2,2);
    hole_4_2 = new TriangleSolitaireModelImpl(4,2);
    hole_4_0_no1 = new TriangleSolitaireModelImpl(4,0);
    hole_4_0_no2 = new TriangleSolitaireModelImpl(4,0);
    hole_4_0_size_7 = new TriangleSolitaireModelImpl(7,4,0);
  }

  @Test
  public void move() {
    hole_4_0_no1.move(4,2,4,0);
    hole_4_0_no2.move(2,0,4,0);
    hole_2_2.move(0,0,2,2);
    hole_4_2.move(4,4,4,2);
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O _ _", hole_4_2.getGameState());
    assertEquals("    _\n" +
            "   O _\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", hole_2_2.getGameState());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O _ _ O O", hole_4_0_no1.getGameState());
    assertEquals("    O\n" +
            "   O O\n" +
            "  _ O O\n" +
            " _ O O O\n" +
            "O O O O O", hole_4_0_no2.getGameState());
  }

  @Test
  public void testBadMoves() {
    int[][] moves = {
            {4,3,2,3},
            {4,4,4,2},
            {0,0,0,2},
            {0,0,2,2},
            {4,3,4,5},
            {3,3,5,3},
            {3,1,3,-1}
    };

    for (int i = 0; i < moves.length; i++) {
      try {
        defaultCons1.move(moves[i][0],moves[i][1],moves[i][2],moves[i][3]);
        fail(String.format("Failed on test %d", i + 1));
      }
      catch (IllegalArgumentException e) { /*Ensures that all bad moves fail*/ }
    }
  }

  /*
                       _
                      _ _
                     O _ _
                    _ _ _ _
                   O O O O O
   */
  @Test
  public void isGameOver() {
    assertEquals(false, defaultCons1.isGameOver());
    defaultCons1.move(2,2,0,0);
    defaultCons1.move(3,1,1,1);
    defaultCons1.move(0,0,2,2);
    defaultCons1.move(3,3,1,1);
    defaultCons1.move(2,0,0,0);
    defaultCons1.move(0,0,2,2);
    defaultCons1.move(4,0,2,0);
    defaultCons1.move(4,2,4,0);
    assertEquals(false, defaultCons1.isGameOver());
    defaultCons1.move(2,2,4,2);
    System.out.println(defaultCons1.getGameState());
    assertEquals(false, defaultCons1.isGameOver());
    defaultCons1.move(4,3,4,1);
    System.out.println(defaultCons1.getGameState());
    assertEquals(false, defaultCons1.isGameOver());
    defaultCons1.move(4,0,4,2);
    assertEquals("    _\n" +
            "   _ _\n" +
            "  O _ _\n" +
            " _ _ _ _\n" +
            "_ _ O _ O", defaultCons1.getGameState());
    assertEquals(true, defaultCons1.isGameOver());
    assertEquals(3,defaultCons1.getScore());
  }

  @Test
  public void getGameState() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", defaultCons1.getGameState());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "_ O O O O", hole_4_0_no2.getGameState());
    assertEquals("      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  _ O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", hole_4_0_size_7.getGameState());
  }

  @Test
  public void getScore() {
    assertEquals(14, defaultCons1.getScore());
    defaultCons1.move(2,0,0,0);
    assertEquals(13, defaultCons1.getScore());
    defaultCons1.move(2,2,2,0);
    assertEquals(12, defaultCons1.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadBase1() {
    TriangleSolitaireModelImpl bad_model = new TriangleSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadBase2() {
    TriangleSolitaireModelImpl bad_model = new TriangleSolitaireModelImpl(- 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHole1() {
    TriangleSolitaireModelImpl bad_model = new TriangleSolitaireModelImpl(0,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHole2() {
    TriangleSolitaireModelImpl bad_model = new TriangleSolitaireModelImpl(- 1,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHole3() {
    TriangleSolitaireModelImpl bad_model = new TriangleSolitaireModelImpl(0,- 2);
  }
}