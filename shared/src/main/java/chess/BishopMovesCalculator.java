package chess;

import java.util.Collection;
import java.util.HashSet;

public class BishopMovesCalculator implements ChessMovesCalculator{



    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board) {
        HashSet<ChessMove> moveSet = new HashSet<>();

        ChessGame.TeamColor color = board.getPiece(startPosition).getTeamColor();
        int startX = startPosition.getRow();
        int startY = startPosition.getColumn();

        // init x and y
        int x = startX;
        int y = startY;

        // check the +x +y direction
        while (x < 8 && y < 8 ){
            x++;
            y++;
            ChessPosition endPosition = new ChessPosition(x, y);
            if (board.getPiece(endPosition) != null){
                if (board.getPiece(endPosition).getTeamColor() == color){
                    break;
                }
                moveSet.add(new ChessMove(startPosition, endPosition, null));
                break;
            }
            moveSet.add(new ChessMove(startPosition, endPosition, null));
        }

        // reset x and y
        x = startX;
        y = startY;

        // check the -x +y direction
        while (x > 1 && y < 8){
            x--;
            y++;
            ChessPosition endPosition = new ChessPosition(x, y);
            if (board.getPiece(endPosition) != null){
                if (board.getPiece(endPosition).getTeamColor() == color){
                    break;
                }
                moveSet.add(new ChessMove(startPosition, endPosition, null));
                break;
            }
            moveSet.add(new ChessMove(startPosition, endPosition, null));
        }
        x = startX;
        y = startY;
        // check the -x -y direction
        while (x > 1 && y > 1){
            x--;
            y--;
            ChessPosition endPosition = new ChessPosition(x, y);
            if (board.getPiece(endPosition) != null){
                if (board.getPiece(endPosition).getTeamColor() == color){
                    break;
                }
                moveSet.add(new ChessMove(startPosition, endPosition, null));
                break;
            }
            moveSet.add(new ChessMove(startPosition, endPosition, null));
        }
        x = startX;
        y = startY;
        // check the +x -y direction
        while (x < 8 && y > 1){
            x++;
            y--;
            ChessPosition endPosition = new ChessPosition(x, y);
            if (board.getPiece(endPosition) != null){
                if (board.getPiece(endPosition).getTeamColor() == color){
                    break;
                }
                moveSet.add(new ChessMove(startPosition, endPosition, null));
                break;
            }
            moveSet.add(new ChessMove(startPosition, endPosition, null));
        }

        return moveSet;
    }
}
