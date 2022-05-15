import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
    private List<Alien> aliens;

    public AlienHorde(int size, int speed)
    {
        //initalize ArrayList
        //and fill with size amount of aliens (75 pixels apart)
        //if your row is full (out of bounds of screen)
        //move down a row (75 pixels)
        //starting point is 25, 50
        //first add aliens with speed of 0
        aliens = new ArrayList<Alien>();
        int x = 25;
        int y = 50;
        for (int i = 0; i < size; i++) {
            if (x > StarFighter.WIDTH - 50) { //Changed WIDTH of Starfighter to public
                x = 25;
                y += 75;
            }
            aliens.add(new Alien(x, y, 35, 35, speed));
            x += 75*(Math.random()+.6);
        }
    }

    public void add(Alien al)
    {
        //add an al to the list
        aliens.add(al);
    }

    public void drawEmAll( Graphics window )
    {
        //make sure you draw all aliens in the list
        for (Alien group : aliens) {
            group.draw(window);
        }
    }

    public List<Alien> getEm(){
        return aliens;
    }

    public void moveEmAll()
    {
        //make sure you move all aliens in the list
        for (Alien group : aliens) {
            group.move("DOWN");
        }
    }

    public String toString()
    {
        return "" + aliens;
    }
}