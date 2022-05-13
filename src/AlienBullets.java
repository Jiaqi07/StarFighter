import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AlienBullets
{
    private List<enemyAmmo> ammo;

    public AlienBullets()
    {
        //initalize ammo
        ammo = new ArrayList<enemyAmmo>();
    }

    public void add(enemyAmmo al)
    {
        //add al to list
        ammo.add(al);
    }

    public void drawEmAll( Graphics window )
    {
        //draw each ammo in the list
        for(enemyAmmo a : ammo){
            a.draw(window);
        }
    }

    public void moveEmAll()
    {
        //move each ammo in the list
        for(enemyAmmo a : ammo){
            a.move("DOWN");
        }
    }

    public void cleanEmUp()
    {
        //for every ammo in the list
        //if the ammo is out of bounds
        //remove it
        for(int i = 0; i < ammo.size(); ++i){
            if(ammo.get(i).getHeight() > 800 || ammo.get(i).getHeight() < 0 || ammo.get(i).getWidth() < 0 || ammo.get(i).getWidth() > 800){
                ammo.remove(i--);
            }
        }
    }

    public List<enemyAmmo> getList()
    {
        //add code
        return ammo;
    }

    public String toString()
    {
        return "" + ammo;
    }
}