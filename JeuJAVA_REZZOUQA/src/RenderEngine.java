import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private Image backgroundImage; // Image de fond

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable) {
        for (Displayable d : displayable) {
            if (!renderList.contains(d)) {
                renderList.add(d);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }
    }


    @Override
    public void update() {
        this.repaint();
    }
}
