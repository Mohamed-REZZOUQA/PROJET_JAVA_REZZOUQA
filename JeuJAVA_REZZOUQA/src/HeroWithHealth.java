import java.awt.*;

public class HeroWithHealth extends DynamicSprite {
    private int vie;
    private final int vieMax;

    public HeroWithHealth(double x, double y, Image image, double largeur, double hauteur, int vieMax) {
        super(x, y, image, largeur, hauteur);
        this.vieMax = vieMax;
        this.vie = vieMax;
    }

    public void subirDommages(int dommages) {
        vie -= dommages;
        if (vie < 0) vie = 0; // La vie ne descend pas en dessous de 0
    }

    public void soigner(int montantSoin) {
        vie += montantSoin;
        if (vie > vieMax) vie = vieMax; // La vie ne dépasse pas le maximum
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g); // Dessiner le sprite du héros

        // Dessiner la barre de vie
        int barWidth = 50;
        int barHeight = 5;
        int barX = (int) x + (int) (width / 4);
        int barY = (int) (y - 10);

        g.setColor(Color.RED);
        g.fillRect(barX, barY, barWidth, barHeight);

        g.setColor(Color.GREEN);
        int currentBarWidth = (int) ((double) vie / vieMax * barWidth);
        g.fillRect(barX, barY, currentBarWidth, barHeight);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(vie + "/" + vieMax, barX + 5, barY - 5);
    }
}
