/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.general;

import chimeras1684.year2013.testing.root.CommandBase;
import chimeras1684.year2013.testing.maps.StringMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Arhowk
 */
public class DriveTrainCommands {
    
    
    

    public static class ShiftHigh extends CommandBase {
        protected void execute() {
            subMap.driveTrain.shiftHigh();
        }
    }
    public static class ShiftLow extends CommandBase {

        protected void execute() {
            subMap.driveTrain.shiftLow();
        }
    }

    public static class XboxDrive extends CommandBase {

        private boolean leftJoystick;
        public XboxDrive(boolean leftJoystick, double timeout) {
            this.leftJoystick = leftJoystick;
            setTimeout((timeout == 0) ? 240 : timeout);
        }
        public void setJoysick(boolean isLeft){
            leftJoystick = isLeft;
        }
        protected void execute() {
            if(!subMap.driveTrain.isDriveRequested()){
               if(leftJoystick){
                   subMap.driveTrain.arcadeDrive(oi.controlDriver.getLeftStickMove(), oi.controlDriver.getLeftStickRotate(), true);
               }else{
                   subMap.driveTrain.arcadeDrive(oi.controlDriver.getRightStickMove(), -oi.controlDriver.getRightStickRotate(), true);
               }
            }
        }
        protected boolean isFinished() {
            return isTimedOut();
        }
    }
    public static class TimedDonut extends CommandBase {
        double leftSpeed;
        double rightSpeed;
        public TimedDonut(double timeout, double leftSpeed, double rightSpeed) {
            setTimeout(timeout);
            this.leftSpeed = leftSpeed;
            this.rightSpeed = rightSpeed;
        }
        protected void execute() { 
            subMap.driveTrain.tankDrive(leftSpeed, rightSpeed);
        }
    }
    public static class Spin extends CommandBase {
        double degrees;
        boolean clockwise;
        double error;

        public Spin(double degrees, double error, boolean clockwise) {
            this.degrees = degrees * constMap.ENCODER_TO_DEGREES;
            this.error = error * constMap.ENCODER_TO_DEGREES;
            this.clockwise = clockwise;
        }
        public void initialize(){
            subMap.driveTrain.leftEncoder.reset();
            subMap.driveTrain.rightEncoder.reset();
        }
        public boolean isFinished(){
            if(clockwise){
                if(subMap.driveTrain.rightEncoder.get() > (degrees - error)){
                    return true;
                }else{
                    subMap.driveTrain.tankDrive(0.9, -0.9);
                    return false;
                }
            }else{
                if(subMap.driveTrain.leftEncoder.get() > (degrees - error)){
                    return true;
                }else{
                    subMap.driveTrain.tankDrive(-0.9, 0.9);
                    return false;
                }
            }
        }
        public void setSpin(double d){
            this.degrees = d;
        }
        public void setError(double d){
            this.error = d;
        }
    }
    public static class DriveAuto extends CommandBase {
    
        boolean driveForward;
        int driveDistance = 2000;
        int currentEncoder;
        double distanceFactor;
        double accelStartConstant;
        double minimumDecel;

        double accelEnd;
        double decelStart;
        double coastPeriod;


        public DriveAuto(double timeout, double distanceFactor, double accelStartConstant, boolean forward, double minimumDecel) {
            driveForward = forward;
            setTimeout(timeout);
            this.distanceFactor = distanceFactor;
            this.accelStartConstant = accelStartConstant;
            this.minimumDecel = minimumDecel;
        }

        // Called just before this Command runs the first time
        protected void initialize() {
            driveDistance = (int)(SmartDashboard.getNumber(StringMap.encoderDistance, 109) * distanceFactor);
            subMap.driveTrain.leftEncoder.reset();
            subMap.driveTrain.rightEncoder.reset();
            accelEnd = SmartDashboard.getNumber(StringMap.accelEnd, 7.27) * distanceFactor;
            decelStart = SmartDashboard.getNumber(StringMap.decelStart, 78.1) * distanceFactor ;
            coastPeriod = SmartDashboard.getNumber(StringMap.coastPeriod, 96.3) + (driveDistance * (distanceFactor - 1));
            subMap.driveTrain.requestDriveMotors();
        }

        // Called repeatedly when this Command is scheduled to run
        protected boolean isFinished() {
           double get = (subMap.driveTrain.leftEncoder.getDistance() + subMap.driveTrain.rightEncoder.getDistance()) / 2;
            if((get < 0 && driveForward) || (get > 0 && !driveForward)){
                get = 0;
                subMap.driveTrain.leftEncoder.reset();
                subMap.driveTrain.rightEncoder.reset();
            }
            if(!driveForward){
                get = Math.abs(get);
            }
            System.out.println(get);
           double speed = 0;
           int negative = 1;
          // if(driveForward){
           if(get < accelEnd){
               speed = (get / accelEnd) + accelStartConstant;
                negative = (speed < 0) ? -1 : 1;
               speed = Math.sqrt(Math.abs(speed));
               speed = speed * negative;
               speed = (speed > 0.8) ? 0.8 : speed;
           }else if(get > coastPeriod && get < driveDistance){
               speed = 0;
           }else if(get > decelStart){
               speed = (1 - (get / (driveDistance - decelStart)));
               negative = (speed < 0) ? -1 : 1;
               speed = Math.abs(speed);
               speed = speed * negative;
               if(speed < minimumDecel && speed < 0.1 || Math.abs(get) > driveDistance){
                   return true;
               }else if(speed < minimumDecel && speed > 0.15){
                  speed = minimumDecel;
               }else{
                 speed = (speed < -0.8) ? speed = -0.8 : speed;
               }
           }else{
               speed = 0.8;
           }
           if(!driveForward){
               speed = -speed;
           }
           subMap.driveTrain.arcadeDrive(-speed,0, false);
           return false;
        }

        // Make this return true when this Command no longer needs to run execute()
        protected void execute() {
        }

        // Called once after isFinished returns true
        protected void end() {
            subMap.driveTrain.releaseDriveMotors();
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected void interrupted() {
            subMap.driveTrain.releaseDriveMotors();
        }
    }
}
