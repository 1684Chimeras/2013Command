/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author Kelly
 */
public class Pneumatics extends SubsystemBase
{
    NetworkTable networkTable = null;
    Relay compressor;
    DigitalInput pressureSwitch;
    Solenoid driveLow;
    Solenoid hopperUp;
    Solenoid fire;
    
    Solenoid armUp;
    Solenoid armDown;
    Solenoid armLoadOut;
    private static volatile Pneumatics instance;
    
    static
    {
        instance = new Pneumatics();
        instance.compressor = new Relay(portMap.compressor);
        instance.pressureSwitch = new DigitalInput(portMap.pressureSwitch);
        instance.networkTable = NetworkTable.getTable("hi");
        instance.driveLow= new Solenoid(1,1);
        instance.hopperUp = new Solenoid(1,2);
        instance.fire= new Solenoid(1,3);
        instance.armDown= new Solenoid(1,4);
        instance.armUp= new Solenoid(1,5);
        instance.armLoadOut= new Solenoid(1,6);       
    }
    private Pneumatics(){}
    public static Pneumatics getInstance(){
        return instance;
    }
    public void compress()
    {
        if(pressureSwitch.get() == true){
                compressor.set(Relay.Value.kOff);
                
                if (networkTable != null){
                    networkTable.putBoolean("fromRobot/compressorFullyCompressed", true);
                }
        }else{
                compressor.set(Relay.Value.kForward);
                
                if (networkTable != null){
                    networkTable.putBoolean("fromRobot/compressorFullyCompressed", false);
                }
        }
    }
}
