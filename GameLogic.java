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
   private boolean IsfirstPlayerTurn;

   public GameLogic(){
       Board=new Disc[BoardSize][BoardSize];
       IsfirstPlayerTurn=true;
       moves=new Stack<>();

   }
    @Override
    public boolean locate_disc(Position a, Disc disc) {
        if (Board[a.row()][a.col()] == null && isinvalid(a)){
            Move move=new Move(a,disc);
            moves.add(move);
            Board[a.row()][a.col()] = disc;
            if(isFirstPlayerTurn()) {

                IsfirstPlayerTurn=false;
            } else{
                IsfirstPlayerTurn=true;
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
        ValidMoves=new ArrayList<Position>();
        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
                if (Board[i][j] == null) {
                    if(countFlips(new Position(i,j))>0){
                        ValidMoves.add(new Position(i,j));
                    }
                }
            }

        }
        return ValidMoves;
    }

    @Override
    public int countFlips(Position a) {
        int total=0;
        Player p;
        if (IsfirstPlayerTurn)
            p=firstplayer;
        else
            p=secondplayer;
        int [][] directions= {{-1,0},{1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,-1},{-1,1}};
        for (int i = 0; i < 8; i++) {
                total+=directflips(a,directions[i][0],directions[i][1],p);
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
        IsfirstPlayerTurn=true;
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
        Board[last.position().row()][last.position().col()]=null;
    }

    private int directflips(Position a, int directrow, int directcol,Player player){
       int flips=0;
       int r=a.row()+directrow;
       int c=a.col()+directcol;
       while((r<BoardSize && c<BoardSize)&& (r>=0&&c>=0)){
           if(Board[r][c]==null)
               return 0;
           if(Board[r][c].getOwner()!=player) {
               flips++;
               r+=directrow;
               c+=directcol;
           }
           else
               return flips;
       }
       return 0;
    }

    private boolean isinvalid(Position a){
       int r=a.row();
       int c=a.col();
        for (int i = 0; i < ValidMoves.size(); i++) {
            Position current=ValidMoves.get(i);
            if(a.row()==current.row() && a.col()==current.col())
                return true;
        }
        return false;
    }
}
