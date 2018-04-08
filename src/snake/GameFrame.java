package snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Michał Wysocki
 */
public class GameFrame extends JFrame implements KeyListener{
    
    //variable, variable2 - first snake, second snake
    private int FREQ; // szybkość ruchów (odświeżania) w ms
    private int direction, direction2; //1-góra, 2-dół, 3-prawo, 4-lewo
    private GamePanel pGame;
    protected static Snake snake, snake2;
    protected static Food food;
    private FileBestScores fileBestScores;
    protected static int numberOfPlayers;
    private boolean escapeButton;
    
    public GameFrame(int numberOfplayers){
        setTitle("Snake (Game)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(605, 635);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
           
        pGame = new GamePanel();
        pGame.setSize(600, 600);
        pGame.setBackground(Color.GRAY);
        addKeyListener(this);
        
        add(pGame);
        setVisible(true);
        
        escapeButton = true;
        
        FREQ = 100; //(ms)
        direction=3;
        direction2=3;
        snake = new Snake();
        snake.randLocalisation(snake.getPositionList());
        
        this.numberOfPlayers = numberOfplayers;
        if (this.numberOfPlayers==2){
            snake2 = new Snake();
            snake2.randLocalisation(snake2.getPositionList());
        }
        
        food = new Food();
        
        fileBestScores = new FileBestScores();
        
        pGame.repaint();
        
        game();
    }
    
    private void game(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int earlierDirection=0, earlierDirection2=0;
                boolean snakeIsAlive=true;
                while (snakeIsAlive && escapeButton){
                    try {
                        if(earlierDirection == 1 && direction == 2) direction = 1;
                        else if(earlierDirection == 2 && direction == 1) direction = 2;
                        else if(earlierDirection == 3 && direction == 4) direction = 3;
                        else if(earlierDirection == 4 && direction == 3) direction = 4;
                        switch(direction){
                            case 1:
                                snake.move(direction);
                                if (snake.isEating(snake.getPositionList().get(0), food.getPoint())){
                                    snake.addPosition(new Point(snake.getPositionList().get(snake.getPositionList().size()-1).x, snake.getPositionList().get(snake.getPositionList().size()-1).y - 10));
                                    food.setNewCoords();
                                    snake.setPoints(1);
                                }
                                break;
                            case 2:
                                snake.move(direction);
                                if (snake.isEating(snake.getPositionList().get(0), food.getPoint())){
                                    snake.addPosition(new Point(snake.getPositionList().get(snake.getPositionList().size()-1).x, snake.getPositionList().get(snake.getPositionList().size()-1).y + 10));
                                    food.setNewCoords();
                                    snake.setPoints(1);
                                }
                                break;
                            case 3:
                                snake.move(direction);
                                if (snake.isEating(snake.getPositionList().get(0), food.getPoint())){
                                    snake.addPosition(new Point(snake.getPositionList().get(snake.getPositionList().size()-1).x + 10, snake.getPositionList().get(snake.getPositionList().size()-1).y));
                                    food.setNewCoords();
                                    snake.setPoints(1);
                                }
                                break;
                            case 4:
                                snake.move(direction);
                                if (snake.isEating(snake.getPositionList().get(0), food.getPoint())){
                                    snake.addPosition(new Point(snake.getPositionList().get(snake.getPositionList().size()-1).x - 10, snake.getPositionList().get(snake.getPositionList().size()-1).y));
                                    food.setNewCoords();
                                    snake.setPoints(1);
                                }
                                break;
                            default:
                                break;
                        }
                        if (numberOfPlayers == 2){
                            if(earlierDirection2 == 1 && direction2 == 2) direction2 = 1;
                            else if(earlierDirection2 == 2 && direction2 == 1) direction2 = 2;
                            else if(earlierDirection2 == 3 && direction2 == 4) direction2 = 3;
                            else if(earlierDirection2 == 4 && direction2 == 3) direction2 = 4;
                            switch(direction){
                                case 1:
                                    snake2.move(direction2);
                                    if (snake.isEating(snake2.getPositionList().get(0), food.getPoint())){
                                        snake2.addPosition(new Point(snake2.getPositionList().get(snake2.getPositionList().size()-1).x, snake2.getPositionList().get(snake2.getPositionList().size()-1).y - 10));
                                        food.setNewCoords();
                                        snake2.setPoints(1);
                                    }
                                    break;
                                case 2:
                                    snake2.move(direction2);
                                    if (snake.isEating(snake2.getPositionList().get(0), food.getPoint())){
                                        snake2.addPosition(new Point(snake2.getPositionList().get(snake2.getPositionList().size()-1).x, snake2.getPositionList().get(snake2.getPositionList().size()-1).y + 10));
                                        food.setNewCoords();
                                        snake2.setPoints(1);
                                    }
                                    break;
                                case 3:
                                    snake2.move(direction2);
                                    if (snake.isEating(snake2.getPositionList().get(0), food.getPoint())){
                                        snake2.addPosition(new Point(snake2.getPositionList().get(snake2.getPositionList().size()-1).x + 10, snake2.getPositionList().get(snake2.getPositionList().size()-1).y));
                                        food.setNewCoords();
                                        snake2.setPoints(1);
                                    }
                                    break;
                                case 4:
                                    snake2.move(direction2);
                                    if (snake.isEating(snake2.getPositionList().get(0), food.getPoint())){
                                        snake2.addPosition(new Point(snake2.getPositionList().get(snake2.getPositionList().size()-1).x - 10, snake2.getPositionList().get(snake2.getPositionList().size()-1).y));
                                        food.setNewCoords();
                                        snake2.setPoints(1);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (numberOfPlayers == 2){
                            if (!snake.isAlive(snake2.getPositionList())) snakeIsAlive = false;
                            if (!snake2.isAlive(snake.getPositionList())) snakeIsAlive = false;
                        } else {
                            if (!snake.isAlive()) snakeIsAlive = false;                            
                        }
                        earlierDirection=direction;
                        earlierDirection2=direction2;
                        //food.sstring();
                        //snake2.sstring();
                        pGame.repaint();
                        Thread.sleep(FREQ);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // end game
                if (numberOfPlayers == 1 && escapeButton) {
                    JOptionPane.showMessageDialog(pGame, "Congratulations! You've earned " + snake.getPoints() + " point(s)!", "Game over", JOptionPane.INFORMATION_MESSAGE);
                    try{
                        fileBestScores.saveFile();                   
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(pGame, "Cannot save your result.", "File not found", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else if (numberOfPlayers == 2 && escapeButton) {
                    if (snake.isAlive()){
                        JOptionPane.showMessageDialog(pGame, "Congratulations! The winner is BLACK snake!\nBlack snake: " + snake.getPoints() + " point(s)\nBlue snake:   " + snake2.getPoints() + " point(s)", "Game over", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(pGame, "Congratulations! The winner is BLUE snake!\nBlack snake: " + snake.getPoints() + " point(s)\nBlue snake:   " + snake2.getPoints() + " point(s)", "Game over", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                dispose();
            }
        });
        thread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Collections.reverse(snake.getPositionList());
        switch (e.getKeyCode()) {
        //VK_UP Constant for the non-numpad up arrow key. BUT VK_KP_UP Constant for the numeric keypad up arrow key.
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                direction=1;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                direction=2;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                direction=3;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                direction=4;
                break;
            case KeyEvent.VK_W:
                direction2=1;
                break;
            case KeyEvent.VK_S:
                direction2=2;
                break;
            case KeyEvent.VK_D:
                direction2=3;
                break;
            case KeyEvent.VK_A:
                direction2=4;
                break;
            case KeyEvent.VK_ESCAPE:
                escapeButton = false;
                dispose();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args){
        //new GameFrame(1);
    }
}
