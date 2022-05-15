import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Difficulty
{
    private Image image;
    public Difficulty()
    {
        try
        {
            URL url = getClass().getResource("Difficulty.png");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            //feel free to do something here
            e.printStackTrace();
        }
    }

    public void draw( Graphics window )
    {
        window.drawImage(image, -100, 0, 1000, 600, null);
    }
}