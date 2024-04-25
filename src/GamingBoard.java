import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamingBoard extends JPanel {
    Timer timer;
    BufferedImage backgroundIma, explosion;
    Player player;
    Enemy[] enemies = new Enemy[15];
    boolean gameOver = false;
    boolean explosionActive = false;
    int explosionX, explosionY;
    int score = 0; // Score tracking variable

    public GamingBoard() {
        setSize(1550,1000);
        backgroundImage();
        explosion();
        player = new Player();
        loadEnemies();
        gameLoop();
        setFocusable(true);
        bindEvent();
    }

    public void explosion() {
        try {
            explosion = ImageIO.read(GamingBoard.class.getResource("exp.gif"));
        } catch (Exception ea) {
            ea.printStackTrace();
        }
    }

    private void bindEvent() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.speed = 25;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.speed = -25;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.speed = 0;
            }
        });
    }

    private void loadEnemies() {
        int x = 50;
        int gap = 150;
        int Speed = 2;
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(x, Speed);
            x += gap;
        }
    }

    private void gameLoop() {
        timer = new Timer(50, (e) -> {
            repaint();
            for (Enemy enemy : enemies) {
                if (isCollide(enemy)) {
                    explosionActive = true;
                    explosionX = enemy.x - (explosion.getWidth() - enemy.w) / 2;
                    explosionY = enemy.y - (explosion.getHeight() - enemy.h) / 2;
                    resetEnemy(enemy);
                    score++;  // Increment score
                    enemy.speed += 1;  // Increase enemy speed after each collision

                }
                if (enemy.y >= 900) { // Assuming the player's "side" is at y = 900
                    gameOver = true;
                }
            }
        });
        timer.start();
    }

    private boolean isCollide(Enemy enemy) {
        int xDis = Math.abs(player.x - enemy.x);
        int yDis = Math.abs(player.y - enemy.y);
        return xDis < player.w && yDis < player.h;
    }

    private void resetEnemy(Enemy enemy) {
        enemy.y = 0; // Reset enemy to the top of the screen
    }
    private void gameOver(Graphics pen) {
        pen.setFont(new Font("Times New Roman", Font.BOLD, 40));
        pen.setColor(Color.RED);
        pen.drawString("Game Over", 700, 380); // Display at the center
        timer.stop();
    }

    private void backgroundImage() {
        try {
            backgroundIma = ImageIO.read(getClass().getResource("galexy.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printEnemies(Graphics pen) {
        for (Enemy enemy : enemies) {
            enemy.draw(pen);
            enemy.move();
        }
    }

    @Override
    public void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        pen.drawImage(backgroundIma, 0, 0, 1900, 1000, null);
        if (gameOver) {
            gameOver(pen);
        } else {
            player.draw(pen);
            player.move();
            printEnemies(pen);
            if (explosionActive) {
                pen.drawImage(explosion, explosionX, explosionY, null);
            }
            // Display score
            pen.setColor(Color.WHITE);
            pen.setFont(new Font("Arial", Font.BOLD, 30));
            pen.drawString("Score: " + score, 20, 40);
        }
    }
}
