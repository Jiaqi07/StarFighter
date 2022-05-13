import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
    private int speed;

    public Ammo()
    {
        this(10,10,10,10,0);
    }

    public Ammo(int x, int y)
    {
        //add code
        this(x, y, 10, 10, 0);
    }

    public Ammo(int x, int y, int s)
    {
        //add code
        this(x, y, 10, 10, s);
    }

    public Ammo(int x, int y, int w, int h, int s)
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
        //add code to draw the ammo
        //ammo should only move up
        window.setColor(Color.RED);
        window.fillRect(getX(), getY(), 10, 10);
    }


    public void move( String direction )
    {
        //add code to move the ammo
        //ammo will be a yellow square
        if(direction.equals("UP")){
            setY(getY() - speed);
        }
        if(direction.equals("Down")){
            setY(getY() + speed);
        }
    }

    public String toString()
    {
        return super.toString() + getSpeed();
    }
}