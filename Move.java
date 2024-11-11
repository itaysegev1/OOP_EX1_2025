import java.util.ArrayList;
import java.util.List;

public class Move {
    private Position _pos;
    private Disc _disc;


    public Move(Position pos, Disc disc){
        _pos=pos;
        _disc=disc;
    }
    public Position position(){
        return _pos;
    }

    public Disc disc(){
        return _disc;
    }

}