/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;
import chimeras1684.year2013.testing.commands.general.ShooterCommands;
import chimeras1684.year2013.testing.commands.general.TargetingCommands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import chimeras1684.year2013.testing.root.TimedCommandGroup;

/**
 *
 * @author Arhowk
 */
public class SpinShootAuton extends TimedCommandGroup {
    
    public SpinShootAuton() {
//      /**/  Command                             /**/Start Time/**/  Duration   /**/  
        add(new     ShooterCommands.ShooterOn(),  /**/   0,     /**/     15);    /**/
        add(new     TargetingCommands.Targeting(),/**/   0,     /**/     15);    /**/
        add(new     ShooterCommands.FireDisc(),   /**/   5,     /**/     0.5);   /**/
        add(new     ShooterCommands.Reset(),      /**/   5.5,   /**/     0.5);   /**/
        add(new     ShooterCommands.FireDisc(),   /**/   6,     /**/     0.5);   /**/
        add(new     ShooterCommands.Reset(),      /**/   6.5,   /**/     0.5);   /**/
        add(new     ShooterCommands.FireDisc(),   /**/   7,     /**/     0.5);   /**/
        add(new     ShooterCommands.Reset(),      /**/   7.5,   /**/     0.5);   /**/
    } 
}
