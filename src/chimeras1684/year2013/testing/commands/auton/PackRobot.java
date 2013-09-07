/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;
import chimeras1684.year2013.testing.commands.general.DiscHandlerCommands;
import chimeras1684.year2013.testing.commands.general.ClimbingCommands;
import chimeras1684.year2013.testing.commands.general.ShooterCommands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import chimeras1684.year2013.testing.root.TimedCommandGroup;

/**
 *
 * @author Arhowk
 */
public class PackRobot extends TimedCommandGroup {

    public PackRobot() {
        // Add Commands here:
        // add(Command command) //will run the command at beginning with no internal timeout
        // add(Cmmand command,double start) //will run the command after "start" seconds with no internal timeout
        // add(Command command, double start,double timeout) //will run the command after "start" seconds with "timeout" seconds of internal timeout
        // e.g. add(new MyCommand()) //will run at start
        //      add(new MyCommand2(),5); //will run after 5 seconds
        //      add(new MyCommamnd3(),10,5); //will run after 10 seconds and terminate after another 5 seconds
        add(new ShooterCommands.ShooterOff(),0,1);
        add(new DiscHandlerCommands.ArmUp(),0,1);
        add(new DiscHandlerCommands.ArmLoadIn(),0,1);
        add(new ShooterCommands.SetDeckTilt(0,0),0,1);
        add(new ClimbingCommands.LeftHookRetract(),0,1);
        add(new ClimbingCommands.RightHookRetract(),0,1);
        add(new ShooterCommands.Reset(),0,0.5);
    }
}