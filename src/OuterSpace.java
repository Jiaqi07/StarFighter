import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
    private Difficulty dm;
    private long time;
    private background bg;
    private AlienBullets enemyShots;
    private AlienHorde horde;
	private Bullets shots;
    private int LEVELS;
    private EndGame endScreen;
    private boolean menu;
    private int ENEMY_BULLET_SPEED;


//	private Alien alienOne;
//	private Alien alienTwo;

    private boolean hit;
    private PowerUp pw;
    private Lives health;
    private Ship ship;
    private boolean[] keys;
    private boolean STOP;
    private BufferedImage back;
    private int previousSize;
    private int COUNTER;
    public boolean pickedUp;

    public OuterSpace()
    {
        menu = true;
        pickedUp = true;
        hit = true;
        pw = new PowerUp();
        setBackground(Color.lightGray);
        time = 0;
        STOP = false;
        LEVELS=1;
        keys = new boolean[5];
        previousSize = 10;
        COUNTER = 0;
        endScreen = new EndGame();
        health = new Lives(3);
        //instantiate what you need as you need it (from global objects above)
        ship = new Ship(310, 450, 5);
//        alienOne = new Alien(100,50, 0);
//        alienTwo = new Alien(150, 50, 0);
        bg = new background();
        horde = new AlienHorde(previousSize, 1);
        shots = new Bullets();
        enemyShots = new AlienBullets();
        dm = new Difficulty();

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window)
    {
        paint(window);
        ++time;
    }

    //the top part of the paint method is done for you
    public void paint( Graphics window )
    {
        if(time >= 10000){
            time = 0;
        }
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D)window;
        if(STOP){ // WORK ON STOP METHOD

        }
        else if(menu){
            dm.draw(window);
        }
        else{
            //take a snap shot of the current screen and save it as an image
            //that is the exact same width and height as the current screen
            if(back==null)
                back = (BufferedImage)(createImage(getWidth(),getHeight()));

            //create a graphics reference to the back ground image
            //we will draw all changes on the background image
            Graphics graphToBack = back.createGraphics();
            bg.draw(graphToBack);

            Font tr = new Font("TimesRoman", Font.PLAIN, 18);
            graphToBack.setFont(tr);
            graphToBack.setColor(Color.WHITE);
            graphToBack.drawString("Level: " + LEVELS, 2, 20); //WHAT VALUES GO IN HERE
            graphToBack.drawString("Kill Counter: " + COUNTER, 2, 40); //WHAT VALUES GO IN HERE

//        horde.add(alienOne);
//        horde.add(alienTwo);
//        alienOne.draw(graphToBack);
//        alienTwo.draw(graphToBack);

            //add code to move Ship, Alien, etc.-- Part 1
            if(keys[0])
            {
                ship.move("LEFT");
            }
            if(keys[1])
            {
                ship.move("RIGHT");
            }
            if(keys[2])
            {
                ship.move("UP");
            }
            if(keys[3])
            {
                ship.move("DOWN");
            }

            //add code to fire a bullet
            if(keys[4]){
                shots.add(new Ammo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 5));
                keys[4] = false;
            }
            int speed = 1;
            //ALIEN SPAWNING MECHANICS
            if(horde.getEm().size() == 0){
                previousSize *= 1.20;
                speed++;
                ++LEVELS;
                horde = new AlienHorde(previousSize, speed);
            }

            if(pickedUp){
                if(pw.getX() >= ship.getX() && pw.getX() <= ship.getX()+ship.getWidth()+50 && pw.getY() >= ship.getY() && pw.getY() <= ship.getY()+ship.getHeight()+100){
                    System.out.println("Touch");
                    health.addLives();
                    pickedUp = false;
                }
            }

            //ENEMY SHOOTING
            if(time % 1000 == 0){
                for(int i = 0; i < horde.getEm().size(); i+=2){
                    enemyShots.add(new enemyAmmo((horde.getEm().get(i).getX() + horde.getEm().get(i).getWidth() / 2) - 5, horde.getEm().get(i).getY() + 5, ENEMY_BULLET_SPEED));
                }
            }

            if(!pickedUp && time % 5000 == 0){ //Power Up regeneration time
                pickedUp = true;
            }

            if(!hit && time % 200 == 0){ //"Flicker"
                hit = true;
            }

            //BULLET COLLISION WITH ENEMY BULLETS
            for(int i = 0; i < enemyShots.getList().size(); ++i){
                for(int j = 0; j < shots.getList().size(); ++j){
                    if(enemyShots.getList().get(j).getX() <= shots.getList().get(j).getX() && enemyShots.getList().get(j).getX()+10 >= shots.getList().get(j).getX() && enemyShots.getList().get(j).getY() <= shots.getList().get(j).getY() && enemyShots.getList().get(j).getY()+10 >= shots.getList().get(j).getY()){
                        enemyShots.getList().remove(i--);
                        shots.getList().remove(j--);
                    }
                }
            }


            //COLLISION WITH BULLETS
            for(int i = 0; i < horde.getEm().size(); ++i){
                for(int j = 0; j < shots.getList().size(); ++j){
                    if(horde.getEm().get(i).getX() <= shots.getList().get(j).getX() && horde.getEm().get(i).getX()+50 >= shots.getList().get(j).getX() && horde.getEm().get(i).getY() <= shots.getList().get(j).getY() && horde.getEm().get(i).getY()+50 >= shots.getList().get(j).getY()){
                        horde.getEm().remove(i--);
                        shots.getList().remove(j);

                        ++COUNTER;
                        break;
                    }
                }
            }
            //Player COLLISION WITH Enemy Bullets
            for(int i = 0; i < enemyShots.getList().size(); ++i){
                if(enemyShots.getList().get(i).getX() >= ship.getX() && ship.getX()+ship.getWidth() >= enemyShots.getList().get(i).getX() && enemyShots.getList().get(i).getY() >= ship.getY() && enemyShots.getList().get(i).getY() <= ship.getY()+ship.getHeight()){ //FIGURE OUT THIS THINGY
                    health.minusLives();
                    enemyShots.getList().remove(i--);
                    System.out.println("Lives: " + health);

                    hit = false;

                    if(health.size() <= 0){
                        horde.getEm().clear();
                        STOP = true;
                    }
                }
            }
            //ALIEN COLLISION WITH Player
            for(int i = 0; i < horde.getEm().size(); ++i){
                if(horde.getEm().get(i).getX() <= ship.getX() && horde.getEm().get(i).getX()+ship.getWidth()+50 >= ship.getX() && horde.getEm().get(i).getY() <= ship.getY() && horde.getEm().get(i).getY()+ship.getHeight() >= ship.getY()){
                    health.minusLives();
                    horde.getEm().remove(i--);
                    System.out.println("Lives: " + health);

                    hit = false;

                    if(health.size() <= 0){
                        horde.getEm().clear();
                        STOP = true;
                    }
                }
            }

            if(!STOP){ //STOP DRAWINGS AND GO TO END SCREEN CUZ YOU DEAD!!!
                health.draw(graphToBack);

                horde.drawEmAll(graphToBack);
                shots.drawEmAll(graphToBack);
                shots.moveEmAll();
                enemyShots.drawEmAll(graphToBack);
                enemyShots.moveEmAll();

                if(hit){
                    ship.draw(graphToBack);
                }

                if(pickedUp){
                    pw.draw(graphToBack);
                }
            }
            else{
                endScreen.draw(graphToBack);
            }

            //make sure you've drawn all your stuff
            twoDGraph.drawImage(back, null, 0, 0);
            back = null;
        }

    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_1 && menu){
            menu = false;
            previousSize = 5;
            health.setLives(3);
            ENEMY_BULLET_SPEED = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_2 && menu){
            menu = false;
            previousSize = 10;
            health.setLives(2);
            ENEMY_BULLET_SPEED = 2;
        }
        if(e.getKeyCode() == KeyEvent.VK_3 && menu){
            menu = false;
            previousSize = 20;
            health.setLives(1);
            ENEMY_BULLET_SPEED = 3;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = true;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        //no code needed here
        //method needs to be implemented
        //because class implements KeyListner
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }catch(Exception e)
        {
            //feel free to add something here, or not
            e.printStackTrace();
        }
    }
}