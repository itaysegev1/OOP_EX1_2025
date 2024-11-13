import java.util.ArrayList;
import java.util.List;

public class flip2 {
    // הוספתי את זה |
    public static List<Disc> directflips(Position a, int directrow, int directcol, Player player, int BoardSize, Disc[][] Board) {
        List<Disc> d = new ArrayList<>();
        int r = a.row() + directrow;
        int c = a.col() + directcol;
        while ((r < BoardSize && c < BoardSize) && (r >= 0 && c >= 0)) {
            if (Board[r][c] == null)
                return d;
            if (Board[r][c].getOwner() != player) {
                if (Board[r][c].getType().equals("⬤")) {
                    d.add(Board[r][c]);
                    r += directrow;
                    c += directcol;
                } else {
                    if (Board[r][c].getType().equals("⭕")) {
                        r += directrow;
                        c += directcol;
                    } else {
                        if (Board[r][c].getType().equals("\uD83D\uDCA3") && Board[r][c].getOwner() != player) {
                            if (!d.contains(Board[r][c])) {
                                d.add(Board[r][c]);
                                List<Disc> n = new ArrayList<>();
                                n.addAll(allsides(new Position(r, c), BoardSize, Board));
                                d.addAll(n);
                                r += directrow;
                                c += directcol;
                            }
                        }
                    }
                }
            }

        }
        return d;
    }

    public static int flips(List<Disc> d) {
        int count = 0;
        int i=0;
        while (!d.isEmpty()&& i!=d.size()) {
            count++;
            i++;
        }
        return count;
    }

    private static List<Disc> ntf(List<Disc> d, Player player,Player firstplayer, Player secondplayer,
                               boolean IsfirstPlayerTurn) {
       List<Disc> newd= new ArrayList<>();
        Player p;
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        for (int i = 0; i < d.size(); i++) {
           d.get(i).setOwner(p);
            newd.add(d.get(i));
        }
        return newd;
    }

//זה הוספתי מפה והלאה
    public static List<Disc> allsides(Position pos, int Boardsize, Disc[][] Board) {
        List<Disc> sides = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < directions.length; i++) {
            int r = pos.row() + directions[i][0];
            int c = pos.col() + directions[i][1];
            if (isvalid(r, c, Boardsize)&& Board[r][c]!=null) {
                sides.add(Board[r][c]);
            }

        }
        return sides;
    }

    public static boolean isvalid(int row, int col, int BoardSize) {
        if ((row < BoardSize && col < BoardSize) && (row >= 0 && col >= 0)) {
            return true;
        }
        return false;
    }
}



