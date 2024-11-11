import java.util.List;

public class Move {
    private Position _pos;
    private Disc _disc;
    private List<Disc> flipped;

    public Move(Position pos, Disc disc){
        _pos=pos;
        _disc=disc;
        setFlipped();
    }
    public Position position(){
        return _pos;
    }

    public Disc disc(){
        return _disc;
    }

    private void setFlipped(){

    }

    public List<Disc> getFlipped() {
        return flipped;
    }
}