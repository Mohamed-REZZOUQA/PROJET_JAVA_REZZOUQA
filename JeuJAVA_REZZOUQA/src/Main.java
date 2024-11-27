import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Main {
    JFrame displayZoneFrame;
    CardLayout cardLayout;
    JPanel mainPanel;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Dungeon Crawler Master");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation de l'affichage du menu
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createMenuPanel(); // Crée le menu
        createGamePanel(); // Prépare le jeu, mais ne l'affiche pas encore

        // Ajoute le menu au panneau principal
        displayZoneFrame.add(mainPanel);
        cardLayout.show(mainPanel, "Menu"); // Affiche le menu au démarrage

        displayZoneFrame.setVisible(true);
    }

    private void createMenuPanel() {
        // Crée une instance de Menu avec l'action de démarrer le jeu
        Menu menuPanel = new Menu(e -> startGame());
        mainPanel.add(menuPanel, "Menu");
    }

    private void createGamePanel() throws Exception {
        // Créer un héros avec une barre de vie (compteur)
        HeroWithHealth hero = new HeroWithHealth(70, 65,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, 100); // 100 est la vie maximale

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();

        // Crée un gameEngine avec uniquement le hero (le PhysicEngine est initialisé dans GameEngine)
        gameEngine = new GameEngine(hero); // On ne passe que le hero ici

        // Timers pour les mises à jour
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);  // Ajouter le héros avec le compteur de vie
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        // Crée un JPanel pour le jeu
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(renderEngine, BorderLayout.CENTER);

        mainPanel.add(gamePanel, "Jeu");

        displayZoneFrame.addKeyListener(gameEngine); // Ajout du KeyListener à la fenêtre principale
    }

    private void startGame() {
        // Lorsque l'utilisateur clique sur "Commencer", on passe du menu au jeu
        cardLayout.show(mainPanel, "Jeu");
        displayZoneFrame.requestFocus(); // Assure que la fenêtre a le focus pour capturer les événements clavier
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}

