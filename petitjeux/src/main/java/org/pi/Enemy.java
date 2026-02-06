package org.pi;

public class Enemy {
    private int x;
    private int y;
    private EnemySprite sprite;
    private int speed;
    private double phase;
    private int baseY;

    public Enemy(int x , int y, int speed, EnemySprite sprite, double phase, int baseY) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.speed = speed;
        this.phase = phase;
        this.baseY = baseY;
    }

    public Enemy(int x, int y, int speed, EnemySprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.speed = speed;
        this.phase = 0;
        this.baseY = 0;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public EnemySprite getSprite() {
        return sprite;
    }
    public void setSprite(EnemySprite sprite) {
        this.sprite = sprite;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void move(int panelWidth) {
        x += speed;
        if (x > panelWidth) {
            x = -sprite.getWidth();
        }
    }
    public void dance(int panelWidth) {
        x += speed;
        phase += 0.15;


        y = baseY + (int) (10 * Math.sin(phase));


        if (x > panelWidth) {
            x = -sprite.getWidth();
        }
    }
}
