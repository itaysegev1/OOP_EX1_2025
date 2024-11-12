import java.io.InputStreamReader;
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
                flips++;
                r += directrow;
                c += directcol;
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
        List<Disc> list=new ArrayList<Disc>();
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
                    Board[r][c].setOwner(p);
                    System.out.println(p.toString() + "flipped the " +Board[r][c].getType() + " in ( "+r+" , "+c+" )");
                    list.add(Board[r][c]);
                    r+=directions[i][0];
                    c+=directions[i][1];
                }
            }
        }
        return list;
    }
}
