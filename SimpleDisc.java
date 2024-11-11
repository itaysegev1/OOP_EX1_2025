public class SimpleDisc implements Disc {
    private Player Owner;

    public SimpleDisc(Player owner){
        Owner=owner;
    }
    @Override
    public Player getOwner() {
        return Owner;
    }

    @Override
    public void setOwner(Player player) {
        Owner=player;
    }

    @Override
    public String getType() {
        return "⬤";
    }
}
