import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        try {
            Scanner scan = new Scanner(System.in);
            busb usb = new busb();

            String input;

            System.out.print("\nLocation for the task script: ");
            input = scan.nextLine();

            usb.executeTaskScript(input);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
