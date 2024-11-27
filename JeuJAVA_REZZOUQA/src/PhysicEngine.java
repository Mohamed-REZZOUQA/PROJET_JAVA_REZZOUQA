import java.util.ArrayList;
import java.util.ArrayList;

public class PhysicEngine {
    private ArrayList<DynamicSprite> movingSprites = new ArrayList<>();
    private ArrayList<Sprite> environment = new ArrayList<>();

    // Ajouter un sprite dynamique (le héros) à la liste
    public void addToMovingSpriteList(DynamicSprite sprite) {
        movingSprites.add(sprite);
    }

    // Définir l'environnement, c'est-à-dire les obstacles et pièges
    public void setEnvironment(ArrayList<Sprite> sprites) {
        environment = sprites;
    }

    public ArrayList<Sprite> getEnvironment() {
        return environment;
    }

    // Met à jour la position des objets et vérifie les collisions avec les pièges
    public void update() {
        for (DynamicSprite movingSprite : movingSprites) {
            movingSprite.moveIfPossible(environment);

            // Vérifie les collisions avec les pièges et inflige des dégâts au héros
            for (Sprite staticSprite : environment) { //parcours la liste environnement
                if (movingSprite.collidesWith(staticSprite)) { //si hero collision avec trap
                    if (staticSprite instanceof Trap && movingSprite instanceof HeroWithHealth) {// vérifie les objet
                        HeroWithHealth hero = (HeroWithHealth) movingSprite;
                        Trap trap = (Trap) staticSprite;

                        // Inflige des dégâts au héros
                        hero.subirDommages(trap.getDamage());
                    }
                }
            }
        }
    }
}
