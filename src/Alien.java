import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Alien extends MovingThing
{
    private int speed;
    private Image image;

    public Alien()
    {
        this(0,0,30,30,0);
    }

    public Alien(int x, int y)
    {
        //add code here
        this(x, y, 30, 30, 0);
    }

    public Alien(int x, int y, int s)
    {
        //add code here
        this(x, y, 30, 30, s);
    }

    public Alien(int x, int y, int w, int h, int s)
    {
        //add code here
        super(x, y, w, h);
        speed = s;
        try
        {
            URL url = getClass().getResource("alien.jpg");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
            e.printStackTrace();
        }
    }

    public void setSpeed(int s)
    {
        //add code
        speed = s;
    }

    public int getSpeed()
    {
        //add code
        return speed;
    }

    public void move(String direction)
    {
        //add code here
        //check bounds of the alien
        //if alien is out of bounds change speed direction
        //and move the alien down a row (25 pixels)
        //constantly change the x position of the alien by the speed

        if ((getX() + speed > 800 || getX() + speed < 0) && direction.equals("DOWN")) { // How to figure out WIDTH
            setY(getY()+50);
            speed *= -1;
        }
        setX(getX() + speed);
    }

    public void draw( Graphics window )
    {
        move("DOWN");
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    public String toString()
    {
        return super.toString() + getSpeed();
    }
}