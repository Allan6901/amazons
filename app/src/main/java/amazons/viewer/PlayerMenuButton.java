package amazons.viewer;

import amazons.controller.GameController;
import amazons.player.PlayerID;
import amazons.util.ImageUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class PlayerMenuButton extends OptionButton {


    public PlayerMenuButton(String imageResource, String tooltip, PlayerID playerID, GameController controller) {
        super(imageResource, tooltip);
        ContextMenu menu = new ContextMenu();
        String resourceIcon = "images/" + playerID.color + "_queen.png";
        menu.getItems().add(new PlayerMenuItem(resourceIcon, "human", e -> controller.setPlayerGUI(playerID)));
        menu.getItems().add(new PlayerMenuItem(resourceIcon, "basic", e -> controller.setPlayerBasic(playerID)));
        menu.getItems().add(new PlayerMenuItem(resourceIcon, "random", e -> controller.setPlayerRandom(playerID)));
        menu.getItems().add(new PlayerMenuItem(resourceIcon, "greedy", e -> controller.setPlayerGreedy(playerID)));
        setContextMenu(menu);
        setOnMouseClicked(e -> menu.show(this, e.getScreenX(), e.getScreenY()));
        setOnMouseExited(e -> menu.hide());
    }


    private static class PlayerMenuItem extends MenuItem{

        static Map<String, Image> cachedIcons = new HashMap<>();

        PlayerMenuItem(String resourceIcon, String text, EventHandler<ActionEvent> event) {
            if (!cachedIcons.containsKey(resourceIcon)) {
                cachedIcons.put(resourceIcon, ImageUtil.loadImage(resourceIcon, 16, 16));
            }
            setGraphic(new ImageView(cachedIcons.get(resourceIcon)));
            setText(text);
            setOnAction(event);
        }
    }
}