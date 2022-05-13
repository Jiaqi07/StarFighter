import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class enemyAmmo extends MovingThing
{
    private int speed;

    public enemyAmmo()
    {
        this(10,10,10,10,0);
    }

    public enemyAmmo(int x, int y)
    {
        //add code
        this(x, y, 10, 10, 0);
    }

    public enemyAmmo(int x, int y, int s)
    {
        //add code
        this(x, y, 10, 10, s);
    }

    public enemyAmmo(int x, int y, int w, int h, int s)
    {
        //add code here
        super(x, y, w, h);
        speed = s;
    }

    public void setSpeed(int s)
    {
        //add code
        speed = s;
    }

    public int getSpeed()
    {
        //add gode
        return speed;
    }

    public void draw( Graphics window )
    {
        //add code to draw the enemyAmmo
        //enemyAmmo should only move up
        window.setColor(Color.BLUE);
        window.fillRect(getX(), getY(), 10, 10);
    }


    public void move( String direction )
    {
        //add code to move the enemyAmmo
        //enemyAmmo will be a yellow square
        if(direction.equals("DOWN")){
            setY(getY() + speed);
        }
    }

    public String toString()
    {
        return super.toString() + getSpeed();
    }
}