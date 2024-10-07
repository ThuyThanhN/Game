package Model;

import java.util.ArrayList;
import java.util.List;

public class Minimax {
    public Move findBestMove(Piece[][] board, String playerColor, int depth) {
        return minimax(board, playerColor, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Minimax, alpha-beta
    private Move minimax(Piece[][] board, String playerColor, int depth, int alpha, int beta) {
        if (depth == 0) {
            Move move = new Move(0, 0, 0, 0);
            move.setScore(evaluateBoard(board, playerColor));
            return move;
        }

        List<Move> moves = generateAllMoves(board, playerColor);
        Move bestMove = null;

        for (Move move : moves) {
            Piece[][] newBoard = applyMove(board, move);
            Move currentMove = minimax(newBoard, playerColor.equals("white") ? "black" : "white", depth - 1, alpha, beta);

            move.setScore(currentMove.getScore());

            if (playerColor.equals("white")) {
                if (currentMove.getScore() > alpha) {
                    alpha = currentMove.getScore();
                    bestMove = move;
                }
            } else {
                if (currentMove.getScore() < beta) {
                    beta = currentMove.getScore();
                    bestMove = move;
                }
            }

            if (beta <= alpha) {
                break; // Alpha-Beta Pruning
            }
        }

        return bestMove;
    }

//  Heuristic hợp lý
    private int evaluateBoard(Piece[][] board, String playerColor) {
        int score = 0;
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null) {
                    if (piece.getColor().equals(playerColor)) {
                        score += getPieceValue(piece);
                    } else {
                        score -= getPieceValue(piece);
                    }
                }
            }
        }
        return score;
    }

    private int getPieceValue(Piece piece) {
        if (piece instanceof Pawn)
            return 100;
        else if (piece instanceof Knight)
            return 320;
        else if (piece instanceof Bishop)
            return 330;
        else if (piece instanceof Rook)
            return 500;
        else if (piece instanceof Queen)
            return 900;
        else if (piece instanceof King)
            return 2000;
        return 0;
    }

    private List<Move> generateAllMoves(Piece[][] board, String playerColor) {
        List<Move> moves = new ArrayList<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != null && board[y][x].getColor().equals(playerColor)) {
                    for (int targetY = 0; targetY < board.length; targetY++) {
                        for (int targetX = 0; targetX < board[targetY].length; targetX++) {
                            Move move = new Move(x, y, targetX, targetY);
                            if (move.isValidMove(board[y][x], board) && targetY >= 0 && targetY < 80 && targetX >= 0 && targetX < 80) {
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }


    private Piece[][] applyMove(Piece[][] board, Move move) {
        Piece[][] newBoard = new Piece[8][8];
        for (int y = 0; y < board.length; y++) {
            System.arraycopy(board[y], 0, newBoard[y], 0, board[y].length);
        }
        newBoard[move.getEndY()][move.getEndX()] = newBoard[move.getStartY()][move.getStartX()];
        newBoard[move.getStartY()][move.getStartX()] = null;
        return newBoard;
    }
}
