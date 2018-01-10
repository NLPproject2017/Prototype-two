
package Model;

/**
 *
 * @author Henrietta
 */
public class Printer {

    public void print(Sentence sentence) {
        System.out.println();
        System.out.println("Sentence: " + sentence.getSentence());
        System.out.println("Annotated sentence: " + sentence.getAnnotatedSentence());

        //intents
        for (int k = 0; k < sentence.getIntents().size(); k++) {
            System.out.println("Intents: " + sentence.getIntents().get(k));
        }
        for (int j = 0; j < sentence.getIntentProb().length; j++) {
            double[] dub = sentence.getIntentProb();
            System.out.println("Probability " + j + ": " + dub[j]);
        }
        System.out.println();
        //entities
        for (int i = 0; i < sentence.getEntities().size(); i++) {
            EntityObject obj = (EntityObject) sentence.getEntities().get(i);
            System.out.println("Entity: " + obj.getEntity() + " Entity type: " + obj.getEntityType());
        }
    }
}
