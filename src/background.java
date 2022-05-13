import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class background
{
    private Image image;
    public background()
    {
        try
        {
            URL url = getClass().getResource("space.jpg");
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
        window.drawImage(image, 0, 0, 800, 700, null);
    }
}