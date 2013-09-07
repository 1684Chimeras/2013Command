/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.maps;
import chimeras1684.year2013.testing.commands.general.ClimbingCommands;
import chimeras1684.year2013.testing.commands.general.DiscHandlerCommands;
import chimeras1684.year2013.testing.commands.general.DriveTrainCommands;
import chimeras1684.year2013.testing.commands.general.TargetingCommands;
import chimeras1684.year2013.testing.commands.general.ShooterCommands;
import chimeras1684.year2013.testing.root.CommandBase;
import chimeras1684.year2013.testing.commands.auton.FiveDiscAuton;
import chimeras1684.year2013.testing.commands.auton.SpinShootAuton;
import chimeras1684.year2013.testing.commands.auton.SevenDiscAuton;
import chimeras1684.year2013.testing.commands.auton.PackRobot;
import chimeras1684.year2013.testing.commands.auton.NineDiscAuton;
import chimeras1684.year2013.testing.commands.auton.ShootDonutMidlineAuton;


/**
 *
 * @author Arhowk
 * 
 * Put any global commands  here.
 * Example commands would be those used by the xbox controllers
 * or on the Smart Dashboard
 * 
 */
public class CommandMap {
    //DriveTrain
    public DriveTrainCommands.DriveAuto driveAutoForward;
    public DriveTrainCommands.XboxDrive driveWithJoysticks;
    public DriveTrainCommands.ShiftLow shiftLow;
    public DriveTrainCommands.ShiftHigh shiftHigh;
    
    //Climber
    public ClimbingCommands.LeftHookExtend leftHookExtend;
    public ClimbingCommands.LeftHookRetract leftHookRetract;
    public ClimbingCommands.RightHookExtend rightHookExtend;
    public ClimbingCommands.RightHookRetract rightHookRetract;
    public ClimbingCommands.RightHookLock rightHookLock;
    public ClimbingCommands.LeftHookLock leftHookLock;
    public ClimbingCommands.RightHookUnlock rightHookUnlock;
    public ClimbingCommands.LeftHookUnlock leftHookUnlock;
    public ClimbingCommands.RightExtendLeftRetract rightExtendLeftRetract;
    public ClimbingCommands.LeftExtendRightRetract leftExtendrightRetract;
    public ClimbingCommands.PeriodicClimbing periodicClaws;
    public ClimbingCommands.AutoClimb autoClimb;
    public ClimbingCommands.ClampForward clampForward;
    public ClimbingCommands.ClampOff clampOff;
    public ClimbingCommands.FlipperJoystick flipperPeriodic;
    public ClimbingCommands.ClimbingPeriodicCheck climbingPeriodicCheck;
    
    //Shooter
    public ShooterCommands.ShooterOn shooterOn;
    public CommandBase teleOpShooter;
    public ShooterCommands.ShooterOff shooterOff;
    public ShooterCommands.Reset reset;
    public ShooterCommands.FirePiston fire;
    public ShooterCommands.JoystickDeckTilt joystickDeckTilt;
    public ShooterCommands.SetShooterLockPoint setShooterLockPoint;
    public ShooterCommands.ReturnToLockPoint returnToLockPoint;
    public ShooterCommands.FireDisc fireDisc;
    public ShooterCommands.RapidFire rapidFire;
    
    //DiscHandler
    public DiscHandlerCommands.ArmDown armDown;
    public DiscHandlerCommands.ArmUp armUp;
    public DiscHandlerCommands.ArmLoadIn armLoadIn;
    public DiscHandlerCommands.ArmLoadOut armLoadOut;
    public DiscHandlerCommands.ArmLoadPosition armLoadPosition;
    public DiscHandlerCommands.HopperUp hopperUp;
    public DiscHandlerCommands.HopperDown hopperDown;
    public DiscHandlerCommands.RollerIn rollerIn;
    
    //Auton
    public FiveDiscAuton fiveDisc;
    public NineDiscAuton nineDisc;
    public SevenDiscAuton sevenDisc;
    public ShootDonutMidlineAuton donutAuton;
    public SpinShootAuton spinAuton;
    public PackRobot packRobot;
    
    //Targeting
    public TargetingCommands.Targeting dualTargeting;
    public TargetingCommands.HorizontalTargeting horizontalTargeting;
    public TargetingCommands.VerticalTargeting verticalTargeting;
    
    private static CommandMap instance;
    
    private void initCommands(){
        //DriveTrain
        driveAutoForward       = new DriveTrainCommands.DriveAuto(5, 1, 0.4, true, 0.5);
        driveWithJoysticks     = new DriveTrainCommands.XboxDrive(true,120);
        shiftLow               = new DriveTrainCommands.ShiftLow();
        shiftHigh              = new DriveTrainCommands.ShiftHigh();
        
        //Climber
        leftHookExtend         = new ClimbingCommands.  LeftHookExtend();
        leftHookRetract        = new ClimbingCommands.  LeftHookRetract();
        rightHookRetract       = new ClimbingCommands.  RightHookRetract();
        rightHookExtend        = new ClimbingCommands.  RightHookExtend();
        rightExtendLeftRetract = new ClimbingCommands.  RightExtendLeftRetract();
        leftExtendrightRetract = new ClimbingCommands.  LeftExtendRightRetract();
        leftHookLock           = new ClimbingCommands.  LeftHookLock();
        leftHookUnlock         = new ClimbingCommands.  LeftHookUnlock();
        rightHookLock          = new ClimbingCommands.  RightHookLock();
        rightHookUnlock        = new ClimbingCommands.  RightHookUnlock();
        periodicClaws          = new ClimbingCommands.  PeriodicClimbing();
        clampForward           = new ClimbingCommands.  ClampForward();
        clampOff               = new ClimbingCommands.  ClampOff();
        autoClimb              = new ClimbingCommands.  AutoClimb();
        climbingPeriodicCheck  = new ClimbingCommands.  ClimbingPeriodicCheck();
        flipperPeriodic        = new ClimbingCommands.  FlipperJoystick();
        
        //Shooter
        shooterOn              = new ShooterCommands.   ShooterOn();
        shooterOff             = new ShooterCommands.   ShooterOff();
        reset                  = new ShooterCommands.   Reset();
        fire                   = new ShooterCommands.   FirePiston();
        joystickDeckTilt       = new ShooterCommands.   JoystickDeckTilt();
        teleOpShooter          = new ShooterCommands.   teleopShooterPeriodic();;
        setShooterLockPoint    = new ShooterCommands.   SetShooterLockPoint();
        returnToLockPoint      = new ShooterCommands.   ReturnToLockPoint();
        fireDisc               = new ShooterCommands.   FireDisc();
        rapidFire               = new ShooterCommands.   RapidFire();
        
        //DiscHandler
        armUp                 = new DiscHandlerCommands.ArmUp();
        armDown               = new DiscHandlerCommands.ArmDown();
        armLoadIn             = new DiscHandlerCommands.ArmLoadIn();
        armLoadOut            = new DiscHandlerCommands.ArmLoadOut();
        armLoadPosition       = new DiscHandlerCommands.ArmLoadPosition();
        hopperUp              = new DiscHandlerCommands.HopperUp();
        hopperDown            = new DiscHandlerCommands.HopperDown();
        rollerIn              = new DiscHandlerCommands.RollerIn();
        
        //Auton
        nineDisc              = new/*      Auton      */NineDiscAuton();
        fiveDisc              = new/*      Auton      */FiveDiscAuton();
        sevenDisc             = new/*      Auton      */SevenDiscAuton();
        donutAuton            = new/*      Auton      */ShootDonutMidlineAuton();
        spinAuton             = new/*      Auton      */SpinShootAuton();
        packRobot             = new/*      Auton      */PackRobot();
        
        //Targeting
        dualTargeting         = new TargetingCommands.  Targeting();
        horizontalTargeting   = new TargetingCommands.  HorizontalTargeting();
        verticalTargeting     = new TargetingCommands.  VerticalTargeting();
        
        
    }
    private CommandMap(){
        instance = (instance == null) ? this : instance;
        initCommands();
    }
    public static CommandMap getInstance(){
        instance = (instance == null) ? new CommandMap() : instance;
        return instance;
    }
    
}
