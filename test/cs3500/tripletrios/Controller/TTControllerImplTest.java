package cs3500.tripletrios.Controller;

import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TTControllerImplTest {

  private Appendable output;
  private Readable userInput;
  private TripleTrioControllerImpl controller;

  @Before
  public void setUp() {
    output = new StringBuilder();
    userInput = new StringReader("1 1 Bird\n");
    controller = new TripleTrioControllerImpl(userInput, output);
  }

  @Test
  public void testConstructorThrowsNullUserInput() {
    try {
      new TripleTrioControllerImpl(null, output);
      fail("Expected IllegalArgumentException for null userInput");
    } catch (IllegalArgumentException e) {
      assertEquals("User input or output cannot be null", e.getMessage());
    }
  }

  @Test
  public void testConstructorThrowsNullOutput() {
    try {
      new TripleTrioControllerImpl(userInput, null);
      fail("Expected IllegalArgumentException for null output");
    } catch (IllegalArgumentException e) {
      assertEquals("User input or output cannot be null", e.getMessage());
    }
  }

  @Test
  public void testPlayGameInvalidModel() {
    try {
      controller.playGame(null, "deckPath", "gridPath");
      fail("Expected IllegalArgumentException for null model");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testPlayGameThrowsNullModel() {
    try {
      controller.playGame(null, "deckPath", "gridPath");
      fail("Expected IllegalArgumentException for null model");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("model cannot be null", e.getMessage());
    }
  }
}
