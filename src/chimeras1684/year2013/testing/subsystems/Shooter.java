/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Timer;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Kelly
 */
public class Shooter extends SubsystemBase
{
    NetworkTable networkTable = null;
    Talon shooterMotor;
    Talon tiltMotor;
    AnalogChannel tiltEncoder;
    Counter speedEncoder;
    DigitalInput deckTiltUp;
    DigitalInput deckTiltDown;
    DriveTrain driveTrain;
    Solenoid fire;
    Solenoid hopper;
    Timer timer;
    
    double baseTilt = 0;
    double deckTilt;
    double tiltAngle = 0;
    double kp_Shooter = 0.1;
    double kp_Tilt = 0.08;
    int currentTilt = 500;
    int maxValue = 511;
    int minValue = 595;
    public int tiltSetPoint = 537;
    public double tiltError = 0;
    public double tiltCorrection = 0;

    public double wheelPeriod = 0.0;
    public double wheelSetPoint = 0.0;
    public double actualRPM = 0.0;
    public double wheelError = 0.0;
    public double wheelCorrection = 0.0;
    
    public int rapidFireState = 0;
    public int rapidFireCounter = 0;
    
    int executed = 0;
    public static volatile Shooter instance;
    
    public static Shooter getInstance(){
        return instance;
    }
//                                  8            7
    static 
    {
        instance = new Shooter();
        instance.shooterMotor = new Talon(portMap.shooterWheelMotor);
        instance.tiltMotor = new Talon(portMap.tiltMotor);
        instance.tiltEncoder = new AnalogChannel(portMap.tiltEncoder);
        instance.speedEncoder = new Counter(portMap.shooterSpeedEncoder);
        instance.deckTiltUp = new DigitalInput(portMap.tiltLimitTop);
        instance.deckTiltDown = new DigitalInput(portMap.tiltLimitBottom);
        instance.fire = subMap.pnuematics.fire;
        instance.hopper = subMap.pnuematics.hopperUp;
        instance.driveTrain = subMap.driveTrain;
//        networkTable = NT;
        
        instance.timer = new Timer();
        
        instance.speedEncoder.start();
        instance.timer.start();
        instance.speedEncoder.reset();
        
        instance.baseTilt = MathUtils.acos(instance.driveTrain.accel.getAcceleration(ADXL345_I2C.Axes.kY));
    }
    
    // used in targeting only
    public double calculateDeckTilt()
    {
        deckTilt = tiltEncoder.getAverageValue();
        
        tiltAngle = deckTilt/* - baseTilt*/;
        
//        networkTable.putString("fromRobot/TiltAngle", "" + tiltAngle);
        
        return tiltAngle;
    }
    
    public void shooterOn(double speed)
    {
        shooterMotor.set(-speed);
    }
    
    public void shooterOff()
    {
        setShooterSpeed(0);
        shooterMotor.set(0.0);
    }
    
//    public void setShooterSpeed(double RPM)
    public void wheelUpdate()
    {        
        if (speedEncoder.getPeriod() > 0.012){
            wheelPeriod = speedEncoder.getPeriod();
        }
        
        actualRPM = (1/wheelPeriod)*60;
        wheelError = wheelSetPoint - actualRPM;
        wheelCorrection = kp_Shooter * wheelError;
        
        if (wheelCorrection > 1.0){
            wheelCorrection = 1.0;
        }else{
            if (wheelCorrection < 0.0){
                if (wheelError < -500){
                    wheelCorrection = 0.0;
                }else{
                    if (wheelSetPoint != 0.0){
                        wheelCorrection = 0.5;
                    }
                }
            }
        }
        
        shooterMotor.set(wheelCorrection);
    }
    
    // this is the one that works !!!!! Should be in a 10ms interrupt but teleopPeriodic is OK for now
    public void tiltUpdate()
    {        
        currentTilt = tiltEncoder.getAverageValue();
        
        tiltError = currentTilt - tiltSetPoint;
        tiltCorrection = kp_Tilt * tiltError;
        
        if (tiltCorrection < 0.0){
            if (currentTilt <= minValue){
                tiltMotor.set(-tiltCorrection);
            }else{
                tiltMotor.set(0.0);
            }
        }else{
            if (currentTilt >= maxValue){
                tiltMotor.set(-tiltCorrection);
            }else{
                tiltMotor.set(0.0);
            }
        }
    }
    
    public void rapidFire()
    {
        switch (rapidFireState){
            case 0:
                hopper.set(true);
                fire.set(true);
                rapidFireState = 1;
                rapidFireCounter = 0;
                break;
            case 1:
                if (rapidFireCounter >= 10){
                    if ((actualRPM > 1000) && (Math.abs(wheelError) < 100)){
                        hopper.set(false);
                        fire.set(false);
                        System.out.println("Fire at   " + rapidFireCounter + "Speed:  " + actualRPM);
                        rapidFireCounter = 0;
                        rapidFireState = 2;
                    }
                }
                break;
            case 2: 
                if (rapidFireCounter >= 5){
                    executed++;
                    rapidFireState = 0;
                }
                break;
            default:
                break;
        }
        rapidFireCounter++;
    }
    
    public void fire()
    {
        fire.set(true);
//        networkTable.putBoolean("fromRobot/FirePiston", true);
    }
    
    public void reset()
    {
        fire.set(false);
//        networkTable.putBoolean("fromRobot/FirePiston", false);
    }
    
    public void setShooterSpeed(int RPM) {
        wheelSetPoint = (int) RPM;
    }
    
    public void setTiltSetpoint(int setpoint){
        tiltSetPoint = setpoint;
    }
    
    // this will increase or decrease the setpoint by operator override
    public void manualTilt (double tiltValue) {
//        if (tiltValue > 0.5) {
//            tiltSetPoint--; 
//        } else {
//            tiltSetPoint++;             
//        }
//        if (tiltSetPoint < maxValue){
//            tiltSetPoint = maxValue;
//        }
//        if (tiltSetPoint > minValue){
//            tiltSetPoint = minValue;
//        }
    }
}