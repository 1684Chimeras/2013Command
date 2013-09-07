/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kelly
 */
public class Climber extends SubsystemBase
{
    NetworkTable networkTable = null;        
    Talon flipper;
    Jaguar leftHook;
    Jaguar rightHook;
    
    Relay clamp;
    
    DigitalInput rightExtendLimit;
    DigitalInput rightRetractLimit;
    DigitalInput leftExtendLimit;   
    DigitalInput leftRetractLimit;   
    
    GearTooth leftHookEncoder;
    GearTooth rightHookEncoder;
    Timer timer;
    double teethExtended;
    
    boolean runningLE;
    boolean runningLR;
    boolean runningRE;
    boolean runningRR;
    
    int count;
    private static volatile Climber instance;
    static
    {
        instance = new Climber();
        instance.flipper = new Talon(portMap.flipperMotor);
        instance.leftHook = new Jaguar(portMap.leftHookMotor);
        instance.rightHook = new Jaguar(portMap.rightHookMotor);
        instance.clamp = new Relay(portMap.hookClamp, Relay.Direction.kBoth);
        instance.leftHookEncoder = new GearTooth(portMap.leftHookEncoderA);
        instance.rightHookEncoder = new GearTooth(portMap.rightHookEncoderA);
        instance.rightExtendLimit = new DigitalInput(portMap.rightHookLimitExtend);
        instance.rightRetractLimit = new DigitalInput(portMap.rightHookLimitRetract);
        instance.leftExtendLimit = new DigitalInput(portMap.leftHookLimitExtend);
        instance.leftRetractLimit = new DigitalInput(portMap.leftHookLimitRetract);
        instance.networkTable = NetworkTable.getTable("hi");

//        timer = new Timer();
//        timer.start();
        instance.rightHookEncoder.start();
        instance.leftHookEncoder.start();        
    }
    private Climber(){}
    
    public static Climber getInstance(){
        return instance;
    }
    public void clampForward(){
        clamp.set(Relay.Value.kForward);
    }
    public void clampOff(){
        clamp.set(Relay.Value.kOff);
    }
    public void unlockHooks()
    {
        leftHookEncoder.reset();
        rightHookEncoder.reset();
        
//        //RIGHT CLAMP
//        clamp.set(Relay.Value.kForward);
//        //LEFT CLAMP
//        clamp.set(Relay.Value.kReverse);
        clamp.set(Relay.Value.kOn);
        
//        if (networkTable != null){
//            networkTable.putBoolean("fromRobot/RightHookLocked", false);
//            networkTable.putBoolean("fromRobot/LeftHookLocked", false);
//        }
    }
    
    public void unlockRightHook()
    {
//        rightHookEncoder.reset();
        
//        if (rightHook.get() < 0){
            rightHook.set(0.2);
            //RIGHT CLAMP
            clamp.set(Relay.Value.kForward);
//            if (networkTable != null){
//                networkTable.putBoolean("fromRobot/RightHookLocked", false);
//            }
//        }
    }
    
    public void unlockLeftHook()
    {
//        leftHookEncoder.reset();
        
//        if (leftHook.get() > 0){
            leftHook.set(-0.2);
            //LEFT CLAMP
            clamp.set(Relay.Value.kReverse);
            
//            if (networkTable != null){
//                networkTable.putBoolean("fromRobot/LeftHookLocked", false);
//            }
//        }
    }
    
    public void lockHooks()
    {
        leftHookEncoder.reset();
        rightHookEncoder.reset();
        
        clamp.set(Relay.Value.kOff);
                
//        if (networkTable != null){
//            networkTable.putBoolean("fromRobot/RightHookLocked", true);
//            networkTable.putBoolean("fromRobot/LeftHookLocked", true);
//        }
    }
        
    public boolean lockRightHook()
    {
        rightHookEncoder.reset();
                
        if (lockLeftHook() == false){
            clamp.set(Relay.Value.kReverse);
//            if (networkTable != null){
//                networkTable.putBoolean("fromRobot/RightHookLocked", true);
//                networkTable.putBoolean("fromRobot/LeftHookLocked", false);
//            }
            return false;
        }else{
            clamp.set(Relay.Value.kOff);
//            if (networkTable != null){
//                networkTable.putBoolean("fromRobot/RightHookLocked", true);
//                networkTable.putBoolean("fromRobot/LeftHookLocked", true);
//            }
            return true;
        }
    }
    
    public boolean lockLeftHook()
    {
        rightHookEncoder.reset();
        
        if (lockRightHook() == false){
            clamp.set(Relay.Value.kForward);
//            if (networkTable != null){
//                networkTable.putBoolean("fromRobot/RightHookLocked", false);
//                networkTable.putBoolean("fromRobot/LeftHookLocked", true);
//            }
            return false;
        }else{
            clamp.set(Relay.Value.kOff);
//            if (networkTable != null){
//                networkTable.putBoolean("fromRobot/RightHookLocked", true);
//                networkTable.putBoolean("fromRobot/LeftHookLocked", true);
//            }
            return true;
        }
    }
    public void flipper(double speed)
    {
        if ((speed < 0.2) && (speed > -0.2)){
            speed = 0;
        }
        
//        if ((speed > -0.2) && (speed < 0)){
//            speed = 0;
//        }
        
        flipper.set(speed);
    }
    
    public void rightHook(double speed)
    {
        teethExtended = ((rightHookEncoder.get()));
        unlockRightHook();
        
        
        rightHook.set(speed);
        if ((speed < 0.20) || (speed > -0.20)){
            lockRightHook();
            rightHook.set(0.0);
        }
    }
    
    public void leftHook(double speed)
    {
        teethExtended = ((leftHookEncoder.get()));
        unlockLeftHook();
        
        leftHook.set(-speed);
        if ((speed < 0.20) || (speed > -0.20)){
            lockLeftHook();
            leftHook.set(0.0);
        }
    }
    
    public boolean rightHookExtend()
    {
        teethExtended = ((rightHookEncoder.get()));
                        
        if (teethExtended < 156){
            if (teethExtended == 0){
                unlockHooks();
                return false;
            }
            
            rightHook.set(1.0);
            runningLR = true;
            return false;
        }else{
            lockHooks();
            rightHook.set(0.0);
            runningLR = false;
            return true;
        }
    }
    
    public boolean rightHookRetract()
    {
        teethExtended = ((rightHookEncoder.get()));
        
        if (teethExtended < 156){
            if (teethExtended == 0){
                unlockHooks();
                return false;
            }
            
            rightHook.set(-1.0);
            runningLR = true;
            return false;
        }else{
            lockHooks();
            rightHook.set(0.0);
            runningLR = false;
            return true;
        }
    }
    
    public boolean leftHookExtend()
    {        
        teethExtended = ((leftHookEncoder.get()));
        
        if (teethExtended < 156){
            if (teethExtended == 0){
                unlockHooks();
                return false;
            }
            
            leftHook.set(-1.0);
            runningLR = true;
            return false;
        }else{
            lockHooks();
            leftHook.set(0.0);
            runningLR = false;
            return true;
        }
    }
    
    public boolean leftHookRetract()
    {
        teethExtended = ((leftHookEncoder.get()));
                
        if (teethExtended < 156){
            if (teethExtended == 0){
                unlockHooks();
                return false;
            }
            
            leftHook.set(1.0);
            runningLR = true;
            return false;
        }else{
            lockHooks();
            leftHook.set(0.0);
            runningLR = false;
            return true;
        }
    }
    
    public void climb()
    {
        count++;
        
        if ((count > 0) && (leftHookExtend() == false)){
            leftHookExtend();
        }
        
        if ((count > 0) && (leftHookExtend() == true)){
            leftHookRetract();
            rightHookExtend();
        }
        
        if ((count > 0) && ((leftHookRetract() == true) && (rightHookExtend() == true))){
            leftHookExtend();
            rightHookRetract();
        }
        
        if ((count > 0) && ((leftHookExtend() == true) && (rightHookRetract() == true))){
            leftHookRetract();
            rightHookExtend();
        }
        
        if ((count > 0) && ((leftHookRetract() == true) && (rightHookExtend() == true))){
            rightHookRetract();
            lockHooks();
            count = -50000;
            leftHook.set(0.0);
            rightHook.set(0.0);
        }
        
        if (count < 0){
            lockHooks();
            leftHook.set(0.0);
            rightHook.set(0.0);
        }
    }
}