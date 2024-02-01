package chess;

import java.util.Collection;
import java.util.HashSet;

public class KingMovesCalculator implements ChessMovesCalculator{

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board) {
        HashSet<ChessMove> moveSet = new HashSet<>();

        int y = startPosition.getRow();
        int x = startPosition.getColumn();
        ChessGame.TeamColor color = board.getPiece(startPosition).getTeamColor();

        ChessPosition endPosition;
        if (x > 1){
            endPosition = new ChessPosition(y, x-1);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
            if (y > 1) {
                endPosition = new ChessPosition(y-1, x-1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 8) {
                endPosition = new ChessPosition(y+1, x-1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }
        if (y > 1) {
            endPosition = new ChessPosition(y-1, x);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
        }
        if (y < 8) {
            endPosition = new ChessPosition(y+1, x);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
        }
        if (x < 8) {
            endPosition = new ChessPosition(y, x+1);
            CheckSquare(startPosition, board, moveSet, color, endPosition);
            if (y > 1) {
                endPosition = new ChessPosition(y-1, x+1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
            if (y < 8) {
                endPosition = new ChessPosition(y+1, x+1);
                CheckSquare(startPosition, board, moveSet, color, endPosition);
            }
        }

        // add castling
        // if white
        if (color == ChessGame.TeamColor.WHITE) {
            // castle long
            if (!board.getPiece(startPosition).isHasMoved() && board.getPiece(new ChessPosition(1, 1)) != null && board.getPiece(new ChessPosition(1,2)) == null && board.getPiece(new ChessPosition(1,3)) == null && board.getPiece(new ChessPosition(1, 4)) == null){
                if (board.getPiece(new ChessPosition(1,1 )).getPieceType() == ChessPiece.PieceType.ROOK && !board.getPiece(new ChessPosition(1, 1)).isHasMoved()){
                    moveSet.add(new ChessMove(startPosition, new ChessPosition(1, 3), null));
                }
            }
            // castle short
            if (!board.getPiece(startPosition).isHasMoved() && board.getPiece(new ChessPosition(1, 8)) != null && board.getPiece(new ChessPosition(1,7)) == null && board.getPiece(new ChessPosition(1,6)) == null){
                if (board.getPiece(new ChessPosition(1,1 )).getPieceType() == ChessPiece.PieceType.ROOK && !board.getPiece(new ChessPosition(1, 8)).isHasMoved()){
                    moveSet.add(new ChessMove(startPosition, new ChessPosition(1, 7), null));
                }
            }
        }
        // if black
        if (color == ChessGame.TeamColor.BLACK) {
            // castle long
            if (!board.getPiece(startPosition).isHasMoved() && board.getPiece(new ChessPosition(8, 1)) != null && board.getPiece(new ChessPosition(8,2)) == null && board.getPiece(new ChessPosition(8,3)) == null && board.getPiece(new ChessPosition(8, 4)) == null){
                if (board.getPiece(new ChessPosition(8,1 )).getPieceType() == ChessPiece.PieceType.ROOK && !board.getPiece(new ChessPosition(8, 1)).isHasMoved()){
                    moveSet.add(new ChessMove(startPosition, new ChessPosition(8, 3), null));
                }
            }
            // castle short
            if (!board.getPiece(startPosition).isHasMoved() && board.getPiece(new ChessPosition(8, 8)) != null && board.getPiece(new ChessPosition(8,7)) == null && board.getPiece(new ChessPosition(8,6)) == null){
                if (board.getPiece(new ChessPosition(8,1 )).getPieceType() == ChessPiece.PieceType.ROOK && !board.getPiece(new ChessPosition(8, 8)).isHasMoved()){
                    moveSet.add(new ChessMove(startPosition, new ChessPosition(8, 7), null));
                }
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
