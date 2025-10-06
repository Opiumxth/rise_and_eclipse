package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public int screenX;
    public int screenY;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        screenX = 100;
        screenY = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/punpun_right_1.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyH.upPressed){
            direction = "up";
            screenY -= speed;
        }
        else if (keyH.downPressed){
            direction = "down";
            screenY += speed;
        }
        else if (keyH.leftPressed) {
            direction = "left";
            screenX -= speed;
        }
        else if (keyH.rightPressed){
            direction = "right";
            screenX += speed;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, screenX,screenY,gp.tileSize,gp.tileSize, null);
    }
}