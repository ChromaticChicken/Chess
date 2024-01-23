package chess;

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
        int x = position.getRow();
        int y = position.getColumn();

        this.pieceArray[x-1][y-1] = piece;
    }

    public void movePiece(ChessMove move) throws InvalidMoveException {
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();

        ChessPiece piece = this.getPiece(start);
        int startX = start.getRow();
        int startY = start.getColumn();
        this.pieceArray[startX-1][startY-1] = null;

        this.addPiece(end, piece);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int x = position.getRow();
        int y = position.getColumn();

        return this.pieceArray[x-1][y-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        this.addPiece(new ChessPosition(1, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        this.addPiece(new ChessPosition(2, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(3, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(4, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        this.addPiece(new ChessPosition(5, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        this.addPiece(new ChessPosition(6, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(7, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(8, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        for (int i = 1; i < 9; i++) {
            this.addPiece(new ChessPosition(i, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        }

        this.addPiece(new ChessPosition(1, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        this.addPiece(new ChessPosition(2, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(3, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(4, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        this.addPiece(new ChessPosition(5, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        this.addPiece(new ChessPosition(6, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        this.addPiece(new ChessPosition(7, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        this.addPiece(new ChessPosition(8, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        for (int i = 1; i < 9; i++) {
            this.addPiece(new ChessPosition(i, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }
    }

}
