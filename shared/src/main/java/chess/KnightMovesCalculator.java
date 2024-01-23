package chess;

import java.util.Collection;
import java.util.HashSet;

public class KnightMovesCalculator implements ChessMovesCalculator{

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board) {
        HashSet<ChessMove> moveSet = new HashSet<>();

        int x = startPosition.getRow();
        int y = startPosition.getColumn();
        ChessGame.TeamColor color = board.getPiece(startPosition).getTeamColor();

        ChessPosition endPosition;
        // check x-2
        if (x > 2) {
            if (y > 1){
                endPosition = new ChessPosition(x-2, y-1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 8){
                endPosition = new ChessPosition(x-2, y+1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }
        // check x-1
        if (x > 1) {
            if (y > 2) {
                endPosition = new ChessPosition(x-1, y-2);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 7) {
                endPosition = new ChessPosition(x-1, y+2);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }
        // check x+1
        if (x < 8) {
            if (y > 2) {
                endPosition = new ChessPosition(x+1, y-2);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 7) {
                endPosition = new ChessPosition(x+1, y+2);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }
        // check x+2
        if (x < 7) {
            if (y > 1){
                endPosition = new ChessPosition(x+2, y-1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 8){
                endPosition = new ChessPosition(x+2, y+1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }

        return moveSet;
    }

    static void CheckSquare(ChessPosition startPosition, ChessBoard board, HashSet<ChessMove> moveSet, ChessGame.TeamColor color, ChessPosition endPosition) {

        if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() == color){
            return;
        }
        moveSet.add(new ChessMove(startPosition, endPosition, null));
    }
}
