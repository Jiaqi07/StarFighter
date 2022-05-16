import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Boss extends MovingThing
{
    private int speed;
    private Image image;
    private int health;

    public Boss()
    {
        this(0,0,30,30,0);
    }

    public Boss(int x, int y)
    {
        //add code here
        this(x, y, 30, 30, 0);
    }

    public Boss(int x, int y, int s)
    {
        //add code here
        this(x, y, 30, 30, s);
    }

    public Boss(int x, int y, int w, int h, int s)
    {
        //add code here
        super(x, y, w, h);
        speed = s;
        health = 100;
        try
        {
            URL url = getClass().getResource("CAPYBARAKINGIV.png");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
            e.printStackTrace();
        }
    }

    public void takeDamage(){
        --health;
    }

    public void setSpeed(int a)
    {
        //add code
        speed = a;
    }

    public void move(String direction)
    {
        //add code here
        //check bounds of the alien
        //if alien is out of bounds change speed direction
        //and move the alien down a row (25 pixels)
        //constantly change the x position of the alien by the speed

        if (direction.equals("DOWN")) {
            setY(getY()+speed);
        }
    }

    public void draw( Graphics window )
    {
        move("DOWN");
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }
}