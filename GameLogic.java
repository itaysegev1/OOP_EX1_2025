import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameLogic implements PlayableLogic {

    private final int BoardSize = 8;
    private Disc[][] Board;
    private List<Position> ValidMoves;
    private Stack<Move> moves;
    private Player firstplayer;
    private Player secondplayer;
    private boolean IsfirstPlayerTurn;
    private static List<Disc>Bombes;

    public GameLogic() {
        Board = new Disc[BoardSize][BoardSize];
        IsfirstPlayerTurn = true;
        moves = new Stack<Move>();

    }

    @Override
    public boolean locate_disc(Position a, Disc disc) {
        if (Board[a.row()][a.col()] == null && Flip.isinvalid(a,ValidMoves)) {
            Move move = new Move(a, disc);
            moves.add(move);
            Board[a.row()][a.col()] = disc;
            move.setflips(Flip.flipaftermove(a,Board,firstplayer,secondplayer,IsfirstPlayerTurn));
            if (isFirstPlayerTurn()) {

                IsfirstPlayerTurn = false;
            } else {
                IsfirstPlayerTurn = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public Disc getDiscAtPosition(Position position) {

        return Board[position.row()][position.col()];
    }

    @Override
    public int getBoardSize() {
        return BoardSize;
    }

    @Override
    public List<Position> ValidMoves() {
        ValidMoves = new ArrayList<Position>();
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                if (Board[i][j] == null) {
                    if (countFlips(new Position(i, j)) > 0) {
                        ValidMoves.add(new Position(i, j));
                    }
                }
            }

        }
        return ValidMoves;
    }

    @Override
    public int countFlips(Position a) {
        int total = 0;
        Player p;
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            total += Flip.directflips(a, directions[i][0], directions[i][1], p,BoardSize,Board);
        }
        return total;
    }

    @Override
    public Player getFirstPlayer() {
        return firstplayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondplayer;
    }

    @Override
    public void setPlayers(Player player1, Player player2) {
        firstplayer = player1;
        secondplayer = player2;

    }

    @Override
    public boolean isFirstPlayerTurn() {
        return IsfirstPlayerTurn;
    }

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public void reset() {
        Board = new Disc[BoardSize][BoardSize];
        IsfirstPlayerTurn = true;
        moves=new Stack<Move>();
        Disc s1 = new SimpleDisc(firstplayer);
        Disc s2 = new SimpleDisc(firstplayer);
        Disc s3 = new SimpleDisc(secondplayer);
        Disc s4 = new SimpleDisc(secondplayer);
        Board[3][3] = s1;
        Board[4][4] = s2;
        Board[3][4] = s3;
        Board[4][3] = s4;


    }

    @Override
    public void undoLastMove() {
        if (!moves.isEmpty()) {
            Player p;
            if (IsfirstPlayerTurn) {
                IsfirstPlayerTurn = false;
            } else {
                IsfirstPlayerTurn = true;
            }

            Move last = moves.pop();
            Board[last.position().row()][last.position().col()] = null;
            List<Disc> a = last.getflips();
            while (!a.isEmpty()) {
                Disc d = a.removeLast();
                p = d.getOwner();
                if (p.isPlayerOne) {
                    d.setOwner(secondplayer);
                } else {
                    d.setOwner(firstplayer);
                }
            }

        }
    }
}