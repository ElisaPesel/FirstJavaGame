package main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 2L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE {
        Menu,
        Help,
        Game,
        Multi,
        End,
        End2
    }

    public static STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        hud = new HUD(this);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "First Java Game", this);

        spawner = new Spawn(handler, hud, this);
        r = new Random();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0){
                gameState = STATE.End;
                handler.clearEnemies();
            }
        }
        if(gameState == STATE.Multi){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0 || HUD.HEALTH2 <= 0){
                gameState = STATE.End2;
                handler.clearEnemies();
            }
        }
        else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.End2){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        }
        if(gameState == STATE.Multi){
            hud.render(g);
        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.End2){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}
