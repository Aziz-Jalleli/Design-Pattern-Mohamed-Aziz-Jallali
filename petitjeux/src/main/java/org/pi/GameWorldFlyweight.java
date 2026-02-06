package org.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameWorldFlyweight {
    private List<Enemy> enemies = new ArrayList<>();
    private static final String[] TYPES = {"alien", "robot", "zombie"};
    private Random random = new Random();


    public void generateEnemies(int count) {
        for (int i = 0; i < count; i++) {
            String type = TYPES[random.nextInt(TYPES.length)];
            EnemySprite sprite = EnemySpriteFactory.getSprite(type);


            int maxX = Math.max(1, 800 - sprite.getWidth());
            int maxY = Math.max(1, 600 - sprite.getHeight());

            int x = random.nextInt(maxX);
            int y = random.nextInt(maxY);

            int speed = 1 + random.nextInt(5);


            enemies.add(new Enemy(x, y, speed, sprite));
        }
    }


    public List<Enemy> getEnemies() {
        return enemies;
    }
}