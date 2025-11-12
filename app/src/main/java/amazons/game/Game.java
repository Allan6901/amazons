package amazons.game;

import amazons.IllegalMoveException;
import amazons.board.*;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;
import amazons.player.Move;
import amazons.player.Player;
import amazons.player.PlayerID;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int DEFAULT_NUMBER_OF_AMAZONS = 4;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 10;
    private static  final int DEFAULT_NUMBER_OF_ROWS = 10;
    private final MatrixBoard board;

    private final int numberOfColumns;
    private final int numberOfRows;


    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));

    private final List<Position>[] initialPositions;

    private final Player[] players = new Player[NUMBER_OF_PLAYERS];

    private PlayerID winner;

    private int turn = 0;
    private boolean isThisIsTheEnd = false;

    private final int numberOfAmazons;


    /**
     * initialize game with parameters
     */
    public Game() {
        this.numberOfColumns = DEFAULT_NUMBER_OF_COLUMNS;
        this.numberOfRows = DEFAULT_NUMBER_OF_ROWS;
        this.numberOfAmazons = DEFAULT_NUMBER_OF_AMAZONS;
        this.initialPositions = new List[NUMBER_OF_PLAYERS];
        this.initialPositions[0] = DEFAULT_PLAYER0_POSITIONS;
        this.initialPositions[1] = DEFAULT_PLAYER1_POSITIONS;

        this.board = new MatrixBoard(numberOfColumns, numberOfRows);
    }

    /**
     *initialize player and amazons by default
     * @param player0: player 0
     * @param player1: player 1
     */
    public void initializeGame(Player player0, Player player1) {
        players[0] = player0;
        players[1] = player1;
        players[0].initialize(numberOfColumns, numberOfRows, PlayerID.PLAYER_ZERO, initialPositions);
        players[1].initialize(numberOfColumns, numberOfRows, PlayerID.PLAYER_ONE, initialPositions);
        Map<MovableFigure, Position> figuresAndPositionsMap = new HashMap<>();
        for(MovableFigure figure : createPlayersFiguresWithDefaultPosition() ){
            figuresAndPositionsMap.put(figure, figure.getPosition());
        }
        board.fill(new PresetFigureGenerator(figuresAndPositionsMap));
    }


    private List<MovableFigure> createPlayersFiguresWithDefaultPosition(){
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: DEFAULT_PLAYER0_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ZERO.index));
        }
        for(Position position: DEFAULT_PLAYER1_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ONE.index));
        }
        return allPlayersFigures;
    }

    /**
     * updates the matrix
      * @param move : move
     */
    public void updateGame(Move move){
        updateGameAmazonMove(move.getAmazonStartPosition(), move.getAmazonDstPosition());
        updateGameArrowShot(move.getAmazonDstPosition(), move.getArrowDestPosition());
    }

    /**
     * Checks whether the amazon can be moved
     * @param amazonStartPosition: amazon starting position
     * @param amazonDstPosition: amazon arrival position
     */
    public void updateGameAmazonMove(Position amazonStartPosition, Position amazonDstPosition){
        try {

            if( this.board.getFigure(amazonStartPosition).getPlayerID().equals(getPlayerID()) ) {
                board.moveFigure(amazonStartPosition, amazonDstPosition);
            } else {
                isThisIsTheEnd = true;
                winner = PlayerID.values()[(turn+1) % NUMBER_OF_PLAYERS];
            }
        } catch (IllegalMoveException e) {
            isThisIsTheEnd = true;
            winner = PlayerID.values()[(turn+1) % NUMBER_OF_PLAYERS];
        }
    }

    /**
     * Checks whether the arrow can be moved
     * @param amazonDstPosition: amazon arrival position
     * @param arrowDstPosition: arrow arrival position
     */
    public void updateGameArrowShot(Position amazonDstPosition, Position arrowDstPosition) {
        try {
            board.shootArrow(amazonDstPosition, arrowDstPosition);
        } catch (IllegalMoveException e) {
            isThisIsTheEnd = true;
            winner = PlayerID.values()[(turn+1) % NUMBER_OF_PLAYERS];
        }
    }

    private boolean hasLost(PlayerID playerID) {
        return !(winner == playerID);
    }

    public Board getBoard(){
        return this.board;
    }

    public PlayerID getWinner(){
        return winner;
    }

    public PlayerID getPlayerID(){
        return turn % NUMBER_OF_PLAYERS == 0 ? PlayerID.PLAYER_ZERO : PlayerID.PLAYER_ONE;
    }

    public Player getPlayer() {
        return players[turn % NUMBER_OF_PLAYERS];
    }

    /**
     * @return whether the game is finished or not
     */
    public boolean hasEnded() {

        for (int Index = 0; Index < NUMBER_OF_PLAYERS; Index++) {
            int count = 0;
            for(Amazon figure : getPlayerAmazons(PlayerID.getPlayerIDFromIndex(Index), this.board) ){
                if(figure.getAccessiblePositions(board).isEmpty()){
                    count++;
                }
            }
            if(count == numberOfAmazons){
                isThisIsTheEnd = true;
                winner = PlayerID.getPlayerIDFromIndex( (Index+1) % NUMBER_OF_PLAYERS);
            }
        }
        return isThisIsTheEnd;
    }

    public void incrementTurn()
    {
        turn++;
    }

    public int getTurn() {return turn; }
    public void resetWinner(){
        winner = null;
    }

    public void resetTurn(){
        turn = 0;
    }

    public void resetIsThisTheEnd(){
        isThisIsTheEnd = false;
    }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }

    /**
     * @param playerID: ID of player
     * @param board: Matrix
     * @return  amazon list via playerID
     */
    public static List<Amazon> getPlayerAmazons(PlayerID playerID, Board board) {
        List<Amazon> amazons = new ArrayList<>();
        for(int i = 0; i < board.getNumberOfColumns(); i++){
            for(int j = 0; j < board.getNumberOfRows(); j++){
                Figure figure = board.getFigure(new Position(i, j));
                if(figure instanceof Amazon && figure.getPlayerID().equals(playerID))
                    amazons.add((Amazon) figure);
            }
        }
        return amazons;
    }


}
