package chimeras1684.year2013.testing.root;

import chimeras1684.year2013.testing.maps.ConstantsMap;
import chimeras1684.year2013.testing.maps.SubsystemMap;
import chimeras1684.year2013.testing.maps.StringMap;
import chimeras1684.year2013.testing.maps.PortMap;
import chimeras1684.year2013.testing.maps.CommandMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import chimeras1684.year2013.testing.root.OI;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    
    public static SubsystemMap subMap;
    public static CommandMap cmdMap;
    public static PortMap portMap;
    public static StringMap stringMap;
    public static ConstantsMap constMap;
    public static OI oi;
    
    protected void initialize(){}
    protected boolean isFinished(){return isTimedOut();}
    protected void execute(){}
    protected void end(){}
    protected void interrupted(){}
    public void start(double timeout){
        setTimeout(timeout);
        super.start();
    }
    public void init() {
        cmdMap = CommandMap.getInstance();
        subMap = SubsystemMap.getInstance();
        oi = OI.getInstance();
    }
    public CommandBase(String name) {
        super(name);
        init();
    }

    public CommandBase() {
        super();
        init();
    }
}
