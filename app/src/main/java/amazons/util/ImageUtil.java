package amazons.util;

import javafx.scene.image.Image;

import java.util.Objects;

public class ImageUtil {
    public static Image loadImage(String resource, int width, int height) {
        return new Image(Objects.requireNonNull(ImageUtil.class.getClassLoader().getResourceAsStream(resource)), width, height, true, true);
    }
}
