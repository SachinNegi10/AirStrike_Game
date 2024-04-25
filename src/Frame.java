import javax.swing.JFrame;

public class Frame extends JFrame {
    public Frame(){
        GamingBoard gb = new GamingBoard();
        setSize(1550,1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setLocation(-7,-5);
        add(gb);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Frame();

    }

}