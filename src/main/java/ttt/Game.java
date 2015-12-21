package ttt;

public class Game {
    private int counter = 0;
    private Board board;
    private DisplayBoard display;
    private Player player;
    private Symbol cross;
    private Symbol nought;
    IO io;

    public Game(Board board, IO io) {
        cross = Symbol.CROSS;
        nought = Symbol.NOUGHT;
        this.board = board;
        this.io = io;
        this.player = new Player(io, board);
    }

    public void gameLoop() {
        initialDisplay();
        while (!checkForWin() && board.boardNotFull()) {
            io.showOutput("Player as symbol " + player.getSymbol() + " make your move:");
            player.markBoard();
            DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
            counter += 1;
            io.showOutput(currentDisplay.showBoard());
        }
        io.showOutput("Player " + player.getSymbol() + " has won!");
        io.showOutput("game over");
    }

    public void initialDisplay() {
        DisplayBoard initialDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(initialDisplay.showBoard());
    }

    public boolean checkForWin() {
        if (isWinningRow(cross) || isWinningDiagonal(cross) || isWinningColumn(cross))  {
            return true;
        }
        if (isWinningRow(nought) || isWinningDiagonal(nought) || isWinningColumn(nought)) {
            return true;
        }
        return false;
    }

    public boolean isWinningRow(Symbol symbol) {
        if (board.get(0).equals(symbol.getSymbol()) && board.get(1).equals(symbol.getSymbol()) && board.get(2).equals(symbol.getSymbol())) {
            return true;
        }
        if (board.get(3).equals(symbol.getSymbol()) && board.get(4).equals(symbol.getSymbol()) && board.get(5).equals(symbol.getSymbol())) {
            return true;
        }
        if (board.get(6).equals(symbol.getSymbol()) && board.get(7).equals(symbol.getSymbol()) && board.get(8).equals(symbol.getSymbol())) {
            return true;
        }
        return false;
    }

    public boolean isWinningColumn(Symbol symbol) {
        if (board.get(0).equals(symbol.getSymbol()) && board.get(3).equals(symbol.getSymbol()) && board.get(6).equals(symbol.getSymbol())) {
            return true;
        }
        if (board.get(1).equals(symbol.getSymbol()) && board.get(4).equals(symbol.getSymbol()) && board.get(7).equals(symbol.getSymbol())) {
            return true;
        }
        if (board.get(2).equals(symbol.getSymbol()) && board.get(5).equals(symbol.getSymbol()) && board.get(8).equals(symbol.getSymbol())) {
            return true;
        }
        return false;
    }


    public boolean isWinningDiagonal(Symbol symbol) {
        if (board.get(0).equals(symbol.getSymbol()) && board.get(4).equals(symbol.getSymbol()) && board.get(8).equals(symbol.getSymbol())) {
            return true;
        }
        if (board.get(2).equals(symbol.getSymbol()) && board.get(4).equals(symbol.getSymbol()) && board.get(6).equals(symbol.getSymbol())) {
            return true;
        }
        return false;
    }
}
