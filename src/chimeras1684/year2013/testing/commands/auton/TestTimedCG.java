/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import chimeras1684.year2013.testing.root.CommandBase;
import chimeras1684.year2013.testing.root.TimedCommandGroup;

/**
 *
 * @author Arhowk
 */
public class TestTimedCG extends TimedCommandGroup {
    
    public TestTimedCG() {
        add(new CommandBase(){
            protected void execute(){
                System.out.println("HI");
            }
        }, 1,5);
        add(new CommandBase(){
            protected void execute(){
                System.out.println("HI2");
            }
        }, 2,5);
        add(new CommandBase(){
            protected void execute(){
                System.out.println("HI3");
            }
        }, 3,5);
        // arm.
    }
}
