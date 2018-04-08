package snake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michał Wysocki
 */
public class Snake {
    private ArrayList<Point> positionList; // coordinates snake's list
    private int points; // gained points
    private boolean alive; // snake is alive or not

    public Snake() {
        positionList = new ArrayList<Point>();
        this.points = 0;
        this.alive = true;
    }   
    
    public ArrayList<Point> randLocalisation(ArrayList<Point> array){
        Point point = new Point(100,100);   // default parameters
        Random rand = new Random();
        point.x = (rand.nextInt(29) + 2) * 10; // rand x from (2 to 30) * 10
        point.y = rand.nextInt(60) * 10; // rand y from (0 to 59) * 10
        array.add(point);
        array.add(new Point(point.x-10, point.y));
        array.add(new Point(point.x-20, point.y));
        return array;
    }
    
    public void addPosition(Point p){
        positionList.add(p);
    }

    private boolean checkCollisions(){
        if (positionList.get(0).x < 0 || positionList.get(0).x >= 600 || positionList.get(0).y < 0 || positionList.get(0).y >= 600) alive = false;
        for(int i=1; i<positionList.size(); i++){
            if (positionList.get(0).equals(positionList.get(i))){
                alive = false;
                break;
            }
        }
        return alive;
    }
    
    public void move(int direction) {        
        for(int i=positionList.size()-1; i>0; i--){ // REST OF SNAKE
                    positionList.get(i).move(positionList.get(i-1).x, positionList.get(i-1).y);
        }
        switch(direction){ // FRONT OF SNAKE (HEAD)
                case 1:
                    positionList.get(0).move(positionList.get(1).x, positionList.get(1).y - 10);
                    break;
                case 2:
                    positionList.get(0).move(positionList.get(1).x, positionList.get(1).y + 10);
                    break;
                case 3:
                    positionList.get(0).move(positionList.get(1).x+10, positionList.get(1).y);
                    break;
                case 4:
                    positionList.get(0).move(positionList.get(1).x-10, positionList.get(1).y);
                    break;
                default:
                    break;
            }
    }
    
    public boolean isEating(Point headSnake, Point food){
        boolean eating = false;
        if (headSnake.equals(food)) eating = true;        
        return eating;
    }
    
    public ArrayList<Point> getPositionList() {
        return positionList;
    }

    public int getPoints() {
        return points;
    }

    public boolean isAlive() {
        this.alive = checkCollisions();
        return alive;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public void sstring() {
        //System.out.println("Snake{" + "positionList=" + positionList.get(0) + ", littleSnake=" + littleSnake + '}');
        System.out.println("Snake length: " + positionList.size() + "       positionList=" + positionList.get(0));
    }   
    
    
    public boolean isAlive(ArrayList<Point> array1) {
        this.alive = checkCollisions(array1);
        return alive;
    }
    
    private boolean checkCollisions(ArrayList<Point> array1){
        if (positionList.get(0).x < 0 || positionList.get(0).x >= 600 || positionList.get(0).y < 0 || positionList.get(0).y >= 600) alive = false;
        for(int i=1; i<positionList.size(); i++){ // czy sam siebie zjada
            if (positionList.get(0).equals(positionList.get(i))){
                alive = false;
                return alive;
            }
        }
        for(int i=1; i<array1.size(); i++){ // czy zjada drugiego węża
            if (positionList.get(0).equals(array1.get(i))){
                alive = false;
                return alive;
            }
        }
        return alive;
    }
}
