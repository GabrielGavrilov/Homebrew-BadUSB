import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class busb {

    public void executeTaskScript(String scriptLocation) throws AWTException {
        Robot robot = new Robot();
        String[] keycodes = {
                "A=65", "B=66", "C=67", "D=68", "E=69", "F=70", "G=71", "H=72",
                "I=73", "J=74", "K=75", "L=76", "M=77", "N=78", "O=79", "P=80",
                "Q=81", "R=82", "S=83", "T=84", "U=85", "V=86", "W=87", "X=88",
                "Y=89", "Z=90", "SPACE=32", "ENTER=10"
        };

        //make a new thread for robot actions instead of thread.wait()-ing the entire program
        Thread roboThread = new Thread(() -> {
            try(BufferedReader br = new BufferedReader(new FileReader(scriptLocation))) {
                String line;
                while((line = br.readLine()) != null) {
                    //make this a single variable instead of making a new one for every if statement
                    String[] cmdSplit = line.split("=");
                    String commandOption = cmdSplit[0];

                    switch (commandOption) {
                        case "MOUSE_MOVE":
                            String[] moveCordinates = cmdSplit[1].split(",");
                            robot.mouseMove(Integer.parseInt(moveCordinates[0]), Integer.parseInt(moveCordinates[1]));
                            break;

                        case "MOUSE_CLICK":
                            if(cmdSplit[1].equals("LEFT")) {
                                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                            }
                            if(cmdSplit[1].equals("RIGHT")) {
                                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                            }
                            break;

                        case "KEY_PRESS":
                            String[] key = line.split("=");
                            for(int i = 0; i < keycodes.length; i++) {
                                String[] keycode = keycodes[i].split("=");
                                if(key[1].equals(keycode[0])) {
                                    Thread.sleep(100);
                                    robot.keyPress(Integer.parseInt(keycode[1]));
                                }
                            }
                            break;

                        case "WAIT":
                            Thread.sleep(Integer.parseInt(cmdSplit[1]));
                            break;

                        case "APP_LAUNCH":
                            String applicationLocation = cmdSplit[1];
                            ProcessBuilder newApp = new ProcessBuilder(applicationLocation);
                            try{
                                newApp.start();
                            }
                            catch(Exception e) {
                                System.out.println("Failed to load the app: " + applicationLocation);
                            }

                            break;
                        case "":
                            //dont run anything if nothing is there
                            break;

                        default: System.out.println("Invalid Command on line >> " + line);
                            break;
                    }
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
        });
        roboThread.start();
    }

}
