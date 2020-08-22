package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r;
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Menu){
            //1 player button
            if(mouseOver(mx,my, 213, 110, 200, 64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                HUD.HEALTH = 100;
            }

            //2 player button
            if(mouseOver(mx,my, 213, 190, 200, 64)){
                game.gameState = Game.STATE.Multi;
                handler.addObject(new Player(240, 200, ID.Player, handler));
                handler.addObject(new Player2(350, 200, ID.Player2, handler));
                HUD.HEALTH = 100;
                HUD.HEALTH2 = 100;
            }

            //help button
            if(mouseOver(mx, my, 213, 270, 200, 64)){
                game.gameState = Game.STATE.Help;
            }

            //quit button
            if(mouseOver(mx, my, 213, 350, 200, 64)){
                System.exit(1);
            }
        }

        //back button for help
        if(game.gameState == Game.STATE.Help){
            if(mouseOver(mx, my, 213,330,200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //back button for end
        if(game.gameState == Game.STATE.End){
            if(mouseOver(mx, my, 213,350,200, 64)){
                game.gameState = Game.STATE.Menu;
                Spawn.scoreKeep = 0;
                hud.setScore(0);
                hud.setLevel(1);
            }

            //try again button single player
            if(mouseOver(mx,my, 213, 270, 200, 64)){
                game.gameState = Game.STATE.Game;
                Spawn.scoreKeep = 0;
                hud.setScore(0);
                hud.setLevel(1);
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                HUD.HEALTH = 100;
            }
        }

        //back button for end2
        if(game.gameState == Game.STATE.End2){
            if(mouseOver(mx, my, 213,350,200, 64)){
                game.gameState = Game.STATE.Menu;
                Spawn.scoreKeep = 0;
                hud.setScore(0);
                hud.setLevel(1);
            }

            //try again button multiplayer
            if(mouseOver(mx,my, 213, 270, 200, 64)){
                game.gameState = Game.STATE.Multi;
                Spawn.scoreKeep = 0;
                hud.setScore(0);
                hud.setLevel(1);
                handler.addObject(new Player(240, 200, ID.Player, handler));
                handler.addObject(new Player2(350, 200, ID.Player2, handler));
                HUD.HEALTH = 100;
                HUD.HEALTH2 = 100;
            }
        }
    }

    public void mouseReleased(MouseEvent e){
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){
    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 58);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 235, 80);

            g.setFont(fnt2);
            g.drawRect(213, 110, 200, 64);
            g.drawString("1 Player", 257, 153);

            g.drawRect(213, 190, 200, 64);
            g.drawString("2 Player", 257, 233);

            g.drawRect(213, 270, 200, 64);
            g.drawString("Help", 278, 313);

            g.drawRect(213, 350, 200, 64);
            g.drawString("Quit", 280, 393);
        }else if(game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 58);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 250, 80);

            g.setFont(fnt3);
            g.drawString("Player 1:", 265, 150);
            g.drawString("Use WASD keys to move player and dodge the enemies",50, 180);

            g.drawString("Player 2:", 265, 250);
            g.drawString("Use Arrow Keys keys to move player and dodge the enemies",30, 280);

            g.setFont(fnt2);
            g.drawRect(213, 350, 200, 64);
            g.drawString("Back", 275, 393);
        }
        else if(game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", 1, 58);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 160, 100);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(),175, 215);

            g.setFont(fnt2);
            g.drawRect(213, 270, 200, 64);
            g.drawString("Try Again", 245, 313);

            g.drawRect(213, 350, 200, 64);
            g.drawString("Menu", 275, 393);
        }
        else if(game.gameState == Game.STATE.End2){
            Font fnt = new Font("arial", 1, 58);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 160, 100);

            if(HUD.HEALTH > HUD.HEALTH2){
                g.setFont(fnt3);
                g.setColor(Color.white);
                g.drawString("Player 1 wins!",245, 215);
            }
            if(HUD.HEALTH2 > HUD.HEALTH){
                g.setFont(fnt3);
                g.setColor(Color.white);
                g.drawString("Player 2 wins!",245, 215);
            }

            g.setFont(fnt2);
            g.drawRect(213, 270, 200, 64);
            g.drawString("Try Again", 245, 313);

            g.drawRect(213, 350, 200, 64);
            g.drawString("Menu", 275, 393);
        }
    }
}
