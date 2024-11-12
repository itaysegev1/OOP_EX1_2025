public class RandomAI extends AIPlayer{
    public RandomAI(boolean isplayerone){
        super(isplayerone);
    }
    @Override
    public Move makeMove(PlayableLogic gameStatus) {
        return null;
    }

    public String tostring(){
        if(isPlayerOne){
            return "Player 1";
        }
        return "Player 2";
    }
}
