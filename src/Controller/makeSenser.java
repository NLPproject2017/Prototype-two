
package Controller;

import Model.ActionTrigger;
import java.io.IOException;
import Model.ClassifyForMe;
import Model.FindEntities;
import Model.Printer;
import Model.Sentence;
import Model.SpeechToText;
import Model.TrainFromArff;

import weka.classifiers.Classifier;

/**
 *
 * @author Henrietta
 */
public class makeSenser {

    private final Printer printer = new Printer();
    //create handler objects(Weka)
    private final TrainFromArff trainer = new TrainFromArff();
    private final ClassifyForMe classifyForMe = new ClassifyForMe();
    //Stanford
    private final FindEntities findEntities = new FindEntities();

    //speech recognition
    public String startRecognition(){
        SpeechToText speechHandler = new SpeechToText();
        String resStr = speechHandler.recog();
        return resStr;
    }
    //stanfordNER
    public void interpretSentence(String input, String classifierLocation, String file) {
        Sentence sentence = new Sentence(input);
        //input = startRecognition();
        try {
            //Weka
            trainer.convertFileToArff(file);
            Classifier trainedCl = trainer.train();
            classifyForMe.makeInstance(sentence);
            sentence = classifyForMe.classify(trainedCl, sentence);
            //StanfordNER
            sentence = findEntities.StanfordNER(sentence, classifierLocation);

            //System.out.println(trainedCl);
        } catch (IOException ex) {
            System.out.println("path to input error" + ex.getMessage());
        } catch (ClassNotFoundException classeEx) {
            System.out.println("could not load stanford classifier" + classeEx.getMessage());
        } catch (Exception e) {
            System.out.println("An unknown error occured" + e.getMessage());
        }
        printer.print(sentence);
        
        //trigger action based on NLU
        ActionTrigger action = new ActionTrigger(sentence);
        action.doAction();
    }
}
