import javax.swing.*;
import java.awt.*;

public class Enemy extends Sprite {

    public Enemy(int x, int speed){
        y = 0;
        this.x = x;
        this.speed = speed;
        w = 100;
        h = 100;
        image = new ImageIcon(Enemy.class.getResource("enemy.gif"));
    }
// need to change
    public void move(){
        y = y+ speed;
    }

}
