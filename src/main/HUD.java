package main;

import java.awt.*;

public class HUD {

    public static int HEALTH = 100;
    private int greenValue = 255;

    public static int HEALTH2 = 100;
    private int greenValue2 = 255;

    private int score = 0;
    private int level = 1;

    private Game game;

    public HUD(Game game) {
        this.game = game;
    }

    public void tick(){
        if(game.gameState == Game.STATE.Game) {
            HEALTH = (int) Game.clamp(HEALTH, 0, 100);
            greenValue = (int) Game.clamp(greenValue, 0, 255);

            greenValue = HEALTH * 2;

            score++;
        }
        if(game.gameState == Game.STATE.Multi){
            HEALTH = (int) Game.clamp(HEALTH, 0, 100);
            greenValue = (int) Game.clamp(greenValue, 0, 255);

            greenValue = HEALTH * 2;

            HEALTH2 = (int) Game.clamp(HEALTH2, 0, 100);
            greenValue2 = (int) Game.clamp(greenValue2, 0, 255);

            greenValue2 = HEALTH2 * 2;

            score++;
        }
    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Game) {
            g.setColor(Color.gray);
            g.fillRect(15, 15, 200, 32);
            g.setColor(new Color(75, greenValue, 0));
            g.fillRect(15, 15, HEALTH * 2, 32);
            g.setColor(Color.white);
            g.drawRect(15, 15, 200, 32);

            g.drawString("Score: " + score, 15, 64);
            g.drawString("Level: " + level, 15, 80);
        }
        else if(game.gameState == Game.STATE.Multi){
            g.setColor(Color.gray);
            g.fillRect(15, 15, 200, 32);
            g.setColor(new Color(75, greenValue, 0));
            g.fillRect(15, 15, HEALTH * 2, 32);
            g.setColor(Color.white);
            g.drawRect(15, 15, 200, 32);

            g.setColor(Color.gray);
            g.fillRect(410, 15, 200, 32);
            g.setColor(new Color(75, greenValue2, 0));
            g.fillRect(410, 15, HEALTH2 * 2, 32);
            g.setColor(Color.white);
            g.drawRect(410, 15, 200, 32);

            g.drawString("Score: " + score, 285, 27);
            g.drawString("Level: " + level, 285, 43);
        }
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}
