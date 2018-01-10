
package Model;

/**
 *
 * @author Henrietta
 */
public class ActionTrigger {

    Sentence sentence;

    public ActionTrigger(Sentence sentence) {
        this.sentence = sentence;
    }
    public void doAction(){
        if(sentence.getIntents().get(0)=="taxi"){
            System.out.println("Taxi app opening...");
        }
        if(sentence.getIntents().get(0)=="train"){
            System.out.println("Train app opening...");
        }
        if(sentence.getIntents().get(0)=="greeting"){
            System.out.println("Hello there yourself!");
        }
         if(sentence.getIntents().get(0)=="goodbye"){
            System.out.println("See you later!");
        }
    }
}
