/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.general;

import chimeras1684.year2013.testing.root.CommandBase;

/**
 *
 * @author Arhowk
 */
public class ShooterCommands {
    public static class SetShooterLockPoint extends CommandBase{
        protected boolean isFinished(){
            subMap.shooter.tiltSetPoint = 544;
            subMap.shooter.wheelSetPoint = 3900;
            return true;
        }
    }
    public static class FireDisc extends CommandBase{
        protected void execute(){
            subMap.shooter.fire();
            subMap.discHandler.hopperUp();
        }
    }
    public static class ReturnToLockPoint extends CommandBase{
        protected boolean isFinished(){
            if(subMap.shooter.calculateDeckTilt() > constMap.TILT_ACCEPTED_ERROR){
                subMap.shooter.manualTilt(-1);
                return false;
            }else if(subMap.shooter.calculateDeckTilt() < -constMap.TILT_ACCEPTED_ERROR){
                subMap.shooter.manualTilt(1);
                return false;
            }else{
                return true;
            }
        }
    }
    public static class RapidFire extends CommandBase{
        protected void execute(){
            subMap.shooter.rapidFire();
        }
    }
    public static class FirePiston extends CommandBase {
        protected void execute() {
            subMap.shooter.fire();
        }
    }
    public static class teleopShooterPeriodic extends CommandBase{
        public void execute(){
            subMap.shooter.manualTilt(oi.controlDriver.getRightStickMove());
            if (oi.controlOperator.rightTrigger() == true){
                subMap.shooter.fire();
                subMap.discHandler.hopperUp();
            }else{
                //QUEUEING
                if (oi.controlOperator.bumperRight()== true){
                    subMap.shooter.fire();
                }else{
                    subMap.shooter.reset();
                }

                if (oi.controlOperator.leftTrigger() == true){
                    subMap.discHandler.hopperUp();
                }else{
                    subMap.discHandler.hopperDown();
                }
            }    
            subMap.shooter.tiltUpdate();
            subMap.shooter.wheelUpdate();
        }
    }
    public static class JoystickDeckTilt extends CommandBase {
        protected void execute() {
        }
    }
    public static class Reset extends CommandBase {
        protected void execute() {
            subMap.shooter.reset();
        }
    }
    public static class SetDeckTilt extends CommandBase {
        double degrees;
        public SetDeckTilt(double degrees, double timeout) {
            setTimeout(timeout);
            this.degrees = degrees;
        }
        protected void execute() {
            subMap.shooter.manualTilt(degrees);
        }
    }
    public static class ShooterOff extends CommandBase {
        protected void initialize() {
            subMap.shooter.setShooterSpeed(0);
        }
    }
    public static class ShooterFullspeed extends CommandBase {
        protected void initialize() {
            subMap.shooter.setShooterSpeed(4000);
        }
        protected void end(){
            subMap.shooter.setShooterSpeed(0);
        }
    }
    public static class ShooterOn extends CommandBase {
        protected void initialize() {
            subMap.shooter.setShooterSpeed(3500);
        }
        protected void end(){
            System.out.println("SET SHOOTER SPEED 0");
            subMap.shooter.shooterOff();
        }
        protected void interrupted(){
            System.out.println("SET SHOOTER SPEED 0");
            subMap.shooter.shooterOff();
        }
    }



  
}
