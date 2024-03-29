package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor turnColor;
    private ChessBoard board;

    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.turnColor;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.turnColor = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets all valid moves for the given team
     * Currently does not account for leaving the king in check/checkmate
     *
     * @param team the team to get valid moves for
     * @return Set of all valid moves for requested team, or null if no valid moves
     */
    private Collection<ChessMove> allValidMoves(ChessGame.TeamColor team) {
        HashSet<ChessMove> moveSet = new HashSet<>();
        ChessPosition startPosition;
        ChessMovesCalculator myCalculator;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                startPosition = new ChessPosition(i+1, j+1);
                ChessPiece startPiece = this.board.getPiece(startPosition);
                if (startPiece == null || startPiece.getTeamColor() != team){
                    continue;
                }
                ChessPiece.PieceType pieceType = startPiece.getPieceType();
                if (pieceType == ChessPiece.PieceType.ROOK){
                    myCalculator = new RookMovesCalculator();
                }
                else if (pieceType == ChessPiece.PieceType.KNIGHT){
                    myCalculator = new KnightMovesCalculator();
                }
                else if (pieceType == ChessPiece.PieceType.BISHOP){
                    myCalculator = new BishopMovesCalculator();
                }
                else if (pieceType == ChessPiece.PieceType.KING){
                    myCalculator = new KingMovesCalculator();
                }
                else if (pieceType == ChessPiece.PieceType.QUEEN){
                    myCalculator = new QueenMovesCalculator();
                }
                else{
                    myCalculator = new PawnMovesCalculator();
                }
                moveSet.addAll(myCalculator.validMoves(startPosition, this.board));
            }
        }

        return moveSet;
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {

        ChessPiece startPiece = this.board.getPiece(startPosition);
        if (startPiece == null){
            return null;
        }
        HashSet<ChessMove> pieceMoves = (HashSet<ChessMove>) startPiece.pieceMoves(board, startPosition);

        ChessBoard originalBoard = board;
        ChessBoard copyBoard;
        ChessGame.TeamColor originalTeam = turnColor;
        ChessGame.TeamColor teamColor = board.getPiece(startPosition).getTeamColor();

        HashSet<ChessMove> validPieceMoves = new HashSet<>();

        for (ChessMove move: pieceMoves) {
            copyBoard = originalBoard.copy();
            board = copyBoard;
            try {
                board.movePiece(move);
                if (!isInCheck(teamColor)) {
                    validPieceMoves.add(move);
                }
            }
            catch (InvalidMoveException e) {
                continue;
            }
        }

        board = originalBoard;
        turnColor = originalTeam;
        return validPieceMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (board.getPiece(move.getStartPosition()).getTeamColor() != turnColor) {
            throw new InvalidMoveException();
        }
        HashSet<ChessMove> possibleMoves = (HashSet<ChessMove>) validMoves(move.getStartPosition());
        if (!possibleMoves.contains(move)){
            throw new InvalidMoveException();
        }

        this.board.movePiece(move);
        if (turnColor == TeamColor.WHITE) {
            turnColor = TeamColor.BLACK;
        }
        else {
            turnColor = TeamColor.WHITE;
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        // find king position
        ChessPosition kingPosition = null;
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (board.getPiece(new ChessPosition(i,j)) != null && board.getPiece(new ChessPosition(i, j)).getPieceType() == ChessPiece.PieceType.KING && board.getPiece(new ChessPosition(i,j)).getTeamColor() == teamColor) {
                    kingPosition = new ChessPosition(i,j);
                }
            }
        }
        HashSet<ChessMove> otherTeamsMoves = new HashSet<>();
        if (teamColor == TeamColor.WHITE) {
            otherTeamsMoves.addAll(this.allValidMoves(TeamColor.BLACK));
        } else {
            otherTeamsMoves.addAll(this.allValidMoves(TeamColor.WHITE));
        }
        for (ChessMove move: otherTeamsMoves) {
            if (move.getEndPosition().equals(kingPosition)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            return false;
        }

        HashSet<ChessMove> possibleMoves = (HashSet<ChessMove>) allValidMoves(teamColor);
        ChessBoard originalBoard = board;
        ChessGame.TeamColor originalTeam = teamColor;
        ChessBoard copyBoard;

        for (ChessMove move: possibleMoves) {
            copyBoard = originalBoard.copy();
            board = copyBoard;
            try {
                copyBoard.movePiece(move);
                if (!isInCheck(teamColor)) {
                    board = originalBoard;
                    return false;
                }
            }
            catch (InvalidMoveException e) {
                continue;
            }
        }

        board = originalBoard;
        turnColor = originalTeam;
        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }

        HashSet<ChessMove> possibleMoves = (HashSet<ChessMove>) allValidMoves(teamColor);
        ChessBoard originalBoard = board;
        ChessGame.TeamColor originalTeam = teamColor;
        ChessBoard copyBoard;


        for (ChessMove move: possibleMoves) {
            copyBoard = board.copy();
            board = copyBoard;

            try {
                copyBoard.movePiece(move);
                if (!isInCheck(teamColor)) {
                    board = originalBoard;
                    return false;
                }
            }
            catch (InvalidMoveException e) {
                continue;
            }
        }

        board = originalBoard;
        turnColor = originalTeam;
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}
