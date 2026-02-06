package org.pi;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class EnemySpriteFactory {
    private static final Map<String, EnemySprite> sprites = new HashMap<>();


    public static EnemySprite getSprite(String type) {
        if (!sprites.containsKey(type)) {
            try {
                String path = "/" + type + ".png";
                InputStream is = EnemySpriteFactory.class.getResourceAsStream(path);
                BufferedImage img = ImageIO.read(is);
                sprites.put(type, new EnemySprite(type, img));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sprites.get(type);
    }


    public static int getSpriteCount() {
        return sprites.size();
    }
}