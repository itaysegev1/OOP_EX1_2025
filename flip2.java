import java.util.ArrayList;
import java.util.List;

public class flip2 {
    // הוספתי את זה |


    //זה הוספתי מפה והלאה

    ///function number 3
    private static List<Position> flipbombneighbors(Position pos, int Boardsize, Disc[][] Board, Player player, List<Position> list) {

        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < directions.length; i++) {
            int r = pos.row() + directions[i][0];
            int c = pos.col() + directions[i][1];
            if (isvalid(r, c, Boardsize) && Board[r][c] != null) {
                if ((Board[r][c].getOwner() != player) && (!Board[r][c].getType().equals("⭕"))) {
                    if (!Board[r][c].getType().equals("\uD83D\uDCA3")) {
                        list.add(new Position(r, c));
                    }
                } else {
                    flipbombneighbors(new Position(r, c), Boardsize, Board, player, list);
                }
            }
        }
        return list;
    }

    //help to function 3
    private static boolean isvalid(int row, int col, int BoardSize) {
        if ((row < BoardSize && col < BoardSize) && (row >= 0 && col >= 0)) {
            return true;
        }
        return false;
    }

    //function number 1
    private static boolean needtoflip(Position a, int directrow, int directcol, Player player, int BoardSize, Disc[][] Board) {
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

    //function number 2!
    public static List<Position> flipaftermove(Position a, Disc[][] Board, Player firstplayer, Player secondplayer, boolean IsfirstPlayerTurn, List<Position> list, int boardsize) {
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
                    if (Board[r][c].getType().equals("\uD83D\uDCA3") && !list.contains(new Position(r, c))) {
                        list.add(new Position(r, c));
                        flipbombneighbors(new Position(r, c), boardsize, Board, p, list);
                    }
                    r += directions[i][0];
                    c += directions[i][1];
                }
            }
        }
        return list;
    }

    //function number 4
    public static List<Disc> mainflip(Position a, Disc[][] Board, Player firstplayer, Player secondplayer, boolean IsfirstPlayerTurn, int boardsize) {
        List<Position> list = new ArrayList<>();
        List<Disc> list2 = new ArrayList<>();
        list.add(a);
        flipaftermove(a, Board, firstplayer, secondplayer, IsfirstPlayerTurn, list, boardsize);
        Player p;
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        for (int i = 0; i < list.size(); i++) {
            int r = list.get(i).row();
            int c = list.get(i).col();
            if (Board[r][c] != null) {
                Board[r][c].setOwner(p);
                list2.add(Board[r][c]);
            }

        }
        return list2;
    }

    //function number 5
    public static int countflip(Position a, Disc[][] Board, Player firstplayer, Player secondplayer, boolean IsfirstPlayerTurn, int boardsize) {
        List<Position> list = new ArrayList<>();
        int flip = 0;
        list.add(a);
        flip++;
        flipaftermove(a, Board, firstplayer, secondplayer, IsfirstPlayerTurn, list, boardsize);
        Player p;
        if (IsfirstPlayerTurn)
            p = firstplayer;
        else
            p = secondplayer;
        for (int i = 0; i < list.size(); i++) {
            int r = list.get(i).row();
            int c = list.get(i).col();
            if (Board[r][c] != null) {
                Board[r][c].setOwner(p);
                flip++;
            }

        }
        return flip;
    }
}







