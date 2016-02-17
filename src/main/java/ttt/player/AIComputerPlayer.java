package ttt.player;

import ttt.Symbol;
import ttt.board.Board;

import static ttt.Symbol.O;
import static ttt.Symbol.X;

public class AIComputerPlayer implements Player {
    private Symbol opponentSymbol;
    private Symbol winningSymbol;

    public AIComputerPlayer(Symbol symbol) {
        this.winningSymbol = symbol;
        this.opponentSymbol = winningSymbol == X ? O : X;
    }

    public BestMove minimax(int depth, Board board, Symbol currentSymbol) {
        BestMove bestMove = new BestMove(-1, 0);
        resetScore(currentSymbol, bestMove);

        if (!board.gameNotOver() || depth == 0) {
            return new BestMove(-1, scoreBoard(board));
        }

        for (int emptyCell : board.validMoves()) {

            Board copyOfBoard = board.markPlayer(emptyCell, currentSymbol);

            BestMove newScore = minimax(depth - 1, copyOfBoard, switchPlayer(currentSymbol));
            int temporaryScore = newScore.score;

            if (currentSymbol.equals(winningSymbol) && temporaryScore >= bestMove.score) {
                bestMove.index = emptyCell;
                bestMove.score = temporaryScore;
            } else if (currentSymbol.equals(opponentSymbol) && temporaryScore <= bestMove.score) {
                bestMove.index = emptyCell;
                bestMove.score = temporaryScore;
            }
        }
        return new BestMove(bestMove.index, bestMove.score);
    }

    private Symbol switchPlayer(Symbol currentSymbol) {
         return currentSymbol == winningSymbol ? opponentSymbol : winningSymbol;
    }

    private void resetScore(Symbol symbol, BestMove bestMove) {
        if (symbol == winningSymbol) {
            bestMove.score = -100;
        } else {
            bestMove.score = 100;
        }
    }

    public int scoreBoard(Board copyOfBoard) {
        if (copyOfBoard.winningSymbol(winningSymbol)) {
            return 1;
        } else if (copyOfBoard.winningSymbol(opponentSymbol)) {
            return -1;
        }
        return 0;
    }

    public Symbol playerSymbol() {
        return winningSymbol;
    }

    public int move(Board board) {
        BestMove bestMove = minimax(7, board, winningSymbol);
        return bestMove.index;
    }
}