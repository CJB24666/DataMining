import weka.classifiers.Classifier;
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.instance.SMOTE;
import weka.filters.unsupervised.attribute.Add;

import java.io.*;
import java.util.ArrayList;


public class classifier {

    public static void main(String[] args) throws Exception {

        String[] instruments = {"Accordian", "clarinet", "Trumpet", "DoubleBass", "Saxophone", "Oboe", "Piano",
                "Violin", "Cello", "Tuba", "Viola", "Trombone"};


        DataSource class2test = new DataSource("C:/Users/chris/Dropbox/Year_three/Data mining/inputfiles/raw/test.csv");
        Instances rawTest = class2test.getDataSet();

        for (String cur_instrument : instruments) {
            // loading data from an ARFF file
            DataSource trainSource = new DataSource("C:/Users/chris/Documents/train/" + cur_instrument + ".arff");
            Instances trainData = trainSource.getDataSet();

            DataSource testSource = new DataSource("C:/Users/chris/Documents/test/" + cur_instrument + ".arff");
            Instances testData = testSource.getDataSet();


            // setting class attribute if the data format does not provide this information
            // For example, the XRFF format saves the class attribute information as well
            if (trainData.classIndex() == -1)
                trainData.setClassIndex(trainData.numAttributes() - 1);

            if (testData.classIndex() == -1)
                testData.setClassIndex(testData.numAttributes() - 1);


            SMOTE smote = new SMOTE();  //create object of SMOTE
            smote.setInputFormat(trainData);
            Instances data_smote;

            switch (cur_instrument) {
                case "clarinet":
                    smote.setPercentage(850);
                    break;
                case "Piano":
                    smote.setPercentage(1300);
                    break;
                case "Saxophone":
                    smote.setPercentage(880);
                    break;
                case "Cello":
                    smote.setPercentage(1500);
                case "Tuba":
                    smote.setPercentage(660);
                    break;
                case "Violin":
                    smote.setPercentage(1960);
                    break;
                case "Viola":
                    smote.setPercentage(820);
                    break;
                case "Trombone":
                    smote.setPercentage(650);
                    break;
                case "Trumpet":
                    smote.setPercentage(1000);
                    break;
                case "Oboe":
                    smote.setPercentage(450);
                    break;
                case "DoubleBass":
                    smote.setPercentage(860);
                    break;

                default:
                    smote.setPercentage(0);
            }

            data_smote = Filter.useFilter(trainData, smote); //Apply SMOTE on Dataset


            Classifier classifier = new SimpleLogistic();

            ArrayList<Attribute> atts = new ArrayList<>(1);

            // atts.add(new Attribute("ID"));
            atts.add(new Attribute(cur_instrument));


            Instances testInst = new Instances("testChris", atts, 0);
            System.out.println("creating " + cur_instrument + " prediction");

            classifier.buildClassifier(data_smote);
            for (int i = 0; i < testData.numInstances(); ++i) {
                double[] clsLabel = classifier.distributionForInstance(testData.instance(i));

                // prediction to then be writing to csv.
                double prediction = clsLabel[1];

                double[] instanceValue1 = new double[1];
                //instanceValue1[0] = i;
                instanceValue1[0] = prediction;

                CSVSaver saver = new CSVSaver();
                saver.setInstances(testInst);
                saver.setFile(new File("C:/Users/chris/Documents/pred/" + cur_instrument + ".csv"));
                saver.writeBatch();

            }
        }
    }
}

