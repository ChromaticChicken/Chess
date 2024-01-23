package chess;

import java.util.Collection;
import java.util.HashSet;

public class PawnMoveCalculator implements ChessMovesCalculator{

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board) {
        HashSet<ChessMove> moveSet = new HashSet<>();

        int y = startPosition.getRow();
        int x = startPosition.getColumn();
        ChessGame.TeamColor color = board.getPiece(startPosition).getTeamColor();

        // a pawn shouldn't exist here so just return an empty set
        if (y == 1 || y == 8){
            return new HashSet<>();
        }

        ChessPosition endPosition;

        // if white (moving in +y direction)
        if (color == ChessGame.TeamColor.WHITE) {
            // if not on 7th column
            if (y != 7) {
                if (x > 1) {
                    endPosition = new ChessPosition(y+1, x-1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        moveSet.add(new ChessMove(startPosition, endPosition, null));
                    }
                }
                endPosition = new ChessPosition(y+1, x);
                if (board.getPiece(endPosition) == null) {
                    moveSet.add(new ChessMove(startPosition, endPosition, null));
                }
                if (x < 8) {
                    endPosition = new ChessPosition(y+1, x+1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        moveSet.add(new ChessMove(startPosition, endPosition, null));
                    }
                }
            }
            // if on 7th column
            else {
                if (x > 1) {
                    endPosition = new ChessPosition(y+1, x-1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                            if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                                continue;
                            }
                            moveSet.add(new ChessMove(startPosition, endPosition, type));
                        }
                    }
                }
                endPosition = new ChessPosition(y+1, x);
                if (board.getPiece(endPosition) == null) {
                    for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                        if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                            continue;
                        }
                        moveSet.add(new ChessMove(startPosition, endPosition, type));
                    }
                }
                if (x < 8) {
                    endPosition = new ChessPosition(y+1, x+1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                            if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                                continue;
                            }
                            moveSet.add(new ChessMove(startPosition, endPosition, type));
                        }
                    }
                }
            }
            // if on 2nd column (first move) add the double move
            if (y == 2) {
                if (board.getPiece(new ChessPosition(y+1, x)) == null && board.getPiece(new ChessPosition(y+2, x)) == null) {
                    moveSet.add(new ChessMove(startPosition, new ChessPosition(y+2, x), null));
                }
            }
        }

        // if black (moving in -y direction)
        if (color == ChessGame.TeamColor.BLACK){
            // if not on 2nd column
            if (y != 2) {
                if (x > 1) {
                    endPosition = new ChessPosition(y - 1, x - 1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        moveSet.add(new ChessMove(startPosition, endPosition, null));
                    }
                }
                endPosition = new ChessPosition(y-1, x);
                if (board.getPiece(endPosition) == null) {
                    moveSet.add(new ChessMove(startPosition, endPosition, null));
                }
                if (x < 8) {
                    endPosition = new ChessPosition(y-1, x+1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        moveSet.add(new ChessMove(startPosition, endPosition, null));
                    }
                }
            }
            // if on 7th column
            else {
                if (x > 1) {
                    endPosition = new ChessPosition(y-1, x-1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                            if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                                continue;
                            }
                            moveSet.add(new ChessMove(startPosition, endPosition, type));
                        }
                    }
                }
                endPosition = new ChessPosition(y-1, x);
                if (board.getPiece(endPosition) == null) {
                    for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                        if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                            continue;
                        }
                        moveSet.add(new ChessMove(startPosition, endPosition, type));
                    }
                }
                if (x < 8) {
                    endPosition = new ChessPosition(y-1, x+1);
                    if (board.getPiece(endPosition) != null && board.getPiece(endPosition).getTeamColor() != color) {
                        for (ChessPiece.PieceType type : ChessPiece.PieceType.values()) {
                            if (type == ChessPiece.PieceType.PAWN || type == ChessPiece.PieceType.KING) {
                                continue;
                            }
                            moveSet.add(new ChessMove(startPosition, endPosition, type));
                        }
                    }
                }
            }
            // if on 7th column (first move) add the double move
            if (y == 7) {
                if (board.getPiece(new ChessPosition(y-1, x)) == null && board.getPiece(new ChessPosition(y-2, x)) == null) {
                    moveSet.add(new ChessMove(startPosition, new ChessPosition(y-2, x), null));
                }
            }
        }

        return moveSet;
    }
}
