public class BombDisc implements Disc {
    private Player Owner;

    public BombDisc(Player owner){
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
        return "\uD83D\uDCA3";
    }
}

