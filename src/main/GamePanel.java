package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.RED);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = (double) 1000000000 /FPS; // Time per frame in nanoseconds
        double delta = 0; // This tracks how many updates should the program run
        long lastTime = System.nanoTime(); // Last frame time
        long currentTime;
        long timer = 0; // Counts 1 second
        long drawCount = 0; // Counts frames per second

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; // Accumulate time
            timer += (currentTime -lastTime); // Add to the 1 sec timer
            lastTime = currentTime;
            if (delta >= 1) {
                update(); // THis updates the game logic
                repaint(); // Rerender the game screen
                delta--;
                drawCount++;
            }
            // This prints in the console the fps
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        Toolkit.getDefaultToolkit().sync();
    }
}