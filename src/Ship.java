import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
    private int speed;
    private Image image;

    public Ship()
    {
        this(0,0,50,50,0);
    }

    public Ship(int x, int y)
    {
        //add code here
        this(x, y, 50, 50, 0);
        speed = 0;
    }

    public Ship(int x, int y, int s)
    {
        //add code here
        this(x, y, 50, 50, s);
    }

    public Ship(int x, int y, int w, int h, int s)
    {
        //add code here
        super(x, y, w, h);
        speed = s;
        try
        {
            URL url = getClass().getResource("ship.jpg");
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
        speed = s;
    }

    public int getSpeed()
    {
        //continue coding
        return speed;
    }

    public void move(String direction)
    {
        if(direction.equals("LEFT")){
            setX(getX()-getSpeed());
        }
        else if(direction.equals("RIGHT")){
            setX(getX() + getSpeed());
        }
        else if(direction.equals("UP")){
            setY(getY() - getSpeed());
        }
        else if(direction.equals("DOWN")){
            setY(getY() + getSpeed());
        }
    }

    public void draw( Graphics window )
    {
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    public String toString()
    {
        return super.toString() + " " + getSpeed();
    }
}