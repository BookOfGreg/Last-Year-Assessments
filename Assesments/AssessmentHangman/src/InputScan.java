
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mjcollin
 */
public class InputScan {
    // This is for handling, scanning of input 

  private Scanner scanner;

  public InputScan() {
    scanner = new Scanner(new BufferedInputStream(System.in));
  }

  public InputScan(File file) {
        try {
            scanner = new Scanner(file);
        }
        catch (IOException exception) {
            System.err.println("Could not open file " + file);
        }
  }

  public InputScan(String s) {

        try {
            File file = new File(s);
            if (file.exists()) {
                scanner = new Scanner(file);
                return;
            }else{
            	System.out.println("File does not exist "+file.getAbsolutePath());
            }
        }
        catch (IOException exception) {
            System.err.println("Could not open file " + s);
        }
    }

  // Check if input is empty
  public boolean isEmpty(){
      return !scanner.hasNext();
  }

  // Currently does nothing
  // Could stick some preprocessing in
  public void  initialize(){}

  // Get next string from scanner
  public String getNextString(){
      return scanner.next();
  }

  public void dropLine(){
      // temp. kludge ... sorry
	  scanner.nextLine();
  }

}
