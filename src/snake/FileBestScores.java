package snake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Michał Wysocki
 */
public class FileBestScores {
    private String filename;
    private ArrayList<Result> resultsList; // list with sorted results
    
    private class Result implements Comparable<Result>{
        private String nick;
        private int points, place;

        public Result(String nick, int points, int place) {
            this.nick = nick;
            this.points = points;
            this.place = place;
        }

        public String getNick() {
            return nick;
        }

        public int getPoints() {
            return points;
        }

        public int getPlace() {
            return place;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        @Override
        public int compareTo(Result o) {
            return Integer.compare(o.points, points);
        }
    }
    
    public FileBestScores(){
        this.filename = "bestscores.txt";
        this.resultsList = new ArrayList<Result>();
    }
    
    public String resultToString(int i){
        return resultsList.get(i).getNick()+ " " + resultsList.get(i).getPoints();
    }
    
    // read file to private variable bestScores
    private void readFile(String filename){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            int i=1;//counter
            String [] nick_points = new String[2];
            line = br.readLine();
            
            if (!resultsList.isEmpty()) resultsList.clear(); 
            
            while (line != null) {
                nick_points = line.split(" ");
                
                resultsList.add(new Result(nick_points[0], Integer.parseInt(nick_points[1]), i));
                line = br.readLine();   
                i++;
            }       
            br.close();
        } catch (FileNotFoundException ex) {
            //JOptionPane.showMessageDialog(null, "The file with best scores was not found.", "File not found", JOptionPane.WARNING_MESSAGE);
            //Logger.getLogger(FileBestScores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(FileBestScores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBestScores() {
        readFile(this.filename);
        String bestScores = "Place    Points    Nick\n";
        for (int i=0; i<resultsList.size(); i++){
            bestScores += resultsList.get(i).getPlace() + "            " + resultsList.get(i).getPoints() + "            " + resultsList.get(i).getNick() + "\n";
        }
        return bestScores;
    }
    
    public void saveFile(){
        readFile(this.filename);
        Collections.sort(resultsList);
        if (resultsList.get(resultsList.size()-1).getPoints() < GameFrame.snake.getPoints() || resultsList.size() < 10){
            String nick = JOptionPane.showInputDialog(null, "Enter your nickname to top 10: ", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            nick = nick.replaceAll("\\s", "");
            Result result = new Result(nick, GameFrame.snake.getPoints(), 11);
            resultsList.add(result);
            Collections.sort(resultsList);
        } 
        else return;
        if (resultsList.size() >= 11){
            resultsList.remove(resultsList.size()-1);
        }
        
        try 
        {
           BufferedWriter bw = new BufferedWriter(new FileWriter(new File(this.filename)));
           for (int i=0; i<resultsList.size(); i++){
               bw.write(resultToString(i)+"\n");
           }
           bw.close();
        }
        catch(IOException e)
        {
            //JOptionPane.showMessageDialog(null, "Something went wrong...");
        }
    }
}
