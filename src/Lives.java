import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Lives extends MovingThing
{
    private int amount;
    private Image image;

    public Lives()
    {
        this(3);
    }

    public Lives(int x)
    {
        //add code here
        amount = x;

        try
        {
            URL url = getClass().getResource("Heart.jpg");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
            e.printStackTrace();
        }
    }

    public void addLives(){
        ++amount;
    }

    public void minusLives(){
        --amount;

    }

    public int size(){
        return amount;
    }

    public void draw( Graphics window )
    {
        for(int i = 0; i < amount; ++i){
            window.drawImage(image,i*40,45,30, 30,null);
        }
    }

    public String toString(){
        return "" + amount;
    }
}