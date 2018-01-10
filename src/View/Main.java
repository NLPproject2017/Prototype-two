
package View;

import Controller.makeSenser;
import Model.SpeechToText;
import java.io.IOException;

/**
 *
 * @author Henrietta
 */
public class Main {
    /* uncomment to run using the hardcoded example sentences one at a time*/
    //static String input = "Book a taxi to go from Stockholm  to Chicago";
    //static String input = "reserve a ticket for the 9:00 service to Brussels tomorrow";
    static String input = "Hello, how are you?";
    //static String input = "Ok, I will see you later!";
    //static String input = "Goodmorning Tom";
    static String classifierLocation = "LOCATION OF YOUR CLASSIFIER";
    //input to be learned
    static String file = "LOCATION OF TRAINING SET FOR INTENTS";

    public static void main(String[] args) throws IOException {
        SpeechToText speechHandler = new SpeechToText();
        //running with hardcoded examples unless the speech recognition is enabled
        classify();
    }

    public static void classify() {
        makeSenser controller = new makeSenser();
        controller.interpretSentence(input, classifierLocation, file);
    }

}
