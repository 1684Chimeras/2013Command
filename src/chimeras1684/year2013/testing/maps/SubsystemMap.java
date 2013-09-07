/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.maps;
import chimeras1684.year2013.testing.subsystems.DiscHandling;
import chimeras1684.year2013.testing.subsystems.Shooter;
import chimeras1684.year2013.testing.subsystems.Pneumatics;
import chimeras1684.year2013.testing.subsystems.DriveTrain;
import chimeras1684.year2013.testing.subsystems.Climber;
//import chimeras1684.year2013.testing.subsystems.Camera;
import chimeras1684.year2013.testing.subsystems.Arm;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 * @author Arhowk
 */
public class SubsystemMap {
    public DriveTrain driveTrain;
//    public Camera camera;
    public Climber climber;
    public Pneumatics pnuematics;
    public Shooter shooter;
    public Arm arm;
    public DiscHandling discHandler;
    
    public NetworkTable targeterTable;
    public NetworkTable targeterTableIn;
    public NetworkTable targeterTableOut;
    
    private static SubsystemMap instance;
    
    private SubsystemMap(){
        instance = (instance == null) ? this : instance;
        pnuematics = Pneumatics.getInstance();
        driveTrain = DriveTrain.getInstance();
        discHandler = DiscHandling.getInstance();
        targeterTable = NetworkTable.getTable(StringMap.targeterTable);
        targeterTableIn = NetworkTable.getTable(StringMap.targeterTable + "/" + StringMap.targeterTableIn);
        targeterTableOut = NetworkTable.getTable(StringMap.targeterTable + "/" + StringMap.targeterTableOut);
        arm = Arm.getInstance();
        climber = Climber.getInstance();
        shooter = Shooter.getInstance();
//        camera = Camera.getInstance();
    }
    
    public static SubsystemMap getInstance(){
        instance = (instance == null) ? new SubsystemMap() : instance;
        return instance;
    }
}
