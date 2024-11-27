import java.awt.*;
import java.util.ArrayList;


public class DynamicSprite extends Sprite {
    private Direction direction = Direction.NONE;
    private double speed = 5;

    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Déplacer le sprite selon la direction
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            switch (direction) {
                case NORTH -> y -= speed;
                case SOUTH -> y += speed;
                case EAST -> x += speed;
                case WEST -> x -= speed;
            }
        }
    }

    // Vérifie si le mouvement est possible en fonction des collisions
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle moved = new Rectangle((int) x, (int) y, (int) width, (int) height);
        for (Sprite s : environment) {
            if (s instanceof SolidSprite && this.collidesWith(s)) {
                return false;
            }
        }
        return true;
    }
}
