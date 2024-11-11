public class HumanPlayer extends Player{
    public HumanPlayer(boolean player1){
        super(player1);
    }

    @Override
    boolean isHuman() {
        return true;
    }
}
