package chess;

import java.util.Collection;
import java.util.HashSet;

public class QueenMovesCalculator implements ChessMovesCalculator{

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board) {
        HashSet<ChessMove> moveSet = new HashSet<>();

        ChessGame.TeamColor color = board.getPiece(startPosition).getTeamColor();
        int startY = startPosition.getRow();
        int startX = startPosition.getColumn();

        // init x and y
        int y = startY;
        int x = startX;

        // copy of Bishop moves
        // check the +x +y direction
        while (x < 8 && y < 8 ){
            x++;
            y++;
            ChessPosition endPosition = new ChessPosition(y, x);
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
        // check the -x +y direction
        while (x > 1 && y < 8){
            x--;
            y++;
            ChessPosition endPosition = new ChessPosition(y, x);
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
            ChessPosition endPosition = new ChessPosition(y, x);
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
            ChessPosition endPosition = new ChessPosition(y, x);
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
        // copy of Rook moves
        // check the +x direction
        while (x < 8){
            x++;
            ChessPosition endPosition = new ChessPosition(y, x);
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
        // check the -x direction
        while (x > 1){
            x--;
            ChessPosition endPosition = new ChessPosition(y, x);
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
        // check the +y direction
        while (y < 8){
            y++;
            ChessPosition endPosition = new ChessPosition(y, x);
            if (board.getPiece(endPosition) != null){
                if (board.getPiece(endPosition).getTeamColor() == color){
                    break;
                }
                moveSet.add(new ChessMove(startPosition, endPosition, null));
                break;
            }
            moveSet.add(new ChessMove(startPosition, endPosition, null));
        }
        y = startY;
        // check the -y direction
        while (y > 1){
            y--;
            ChessPosition endPosition = new ChessPosition(y, x);
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
