package amazons.viewer;

import amazons.controller.GameController;
import amazons.player.PlayerID;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MenuView extends BorderPane {

    private final Label status;
    public MenuView(GameController controller){
        setPadding(new Insets(10, 10, 10, 0));
        GridPane options =  new GridPane();
        // buttons
        options.setAlignment(Pos.BOTTOM_RIGHT);
        options.add(new OptionButton("images/reset.png",
                        e -> controller.resetGame(),"reset"),
                2, 0 ,1 ,1);
        options.add(new PlayerMenuButton("images/" + PlayerID.PLAYER_ONE.color + "_queen.png",
                "select player " + PlayerID.PLAYER_ONE.index, PlayerID.PLAYER_ONE, controller),
                1, 0 ,1 ,1);

        options.add(new PlayerMenuButton("images/" + PlayerID.PLAYER_ZERO.color + "_queen.png",
                        "select player " + PlayerID.PLAYER_ZERO.index, PlayerID.PLAYER_ZERO, controller),
                0, 0 ,1 ,1);
        setRight(options);

        //status text
        status = new Label();
        status.setAlignment(Pos.BOTTOM_LEFT);
        status.setPadding(new Insets(10, 0, 10, 10));
        setLeft(status);
    }

    public void setStatusText(String text) {
        status.setText(text);
    }
}
