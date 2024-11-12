public class BombDisc implements Disc {
    private Player Owner;

    public BombDisc(Player owner){
        Owner=owner;
    }
    /***
     * Get the player who owns the Disc.
     * The player who is the owner of Bomb disc.
     */
    @Override
    public Player getOwner() {
        return Owner;
    }
    /**
     * Set the player who owns the BombDisc.
     *
     */

    @Override
    public void setOwner(Player player) {
        Owner=player;
    }
    /**
     * Get the type of the disc.
     *we used the:
     *        "💣"
     *       Bomb Disc
     */
    @Override
    public String getType() {
        return "💣";
    }
}

