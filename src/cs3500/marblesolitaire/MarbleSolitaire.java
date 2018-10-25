package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.AMarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.TriangleSolitaireModelImpl;

import java.io.InputStreamReader;

/**
 * To represent a game of Marble Solitaire. Contains the main method for entry into the game.
 */
public final class MarbleSolitaire {
  /**
   * Method to initiate and run the game of Marble Solitaire. I wish I made a builder.
   *
   * @param args to represent the arguments to be evaluated by the command line.
   */
  public static void main(String[] args) {
    String model = args[0];
    String constructor = "default";
    AMarbleSolitaireModelImpl impl;
    MarbleSolitaireController controller;
    InputStreamReader rd = new InputStreamReader(System.in);
    int a = - 1;
    int sRow = - 1;
    int sCol = - 1;
    for (int i = 1; i < args.length; i += 2) {
      switch (args[i]) {
        case "-size":
          a = Integer.valueOf(args[i + 1]);
          break;
        case "-hole":
          sRow = Integer.valueOf(args[i + 1]);
          sCol = Integer.valueOf(args[i + 2]);
          i += 1;
          break;
        case "": break;
        default:
          throw new IllegalArgumentException("Bad command line");
      }
    }
    // controller = new MarbleSolitaireControllerImpl(rd, System.out).playGame(impl);
    if (a < 0 && sRow < 0 && sCol < 0) {
      switch (model) {
        case "european": impl = new EuropeanSolitaireModelImpl();
          break;
        case "triangle": impl = new TriangleSolitaireModelImpl();
          break;
        case "english": impl = new MarbleSolitaireModelImpl();
          break;
        default:
          throw new IllegalArgumentException("Bad model");
      }
    }
    else if (a < 0 && sRow > 0 && sCol > 0) {
      switch (model) {
        case "european": impl = new EuropeanSolitaireModelImpl(sRow, sCol);
          break;
        case "triangle": impl = new TriangleSolitaireModelImpl(sRow, sCol);
          break;
        case "english": impl = new MarbleSolitaireModelImpl(sRow, sCol);
          break;
        default:
          throw new IllegalArgumentException("Bad model");
      }
    }
    else if (a > 0 && sRow < 0 && sCol < 0) {
      switch (model) {
        case "european": impl = new EuropeanSolitaireModelImpl(a);
          break;
        case "triangle": impl = new TriangleSolitaireModelImpl(a);
          break;
        case "english": impl = new MarbleSolitaireModelImpl(a);
          break;
        default:
          throw new IllegalArgumentException("Bad model");
      }
    }
    else {
      switch (model) {
        case "european": impl = new EuropeanSolitaireModelImpl(a, sRow, sCol);
          break;
        case "triangle": impl = new TriangleSolitaireModelImpl(a, sRow, sCol);
          break;
        case "english": impl = new MarbleSolitaireModelImpl(a, sRow, sCol);
          break;
        default:
          throw new IllegalArgumentException("Bad model");
      }
    }
    new MarbleSolitaireControllerImpl(rd, System.out).playGame(impl);
  }
}