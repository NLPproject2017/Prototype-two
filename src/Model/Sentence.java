
package Model;

import java.util.ArrayList;

/**
 *
 * @author Henrietta
 */
public class Sentence {
    
    private final String sentence;
    private String annotatedSentence;
    private ArrayList entities = new ArrayList();
    private ArrayList intents = new ArrayList();
    double[] dub;
    
    public Sentence(String sentence){
        this.sentence=sentence;
    }
    public void setSentence(){
        
    }
    public String getSentence(){
        return sentence;
    }
    public void setAnnotatedSentence(String annotatedSentence){
        this.annotatedSentence = annotatedSentence;
    }
    public String getAnnotatedSentence(){
        return annotatedSentence;
    }
    public void setIntentProb(double[] dub){
        this.dub = dub;
    }
      public double[] getIntentProb(){
        return dub;
    }
    public void addEntity(ArrayList entities){
        this.entities = entities;
    }
    public ArrayList getEntities(){
        return entities;
    }
    public void addIntent(ArrayList intents){
        this.intents = intents;
    }
    public ArrayList getIntents(){
        return intents;
    }
}
