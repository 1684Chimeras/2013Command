/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author JAKE FROM STAE FARM
 */
public class Arm extends SubsystemBase
{
    Solenoid armUp;
    Solenoid armDown;
    Solenoid armLoadOut;
    NetworkTable networkTable = null;
    Timer timer;
    Jaguar roller;
    public static Arm instance;
    
    static{
        instance = new Arm();
        instance.armUp = subMap.pnuematics.armUp;
        instance.armDown = subMap.pnuematics.armDown;
        instance.armLoadOut = subMap.pnuematics.armLoadOut;
        instance.timer = new Timer();
        instance.timer.start();
        
        instance.roller = new Jaguar(portMap.Roller);
        
//        this.networkTable = networkTable;
    }
    public static Arm getInstance(){
        return instance;
    }
    private Arm(){}
    
    public void armUp()
    {
        armUp.set(true);
        armDown.set(false);
    
//        System.out.println("ARM UP!!!!!!!!!!!!!!!");
        
        if (networkTable != null){
            if (armLoadOut.get() == true){
                networkTable.putBoolean("fromRobot/ArmLoadPosition", true);
                networkTable.putBoolean("fromRobot/ArmDown", false);
                networkTable.putBoolean("fromRobot/ArmUp", false);
            }else{
                networkTable.putBoolean("fromRobot/ArmUp", true);
                networkTable.putBoolean("fromRobot/ArmDown", false);
            }
        }    
//                System.out.println("Shooter timer Value" + timer.get());

    }
    
    public void armDown()
    {
        armUp.set(false);
        armDown.set(true);
        armLoadOut.set(false);
        
//        System.out.println("ARM DOWN!!!!!!!!!!!!!!!");
        
        if (networkTable != null){
            networkTable.putBoolean("fromRobot/ArmUp", false);
            networkTable.putBoolean("fromRobot/ArmDown", true);
            networkTable.putBoolean("fromRobot/ArmLoadPosition", false);
        }
    }
    
    public void armLoadPosition()
    {
        armLoadOut.set(true);
        armUp.set(true);
        armDown.set(false);
        
//        System.out.println("ARM MIDDLE!!!!!!!!!!!!!!!");
        
        if (networkTable != null){
            networkTable.putBoolean("fromRobot/ArmLoadPistons", true);
            networkTable.putBoolean("fromRobot/ArmLoadPosition", true);
            networkTable.putBoolean("fromRobot/ArmDown", false);
            networkTable.putBoolean("fromRobot/ArmUp", false); 
        }
    }
    
    public void armLoadOut()
    {
        armLoadOut.set(true);
        
        if (networkTable != null){
            networkTable.putBoolean("fromRobot/ArmLoadPistons", true);
        }
    }
    
    public void armLoadIn()
    {
        armLoadOut.set(false);
       
        if (networkTable != null){
            networkTable.putBoolean("fromRobot/ArmLoadPistons", false);
        }
    }
    
    public void rollerOut(){
        roller.set(0.85);
    }
    public void rollerOff(){
        roller.set(0.0);
    }
    public void rollerIn(){
        roller.set(-0.85);
    }
}