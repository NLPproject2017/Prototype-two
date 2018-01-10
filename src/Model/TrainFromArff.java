
package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.stemmers.IteratedLovinsStemmer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author Henrietta
 */
public class TrainFromArff {

    private Instances data;
    //to convert input from string to wordvectors (naive bayes requirement)

    public Classifier train() throws Exception {
        data.setClassIndex(0); //class of arff is first attribute
        //create the classifier
        StringToWordVector filter = createFilter();
        Classifier createdClassifier = createClassifier(filter);
        return createdClassifier;
    }

    public void convertFileToArff(String file) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader r = new BufferedReader(fr);
        ArffReader arff = new ArffReader(r);
        data = arff.getData();
    }

    private StringToWordVector createFilter() {
        StringToWordVector filter = new StringToWordVector();
        filter.setAttributeIndices("last");
        filter.setTokenizer(new WordTokenizer());
        filter.setStemmer(new IteratedLovinsStemmer());

        return filter;
    }
    private Classifier createClassifier(StringToWordVector filter) throws Exception {
        FilteredClassifier classifier = new FilteredClassifier();
        classifier.setFilter(filter);
        classifier.setClassifier(new NaiveBayes());
        classifier.buildClassifier(data);
        return classifier;
    }
}
