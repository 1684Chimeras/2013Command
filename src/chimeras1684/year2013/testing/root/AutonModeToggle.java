/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;
import edu.wpi.first.wpilibj.DriverStationLCD;
/**
 *
 * @author Arhowk
 */
public class AutonModeToggle {
    String autonToggle[][];
    int currentAutonToggle;
    int maxAutonToggle;
    AdvancedButton button;
    boolean selectHasPressed;
    
    public AutonModeToggle(/*int maxAutonToggle, */AdvancedButton buttonToggle){
    //    this.maxAutonToggle = maxAutonToggle;
        ;
        this.maxAutonToggle = 4;
        currentAutonToggle = 0;
        autonToggle = new String[5][7];
        autonToggle[0][1] = "Nine Disc            ";
        autonToggle[0][2] = "                     ";
        autonToggle[0][3] = "                     ";
        autonToggle[0][4] = "                     ";
        autonToggle[0][5] = "                     ";
        autonToggle[1][1] = "(mid-back pyramid)   ";
        autonToggle[1][2] = "Shoot 3              ";
        autonToggle[1][3] = "Center of pyramid    ";
        autonToggle[1][4] = "Get Discs            ";
        autonToggle[1][5] = "Shoot 2              ";
        autonToggle[2][1] = "Seven Disc           ";
        autonToggle[2][2] = "                     ";
        autonToggle[2][3] = "                     ";
        autonToggle[2][4] = "                     ";
        autonToggle[2][5] = "                     ";
        autonToggle[3][1] = "Shoot Donut Midline  ";
        autonToggle[3][2] = "                     ";
        autonToggle[3][3] = "                     ";
        autonToggle[3][4] = "                     ";
        autonToggle[3][5] = "                     ";
        autonToggle[4][1] = "Target and Shoot     ";
        autonToggle[4][2] = "                     ";
        autonToggle[4][3] = "                     ";
        autonToggle[4][4] = "                     ";
        autonToggle[4][5] = "                     ";
        this.button = buttonToggle;
    }
    public void checkForToggle(){
        if(button.isPressed() && !selectHasPressed){
            selectHasPressed = true;
            toggle();
        }else if(!button.isPressed()&& selectHasPressed){
            selectHasPressed = false;
        }
    }
    public void toggle(){
        currentAutonToggle++;
        if(currentAutonToggle > maxAutonToggle){
            currentAutonToggle = 0;
        }
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "Mode : " + (currentAutonToggle + 1));
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, autonToggle[currentAutonToggle][1]);
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, autonToggle[currentAutonToggle][2]);
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, autonToggle[currentAutonToggle][3]);
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, autonToggle[currentAutonToggle][4]);
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, autonToggle[currentAutonToggle][5]);
        DriverStationLCD.getInstance().updateLCD();
    }
    public int getToggleStage(){
        return currentAutonToggle;
    }
}
