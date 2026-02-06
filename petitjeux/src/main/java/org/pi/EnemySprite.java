package org.pi;

import java.awt.image.BufferedImage;


public class EnemySprite {
    private final String type;
    private final BufferedImage image;
    private final int width;
    private final int height;


    public EnemySprite(String type, BufferedImage image) {
        this.type = type;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }


    public String getType() { return type; }
    public BufferedImage getImage() { return image; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}