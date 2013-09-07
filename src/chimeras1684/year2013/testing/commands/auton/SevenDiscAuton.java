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
public class SevenDiscAuton extends CommandGroup {
    
    public SevenDiscAuton() {
        addParallel  (new ShooterCommands.ShooterOn(),20); ////
        addParallel  (new TargetingCommands.VerticalTargeting    (),  20); 
        addSequential(new ShooterCommands.FireDisc (), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new ShooterCommands.FireDisc (), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new ShooterCommands.FireDisc (), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new DriveTrainCommands.DriveAuto(10, 0.25, 0.4, true, 0.45)); //Drive backward according to smartdashboard data
        addSequential(new ShooterCommands.FireDisc (), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addSequential(new ShooterCommands.FireDisc (), 0.5);
        addSequential(new ShooterCommands.Reset(), 0.5);
        addParallel  (new DiscHandlerCommands.ArmDown   (),  0.5);
        addParallel  (new DiscHandlerCommands.ArmLoadIn (),  0.5);
        addSequential(new DriveTrainCommands.DriveAuto(10, 0.5, 0.4, true, 0.45)); //Drive backward according to smartdashboard data
        addParallel  (new DiscHandlerCommands.ArmUp     (),  0.5);
        addParallel  (new DiscHandlerCommands.ArmLoadOut(),  0.5);
    }
}
