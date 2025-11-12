package amazons.game;

public enum TurnPhase {
    AMAZON_PHASE, ARROW_PHASE, END_PHASE;

    public TurnPhase next(){
        return switch (this){
            case AMAZON_PHASE -> ARROW_PHASE;
            case ARROW_PHASE -> END_PHASE;
            case END_PHASE -> AMAZON_PHASE;
        };
    }
}
