import java.util.ArrayList;
import java.util.List;

public class Move {
    private Position _pos;
    private Disc _disc;
    private List<Disc> listofflips;


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
    public void setflips(List<Disc>a){
        listofflips=a;
    }

    public List<Disc> getflips() {
        return listofflips;
    }
}