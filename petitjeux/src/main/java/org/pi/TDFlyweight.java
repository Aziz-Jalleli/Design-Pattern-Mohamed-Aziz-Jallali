package org.pi;

import javax.swing.*;


public class TDFlyweight {


    private static long usedMemory() {
        Runtime r = Runtime.getRuntime();
        return r.totalMemory() - r.freeMemory();
    }


    public static void main(String[] args) {
        System.out.println("Mémoire avant : " + usedMemory() / 1024 + " KB");


        GameWorldFlyweight world = new GameWorldFlyweight();
        world.generateEnemies(50);


        System.out.println("Mémoire après : " + usedMemory() / 1024 + " KB");
        System.out.println("Sprites créés : " + EnemySpriteFactory.getSpriteCount());


        JFrame frame = new JFrame("Jeu Flyweight");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel(world.getEnemies());
        frame.add(panel);
        frame.setVisible(true);


        Timer timer = new Timer(30, e -> {
            panel.updatePositions();
            panel.repaint();
        });
        timer.start();
    }
}