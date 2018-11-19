import weka.core.Attribute;
import weka.core.Instances;
//import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils;
import java.util.ArrayList;

public class class2 {
    public static void main(String[] args) throws Exception {
        // read in predictions


        ConverterUtils.DataSource predsource = new ConverterUtils.DataSource("C:/Users/chris/Dropbox/Year_three/Data mining/finalpred/combinedfinalpred.csv");
        Instances predictions = predsource.getDataSet();


        ArrayList<Attribute> atts = new ArrayList<>(2);
        atts.add(new Attribute("ID", (ArrayList<String>) null));
        atts.add(new Attribute("Instrument", (ArrayList<String>) null));

        Instances finalData = new Instances("Predictions", atts, 0);

        // Row by row
        for (int i = 0; i < predictions.numInstances(); i++) {
            double highest = 0;
            String instrument = "";
            for (int j = 1; j < predictions.numAttributes() - 3; j++) {
                // get the max value that belongs to the right class
                if (predictions.instance(i).value(j) > highest) {
                    highest = predictions.instance(i).value(j);
                    instrument = predictions.attribute(j).name();
                }
//            }
                // If the instrument doesn't belong to the right class2, change it
//                System.out.println(i + instrument);
                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("chrd_simple")) {
                    if (predictions.instance(i).value(6) < 0.33) {
                        instrument = "SynthBass";
                    }
                }
                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("aero_free-reed")) {

                    instrument = "Accordian";

                }

                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("aero_side")) {
                    if (predictions.instance(i).value(predictions.numAttributes() - 1) < 471) {
                        instrument = "Flute";
                    } else {
                        instrument = "Piccolo";
                    }
                }
                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("aero_double-reed")) {
                    if (predictions.instance(i).value(predictions.numAttributes() - 1) < 376) {
                        instrument = "Bassoon";
                    } else {
                        instrument = "Oboe";
                    }
                }
                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("aero_single-reed")) {
                    if (predictions.instance(i).value(predictions.numAttributes() - 1) < 315) {
                        instrument = "Clarinet";
                    } else {
                        instrument = "Saxophone";
                    }
                }

                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("chrd_composite")) {

                    if (predictions.instance(i).value(predictions.numAttributes() - 1) < 2058 && predictions.instance(i).value(predictions.numAttributes() - 1) > 549) {
                        instrument = "Violin";

                    } else if (predictions.instance(i).value(predictions.numAttributes() - 1) < 548 && predictions.instance(i).value(predictions.numAttributes() - 1) > 333) {
                        instrument = "viola";

                    } else if (predictions.instance(i).value(predictions.numAttributes() - 1) < 332 && predictions.instance(i).value(predictions.numAttributes() - 1) > 147) {
                        instrument = "Cello";

                    } else {
                        instrument = "Doublebass";

                    }
                }
                if (predictions.instance(i).stringValue(predictions.numAttributes() - 2).equals("aero_lip-vibrated")) {

                    if ((predictions.instance(i).value(predictions.numAttributes() - 1) < 3770 && predictions.instance(i).value(predictions.numAttributes() - 1) > 1723)) {
                        instrument = "Englishhorn";

                    } else if (predictions.instance(i).value(predictions.numAttributes() - 1) < 1722 && (predictions.instance(i).value(predictions.numAttributes() - 1) > 406)) {

                        instrument = "Trumpet";

                    } else if (predictions.instance(i).value(predictions.numAttributes() - 1) < 405 && (predictions.instance(i).value(predictions.numAttributes() - 1) > 362)) {
                        instrument = "Frenchhorn";

                    } else if (predictions.instance(i).value(predictions.numAttributes() - 1) < 361 && (predictions.instance(i).value(predictions.numAttributes() - 1) > 282)) {
                        instrument = "Trombone";
                    } else {
                        instrument = "Tuba";

                    }

                }
            }

            System.out.println(i+1 + "," + instrument);

        }


    }

}













//old code old system doesn't seem to work :(.

//            CSVSaver saver = new CSVSaver();
//            saver.setInstances(finalData);
//            saver.setFile(new File("C:\\Users\\chris\\Documents\\finalpred\\completepred.csv"));
//            saver.writeBatch();


//System.out.println("ID: " + i + " Prediction: " + instrument);

//                if (predictions.instance(i).toString().contains "chrd_simple" in predictions.numAttributes()-2) {
//
//                predictions.instance(i) <= 0.5
//
//                        else{
//
//                            set row to SynthBass
//
//

//        ConverterUtils.DataSource class2test = new ConverterUtils.DataSource("C:/Users/chris/Dropbox/Year_three/Data mining/inputfiles/raw/test.csv");
//        Instances rawTest = class2test.getDataSet();
//
//        for (String cur_instrument : instruments) {
//            // loading data from an ARFF file
//            ConverterUtils.DataSource source = new ConverterUtils.DataSource("C:/Users/chris/Documents/pred/" + cur_instrument + ".csv");
//            Instances predictions = source.getDataSet();
//
//
//            System.out.println(cur_instrument);
//
//            for (int i = 0; i < predictions.numInstances(); i++) {
//                switch (rawTest.instance(i).stringValue(rawTest.numAttributes() - 1)) {
//                    case "chrd_simple":
//                        if (cur_instrument.equals("Piano") & (rawTest.instance(i).value(17) >= 110)) {
//                            instanceValue1[0] = instanceValue1[0] - 1;
//                        }
//                        break;
//                    case "aero_side":
//                        if (cur_instrument.equals("Picolo") & (rawTest.instance(i).value(18) <= 359)) {
//                            instanceValue1[0] = instanceValue1[0] - 1;
//                        }
//                        break;
//                    case "aero_double-reed":
//                        if (cur_instrument.equals("Oboe") & (rawTest.instance(i).value(18) <= 777)) {
//                            instanceValue1[0] = instanceValue1[0] - 1;
//                        }
//                        break;
//                    case "aero_single-reed":
//                        if (cur_instrument.equals("Saxophone") & (rawTest.instance(i).value(19) <= 282)) {
//                            instanceValue1[0] = instanceValue1[0] - 1;
//                        }
//                        break;
//                    case "aero_lip-vibrated":
//                        if (rawTest.instance(i).value(18) >= 110) {
//                            instanceValue1[0] = instanceValue1[0] - 1;
//                        }
//                        break;
//                    case "chrd_composite":
//                        if (rawTest.instance(i).value(18) >= 110) {
//                            instanceValue1[0] = instanceValue1[0] - 1;
//                        }
//                        break;
//            }
//
//        }
//    }


//        switch (rawTest.instance(i).stringValue(rawTest.numAttributes() - 1)) {
//            case "chrd_simple":
//                if (cur_instrument.equals("Piano")) {
//                    instanceValue1[0] = instanceValue1[0] * 1.5;
//                }
//                break;
//            case "chrd_composite":
//                if (cur_instrument.equals("Violin") || cur_instrument.equals("Viola") || cur_instrument.equals("DoubleBass") || cur_instrument.equals("Cello")) {
//                    instanceValue1[0] = instanceValue1[0] * 1.7;
//                }
//                break;
//            case "aero_lip-vibrated":
//                if (cur_instrument.equals("Trombone") || cur_instrument.equals("Trumpet") || cur_instrument.equals("Tuba")) {
//                    instanceValue1[0] = instanceValue1[0] * 1.7;
//                }
//                break;
//            case "aero_side":
//                if (cur_instrument.equals("Piccolo") || cur_instrument.equals("Flute")) {
//                    instanceValue1[0] = instanceValue1[0] + 2;
//                }
//                break;
//            case "aero_double-reed":
//                if (cur_instrument.equals("Oboe") || cur_instrument.equals("Bassoon")) {
//                    instanceValue1[0] = instanceValue1[0] * 1.7;
//                }
//                break;
//            case "aero_single-reed":
//                if (cur_instrument.equals("Clarinet") || cur_instrument.equals("Saxophone")) {
//                    instanceValue1[0] = instanceValue1[0] * 1.7;
//                }
//            case "aero_free-reed":
//                if (cur_instrument.equals("Accordian")) {
//                    instanceValue1[0] = instanceValue1[0] + 2;
//                }
//                break;
//
//        }
//        System.out.println(rawTest.attribute(rawTest.numAttributes() - 1).name());
//
//        switch (rawTest.instance(i).stringValue(rawTest.numAttributes() - 1)) {
//            case "chrd_simple":
//                if (cur_instrument.equals("Piano") & (rawTest.instance(i).value(17) >= 110)) {
//                    instanceValue1[0] = instanceValue1[0] - 1;
//                }
//                break;
//            case "aero_side":
//                if (cur_instrument.equals("Picolo") & (rawTest.instance(i).value(18) <= 359)) {
//                    instanceValue1[0] = instanceValue1[0] - 1;
//                }
//                break;
//            case "aero_double-reed":
//                if (cur_instrument.equals("Oboe") & (rawTest.instance(i).value(18) <= 777)) {
//                    instanceValue1[0] = instanceValue1[0] - 1;
//                }
//                break;
//            case "aero_single-reed":
//                if (cur_instrument.equals("Saxophone") & (rawTest.instance(i).value(19) <= 282)) {
//                    instanceValue1[0] = instanceValue1[0] - 1;
//                }
//                break;
////                    case "aero_lip-vibrated":
////                        if (rawTest.instance(i).value(18) >= 110) {
////                            instanceValue1[0] = instanceValue1[0] - 1;
////                        }
////                        break;
////                    case "chrd_composite":
////                        if (rawTest.instance(i).value(18) >= 110) {
////                            instanceValue1[0] = instanceValue1[0] - 1;
////                        }
////                        break;
//        }
//        testInst.add(new DenseInstance(1.0, instanceValue1));
//    }
//
//    CSVSaver saver = new CSVSaver();
//                saver.setInstances(testInst);
//                saver.setFile(new
//
//    File("C:/Users/chris/Documents/pred/"+cur_instrument +".csv"));
//                saver.writeBatch();

