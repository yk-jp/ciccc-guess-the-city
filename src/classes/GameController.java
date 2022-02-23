package classes;

public class GameController {

    public Question question;

    public GameController(Question question) {
        this.question = question;
    }

    public void start() {
        this.initialSetup();

        System.out.println(this.question.getCurrResult());
        System.out.println(this.question.getAnswer());
    }

    public void initialSetup() {
        this.question.resetAnswer();
        this.convertLettersToUnderScore();
    }


    private void convertLettersToUnderScore() {
        String answer = this.question.getAnswer();
        String underScores = "";

        for (int i = 0; i < answer.length(); i++) {
            underScores += "_";
        }

        this.question.setCurrResult(underScores);
    }
}