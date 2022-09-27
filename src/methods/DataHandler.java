package methods;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataHandler {

    public static String fileText;
    public void readFile() throws IOException {
        FileReader fileReader = new FileReader("todo.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String str = br.readLine();
        while (str != null) {
            fileText = fileText + str + "\n";
            str = br.readLine();
        }
        System.out.println(fileText);
        fileReader.close();
    }

    public void saveFile(JTextField jTextField) throws FileNotFoundException {
        FileReader fileReader = new FileReader("todo.txt");
        BufferedReader br = new BufferedReader(fileReader);
        jTextField.getText();

    }
}
