package Model;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Henrietta
 */
public class FindEntities {

    private final ArrayList entitiesList = new ArrayList();
    
    public Sentence StanfordNER(Sentence sentence, String classifierLocation) throws IOException, ClassNotFoundException {
        //load the classifier
        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(classifierLocation);
    
      //saves the output entities to a list(our case only one entry)
      List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(sentence.getSentence());
      //What the annotated string looks like
      sentence.setAnnotatedSentence(classifier.classifyToString(sentence.getSentence()));
      
        for (Triple<String, Integer, Integer> item : list) {
            String entity = sentence.getSentence().substring(item.second(), item.third());
            String entityType = item.first();
           
        //to store result
        EntityObject entObj = new EntityObject();    
        entObj.setEntity(entity);
        entObj.setEntityType(entityType);
        
        entitiesList.add(entObj);
        sentence.addEntity(entitiesList);
        }
        return sentence; 
        }
    }
    
    

