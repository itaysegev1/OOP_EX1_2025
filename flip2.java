import java.util.ArrayList;
import java.util.List;

public class flip2 {
    // הוספתי את זה |




//        public static List<Disc> directflips (Position a,int directrow, int directcol, Player player,
//        int BoardSize, Disc[][] Board){
//            List<Disc> d = new ArrayList<>();
//            int r = a.row() + directrow;
//            int c = a.col() + directcol;
//            while ((r < BoardSize && c < BoardSize) && (r >= 0 && c >= 0)) {
//                if (Board[r][c] == null)
//                    return d;
//                if (Board[r][c].getOwner() != player) {
//                    if (Board[r][c].getType().equals("⬤")) {
//                        d.add(Board[r][c]);
//                        r += directrow;
//                        c += directcol;
//                    } else {
//                        if (Board[r][c].getType().equals("⭕")) {
//                            r += directrow;
//                            c += directcol;
//                        } else {
//                            if (Board[r][c].getType().equals("\uD83D\uDCA3") && Board[r][c].getOwner() != player) {
//                                if (!d.contains(Board[r][c])) {
//                                    d.add(Board[r][c]);
//                                    List<Disc> n = new ArrayList<>();
//                                    n.addAll(allsides(new Position(r, c), BoardSize, Board, player));
//                                    d.addAll(n);
//                                    r += directrow;
//                                    c += directcol;
//                                }
//                            }
//                        }
//                    }
//                }
//
//            }
//            return d;
//        }

//        public static int flipscounter (List < Disc > d) {
//            int count = 0;
//            int i = 0;
//            while (!d.isEmpty() && i != d.size()) {
//                count++;
//                i++;
//            }
//            return count;
//        }
//זה הוספתי מפה והלאה
        public static List<Disc> flipbombneighbors (Position pos,int Boardsize, Disc[][] Board, Player player){
            System.out.println("DEBUGGGG");
            List<Disc> sides = new ArrayList<>();
            int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
            for (int i = 0; i < directions.length; i++) {
                int r = pos.row() + directions[i][0];
                int c = pos.col() + directions[i][1];
                if (isvalid(r, c, Boardsize) && Board[r][c] != null) {
                    if (Board[r][c].getOwner() == player) {
                        sides.add(Board[r][c]);
                        System.out.println(player.toString() + "flipped the " + Board[r][c].getType() + " in ( " + r + " , " + c + " )");
                    }
                }

            }
            return sides;
        }

        private static boolean isvalid ( int row, int col, int BoardSize){
            if ((row < BoardSize && col < BoardSize) && (row >= 0 && col >= 0)) {
                return true;
            }
            return false;
        }
        private static List<Disc> finallistflip(Player p, List<Disc> fl,Disc[][] Board){
            List<Disc> flipped = new ArrayList<>();
            for (int i = 0; i < fl.size(); i++) {
                if(fl.get(i).getOwner()!=p){
                    fl.get(i).setOwner(p);
                    flipped.add(fl.get(i));
                }
            }
            return flipped;
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

    public static List<Disc> fliipedaftermove(Position a, Disc[][]Board, Player firstplayer, Player secondplayer, boolean IsfirstPlayerTurn,int boardsize) {
        List<Disc> list = new ArrayList<>();
        List<Position> bomblist = new ArrayList<>();
        Player p;
        if (IsfirstPlayerTurn) {
            p = firstplayer;
        } else {
            p = secondplayer;
        }
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            if (needtoflip(a, directions[i][0], directions[i][1], p, Board.length, Board)) {
                int r = a.row() + directions[i][0];
                int c = a.col() + directions[i][1];
                if (Board[r][c].getType().equals("\uD83D\uDCA3") && !bomblist.contains(Board[r][c])) {
                    bomblist.add(new Position(r,c));
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

        while (!bomblist.isEmpty()) {
            Position pos = bomblist.removeLast();
            list.addAll(flipbombneighbors(pos,boardsize,Board,p));
        }
            return list;
        }
    }





