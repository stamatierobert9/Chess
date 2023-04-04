public class Pawn extends Piece {

    public Pawn(boolean available, int x, int y) {
        super(available, x, y);
    }

    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isValid(board, fromX, fromY, toX, toY)) {
            return false;
        }

        int direction = this.isAvailable() ? 1 : -1; // determine the direction of movement based on which side the pawn is on

        if (fromX == toX && fromY + direction == toY && board.getPieceAt(toX, toY) == null) { // moving forward by one square
            return true;
        } else if (fromX == toX && fromY + (2 * direction) == toY && this.isAvailable() && board.getPieceAt(toX, toY) == null && board.getPieceAt(toX, toY - direction) == null) { // moving forward by two squares (only possible on the pawn's first move)
            return true;
        } else if ((fromX + 1 == toX || fromX - 1 == toX) && fromY + direction == toY) { // capturing an opponent's piece diagonally
            Piece piece = board.getPieceAt(toX, toY);
            if (piece != null && piece.isAvailable() != this.isAvailable()) {
                return true;
            }
        }

        return false;
    }


}
