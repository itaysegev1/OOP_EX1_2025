public class UnflippableDisc implements Disc{
    private Player Owner;

    public UnflippableDisc(Player owner){
        Owner=owner;
    }
    @Override
    public Player getOwner() {
        return Owner;
    }

    @Override
    public void setOwner(Player player) {

    }


    @Override
    public String getType() {
        return "⭕";
    }
}
