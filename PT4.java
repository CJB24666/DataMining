import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Add;
import weka.filters.unsupervised.attribute.Remove;

public class PT4 {

    public static void main(String[] args) throws Exception {

        // loading data from an ARFF file
        DataSource trainSource = new DataSource("C:/Users/chris/Dropbox/Year_three/Data mining/inputfiles/train.csv");
        Instances trainData = trainSource.getDataSet();

        DataSource testSource = new DataSource("C:/Users/chris/Dropbox/Year_three/Data mining/inputfiles/test.csv");
        Instances testData = testSource.getDataSet();

        String[] instruments = {"Accordian", "clarinet", "Trumpet", "DoubleBass", "Saxophone", "Oboe", "Piano",
                "Violin", "Cello", "Tuba", "Viola", "Trombone"};

        for (String cur_instrument : instruments) {
            System.out.println("Starting " + cur_instrument);
            Add filter = new Add();
            filter.setAttributeIndex("last");
            filter.setNominalLabels("false, true");
            filter.setAttributeName("is" + cur_instrument);
            filter.setInputFormat(trainData);
            Instances newData = Filter.useFilter(trainData, filter);

            Add filter2 = new Add();
            filter2.setAttributeIndex("last");
            filter2.setNominalLabels("false,true");
            filter2.setAttributeName("is" + cur_instrument);
            filter2.setInputFormat(testData);
            Instances newData2 = Filter.useFilter(testData, filter2);

            for (int i = 0; i < newData.numInstances(); i++) {
                // newData is entire data set
                // newData.instance(0).stringValue(x) is cell at x in row 0. EG. = "true"

                if (newData.instance(i).toString().contains(cur_instrument)) {
                    newData.instance(i).setValue(newData.numAttributes()-1, "true");
                    // change ? to true
                } else {
                    newData.instance(i).setValue(newData.numAttributes()-1, "false");
                }
            }

            Remove removeFilter = new Remove();
            removeFilter.setAttributeIndices(Integer.toString(newData2.numAttributes()-5)+","+Integer.toString(newData2.numAttributes()-4)+","+Integer.toString(newData2.numAttributes()-3)+","+Integer.toString(newData2.numAttributes()-2)+"," +Integer.toString(newData2.numAttributes()-1));
//			removeFilter.setAttributeIndices("1");
            removeFilter.setInvertSelection(false);
            removeFilter.setInputFormat(newData2);
            Instances testnodata = Filter.useFilter(newData2, removeFilter);


            Remove removeFilter2 = new Remove();
            removeFilter2.setAttributeIndices(Integer.toString(newData.numAttributes()-2) +","+ Integer.toString(newData.numAttributes()-1));
            removeFilter2.setInvertSelection(false);
            removeFilter2.setInputFormat(newData);
            Instances nodata = Filter.useFilter(newData, removeFilter2);

            Instances dataset= testnodata;
            ArffSaver arffSaver = new ArffSaver();
            arffSaver.setInstances(dataset);
            arffSaver.setFile(new File("C:/Users/chris/Documents/test/" + cur_instrument + ".arff"));
            arffSaver.writeBatch();

            Instances dataset2= nodata;
            ArffSaver arffSaver2 = new ArffSaver();
            arffSaver2.setInstances(dataset2);
            arffSaver2.setFile(new File("C:/Users/chris/Documents/train/" + cur_instrument + ".arff"));
            arffSaver2.writeBatch();

            System.out.print("creating " + "train " + cur_instrument + ".arff"+ "\n");
            System.out.print("creating " + "test " + cur_instrument + ".arff"+ "\n");
        }
    }
}