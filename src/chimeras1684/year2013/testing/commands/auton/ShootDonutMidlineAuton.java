
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;

import chimeras1684.year2013.testing.commands.general.DriveTrainCommands;
import chimeras1684.year2013.testing.commands.general.DiscHandlerCommands;
import chimeras1684.year2013.testing.commands.general.ShooterCommands;
import chimeras1684.year2013.testing.commands.general.TargetingCommands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Arhowk
 */
public class ShootDonutMidlineAuton extends CommandGroup {
    
    public ShootDonutMidlineAuton() {
        addParallel  (new ShooterCommands.ShooterOn(),15);
        addParallel  (new TargetingCommands.Targeting(),15);
        addSequential(new ShooterCommands.FireDisc(), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new ShooterCommands.FireDisc(), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new ShooterCommands.FireDisc(), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new DiscHandlerCommands.ArmDown(), 0.75);
        addSequential(new DriveTrainCommands.TimedDonut(3.5,0.875,0.25));
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.25, 0.4, true, 0.45)); 
        addSequential(new DiscHandlerCommands.ArmUp(), 0.75);
        addSequential(new DiscHandlerCommands.ArmDown(), 0.75);
        addSequential(new DriveTrainCommands.Spin(180, 15, true));
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.25, 0.4, true, 0.45)); 
        addSequential(new ShooterCommands.FireDisc(), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new ShooterCommands.FireDisc(), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
    }
}
