/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.HiTechnicColorSensor;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kelly
 */
public class DiscHandling extends SubsystemBase
{  
    NetworkTable networkTable = null;
    Solenoid hopperUp;
    HiTechnicColorSensor redColorSensor;
    HiTechnicColorSensor blueColorSensor;
    boolean redColorSensorState = true;
    boolean blueColorSensorState = true;
    Timer timer;
    
    public static volatile DiscHandling instance;
    
    static
    {
        instance = new DiscHandling();
        instance.hopperUp = subMap.pnuematics.hopperUp;
        instance.timer = new Timer();
        instance.timer.start();
//        networkTable = NT;
//        redColorSensor = new HiTechnicColorSensor(RobotMap.redDiskColorSensor);
//        blueColorSensor = new HiTechnicColorSensor(RobotMap.blueDiskColorSensor);
    }
    private DiscHandling(){}
    public static DiscHandling getInstance(){
        return instance;
    }
    public void hopperUp() 
    {
        hopperUp.set(true);
        
//        networkTable.putBoolean("fromRobot/HopperUp", true);
//        networkTable.putBoolean("fromRobot/HopperDown", false);
    }
    
    public void hopperDown() 
    {
        hopperUp.set(false);
                
//        networkTable.putBoolean("fromRobot/HopperUp", false);
//        networkTable.putBoolean("fromRobot/HopperDown", true);
    }
    
    public void queue()
    {
        hopperUp();
        hopperDown();
    }
    
    public void colorSensor() 
    {
//        if (redColorSensor.getRed() > blueColorSensor.getBlue()) {
//            redColorSensorState = true;
//            blueColorSensorState = false;
//        } else if (redColorSensor.getRed() < blueColorSensor.getBlue()) {
//            redColorSensorState = false;
//            blueColorSensorState = true;
//        } else if (redColorSensor.getRed() == blueColorSensor.getBlue()) {
//            redColorSensorState = true;
//            blueColorSensorState = true;
//        } else {
//            redColorSensorState = false;
//            blueColorSensorState = false;
//        }
    }
}
