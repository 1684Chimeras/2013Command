/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.general;

import chimeras1684.year2013.testing.root.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Arhowk
 */
/*
 container ClimbingCommands 
 * group LeftExtendRightRetract
 *      addParallel(
 *      addSequential(
 */
public class ClimbingCommands{
    
    public static class LeftExtendRightRetract extends CommandGroup {
        public LeftExtendRightRetract() {
            addParallel (new LeftHookExtend());
            addSequential(new RightHookRetract());
        }
    }
    public static class ClimbingPeriodicCheck extends CommandBase {
        // Called repeatedly when this Command is scheduled to run
        protected boolean isFinished() {
            oi.swapModes((oi.currentMode == 1) ? 0 : 1);
            return true;
        }
    }

    public static class AutoClimb extends CommandBase{
        protected void execute(){
            subMap.climber.climb();
            subMap.driveTrain.arcadeDrive(0.2, 0,false);
        }
    }
    public static class FlipperJoystick extends CommandBase{
        protected void execute(){
            subMap.climber.flipper(oi.controlDriver.getRightStickMove());
        }
    }
    public static class PeriodicClimbing extends CommandBase{
        protected void execute(){
            subMap.climber.leftHook(oi.controlOperator.getLeftStickMove());
            subMap.climber.rightHook(oi.controlOperator.getRightStickMove());
            if (Math.abs(oi.controlOperator.getLeftStickMove()) < 0.1 && (oi.controlOperator.a() == false)){
                subMap.climber.lockLeftHook();
            }
            
            if (Math.abs(oi.controlOperator.getLeftStickMove()) < 0.1 && (oi.controlOperator.a() == false)){
                subMap.climber.lockRightHook();
            }
        }
    }
    public static class LeftHookExtend extends CommandBase {
        protected boolean isFinished() {
            subMap.climber.leftHookExtend();
                return true;
        }
    }
    public static class LeftHookRetract extends CommandBase {
        protected void execute() {
            subMap.climber.leftHookRetract();
        }
    }
    public static class LeftHookLock extends CommandBase {
        protected void execute() {
            System.out.println("lcck");
            subMap.climber.lockLeftHook();
        }
    }
    public static class LeftHookUnlock extends CommandBase {
        protected void execute() {
            subMap.climber.lockRightHook();
        }
    }
    public static class RightExtendLeftRetract extends CommandGroup {

        public RightExtendLeftRetract() {
            addParallel(new LeftHookRetract());
            addSequential(new RightHookExtend());
        }
    }
    public static class RightHookExtend extends CommandBase {
        protected void execute() {
            subMap.climber.rightHookExtend();
        }
    }
    public static class RightHookLock extends CommandBase {
        protected void execute() {
            System.out.println("lock234");
            subMap.climber.lockRightHook();
        }
    }
    public static class RightHookRetract extends CommandBase {
        protected void execute() {
            subMap.climber.rightHookRetract();
        }
    }
    public static class RightHookUnlock extends CommandBase {
        protected void execute() {
            subMap.climber.unlockRightHook();
        }
    }
    public static class ClampForward extends CommandBase {
        protected void execute(){
            subMap.climber.clampForward();
        }
    }
    public static class ClampOff extends CommandBase {
        protected void execute(){
            subMap.climber.clampOff();
        }
    }

}
