import java.util.ArrayList;
import java.util.List;


public class Flip {
    public static int directflips(Position a, int directrow, int directcol, Player player, int BoardSize, Disc[][]Board) {
        int flips = 0;
        int r = a.row() + directrow;
        int c = a.col() + directcol;
        while ((r < BoardSize && c < BoardSize) && (r >= 0 && c >= 0)) {
            if (Board[r][c] == null)
                return 0;
            if (Board[r][c].getOwner() != player) {
                if(Board[r][c].getType().equals("⬤")) {
                    flips++;
                    r += directrow;
                    c += directcol;
                }
                else {
                    if (Board[r][c].getType().equals("⭕")) {
                        r += directrow;
                        c += directcol;
                        }
                    else {
                        if ( Board[r][c].getType().equals("\uD83D\uDCA3") && Board[r][c].getOwner() != player) {
                            List<Disc> d = new ArrayList<>();
                            flips += countBombflips(new Position(r, c), player,BoardSize, Board, d);
                            r += directrow;
                            c += directcol;
                        }
                    }
                }
            } else
                return flips;
        }
        return 0;
    }

    public static boolean isinvalid(Position a, List<Position> ValidMoves) {
        int r = a.row();
        int c = a.col();
        for (int i = 0; i < ValidMoves.size(); i++) {
            Position current = ValidMoves.get(i);
            if (a.row() == current.row() && a.col() == current.col())
                return true;
        }
        return false;
    }

    private static boolean needtoflip(Position a, int directrow, int directcol, Player player, int BoardSize, Disc[][]Board) {
        int flips = 0;
        int r = a.row() + directrow;
        int c = a.col() + directcol;
        while ((r < BoardSize && c < BoardSize) && (r >= 0 && c >= 0)) {
            if (Board[r][c] == null)
                return false;
            if (Board[r][c].getOwner() != player) {
                flips++;
                r += directrow;
                c += directcol;
            } else {
                if (flips > 0) {
                    return true;
                }
                return false;

            }

        }
        return false;
    }


    public static List<Disc> flipaftermove(Position a, Disc[][]Board, Player firstplayer, Player secondplayer, boolean IsfirstPlayerTurn) {
        List<Position>bomblist=new ArrayList<>();
        List<Disc> list= new ArrayList<>();
        Player p;
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            if (needtoflip(a, directions[i][0], directions[i][1], p,Board.length,Board)) {
                int r=a.row()+directions[i][0];
                int c=a.col()+directions[i][1];
                while(Board[r][c].getOwner()!=p){
                    if(Board[r][c].getOwner().equals("\uD83D\uDCA3") && !bomblist.contains(Board[r][c])){
                        bomblist.add(new Position(r,c));
                    }
                    Board[r][c].setOwner(p);
                    if(!Board[r][c].getType().equals("⭕")) {
                        System.out.println(p.toString() + "flipped the " + Board[r][c].getType() + " in ( " + r + " , " + c + " )");
                    }
                    list.add(Board[r][c]);
                    r+=directions[i][0];
                    c+=directions[i][1];
                }
            }
        }
        while(!bomblist.isEmpty()){
            Position pos=bomblist.removeLast();
            list.addAll(flipbomb(pos,Board));
        }
        System.out.println();
        return list;
    }

    private static List<Disc> flipbomb( Position pos, Disc[][] Board){
        Player p=Board[pos.row()][pos.col()].getOwner();
        List<Disc> a=new ArrayList<Disc>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i <8 ; i++) {
            int r= pos.row()+directions[i][0];
            int c=pos.col()+directions[i][1];
            if(c<Board.length&& c>0 &&r>0 && r<Board.length){
                if(Board[r][c]!=null){
                    if (Board[r][c].getOwner()!=p) {
                        if(!Board[r][c].getType().equals("⭕")){
                            a.add(Board[r][c]);
                            Board[r][c].setOwner(p);
                            System.out.println(p.toString()+ " flipped the "+Board[r][c].getType()+ " in "+ "( "+r+" , "+c+" )");
                        }
                    }
                }
            }
        }
        return a;
    }

    private static int countBombflips(Position pos, Player player,int BoardSize, Disc[][]Board,List<Disc>bomblist){
        int count=0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i <8 ; i++) {
            int r = pos.row() + directions[i][0];
            int c = pos.col() + directions[i][1];
            if((r < BoardSize && c < BoardSize) && (r >= 0 && c >= 0)){
            if (Board[r][c] != null) {
                if (Board[r][c].getOwner() != player) {
                    if (Board[r][c].getType().equals("⬤")) {
                        count++;
                    }
                    if (Board[r][c].getType().equals("\uD83D\uDCA3") &&!(Board[r][c].getOwner()!=player)) {
                            count += countBombflips(new Position(r, c), player,BoardSize, Board, bomblist) + 1;
                            System.out.println("DEBUG"+ bomblist.size());
                        }
                     }
                  }
                }
            }

        return count;
    }
}
