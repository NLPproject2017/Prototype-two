package Model;

import View.Main;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henrietta
 */
public class SpeechToText {

    private LiveSpeechRecognizer rec;
    private final ArrayList arr = new ArrayList();

    public static Configuration config() {
        Configuration config = new Configuration();
        config.setSampleRate(16000);
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        return config;
    }

    public String recog() {
        Configuration c = config();
        //USING WAV
        try {
            StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(c);

            InputStream stream = new FileInputStream(new File("chicago16.wav"));
            recognizer.startRecognition(stream);
            SpeechResult result;
            while ((result = recognizer.getResult()) != null) {
                System.out.format("Hypothesis: %s\n", result.getHypothesis());
                arr.add(result.getHypothesis());

            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*uncomment for using MIC as input*/
        //Thread trad = new recogTrad();
        //trad.start();

        String sentence = makeString();
        return sentence;
    }

    private String makeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            String word = (String) arr.get(i);
            sb.append(word);
            sb.append(" ");
        }
        String sentence = sb.toString();
        return sentence;
    }

    //runs with mic in separate thread to keep listening
    private static class recogTrad extends Thread {

        LiveSpeechRecognizer recognizer;

        public recogTrad() {

        }

        public void run() {
            Configuration configuration = config();

            //USING MIC
            try {
                recognizer = new LiveSpeechRecognizer(configuration);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            recognizer.startRecognition(true);
            System.out.println("Hello from the thread!");
            SpeechResult result;

            while ((result = recognizer.getResult()) != null) {
                //System.out.println("Hello from a thread in while!");
                String spoken = result.getHypothesis();
                System.out.println("You said: " + spoken);
            }
            recognizer.stopRecognition();
        }
    }

}
