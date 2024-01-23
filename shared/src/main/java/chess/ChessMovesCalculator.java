package chess;

import java.util.Collection;

interface ChessMovesCalculator {
    public Collection<ChessMove> validMoves(ChessPosition startPosition, ChessBoard board);

}
