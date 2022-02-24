package classes;

public class Validation {
    public static boolean isValidInput(String userInput) {
        if (!Validation.isSingleLetter(userInput)) return false;

        return true;
    }

    private static boolean isSingleLetter(String userInput) {

        return userInput.length() == 1;
    }

}
