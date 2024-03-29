package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    // This can be either a list or a 2-dimensional array
    private ChessPiece[][] pieceArray;


    public ChessBoard() {
        this.pieceArray = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int y = position.getRow();
        int x = position.getColumn();

        this.pieceArray[y-1][x-1] = piece;
    }

    public void movePiece(ChessMove move) throws InvalidMoveException {



        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();
        ChessPiece.PieceType promotion = move.getPromotionPiece();

        ChessPiece piece = this.getPiece(start);
        int startX = start.getRow();
        int startY = start.getColumn();
        this.pieceArray[startX-1][startY-1] = null;

        if (promotion == null){
            this.addPiece(end, piece);
        }
        else {
            this.addPiece(end, new ChessPiece(piece.getTeamColor(), promotion));
        }
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int y = position.getRow();
        int x = position.getColumn();

        return this.pieceArray[y-1][x-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        this.addPiece(new ChessPosition(1, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        this.addPiece(new ChessPosition(1, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(1, 3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(1, 4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        this.addPiece(new ChessPosition(1, 5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        this.addPiece(new ChessPosition(1, 6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(1, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(1, 8), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        for (int i = 1; i < 9; i++) {
            this.addPiece(new ChessPosition(2, i), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        }

        this.addPiece(new ChessPosition(8, 1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        this.addPiece(new ChessPosition(8, 2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(8, 3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(8, 4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        this.addPiece(new ChessPosition(8, 5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        this.addPiece(new ChessPosition(8, 6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(8, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(8, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        for (int i = 1; i < 9; i++) {
            this.addPiece(new ChessPosition(7, i), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }
    }

    public ChessBoard copy(){
        ChessBoard copy = new ChessBoard();
        copy.resetBoard();

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                copy.addPiece(new ChessPosition(i,j), getPiece(new ChessPosition(i,j)));
            }
        }

        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Arrays.deepEquals(pieceArray, that.pieceArray);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(pieceArray);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceArray[i][j] == null){
                    output.append(" _ ");
                } else if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.PAWN) {
                    if (pieceArray[i][j].getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output.append(" P ");
                    }
                    else {
                        output.append(" p ");
                    }
                } else if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.ROOK) {
                    if (pieceArray[i][j].getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output.append(" R ");
                    }
                    else {
                        output.append(" r ");
                    }
                } else if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.KNIGHT) {
                    if (pieceArray[i][j].getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output.append(" N ");
                    }
                    else {
                        output.append(" n ");
                    }
                } else if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.BISHOP) {
                    if (pieceArray[i][j].getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output.append(" B ");
                    }
                    else {
                        output.append(" b ");
                    }
                } else if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.KING) {
                    if (pieceArray[i][j].getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output.append(" K ");
                    }
                    else {
                        output.append(" k ");
                    }
                } else if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.QUEEN) {
                    if (pieceArray[i][j].getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output.append(" Q ");
                    }
                    else {
                        output.append(" q ");
                    }
                }
            }
            output.append("\n");
        }
        return output.toString();
    }
}
