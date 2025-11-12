package amazons.player;

public enum PlayerID {
    PLAYER_ZERO("white", 0), PLAYER_ONE("black",1), NONE("",-1);
    public final String color;
    public final int index;

    PlayerID(String color, int index){
        this.color = color;
        this.index = index;
    }
    @Override
    public String toString(){
        return "player " + index + " ("+color+")";
    }
    public int getIndex()
    {
        return this.index ;

    }

    public PlayerID opponent(){
        return switch (this){
            case PLAYER_ZERO -> PLAYER_ONE;
            case PLAYER_ONE -> PLAYER_ZERO;
            default -> NONE;
        };
    }
    public static PlayerID getPlayerIDFromIndex(int index){
        return switch(index){
            case 0 -> PLAYER_ZERO;
            case 1 -> PLAYER_ONE;
            default -> NONE;
        };
    }

}
