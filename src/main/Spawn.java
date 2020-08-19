package main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Game game;
    private Random r = new Random();

    public static int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick(){
        if(game.gameState == Game.STATE.Game){
            scoreKeep++;

            if(scoreKeep == 1){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }

            if(scoreKeep >= 250) {
                scoreKeep = 2;
                hud.setLevel(hud.getLevel() + 1);

                if (hud.getLevel() != 5 && hud.getLevel() != 7) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }

                if (hud.getLevel() == 5) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }

                if (hud.getLevel() == 7) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                }
            }
        }

        if(game.gameState == Game.STATE.Multi) {
            scoreKeep++;

            if(scoreKeep == 1){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }

            if (scoreKeep >= 250) {
                scoreKeep = 2;
                hud.setLevel(hud.getLevel() + 1);

                if (hud.getLevel() != 5 && hud.getLevel() != 7) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }

                if (hud.getLevel() == 5) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                }

                if (hud.getLevel() == 7) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
                }

                if (hud.getLevel() == 7) {
                    handler.addObject(new SmartEnemy2(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy2, handler));
                }
            }
        }
    }
}
