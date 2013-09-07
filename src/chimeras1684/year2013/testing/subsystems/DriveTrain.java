/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author Kelly
 */
public class DriveTrain extends SubsystemBase
{
    NetworkTable networkTable = null;
    Talon rightDrive;
    Talon leftDrive;
    
    public Encoder leftEncoder;
    public Encoder rightEncoder;
    
    Gyro theGyro;
    
    ADXL345_I2C accel;
    
    Pneumatics Pneumatics;
    
    Timer timer;
    
    int driveTrainRequest;
    
    boolean state = true;
    double move = 0.0, rotate = 0.0;
    public double average;
    public double xAngle;
    public double yAngle;
    double gyroAngle;
    public static volatile DriveTrain instance;
    static 
    {
        instance = new DriveTrain();
        instance.leftDrive = new Talon(1);
        instance.rightDrive = new Talon(2);
        
//        leftEncoder = new Encoder(portMap.leftDriveEncoderA, portMap.leftDriveEncoderB);
//        rightEncoder = new Encoder(portMap.rightDriveEncoderB, portMap.rightDriveEncoderA);
        instance.leftEncoder = new Encoder(2,3);
        instance.rightEncoder = new Encoder(4,5);
        
//        theGyro = new Gyro(RobotMap.gyro);
        
        instance.accel = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k2G);
        
        instance.timer = new Timer();
        instance.timer.start();
        
        instance.Pneumatics = subMap.pnuematics;
        
        instance.leftEncoder.setDistancePerPulse(.018); 
        instance.rightEncoder.setDistancePerPulse(.018); 

        instance.leftEncoder.start();
        instance.rightEncoder.start();
        
        instance.leftEncoder.reset();
        instance.rightEncoder.reset();
        
    }

    public static DriveTrain getInstance() 
    {
        instance = instance == null ? new DriveTrain() : instance;
        return instance;
    }
    public void requestDriveMotors(){
        driveTrainRequest += 1;
    }
    public void releaseDriveMotors(){
        driveTrainRequest += 1;
    }
    public boolean isDriveRequested(){
        return driveTrainRequest != 0;
    }
    public void tankDrive(double left, double right){
        leftDrive.set(Math.min(1, Math.max(-1, left)));
        rightDrive.set(Math.min(1, Math.max(-1, right)));
    }
    public void arcadeDrive(double moveValue, double rotateValue, boolean hello)
    {
         // local variables to hold the computed PWM values for the motors
        //RobotMap.drive.arcadeDrive(moveValue, rotateValue, squaredInputs);
        
        double leftMotorSpeed;
        double rightMotorSpeed;
        double movevalue;
        double rotatevalue;

        if (moveValue > 1.0) moveValue = 1.0;
        if (moveValue < -1.0) moveValue = -1.0;
        if (rotateValue > 1.0) rotateValue = 1.0;
        if (rotateValue < -1.0) rotateValue = -1.0;

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        
//        System.out.println("leftMotorSpeed" + leftMotorSpeed);
//        System.out.println("rightMotorSpeed" + rightMotorSpeed);
 
        leftDrive.set(leftMotorSpeed);
        rightDrive.set(-rightMotorSpeed);
        
//        encoderDrive(leftMotorSpeed, rightMotorSpeed);
    }
    
    public void encoderDrive(double leftSpeed, double rightSpeed)
    {
        double leftTarget;
        double rightTarget;
        double leftError;
        double rightError;
        
        double leftDistance = leftEncoder.getDistance();
        double rightDistance = rightEncoder.getDistance();
//        double leftRate = leftEncoder.getRate();
//        double rightRate = rightEncoder.getRate();

        if((leftSpeed >= -0.15 && leftSpeed <= 0.15) && (rightSpeed >= -0.15 && rightSpeed <= 0.15)){
            if(state){
                leftEncoder.reset();
                rightEncoder.reset();
                state = false;
            }
            
            leftTarget = 0.0;
            rightTarget = 0.0;

//            leftError = leftTarget + leftRate;
//            rightError = rightTarget + rightRate;
            
            leftError = leftTarget + leftDistance;
            rightError = rightTarget + rightDistance;
            
            if(leftError >= -.4 && leftError <= .4)leftError = 0.0;
            if(rightError >= -.4 && rightError <= .4)rightError = 0.0;
        }else{
            state = true;
            
            leftTarget = leftDistance + leftSpeed;
            rightTarget = rightDistance + rightSpeed;
        
            leftError = leftTarget - leftDistance;
            rightError = rightTarget - rightDistance;
        }
        
//        System.out.println("left rate  " + leftRate + "  " + "right rate  " + rightRate);
//        System.out.println("left error  " + leftError + "  " + "right error  " + rightError);
//        System.out.println("left speed  " + leftSpeed + "  " + "right speed  " + rightSpeed);
//        System.out.println("state  " + state);

//        RobotMap.drive.tankDrive(leftError, rightError, true);
        
        System.out.println("leftError" + leftError);
        System.out.println("rightError" + rightError);
        
        leftDrive.set(leftError);
        rightDrive.set(rightError);
    }
    
    public void curve(double angle)
    {
        double leftArc;
        double rightArc;
        double leftWheelOffset = 36;
        double rightWheelOffset = 7;
        double leftChordLength;
        double rightChordLength;
        double leftRadius;
        double rightRadius;
        double totalAngle;
        double leftDistanceError;
        double rightDistanceError;
        double leftCurrentPosition = leftEncoder.getDistance();
        double rightCurrentPosition = rightEncoder.getDistance();
                
        leftChordLength = 60 + leftWheelOffset; //96
        rightChordLength = 60 + rightWheelOffset; //67
        
//        leftRadius = (((leftChordLength*leftChordLength)/(8*leftWheelOffset)) + (leftWheelOffset/2)); //50
//        rightRadius = (((rightChordLength*rightChordLength)/(8*rightWheelOffset)) + (rightWheelOffset/2)); //83

        totalAngle = 180 - (90 - angle);

        leftRadius = Math.sqrt(   MathUtils.pow(  ((leftChordLength/2)*(Math.sin(  90-(totalAngle/2)  ))), 2  ) + MathUtils.pow(  (leftChordLength/2), 2  )  );//49
        rightRadius = Math.sqrt(   MathUtils.pow(  ((rightChordLength/2)*(Math.sin(  90-(totalAngle/2)  ))), 2  ) + MathUtils.pow(  (rightChordLength/2), 2  )  );//34
                
        leftArc = (((((2*leftRadius*Math.PI)*(totalAngle))/360)));//131.6
        rightArc = (((((2*rightRadius*Math.PI)*(totalAngle))/360)));//91.8
//        leftArc = 230;
//        rightArc = 80;
        
        leftDistanceError = leftArc - leftCurrentPosition;
        rightDistanceError = rightArc - rightCurrentPosition;
        
        System.out.println(" lefterror   " + leftDistanceError);
        System.out.println(" righterror   " + rightDistanceError);

        System.out.println(" leftArc   " + leftArc);
        System.out.println(" rightArc   " + rightArc);
        System.out.println(" left encoder value   " + leftCurrentPosition);
        System.out.println(" right encoder value   " + rightCurrentPosition);
                
        if (leftDistanceError > (leftArc/4)){
            leftDrive.set(0.75);
        }
        if (leftDistanceError > (leftArc/2)){
            leftDrive.set(1.0);
        }
        if (leftDistanceError > (3*(leftArc/4))){
            leftDrive.set(0.75);
        }
        if (leftDistanceError <= 0.0){
            leftDrive.set(0.0);
        }
        
        if (rightDistanceError > (rightArc/4)){
            rightDrive.set(-0.1);
        }
        if (rightDistanceError > (rightArc/2)){
            rightDrive.set(-0.2);
        }
        if (rightDistanceError > (3*(rightArc/4))){
            rightDrive.set(-0.1);
        }
        if (rightDistanceError <= 0.0){
            rightDrive.set(0.0);
        }
    }
    
    public void gyroDrive(double angle)
    {
        double angleError;
        double currentAngle = theGyro.getAngle();
        
        angleError = angle - currentAngle;
        
        leftDrive.set(angleError*0.01);
        rightDrive.set(-angleError*0.01);
    }
    
    public void shiftLow(){
        System.out.println("shiftlow");
            Pneumatics.driveLow.set(false);
//            Pneumatics.driveLow.set(true);
    }
    
    public void shiftHigh(){
        System.out.println("shifthigh");
            Pneumatics.driveLow.set(true);
//            Pneumatics.driveLow.set(false);
    }
    
    public void angles()
    {
        xAngle = accel.getAcceleration(ADXL345_I2C.Axes.kX);
        yAngle = accel.getAcceleration(ADXL345_I2C.Axes.kY);
        gyroAngle = theGyro.getAngle();
    }
}
