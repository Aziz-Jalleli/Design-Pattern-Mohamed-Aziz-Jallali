package org.pi;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class GamePanel extends JPanel {
    private List<Enemy> enemies;


    public GamePanel(List<Enemy> enemies) {
        this.enemies = enemies;
        setBackground(Color.BLACK);
    }


    public void updatePositions() {
        for (Enemy e : enemies) {
            e.dance(getWidth());
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Enemy e : enemies) {
            g.drawImage(e.getSprite().getImage(), e.getX(), e.getY(), null);
        }
    }
}