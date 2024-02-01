package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    private boolean movedTwoSpaces; //Used for pawns for en passant

    private boolean hasMoved; //Used for kings and rooks for castling

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
        this.movedTwoSpaces = false;
        this.hasMoved = false;
    }

    public boolean isMovedTwoSpaces() {
        return movedTwoSpaces;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void setMovedTwoSpaces(boolean movedTwoSpaces) {
        this.movedTwoSpaces = movedTwoSpaces;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        ChessMovesCalculator myCalculator;
        if (this.type == PieceType.BISHOP){
            myCalculator = new BishopMovesCalculator();
        } else if (this.type == PieceType.KING) {
            myCalculator = new KingMovesCalculator();
        } else if (this.type == PieceType.KNIGHT) {
            myCalculator = new KnightMovesCalculator();
        } else if (this.type == PieceType.PAWN) {
            myCalculator = new PawnMovesCalculator();
        } else if (this.type == PieceType.QUEEN) {
            myCalculator = new QueenMovesCalculator();
        } else if (this.type == PieceType.ROOK) {
            myCalculator = new RookMovesCalculator();
        } else {
            return new HashSet<>();
        }

        return myCalculator.validMoves(myPosition, board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return movedTwoSpaces == that.movedTwoSpaces && hasMoved == that.hasMoved && pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type, movedTwoSpaces, hasMoved);
    }
}
