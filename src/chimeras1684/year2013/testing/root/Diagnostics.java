/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chimeras1684.year2013.testing.root;
import chimeras1684.year2013.testing.maps.ConstantsMap;
import chimeras1684.year2013.testing.maps.SubsystemMap;
import chimeras1684.year2013.testing.maps.CommandMap;
import chimeras1684.year2013.testing.maps.StringMap;
import chimeras1684.year2013.testing.maps.PortMap;
import edu.wpi.first.wpilibj.DriverStationLCD;
/**
 *
 * @author Arhowk
 */
public class Diagnostics {
    private static Diagnostics instance;
    private DiagnosticsInstance instances[];
    private int instanceCounter;
    private int currentInstanceCounter;
    private DiagnosticsInstance currentInstance;
    private static DriverStationLCD lcd;
    
    public static abstract class DiagnosticsInstance{
        private String descriptions[];
        private String numbString;

        protected CommandMap cmdMap;
        protected SubsystemMap subMap;
        protected ConstantsMap constMap;
        protected StringMap stringMap;
        protected PortMap portMap;

        private AdvancedButton button;

        public DiagnosticsInstance(){
            descriptions = new String[6];
            numbString = "$NUMB";
            cmdMap = CommandMap.getInstance();
            subMap = SubsystemMap.getInstance();

        }
        protected void setDescription(String s, int line){
            descriptions[line] = s;
        }
        protected abstract double getAtLine(int line);
        public void update(){
            System.out.println("update");
            for(int i = 1; i < 7; i++){
                String s = descriptions[i];
                if(s == null){
                    break;
                }
                System.out.println(s == null ? "True" : "false");
                if(s.substring(s.length() - 5, s.length()).equalsIgnoreCase("$NUMB")){
                    s = s.substring(0,s.length() - 5) + getAtLine(i);
                }
                switch(i){
                    case 1:
                        lcd.println(DriverStationLCD.Line.kUser1, 1,s);
                        break;
                    case 2:
                        lcd.println(DriverStationLCD.Line.kUser2, 1,s);
                    case 3:
                        lcd.println(DriverStationLCD.Line.kUser3, 1,s);
                    case 4:
                        lcd.println(DriverStationLCD.Line.kUser4, 1,s);
                    case 5:
                        lcd.println(DriverStationLCD.Line.kUser5, 1,s);
                    case 6:
                        lcd.println(DriverStationLCD.Line.kUser6, 1,s);
                }
            }
        }
    }
    private Diagnostics(){
        instance = this;
        instances = new DiagnosticsInstance[50];
        lcd = DriverStationLCD.getInstance();
        instanceCounter = -1;
        currentInstanceCounter = -1;
    }
    public static Diagnostics getInstance(){
        instance = (instance == null) ? new Diagnostics() : instance;
        return instance;
    }
    public void addInstance(DiagnosticsInstance i){
        instanceCounter++;
        instances[instanceCounter] = i;
    }
    public void wipeLCD(){
        lcd.println(DriverStationLCD.Line.kUser1, 0, "                           ");
        lcd.println(DriverStationLCD.Line.kUser2, 0, "                           ");
        lcd.println(DriverStationLCD.Line.kUser3, 0, "                           ");
        lcd.println(DriverStationLCD.Line.kUser4, 0, "                           ");
        lcd.println(DriverStationLCD.Line.kUser5, 0, "                           ");
        lcd.println(DriverStationLCD.Line.kUser6, 0, "                           ");
    }
    public void toggleInstance(){
        currentInstanceCounter++;
        if(instances[currentInstanceCounter] == null){
            currentInstanceCounter = 0;
        }
        currentInstance = instances[currentInstanceCounter];
    }
    public void update(){
        if(currentInstance == null){
            System.out.println("No Given Instance");
        }else{
            System.out.println("updating");
            currentInstance.update();
        }
    }
}
