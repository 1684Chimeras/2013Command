/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package chimeras1684.year2013.testing.root;


import chimeras1684.year2013.testing.maps.ConstantsMap;
import chimeras1684.year2013.testing.maps.SubsystemMap;
import chimeras1684.year2013.testing.maps.CommandMap;
import chimeras1684.year2013.testing.maps.PortMap;
import chimeras1684.year2013.testing.maps.StringMap;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MainRobot extends IterativeRobot {
//
//    Command autonomousCommand;
    CommandMap cmdMap;
    PortMap portMap;
    StringMap stringMap;
    SubsystemMap subMap;
    ConstantsMap constMap;
    OI oi;
    AutonModeToggle AMT;
    PS3Checker opCheck;
    PS3Checker driveCheck;
//    Diagnostics diagnostics;
    boolean isRightThumbPressed = false;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        cmdMap = CommandMap.getInstance();
        subMap = SubsystemMap.getInstance();
        oi = OI.getInstance();
        oi.initSmartDashboardData();
        AMT = new AutonModeToggle(oi.controlDriver.thumbPadRight);
        oi.initAllButtons();
        opCheck = new PS3Checker(true);
        driveCheck = new PS3Checker(false);
//        diagnostics = Diagnostics.getInstance();
//        diagnostics.addInstance(new CameraDiagnostics());
//        diagnostics.addInstance(new ShooterDiagnostics());
    }

    public void autonomousInit() {
//        switch((int)SmartDashboard.getNumber("Auton Stage")){
        switch(AMT.getToggleStage()){
            case 0:
                cmdMap.nineDisc.start();
                break;
            case 1:
                cmdMap.fiveDisc.start();
                break;
            case 2:
                cmdMap.sevenDisc.start();
                break;
            case 3:
                cmdMap.donutAuton.start();
                break;
            case 4:
                cmdMap.spinAuton.start();
                break;
          
        }
    }
    public void autonomousPeriodic() {
//        subMap.pnuematics.compress();
//        Scheduler.getInstance().run();
    }

    public void teleopInit() {
//        cmdMap.driveWithJoysticks.start();
        oi.start();
//        oi.initAllButtons();
    }
    public void teleopPeriodic() {
        
        SmartDashboard.putData(Scheduler.getInstance());
        subMap.pnuematics.compress();
        Scheduler.getInstance().run();
    }
    public void testInit(){
     //   (new PackRobot()).start();
    }
    public void testPeriodic() {
       // LiveWindow.run();
    }
    public void disabledInit(){
        cmdMap.donutAuton.cancel();
        cmdMap.nineDisc.cancel();
        cmdMap.sevenDisc.cancel();
        cmdMap.spinAuton.cancel();
        oi.cancelAll();
    }
    public void disabledPeriodic(){
        if(opCheck.isPS3() && !oi.controlOperator.isPS3){
            oi.controlOperator.setControllerType(true);
        }else if(!opCheck.isPS3() && oi.controlOperator.isPS3){
            oi.controlOperator.setControllerType(false);
        }
        if(driveCheck.isPS3() && !oi.controlDriver.isPS3){
            oi.controlDriver.setControllerType(true);
        }else if(!driveCheck.isPS3() && oi.controlDriver.isPS3){
            oi.controlDriver.setControllerType(false);
        }
//        AMT.checkForToggle();
    }
}
