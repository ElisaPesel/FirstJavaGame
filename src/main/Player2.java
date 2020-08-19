package main;

import java.awt.*;
import java.util.Random;

public class Player2 extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player2(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 71);

        handler.addObject(new Trail(x, y, ID.Tail, Color.blue, 32, 32, 0.08f, handler));

        collision();
    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.SmartEnemy2){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH2 -= 2;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}
