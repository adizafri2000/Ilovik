import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    static int[][] testData = new int[8][7];
  public static void main(String[] args) {
    try {
      File myObj = new File("dataText.txt");
      Scanner myReader = new Scanner(myObj);
      int i = 0;
      int j = 0;
      while (myReader.hasNextLine()) {
        
        String data = myReader.nextLine();
        String[] gg = myReader.nextLine().split("\n");
        String[] kk = new String[7];
        for (String s:gg)
        System.out.println(gg);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      //e.printStackTrace();
    }
  }
}