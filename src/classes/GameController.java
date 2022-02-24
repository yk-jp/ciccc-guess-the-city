package classes;

import java.util.Scanner;

public class GameController {
    private final int MAX_LIFE_POINT = 10;

    private int lifePoint;

    private Question question;


    public GameController(Question question) {
        this.question = question;
        this.lifePoint = this.MAX_LIFE_POINT;
    }

    public void start() {
        this.initialSetup();

        // main logic
        this.printStart();

        while (true) {
            this.showResult();

            String userInput = this.userInput();

            System.out.println("userInput: " + userInput);
            if (!Validation.isValidInput(userInput)) {
                System.out.println("invalid input. try again");
                continue;
            }

            //input check
            if (this.isUserInputHit(userInput)) {
                //test an edge case
                if (this.isSameUserInput(userInput)) {
                    System.out.println("input letter is already taken. try again");
                    continue;
                }

                this.updateCurrResult(userInput);
                System.out.println("You have guessed correct letters: " + userInput);

                //check if user wins
                if(this.isUserWin()) {
                    System.out.println("You win!");
                    System.out.println("You have guessed '" + this.question.getAnswer() + "' correctly.");
                    break;
                }
            } else {
                this.updateLifePoint(-1);
                System.out.println("You have guessed wrong letters: " + userInput);

                // check curr life point
                if(isUserLose()) {
                    System.out.println("You have guessed (" + this.MAX_LIFE_POINT + ") wrong letters");
                    System.out.println("You lose!");
                    System.out.println("The correct word was '" + this.question.getAnswer() + "'!");
                    break;
                }
            }
        }
    }

    public boolean isUserWin() {
        return this.question.getCurrResult().equals(this.question.getAnswer());
    }

    public boolean isUserLose() {
        return this.getLifePoint() <= 0;
    }

    public void updateLifePoint(int lifePoint) {
        int newLifePoint = this.getLifePoint() + lifePoint;
        this.setLifePoint(newLifePoint);
    }

    public void updateCurrResult(String userInput) {
        final String answer = this.question.getAnswer();
        final String currResult = this.question.getCurrResult();
        userInput = userInput.toLowerCase();

        String newResult = "";

        for (int i = 0; i < answer.length(); i++) {
            String letter = "_";
            String currLetterOfAnswer = String.valueOf(answer.charAt(i));
            String currLetterOfCurrResult = String.valueOf(currResult.charAt(i));

            if (currLetterOfAnswer.toLowerCase().equals(userInput)) letter = currLetterOfAnswer;
            else if (!currLetterOfCurrResult.valueOf(currResult.charAt(i)).equals("_")) {
                letter = currLetterOfCurrResult;
            }

            newResult += letter;
        }

        this.question.setCurrResult(newResult);
    }


    private boolean isSameUserInput(String userInput) {
        final String currResult = this.question.getCurrResult();

        return currResult.indexOf(userInput) != -1;
    }

    private boolean isUserInputHit(String userInput) {
        final String answer = this.question.getAnswer();

        return answer.toLowerCase().indexOf(userInput.toLowerCase()) != -1;
    }

    public String userInput() {
        System.out.print("Guess a number: ");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        return userInput;
    }

    public void showResult() {
        System.out.println("Life Point:" + " " + this.getLifePoint());
        System.out.println("Answer:" + " " + this.question.getAnswer());
        System.out.println("Current Result:" + " " + this.question.getCurrResult());
    }

    public void printStart() {
        System.out.println("Here's a question");
    }

    private void initialSetup() {
        this.question.resetAnswer();
        this.convertLettersToUnderScore();
        this.setLifePoint(this.MAX_LIFE_POINT);
    }

    private void convertLettersToUnderScore() {
        String answer = this.question.getAnswer();
        String underScores = "";

        for (int i = 0; i < answer.length(); i++) {
            if(String.valueOf(answer.charAt(i)).equals(" ")){
                underScores += " ";
                continue;
            }
            underScores += "_";
        }

        this.question.setCurrResult(underScores);
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public int getLifePoint() {
        return lifePoint;
    }
}