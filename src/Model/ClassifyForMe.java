
package Model;

import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 *
 * @author Henrietta
 */
public class ClassifyForMe {
    
    private Instances arffBuild;
    private ArrayList intents = new ArrayList();

    public ClassifyForMe() {
    }

    public Sentence classify(Classifier trainedCl, Sentence sentence) throws Exception {

        //classify sentence in created arff, instance(0)
        double guessedIntent = trainedCl.classifyInstance(arffBuild.instance(0));
        
        String intent = arffBuild.classAttribute().value((int) guessedIntent);
        intents.add(intent);
        sentence.addIntent(intents);
        
        //store probabilities
        double[] probabilities = trainedCl.distributionForInstance(arffBuild.instance(0));
        sentence.setIntentProb(probabilities);
       
        return sentence;
    }
    public void makeInstance(Sentence sentence) {
        //could be redone to work dynamically depending on input
        //create arff(Instance)
        //Attributes   
        //classs
        ArrayList classValues = new ArrayList();

        classValues.add("taxi");
        classValues.add("train");
        classValues.add("greeting");
        classValues.add("goodbye");
        Attribute one = new Attribute("classs", classValues);
        //Text - null for string def
        Attribute two = new Attribute("Text", (ArrayList) null);

        //collect
        ArrayList attributeList = new ArrayList();
        attributeList.add(one);
        attributeList.add(two);

        //make instance
        Instances arffInstance = new Instances("Classification arff", attributeList, 1);
        arffBuild = arffInstance;

        //set index of class attribute
        arffInstance.setClassIndex(0); //first one
        //data -2 attribute values
        DenseInstance dataRow = new DenseInstance(2);
        //set values of two(Text value) to string
        dataRow.setValue(two, sentence.getSentence());
        //add data row to arff
        arffInstance.add(dataRow);
    }

}
