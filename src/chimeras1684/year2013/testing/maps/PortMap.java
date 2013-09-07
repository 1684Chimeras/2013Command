
package chimeras1684.year2013.testing.maps;

/**
 *
 * @author Arhowk
 */
public class PortMap
{
//SideCar 1
    
    //Pneumatics
    public static final int solenoidCard = 1;
    public static final int driveLow = 1;
    public static final int hopperUp = 2;
    public static final int fire = 3;
    public static final int armDown = 4;
    public static final int armUp = 5;
    public static final int armLoad = 6;
    
    //PWMs
    public static final int leftDriveMotors   = 1;
    public static final int rightDriveMotors  = 2;
    public static final int leftHookMotor     = 3;
    public static final int rightHookMotor    = 4;
    public static final int shooterWheelMotor = 5;
    public static final int flipperMotor      = 6;
    public static final int tiltMotor         = 7;
    public static final int Roller            = 8;
    public static final int shooterTrimServo  = 9;
    public static final int sparePWM1         = 10;
    
    //GPIO
    public static final int pressureSwitch            = 1;
    public static final int leftDriveEncoderA         = 2;
    public static final int leftDriveEncoderB         = 3;
    public static final int rightDriveEncoderA        = 4;
    public static final int rightDriveEncoderB        = 5;
    public static final int leftHookEncoderA          = 6;
    public static final int rightHookEncoderA         = 7;
    public static final int shooterSpeedEncoder       = 8;    
    public static final int tiltLimitTop              = 9;    
    public static final int tiltLimitBottom           = 10;    
    public static final int leftHookLimitExtend        = 11;    
    public static final int leftHookLimitRetract       = 12;   
    public static final int rightHookLimitExtend      = 13;    
    public static final int rightHookLimitRetract       = 14; 
    
    //Relays
    public static final int compressor     = 1;
    public static final int hookClamp      = 2;
    public static final int spareRelay1    = 3;
    public static final int hopperLED1     = 4;
    public static final int hopperLED2     = 5;
    public static final int hopperLED3     = 6;
    public static final int hopperLED4     = 7;
    public static final int spareRelay2    = 8;
    
//Analog Inputs
    public static final int tiltEncoder = 1;
    public static final int gyro  = 2;
    public static final int flipperPosition         = 3;
    public static final int spareAnalog2 = 4;
    public static final int spareAnalog3 = 5;
    public static final int spareAnalog4 = 6;
    public static final int spareAnalog5 = 7;
    public static final int battery      = 8;
}
