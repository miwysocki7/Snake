package snake;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Michał Wysocki
 */
public class Food {
    private Point point; // coordinates food
    private boolean isAvaible; // if snake no eat it

    public Food() {
        this.point=randPoint();
        isAvaible=true;
    }
    
    private Point randPoint(){
        Point p = new Point(100, 100); // default parameters
        Random rand = new Random();
        p.x = rand.nextInt(60) * 10; // rand x from 0 to 590
        p.y = rand.nextInt(60) * 10; // rand y from 0 to 590
        return p;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isIsAvaible() {
        return isAvaible;
    }

    public void setNewCoords() {
        this.point = randPoint();
    }

    public void setIsAvaible(boolean isAvaible) {
        this.isAvaible = isAvaible;
    }
    
    public void sstring(){
        System.out.println("Koordy jedzenia: X = " + point.x + " Y = " + point.y);
    }
    
    
}
