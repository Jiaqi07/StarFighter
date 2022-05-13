import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class EndGame
{
    private Image image;
    public EndGame()
    {
        try{
            URL url = getClass().getResource("gameOVER.jpg");
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
        window.drawImage(image,0,0,800,600,null);
    }
}