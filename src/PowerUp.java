import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class PowerUp
{
    private Image image;
    private int x, y, w, h;

    public PowerUp()
    {
        //add code here
        x = (int) (Math.random()*800);
        y = (int) (Math.random()*400)+100;
        w = 30;
        h = 30;
        try
        {
            URL url = getClass().getResource("powerHeart.png");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }

    public void draw(Graphics window )
    {
        window.drawImage(image, x, y,w, h,null);
    }
}