import javax.swing.*;
import java.awt.*;

public class Player extends Sprite {
    private static final int SCREEN_WIDTH = 1550; // Assuming screen width of 1900 pixels
    private static final int SCREEN_HEIGHT = 1000;


     Player(){
         w = 100;
         h = 200;
         x = 100;
         y = 550;
         image = new ImageIcon(Player.class.getResource("player.gif"));
     }
     public void move(){
         int newX = x+ speed;
         if (newX >= 0 && (newX + w )<= SCREEN_WIDTH) {
             x = newX; // Only update x if within bounds
         }
     }



}
