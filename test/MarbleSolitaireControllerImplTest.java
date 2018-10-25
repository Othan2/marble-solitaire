import cs3500.marblesolitaire.model.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.TriangleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.PipedWriter;
import java.io.StringReader;
import java.io.Writer;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;

public class MarbleSolitaireControllerImplTest {
  StringBuilder ap1;
  StringBuilder ap2;
  StringBuilder ap3;
  StringBuilder ap4;
  StringBuilder ap5;
  StringBuilder ap6;
  StringBuilder ap7;
  StringBuilder ap8;
  StringBuilder null_ap;

  Writer closedAp;

  StringReader rd1;
  StringReader rd2;
  StringReader rd3;
  StringReader rd4;
  StringReader rd5;
  StringReader rd6;
  StringReader rd7;
  StringReader rd8;
  StringReader rd9;
  StringReader rd10;
  StringReader rd11;
  StringReader rd12;
  StringReader rd13;
  StringReader bad_rd1;
  StringReader bad_rd3;
  StringReader bad_rd4;
  StringReader bad_rd5;
  StringReader bad_rd6;
  StringReader bad_rd7;
  StringReader null_rd;

  MarbleSolitaireModelImpl nullImpl;
  MarbleSolitaireModelImpl emptyCons;
  MarbleSolitaireModelImpl emptyCons2;
  MarbleSolitaireModelImpl emptyCons3;
  MarbleSolitaireModelImpl emptyCons4;

  TriangleSolitaireModelImpl triangle;

  EuropeanSolitaireModelImpl euro;

  MarbleSolitaireControllerImpl controller;
  MarbleSolitaireControllerImpl controller2;
  MarbleSolitaireControllerImpl controller3;
  MarbleSolitaireControllerImpl bad_ap_controller;
  MarbleSolitaireControllerImpl quit_controller4;
  MarbleSolitaireControllerImpl quit_controller1;
  MarbleSolitaireControllerImpl quit_controller2;
  MarbleSolitaireControllerImpl quit_controller3;
  MarbleSolitaireControllerImpl debugController;
  MarbleSolitaireControllerImpl test_move_controller;
  MarbleSolitaireControllerImpl test_move_controller2;
  MarbleSolitaireControllerImpl test_move_controller3;
  MarbleSolitaireControllerImpl test_move_controller4;
  MarbleSolitaireControllerImpl bad_controller1;
  MarbleSolitaireControllerImpl bad_controller2;
  MarbleSolitaireControllerImpl testBadRowAndColumn_controller;
  MarbleSolitaireControllerImpl testBadRowAndColumn_controller2;
  MarbleSolitaireControllerImpl testBadRowAndColumn_controller3;
  MarbleSolitaireControllerImpl testBadRowAndColumn_controller4;
  MarbleSolitaireControllerImpl testBadMovePromptUser_controller;

  /**
   * Sets up data to be tested.
   */
  @Before
  public void setUp() {
    rd1 = new StringReader("4\n2\n4\n4\n q abc\n"); // Jumping from left onto middle square
    rd2 = new StringReader("2 2\n3 1\n Q");
    rd3 = new StringReader("q\n");
    rd4 = new StringReader("Q\n");
    rd5 = new StringReader("2 2\n3 1 q\n");
    rd6 = new StringReader("2 2\n3 1q\n");
    rd7 = new StringReader("Q");
    rd8 = new StringReader("4\n2\n4\n4\n4\n5\n4\n3 q \n");
    rd9 = new StringReader("2\n4\n4\n4\n q \n");
    rd10 = new StringReader("4\n2\n4\n4 adf \n-4 -6 4\n5 -3\n4\n3 q \n");
    rd11 = new StringReader("2 Q\n");
    rd12 = new StringReader("2 4 Q\n");
    rd13 = new StringReader("2 5 6 Q\n");
    bad_rd1 = new StringReader("abc Q");
    bad_rd3 = new StringReader("1 23 4 5\n q");
    bad_rd4 = new StringReader("-1 2 3 4 q");
    bad_rd5 = new StringReader("1 -2 3 4 q");
    bad_rd6 = new StringReader("1 2 -3 4 q");
    bad_rd7 = new StringReader("1 2 3 -4 q");

    ap1 = new StringBuilder();
    ap2 = new StringBuilder("   O O O\\n\" +\n" +
            "        \"    O O O\\n\" +\n" +
            "        \"O O O O O O O\\n\" +\n" +
            "        \"O O O _ O O O\\n\" +\n" +
            "        \"O O O O O O O\\n\" +\n" +
            "        \"    O O O\\n\" +\n" +
            "        \"    O O O"); // Standard initial board
    ap3 = new StringBuilder("   O O O\\n\" +\n" +
            "        \"    O O O\\n\" +\n" +
            "        \"O O O O O O O\\n\" +\n" +
            "        \"O _ _ O O O O\\n\" +\n" +
            "        \"O O O O O O O\\n\" +\n" +
            "        \"    O O O\\n\" +\n" +
            "        \"    O O O"); // Standard initial board, after jump specified by r1
    ap4 = new StringBuilder();
    ap5 = new StringBuilder();
    ap6 = new StringBuilder();
    ap7 = new StringBuilder();
    ap8 = new StringBuilder();
    closedAp = new PipedWriter();

    this.controller = new MarbleSolitaireControllerImpl(rd7, ap1);
    this.controller2 = new MarbleSolitaireControllerImpl(rd3, ap7);
    this.controller3 = new MarbleSolitaireControllerImpl(rd4, ap8);
    this.bad_ap_controller = new MarbleSolitaireControllerImpl(bad_rd3, closedAp);
    this.quit_controller1 = new MarbleSolitaireControllerImpl(rd7, ap1);
    this.quit_controller2 = new MarbleSolitaireControllerImpl(rd11, ap4);
    this.quit_controller3 = new MarbleSolitaireControllerImpl(rd12, ap5);
    this.quit_controller4 = new MarbleSolitaireControllerImpl(rd13, ap6);
    this.test_move_controller = new MarbleSolitaireControllerImpl(rd1, ap1);
    this.test_move_controller2 = new MarbleSolitaireControllerImpl(rd8, ap6);
    this.test_move_controller3 = new MarbleSolitaireControllerImpl(rd9, ap5);
    this.test_move_controller4 = new MarbleSolitaireControllerImpl(rd10, ap4);
    this.testBadRowAndColumn_controller = new MarbleSolitaireControllerImpl(bad_rd4, ap1);
    this.testBadRowAndColumn_controller2 = new MarbleSolitaireControllerImpl(bad_rd5, ap4);
    this.testBadRowAndColumn_controller3 = new MarbleSolitaireControllerImpl(bad_rd6, ap5);
    this.testBadRowAndColumn_controller4 = new MarbleSolitaireControllerImpl(bad_rd7, ap6);
    this.testBadMovePromptUser_controller = new MarbleSolitaireControllerImpl(rd2, ap1);
    this.bad_controller1 = new MarbleSolitaireControllerImpl(bad_rd1, ap1);

    this.emptyCons = new MarbleSolitaireModelImpl();
    this.emptyCons2 = new MarbleSolitaireModelImpl();
    this.emptyCons3 = new MarbleSolitaireModelImpl();
    this.emptyCons4 = new MarbleSolitaireModelImpl();

    this.triangle = new TriangleSolitaireModelImpl();

    this.euro = new EuropeanSolitaireModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    controller.playGame(nullImpl);
  }

  @Test(expected = IllegalStateException.class)
  public void testBadAppendable() {
    this.bad_ap_controller.playGame(emptyCons);
  }

  @Test
  public void testBadInput() {
    bad_controller1.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Please re-enter that move\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullrd() {
    bad_controller2 = new MarbleSolitaireControllerImpl(null_rd, ap1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    bad_controller2 = new MarbleSolitaireControllerImpl(rd1, null_ap);
  }

  // Needed?
  @Test
  public void displayEmptyConsGameState() {
    controller.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap1.toString());
  }

  @Test
  public void testMove() {
    test_move_controller.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", ap1.toString());
    test_move_controller2.playGame(emptyCons2);
    // Tests consecutive moves
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", ap6.toString());
    test_move_controller3.playGame(emptyCons3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", ap5.toString());
    test_move_controller4.playGame(emptyCons4);
    // Tests that only invalid input is re-entered
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Please re-enter that move\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Please re-enter that move\n" +
            "Please re-enter that move\n" +
            "Please re-enter that move\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", ap4.toString());
  }

  @Test
  public void testBadRowOrColumn() {
    testBadRowAndColumn_controller.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Please re-enter that move\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap1.toString());
    testBadRowAndColumn_controller2.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Please re-enter that move\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap4.toString());
    testBadRowAndColumn_controller3.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Please re-enter that move\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap5.toString());
    testBadRowAndColumn_controller4.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Please re-enter that move\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap6.toString());
  }

  @Test
  public void badMovePromptUser() {
    testBadMovePromptUser_controller.playGame(emptyCons);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again. Invalid move:\n" +
            "From row 2, column 2\n" +
            "To row 3, column 1.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap1.toString());
  }

  @Test
  public void testQuits() {
    this.quit_controller1.playGame(emptyCons);
    this.quit_controller2.playGame(emptyCons2);
    this.quit_controller3.playGame(emptyCons3);
    this.quit_controller4.playGame(emptyCons4);
    this.controller2.playGame(triangle);
    this.controller3.playGame(euro);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap1.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap4.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap5.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", ap6.toString());
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14", ap7.toString());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", ap8.toString());
  }
}