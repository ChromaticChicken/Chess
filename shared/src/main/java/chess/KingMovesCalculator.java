package chess;

import java.util.Collection;
import java.util.HashSet;

public class KingMovesCalculator implements ChessMovesCalculator{

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board) {
        HashSet<ChessMove> moveSet = new HashSet<>();

        int x = startPosition.getRow();
        int y = startPosition.getColumn();
        ChessGame.TeamColor color = board.getPiece(startPosition).getTeamColor();

        ChessPosition endPosition;
        if (x > 1){
            endPosition = new ChessPosition(x-1, y);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
            if (y > 1) {
                endPosition = new ChessPosition(x-1, y-1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 8) {
                endPosition = new ChessPosition(x-1, y+1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }
        if (y > 1) {
            endPosition = new ChessPosition(x, y-1);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
        }
        if (y < 8) {
            endPosition = new ChessPosition(x, y+1);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
        }
        if (x < 8) {
            endPosition = new ChessPosition(x+1, y);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
            if (y > 1) {
                endPosition = new ChessPosition(x+1, y-1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 8) {
                endPosition = new ChessPosition(x+1, y+1);
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
