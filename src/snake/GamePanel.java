package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Michał Wysocki
 */
public class GamePanel extends JPanel{
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rect;
        
        //body snake
        for (int i=1; i<GameFrame.snake.getPositionList().size(); i++){
            g2d.setColor(Color.BLACK);
            rect = new Rectangle2D.Double(GameFrame.snake.getPositionList().get(i).x, GameFrame.snake.getPositionList().get(i).y, 10, 10);
            g2d.fill(rect);
        }
        
        //snake 2
        if (GameFrame.numberOfPlayers == 2) {
            for (int i=1; i<GameFrame.snake2.getPositionList().size(); i++){
                g2d.setColor(Color.BLUE);
                rect = new Rectangle2D.Double(GameFrame.snake2.getPositionList().get(i).x, GameFrame.snake2.getPositionList().get(i).y, 10, 10);
                g2d.fill(rect);
            }
            g2d.setColor(Color.MAGENTA);
            rect = new Rectangle2D.Double(GameFrame.snake2.getPositionList().get(0).x, GameFrame.snake2.getPositionList().get(0).y, 10, 10);
            g2d.fill(rect);
        }
        
        //head snake
        rect = new Rectangle2D.Double(GameFrame.snake.getPositionList().get(0).x, GameFrame.snake.getPositionList().get(0).y, 10, 10);       
        g2d.setColor(Color.RED);
        g2d.fill(rect);
        
        // food is green
        rect = new Rectangle2D.Double(GameFrame.food.getPoint().x, GameFrame.food.getPoint().y, 10, 10);
        g2d.setColor(Color.GREEN);
        g2d.fill(rect);
        
        
        //display points
        int alpha = 100; // 50% opacity
        Font font = new Font("Serif", Font.BOLD, 40);
        g2d.setColor(new Color(255, 216, 63, alpha));
        g2d.setFont(font);
        if (GameFrame.numberOfPlayers == 1) {
            g2d.drawString("Points: " + GameFrame.snake.getPoints(), 10, 40);
        }
        
        if (GameFrame.numberOfPlayers == 2){
            font = new Font("Serif", Font.BOLD, 30);
            g2d.setFont(font);
            g2d.drawString("(BLACK) Points: " + GameFrame.snake.getPoints(), 10, 40);
            g2d.drawString("(BLUE) Points: " + GameFrame.snake2.getPoints(), 340, 40);
        }
    }   
}
