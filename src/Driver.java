import classes.GameController;
import classes.Question;

public class Driver {
    public static void main(String[] args) {
        Question question = new Question();
        GameController game = new GameController(question);
        game.start();
    }
}
