/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.subsystems;
import chimeras1684.year2013.testing.maps.ConstantsMap;
import chimeras1684.year2013.testing.maps.CommandMap;
import chimeras1684.year2013.testing.maps.SubsystemMap;
import chimeras1684.year2013.testing.maps.StringMap;
import chimeras1684.year2013.testing.maps.PortMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import chimeras1684.year2013.testing.root.OI;

/**
 *
 * @author Arhowk
 */
public abstract class SubsystemBase extends Subsystem{

    
    public static final SubsystemMap subMap;
    public static final CommandMap cmdMap;
    public static final PortMap portMap;
    public static final StringMap stringMap;
    public static final ConstantsMap constMap;
    public static final OI oi;
    
    static{
        subMap = SubsystemMap.getInstance();
        cmdMap = CommandMap.getInstance();
        oi = OI.getInstance();
        portMap = null;
        stringMap = null;
        constMap = null;
    }
    public void initDefaultCommand(){}
    protected SubsystemBase(){}
    
}
