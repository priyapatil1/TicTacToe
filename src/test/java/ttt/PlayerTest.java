package ttt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;

public class PlayerTest {
    String empty = "-";
    String nought = "O";
    String cross = "X";
    List<String> emptyBoard = Arrays.asList(empty, empty, empty,
            empty, empty, empty,
            empty, empty, empty);
    Board currentBoard = new Board(emptyBoard);

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    @Test
        public void playerMarksMove() {
            FakeIO fakeInput = getFakeIO(Arrays.asList("0"));
            Player player = new Player(fakeInput, currentBoard);
            Assert.assertEquals("0", player.takesUserInput());
        }

    @Test
        public void boardChangedWhenPlayerMakesMove() {
        List<String> changedBoard = Arrays.asList(cross, nought, empty,
                                                  empty, empty, empty,
                                                  empty, empty, empty);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Ignore
    @Test
        public void switchesPlayersFromCrossToNought() {
        List<String> changedBoard = Arrays.asList(cross, nought, empty,
                                                  empty, empty, empty,
                                                  empty, empty, empty);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void switchesPlayerFromNoughtToCross() {
        List<String> changedBoard = Arrays.asList(cross, cross, empty,
                                                  empty, nought, empty,
                                                  empty, nought, cross);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "7", "8", "4", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            player.markBoard();
            player.markBoard();
            player.markBoard();
            Assert.assertEquals(changedBoard, currentBoard.getCurrentBoard());
        }

    @Test
        public void getsThePlayerWhoJustPlayed() {
        List<String> changedBoard = Arrays.asList(cross, cross, empty,
                                                  empty, nought, empty,
                                                  empty, nought, cross);
            FakeIO fakeInput = getFakeIO(Arrays.asList("0", "7", "8", "4", "1"));
            Player player = new Player(fakeInput, currentBoard);
            player.markBoard();
            player.markBoard();
            Assert.assertEquals("O", player.getSymbol());
        }

    @Ignore
    @Test
        public void playerOnlyMakesValidMove() {
        ByteArrayOutputStream recordedOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(recordedOutput);
        List<String> newBoard = Arrays.asList(empty, cross, empty,
                                              empty, nought, empty,
                                              empty, nought, cross);
        InputStream inputStream = new ByteArrayInputStream("1\n1\n".getBytes());
        ConsoleIO io = new ConsoleIO(inputStream, out);
        Board currentBoard  = new Board(newBoard);
        Game newGame = new Game(currentBoard, io);
        Player player = new Player(io, currentBoard);
        player.markBoard();
        Assert.assertTrue(recordedOutput.toString().contains("that position is already taken, try again"));
        }
}

class FakeIO implements IO {
    private LinkedList<String> input;

    public FakeIO(List<String> input) {
        this.input = new LinkedList<String>(input);
    }

    @Override
        public String takeInput() {
            return input.pop();
        }

    @Override
        public String showOutput(String message) {
            return message;
        }
}
