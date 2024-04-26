//import javax.swing.JFrame;
//
//public class Frame extends JFrame {
//    public Frame(){
//        GamingBoard gb = new GamingBoard();
//        setSize(1550,1000);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setLocation(-7,-5);
//        add(gb);
//        setVisible(true);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    public static void main(String[] args) {
//        new Frame();
//
//    }
//
//}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Frame extends JFrame implements ActionListener {
    private JButton start;
    private BufferedImage image;
    private JPanel panel;
    private JLabel background;

    public Frame()  {
        try {
            image = ImageIO.read(Frame.class.getResource("/s5.png"));
        } catch (IOException | IllegalArgumentException ea) {
            ea.printStackTrace();
            // Set a noticeable background color if the image fails to load
            background = new JLabel();
            background.setOpaque(true);
            background.setBackground(Color.RED);
        } finally {
            if (image != null) {
                background = new JLabel(new ImageIcon(image));
            }
            background.setBounds(0, 0, 1900, 1000);
        }

        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(null);
        panel.setBounds(-5, -7, 1900, 1000);  // Ensure panel covers entire JFrame

        start = new JButton("START GAME");
        start.setBounds(700, 380, 200, 50);
        start.setBackground(Color.WHITE);
        start.setForeground(Color.BLACK);
        start.addActionListener(this);
        panel.add(start);

        background.add(panel);
        add(background);

        setSize(1550, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(-7,-5);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Frame();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");  // Debugging line
        if (e.getSource() == start) {
            // GameBoard gb = new GameBoard();
            GamingBoard gb = new GamingBoard();
            gb.setBounds(0, 0, 1900, 1000);
            setContentPane(gb);
            gb.requestFocusInWindow();
            revalidate();
            repaint();
        }
    }
}
