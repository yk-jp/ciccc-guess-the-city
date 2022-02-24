package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question {
    private final String DEFAULT_CITY = "Chongqing";
    private final String DEFAULT_FILEPATH = "src/data/cities.txt";
    private String filePath;
    private String answer;
    private String currResult;

    public Question() {
        setFilePath(this.DEFAULT_FILEPATH);
    }

    public Question(String filePath) {
        this.filePath = filePath;
    }

    public void resetAnswer() {
        String newAnswer = this.getCity();

        if(newAnswer == "") {
            setAnswer(this.DEFAULT_CITY);
            return;
        }
        setAnswer(newAnswer);
    }

    public String getCity() {
        int indexAt = this.getRandomInt();

        int counter = 0;
        try {
            File file = new File(this.filePath);
            Scanner in = new Scanner(file);

            while (indexAt != counter && in.hasNextLine()) {
                counter++;
                String currCity = in.nextLine();
                if (indexAt == counter) return currCity;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            e.printStackTrace();
        }

        // indexAt is out of range
        return "";
    }

    private int getRandomInt() {
        int randomNum = (int)Math.floor(Math.random()*(100)+1);
        return randomNum;
    }

    public String getCurrResult() {
        return currResult;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setCurrResult(String currResult) {
        this.currResult = currResult;
    }
}
