import java.awt.*;

public class Trap extends SolidSprite {
    private int damage;

    public Trap(double x, double y, Image image, double width, double height, int damage) {
        super(x, y, image, width, height);
        this.damage = damage;
    }

    // Retourne les dégâts infligés par le piège
    public int getDamage() {
        return damage;
    }
}
