package snake;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Michał Wysocki
 */
public class OptionsFrame extends JFrame implements ActionListener{

    private JPanel pButtons;
    private JButton bStart, bExit, bBestScores, bTwoPlayers;
    private JMenuBar menuBar;
    private JMenu menuOptions;
    private JMenuItem menuItemInstructions, menuItemAuthor;
    protected FileBestScores fileBestScores;
    
    public OptionsFrame() {
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        
        menuBar = new JMenuBar();
        menuOptions = new JMenu("Options");
        menuItemInstructions = new JMenuItem("Instructions", KeyEvent.VK_A);
        menuItemInstructions.addActionListener(this);
        menuItemAuthor = new JMenuItem("Author", KeyEvent.VK_B);
        menuItemAuthor.addActionListener(this);
        
        menuOptions.add(menuItemInstructions);
        menuOptions.add(menuItemAuthor);
        menuBar.add(menuOptions);
        setJMenuBar(menuBar);
        
        pButtons = new JPanel(new GridLayout(4, 1, 0, 20));
        pButtons.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        bStart = new JButton("Start (single player)");
        bStart.addActionListener(this);
        bTwoPlayers = new JButton("Start (2 players)");
        bTwoPlayers.addActionListener(this);
        bBestScores = new JButton("Best scores");
        bBestScores.addActionListener(this);
        bExit = new JButton("Exit");
        bExit.addActionListener(this);
        
        fileBestScores = new FileBestScores();
        
        pButtons.add(bStart);
        pButtons.add(bTwoPlayers);
        pButtons.add(bBestScores);
        pButtons.add(bExit);
        
        add(pButtons);
        setVisible(true);
        
        // food test
        /*int xmin=1000, ymin=1000, xmax=0, ymax=0;
        Food food;
        for(int i=0; i<1000; i++){
            food = new Food();
            //System.out.println(i);
            if (ymin > food.getPoint().y) ymin = food.getPoint().y;
            if (xmin > food.getPoint().x) xmin = food.getPoint().x;
            if (xmax < food.getPoint().x) xmax = food.getPoint().x;
            if (ymax < food.getPoint().y) ymax = food.getPoint().y;
            
        }
        System.out.println("Xmin="+xmin+"Ymin="+ymin+"Xmax="+xmax+"Ymax="+ymax);*/
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        
        if ( event == bStart){
            GameFrame gf = new GameFrame(1);
        }
        else if ( event == bTwoPlayers){
            GameFrame gf = new GameFrame(2);
        }
        else if (event == bBestScores){
            JOptionPane.showMessageDialog(pButtons, fileBestScores.getBestScores(), "Best scores", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (event == bExit) {
            dispose();
        }
        else if (event == menuItemInstructions) {
            String instruction = "Welcome in Snake!\n\nIn the Snake game you have two game modes to choose from.\n\n"
                    + "Single player mode controls:\nUP - up arrow\nDOWN - down arrow\nRIGHT - right arrow\nLEFT - left arrow\n\n"
                    + "Two players mode controls: czarny wąż -> strzałki, niebieski wąż -> wsad\n\nW każdej chwili możesz opuścić grę naciskając ESCAPE  (wynik nie zapisuje się)\n\n\n"
                    + "todo list:\n-tworzenie pliku z bestscores jesli go nie ma\n-odswiezenie grafiki na przyjemniejszą\n-wybor FREQ i może rozmiaru okna";
            JOptionPane.showMessageDialog(pButtons, instruction, "Snake - instruction", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (event == menuItemAuthor) {
            JOptionPane.showMessageDialog(pButtons, "Michał Wysocki (michaw20@o2.pl)", "Snake - author", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
}
