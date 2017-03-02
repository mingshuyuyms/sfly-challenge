package src.main;

import java.io.IOException;
import java.text.ParseException;


import src.main.ltvData.LTVDataSet;

/**
 * Created by mingshuyu on 3/1/17.
 */
public class TopKTest {

    public static void main(String args[]) throws IOException, ParseException {
        //change input and out related path here
        Manipulate m = new Manipulate("input/input2.txt","output/output.txt");
        LTVDataSet ltvDataSet = m.readFromFile();
        //get top x, fisrt arg is x
        m.opXSimpleLTVCustomers(3,ltvDataSet);
    }

}
