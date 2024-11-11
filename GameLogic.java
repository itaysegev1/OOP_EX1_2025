import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameLogic implements PlayableLogic {

    private final int BoardSize=8;
    private  Disc [][] Board;
    private List<Position> ValidMoves;
    private Stack<Move> moves;
    private Player firstplayer;
   private Player secondplayer;
   private Player currentplayer;
   private boolean IsfirstPlayerTurn;

   public GameLogic(){
       Board=new Disc[BoardSize][BoardSize];
       currentplayer=firstplayer;
       moves=new Stack<>();

   }
    @Override
    public boolean locate_disc(Position a, Disc disc) {

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
        ValidMoves=new ArrayList<Position>();
        Position p=new Position(5,4);
        ValidMoves.add(p);
        return ValidMoves;
    }

    @Override
    public int countFlips(Position a) {
        return 0;
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
        firstplayer=player1;
        secondplayer=player2;

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
        Board=new Disc[BoardSize][BoardSize];
        currentplayer=firstplayer;
        Disc s1=new SimpleDisc(firstplayer);
        Disc s2=new SimpleDisc(firstplayer);
        Disc s3=new SimpleDisc(secondplayer);
        Disc s4=new SimpleDisc(secondplayer);
        Board[3][4]=s1;
        Board[4][3]=s2;
        Board[3][3]=s3;
        Board[4][4]=s4;

    }

    @Override
    public void undoLastMove() {
        if (IsfirstPlayerTurn){IsfirstPlayerTurn=false;}
        else {IsfirstPlayerTurn=true;}
        Move last=moves.pop();
        List<Disc> lastturn=last.getFlipped();
    }
}
