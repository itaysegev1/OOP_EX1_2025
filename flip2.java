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
    public static int flips(List<Disc> d){
        int count=0;
        while(!d.isEmpty()){
            count++;
        }
        return count;
    }


    //function determines whether a move would result in any opponent discs
    // being flipped in a specific direction on the board.
    //לא נגעתי בזה
    private static boolean needtoflip(Position a, int directrow, int directcol, Player player,
                                      int BoardSize, Disc[][] Board) {
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
    /**
    public static List<Disc> flipaftermove(List<Disc> d,Player firstplayer, Player secondplayer, boolean IsfirstPlayerTurn){
        Player p;
        List<Disc> list = new ArrayList<>();
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        for (int i = 0; i < d.size(); i++) {

        }


    }
     **/

    //handle flipping discs after a player makes a move.
    // This function flips opponent discs
    // and it handles special bomb discs that trigger additional flips around them.
    //לא נגעתי עדייןןן
    public static List<Disc> flipaftermove(Position a, Disc[][] Board, Player firstplayer, Player secondplayer,
                                           boolean IsfirstPlayerTurn) {
        List<Position> bomblist = new ArrayList<>();
        List<Disc> list = new ArrayList<>();
        Player p;
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            if (needtoflip(a, directions[i][0], directions[i][1], p, Board.length, Board)) {
                int r = a.row() + directions[i][0];
                int c = a.col() + directions[i][1];
                while (Board[r][c].getOwner() != p) {
                    if (Board[r][c].getType().equals("\uD83D\uDCA3") && !bomblist.contains(Board[r][c])) {
                        bomblist.add(new Position(r, c));
                    }
                    Board[r][c].setOwner(p);
                    if (!Board[r][c].getType().equals("⭕")) {
                        System.out.println(p.toString() + "flipped the " + Board[r][c].getType() + " in ( " + r + " , " + c + " )");
                    }
                    list.add(Board[r][c]);
                    r += directions[i][0];
                    c += directions[i][1];
                }
            }
        }
        while (!bomblist.isEmpty()) {
            Position pos = bomblist.removeLast();
            list.addAll(flipbomb(pos, Board));
        }
        return list;
    }
// זה לדעתי צריך למחוק
    private static List<Disc> flipbomb(Position pos, Disc[][] Board) {
        Player p = Board[pos.row()][pos.col()].getOwner();
        List<Disc> a = new ArrayList<Disc>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            int r = pos.row() + directions[i][0];
            int c = pos.col() + directions[i][1];
            if (c < Board.length && c > 0 && r > 0 && r < Board.length) {
                if (Board[r][c] != null) {
                    if (Board[r][c].getOwner() != p) {
                        if (!Board[r][c].getType().equals("⭕")) {
                            a.add(Board[r][c]);
                            Board[r][c].setOwner(p);
                            System.out.println(p.toString() + " flipped the " + Board[r][c].getType() + " in " + "( " + r + " , " + c + " )");
                        }
                    }
                }
            }
        }
        return a;
    }
//גם את זה צריך למחוק
    private static int countBombflips(Position pos, Player player, int BoardSize, Disc[][]
            Board, List<Disc> bomblist) {
        int count = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            int r = pos.row() + directions[i][0];
            int c = pos.col() + directions[i][1];
            if ((r < BoardSize && c < BoardSize) && (r >= 0 && c >= 0)) {
                if (Board[r][c] != null) {
                    if (Board[r][c].getOwner() != player) {
                        if (Board[r][c].getType().equals("⬤")) {
                            count++;
                        }
                        if (Board[r][c].getType().equals("\uD83D\uDCA3") && !(Board[r][c].getOwner() != player)) {
                            count += countBombflips(new Position(r, c), player, BoardSize, Board, bomblist) + 1;
                            System.out.println("DEBUG" + bomblist.size());
                        }
                    }
                }
            }
        }

        return count;
    }
//זה הוספתי מפה והלאה
    public static List<Disc> allsides(Position pos, int Boardsize, Disc[][] Board) {
        List<Disc> sides = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < directions.length; i++) {
            int r = pos.row() + directions[i][0];
            int c = pos.col() + directions[i][1];
            if (isvalid(r, c, Boardsize)) {
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


