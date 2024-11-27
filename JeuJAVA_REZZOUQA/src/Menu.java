import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JPanel {
    private Image backgroundImage;

    public Menu(ActionListener startAction) {
        try {
            // Charger l'image de fond
            backgroundImage = ImageIO.read(new File("./img/background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Labyrinthe", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(title);

        JButton startButton = new JButton("PLAY");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(startAction);
        startButton.setPreferredSize(new Dimension(300, 100));

        add(Box.createRigidArea(new Dimension(0, 60)));
        add(startButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner l'image de fond
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
