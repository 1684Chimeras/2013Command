/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;
import chimeras1684.year2013.testing.commands.general.Wait;
import chimeras1684.year2013.testing.commands.general.DiscHandlerCommands;
import chimeras1684.year2013.testing.commands.general.DriveTrainCommands;
import chimeras1684.year2013.testing.commands.general.TargetingCommands;
import chimeras1684.year2013.testing.commands.general.ShooterCommands;
import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 * @author Arhowk
 */
public class NineDiscAuton extends CommandGroup{ 
    
    public NineDiscAuton(){
        
        //////////////////////First Stage : Backwards////////////////////////////////
        addParallel  (new   ShooterCommands.ShooterOn(),20                               ); 
        addParallel  (new   TargetingCommands.VerticalTargeting(),20                               ); 
        addParallel  (new   DiscHandlerCommands.ArmUp());
        addSequential(new   Wait(0.5));
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        //////////////////////Second Stage : Backwards///////////////////////////////\
        addParallel  (new   DiscHandlerCommands.ArmDown());
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.25, 0.4, false, 0.45)); //Drive backward according to smartdashboard data
        addSequential(new   Wait(1.2));
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.25, 0.4, true, 0.45)); //Drive backward according to smartdashboard data
        //TODO: Turn Shoot
        addSequential(new   DiscHandlerCommands.ArmUp(), 1.25);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        
        addParallel  (new   DiscHandlerCommands.ArmDown());
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.25, 0.4, true, 0.45)); //Drive backward according to smartdashboard data
        addSequential(new   Wait(1.2));
        addSequential(new   DiscHandlerCommands.ArmUp(), 1.25);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset() ,0.5);
        addParallel  (new   DiscHandlerCommands.ArmDown());
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.5, 0.4, true, 0.45)); //Drive backward according to smartdashboard data
        addSequential(new   Wait(1.2));
        addSequential(new   DriveTrainCommands.DriveAuto(10, 0.25, 0.4, false, 0.45)); //Drive backward according to smartdashboard data
        addSequential(new   DiscHandlerCommands.ArmUp(), 1.25);
        addSequential(new   Wait(1.2));
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        addSequential(new   ShooterCommands.FireDisc (), 0.5);
        addSequential(new   ShooterCommands.Reset(), 0.5);
        
    }
}
