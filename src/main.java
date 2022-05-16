import java.io.File;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        new main();
    }
    private String input;
    public main() {
        try {
            //ask for user input
            Scanner scan = new Scanner(System.in);

            System.out.print("\nLocation for the task script: ");
            //set user input file to input variable
            input = scan.nextLine();


            //check if the file actually exists, if not then restart the program and ask again
            File fileInput = new File(input);
            if (!fileInput.exists()) {
                new main();
                return;
            }

            //run the task using the input file
            new busb().executeTaskScript(input);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
