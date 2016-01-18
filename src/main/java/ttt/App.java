package ttt;

import ttt.board.Board;
import ttt.inputOutput.ConsoleIO;
import ttt.inputOutput.IO;
import ttt.player.HumanPlayer;
import ttt.player.RealComputerPlayer;

import java.util.List;

public class App {
    public static void main(String[] args) {
        GameSetup initialSetup = new GameSetup();
        List<Symbol> empty = initialSetup.emptyBoard(3, 3);
        Board board = new Board();
        IO io = new ConsoleIO(System.in, System.out);
        HumanPlayer human = new HumanPlayer(io, board);
        RealComputerPlayer realComputerPlayer = new RealComputerPlayer(board);
        Game newGame = new Game(board, io, realComputerPlayer, human);
        newGame.gameLoop();
    }
}
