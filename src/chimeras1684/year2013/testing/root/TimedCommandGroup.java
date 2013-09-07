/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;

import chimeras1684.year2013.testing.maps.CommandMap;
import chimeras1684.year2013.testing.maps.ConstantsMap;
import chimeras1684.year2013.testing.maps.PortMap;
import chimeras1684.year2013.testing.maps.StringMap;
import chimeras1684.year2013.testing.maps.SubsystemMap;
import com.sun.squawk.Method;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Hashtable;
import java.util.Enumeration;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author Arhowk
 */
public abstract class TimedCommandGroup extends Command{
    private Hashtable hash;
    private int index = 0;
    private Timer timer;
    private Integer globalKey;
    private double offset;
    
    public static SubsystemMap subMap;
    public static CommandMap cmdMap;
    public static PortMap portMap;
    public static StringMap stringMap;
    public static ConstantsMap constmap;
    public static OI oi;
    
    public TimedCommandGroup(){
        hash = new Hashtable();
        timer = new Timer();
        globalKey = new Integer(0);
        cmdMap = CommandMap.getInstance();
        subMap = SubsystemMap.getInstance();
    }
    protected boolean isFinished(){return false;}
    protected void end(){
        _cancel();
    }
    protected void execute(){
        int numOfRunningCommands = 0;
        Enumeration runningCommandsIteration = hash.keys();
        TimedCommand c;
        while(runningCommandsIteration.hasMoreElements()){
            c = (TimedCommand)(runningCommandsIteration.nextElement());
            if(!c.command.isRunning()){
                if(timer.get() > c.start && !c.hasStarted){
                    c.command.start();
                    numOfRunningCommands += 1;
                    c.hasStarted = true;
                }else if(!c.hasStarted){
                    numOfRunningCommands += 1;
                }
            }else{
                if(((timer.get() ) - c.start) > c.timeout && c.timeout != -1){
                    c.command.cancel();
                }else{
                    numOfRunningCommands += 1;
                }
            }
        }
        if(numOfRunningCommands == 0){
            this.cancel();
        }
    }
    protected void _cancel(){
        Enumeration e = hash.keys();
        TimedCommand c;
        while(e.hasMoreElements()){
            c = (TimedCommand)(e.nextElement());
            c.command.cancel();
        }
    }
    protected void interrupted(){
        _cancel();
    }
    protected void initialize(){
        timer.start();
        timer.reset();
    }
    protected void add(final Command command){
        TimedCommand tcmd = new TimedCommand();
        tcmd.start = 0;
        tcmd.timeout = -1;
        tcmd.command = command;
        hash.put(tcmd,globalKey);
    }
    protected void add(final Command command, double start){
        TimedCommand tcmd = new TimedCommand();
        tcmd.start = start;
        tcmd.timeout = -1;
        tcmd.command = command;
        hash.put(tcmd,globalKey);
    }
    protected void add(final Command command, double start, double timeout){
        TimedCommand tcmd = new TimedCommand();
        tcmd.start = start;
        tcmd.timeout = timeout;
        tcmd.command = command;
        hash.put(tcmd,globalKey);
    }
    private class TimedCommand extends Object{
        public double start;
        public double timeout;
        public Command command;
        public boolean hasStarted = false;
    }
}
