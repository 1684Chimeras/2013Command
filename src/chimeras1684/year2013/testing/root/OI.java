
package chimeras1684.year2013.testing.root;

import chimeras1684.year2013.testing.maps.SubsystemMap;
import chimeras1684.year2013.testing.maps.StringMap;
import chimeras1684.year2013.testing.maps.CommandMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI{
    //end setup
    public   XboxController controlDriver;
    public   XboxController controlOperator;
    
    public   SubsystemMap   subMap;
    public   CommandMap     cmdMap;
    
    private  static OI      instance;
    
    public   int          currentMode;
    
    
    
    private OI(){
        instance        = (instance == null) ? this : instance;
        subMap          = SubsystemMap.getInstance();
        cmdMap          = CommandMap.getInstance();
        controlDriver   = new XboxController(1, false);
        controlOperator = new XboxController(2, false);
        currentMode      = 0;
    }
    public static OI getInstance(){
        instance = (instance == null) ? new OI() : instance;
        return instance;
    }
    public void initAllButtons(){
        
        //Normal Commands
        
        //DRIVER                TRIGGER           TYPE              COMMAND                  MODE
        controlDriver.       leftTrigger.    whileHeld    (      cmdMap.shiftLow             ,0);
        controlDriver.       rightTrigger.   whileHeld    (      cmdMap.shiftHigh            ,0);
        controlDriver.       y.              whileHeld    (      cmdMap.armUp                ,0);
        controlDriver.       a.              whileHeld    (      cmdMap.armDown              ,0);
        controlDriver.       bumperRight.    whileHeld    (      cmdMap.armLoadOut           ,0);
        controlDriver.       bumperLeft.     whileHeld    (      cmdMap.armLoadIn            ,0);
        controlDriver.       b.              whileHeld    (      cmdMap.armLoadPosition      ,0);
        controlDriver.       x.              whileHeld    (      cmdMap.rollerIn             ,0);
        controlDriver.       thumbPadRight.  whenPressed  (      cmdMap.climbingPeriodicCheck,0);
        
        //OPEARTOR               TRIGGER           TYPE              COMMAND
        controlDriver.     a.              whileHeld    (      cmdMap.clampForward         ,1);
        controlDriver.     b.              whileReleased(      cmdMap.clampOff             ,1);
        controlDriver.     y.              togglePressed(      cmdMap.shooterOn            ,1);
        controlDriver.     thumbPadRight.  whenPressed  (      cmdMap.climbingPeriodicCheck,1);
        controlDriver.     x.              whileHeld    (      cmdMap.setShooterLockPoint  ,1);
        controlDriver.     rightTrigger.   whileHeld    (      cmdMap.fireDisc             ,1);
        controlDriver.     leftTrigger.    whileHeld    (      cmdMap.rapidFire            ,1);
        controlDriver.     bumperRight.    whileHeld    (      cmdMap.fire                 ,1);
        controlDriver.     bumperRight.    whileReleased(      cmdMap.reset                ,1);
        //OPEARTOR               TRIGGER           TYPE              COMMAND
//        controlOperator.     a.              whileHeld    (      cmdMap.clampForward         ,0);
//        controlOperator.     a.              whileReleased(      cmdMap.clampOff             ,0);
//        controlOperator.     y.              whileHeld    (      cmdMap.shooterOn            ,0);
//        controlOperator.     thumbPadRight.  whenPressed  (      cmdMap.climbingPeriodicCheck,0);
//        controlOperator.     x.              whileHeld    (      cmdMap.shooterOff           ,0);
//        controlOperator.     rightTrigger.   whileHeld    (      cmdMap.fireDisc             ,0);
//        controlOperator.     leftTrigger.    whileHeld    (      cmdMap.hopperUp             ,0);
//        controlOperator.     leftTrigger.    whileReleased(      cmdMap.hopperDown           ,0);
//        controlOperator.     bumperRight.    whileHeld    (      cmdMap.fire                 ,0);
//        controlOperator.     bumperRight.    whileReleased(      cmdMap.reset                ,0);
        
        
        
        //Climbing Commands
        
////        DRIVER            TRIGGER           TYPE              COMMAND
//        controlDriver.       a.              whileHeld    (     cmdMap.leftHookUnlock        ,1);
//        controlDriver.       a.              whileReleased(     cmdMap.leftHookLock          ,1);
//        controlDriver.       x.              whileHeld    (     cmdMap.rightHookUnlock       ,1);
//        controlDriver.       x.              whileReleased(     cmdMap.rightHookLock         ,1);
////        //OPERATOR
//        controlOperator.     thumbPadRight.  whenPressed  (     cmdMap.climbingPeriodicCheck ,1);
//        controlOperator.     bumperRight.    whileHeld    (     cmdMap.armLoadOut            ,1);
//        controlOperator.     bumperLeft.     whileHeld    (     cmdMap.armLoadIn             ,1);
//        controlOperator.     a.              whileHeld    (     cmdMap.autoClimb             ,1);
        
    }
    public  void swapModes(int mode){
        currentMode = mode;
        setControllerModes(mode);
//        if(toClimbing){
//            System.out.println("swaptoclimb");
//            currentMode = 1;
//            cmdMap.joystickDeckTilt.start();
//            cmdMap.teleOpShooter.start();
////            cancelNormalCommands();
////            initClimbingCommands();
//            setControllerModes(1);
//        }else{
//            System.out.println("swapout");
//            currentMode = 0;
//            cmdMap.joystickDeckTilt.cancel();
//            cmdMap.teleOpShooter.cancel();
////            cancelClimbingCommands();
////            initNormalCommands();
//            setControllerModes(0);
//        }
    }
    public void start(){
        initNormalCommands();
    }
    private  void cancelNormalCommands(){
        cmdMap.driveWithJoysticks.cancel();
        cmdMap.joystickDeckTilt.  cancel();
        cmdMap.teleOpShooter.     cancel();
        cmdMap.flipperPeriodic.   cancel();
    }
    private  void cancelClimbingCommands(){
        cmdMap.periodicClaws.  cancel();
        cmdMap.flipperPeriodic.cancel();
        cmdMap.driveWithJoysticks.cancel();
    }
    public  void resetControllers(){
        controlDriver.  wipeCurrentCommands();
        controlOperator.wipeCurrentCommands();
    }
    private  void initNormalCommands(){
        cmdMap.driveWithJoysticks.start();
        cmdMap.joystickDeckTilt.  start();
        cmdMap.teleOpShooter.     start();
//        cmdMap.flipperPeriodic.   start();
    }
    private  void initClimbingCommands(){
        cmdMap.periodicClaws.     start();
        cmdMap.driveWithJoysticks.start();
        cmdMap.flipperPeriodic.   start();
    }
    private  void setControllerModes(int modes){
        controlDriver.  setButtonModes(modes);
        controlOperator.setButtonModes(modes);
    }
//      
    public  void cancelAll(){
        resetControllers();
        cmdMap.driveWithJoysticks.   cancel();
        cmdMap.joystickDeckTilt.     cancel();
        cmdMap.teleOpShooter.        cancel();
        cmdMap.periodicClaws.        cancel();
        cmdMap.flipperPeriodic.      cancel();
        cmdMap.climbingPeriodicCheck.cancel();
        
    }
    public  void initSmartDashboardData(){
//        SmartDashboard.putNumber(StringMap.targeterKPString, 0.009);
//        SmartDashboard.putNumber("Encoder Distance", 109);
        SmartDashboard.putNumber(StringMap.accelEnd, 7.27);
        SmartDashboard.putNumber(StringMap.decelStart, 78.1);
        SmartDashboard.putNumber(StringMap.coastPeriod, 96.3);
        SmartDashboard.putNumber("P - Y Error", 30);
        SmartDashboard.putNumber("P - X Error", 3);
        SmartDashboard.putNumber("Auton Stage", 1);
//        SmartDashboard.putData("Forward" , new DriveTrainCommands.DriveAuto(5, 1, 0.3, true, 0.5));
//        SmartDashboard.putData("Backward" , new DriveTrainCommands.DriveAuto(5, 1, 0.3, false, 0.5));
    }
}

