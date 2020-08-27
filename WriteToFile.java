import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.*;

public class WriteToFile {

    static String[][] data = {
            {"bp", "bt", "bc", "bs", "bc", "bt", "bp"},
            {"bau", "", "bau", "", "bau", "", "bau"},
            {"", "", "", "", "", "", ""},
            {"", "", "", "", "", "", ""},
            {"", "", "", "", "", "", ""},
            {"", "", "", "", "", "", ""},
            {"rau", "", "rau", "", "rau", "", "rau"},
            {"rp", "rt", "rc", "rs", "rc", "rt", "rp"},
        };

    public static void main(String[] args) {
        try {
            FileWriter myWriter = new FileWriter("dataText.txt");
            for (int i=0;i<8;i++){
                for (int j=0;j<7;j++){
                    myWriter.write(data[i][j]);
                    myWriter.write("\t");
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



}