public class HumanPlayer extends Player{
    public HumanPlayer(boolean player1){
        super(player1);
    }

    @Override
    boolean isHuman() {
        return true;
    }

    public String tostring(){
        if(isPlayerOne){
            return "Player 1";
        }
        return "Player 2";
    }
}
