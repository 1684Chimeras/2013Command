/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;

import chimeras1684.year2013.testing.commands.general.DiscHandlerCommands;
import chimeras1684.year2013.testing.commands.general.DriveTrainCommands;
import chimeras1684.year2013.testing.commands.general.TargetingCommands;
import chimeras1684.year2013.testing.commands.general.ShooterCommands;
import chimeras1684.year2013.testing.root.TimedCommandGroup;

/**
 *
 * @author Arhowk
 */
public class FiveDiscAuton extends TimedCommandGroup {
    public FiveDiscAuton() {
//      /**/  Command                                                           /**/Start Time/**/  Duration  /**/  
        add(new     ShooterCommands.    ShooterOn(),                            /**/   0,     /**/    15  );  /**/
        add(new     TargetingCommands.  VerticalTargeting(),                    /**/   0,     /**/    15  );  /**/
        add(new     ShooterCommands.    Reset(),                                /**/   3.5,   /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   3.5,   /**/    0.5);   /**/
        add(new     ShooterCommands.    FireDisc(),                             /**/   4,     /**/    0.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   4.5,   /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   4.5,   /**/    0.5);   /**/
        add(new     ShooterCommands.    FireDisc(),                             /**/   5,     /**/    0.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   5.5,   /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   5.5,   /**/    0.5);   /**/
        add(new     ShooterCommands.    FireDisc(),                             /**/   6,     /**/    0.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   6.5,   /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   6.5,   /**/    0.5);   /**/
        add(new     DriveTrainCommands. DriveAuto(1000, 0.15, 0.4, true, 0.45), /**/   7,     /**/    1.5);   /**/ //1000 timeout, 0.15 x original distance, 0.4 is minimum accel, true is direction (true = forward), 0.45 is minimum decel (untill coasting)
        add(new     DiscHandlerCommands.ArmUp(),                                /**/   8.5,   /**/    1  );   /**/
        add(new     DiscHandlerCommands.ArmLoadIn(),                            /**/   8.5,   /**/    1  );   /**/
        add(new     DiscHandlerCommands.ArmLoadOut(),                           /**/   9.5,   /**/    4.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   10,    /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   10,    /**/    0.5);   /**/
        add(new     ShooterCommands.    FireDisc(),                             /**/   10.5,  /**/    0.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   11,    /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   11,    /**/    0.5);   /**/
        add(new     ShooterCommands.    FireDisc(),                             /**/   11.5,  /**/    0.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   12,    /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   12,    /**/    0.5);   /**/
        add(new     ShooterCommands.    FireDisc(),                             /**/   12.5,  /**/    0.5);   /**/
        add(new     ShooterCommands.    Reset(),                                /**/   13,    /**/    0.5);   /**/
        add(new     DiscHandlerCommands.HopperDown(),                           /**/   13,    /**/    0.5);   /**/
    }
}

//Alternatively, you can use a timed CommandGroup
//Timed CommandGroup code can be found from a fellow coder as i have no idea how to build it in to a FRC package
//Author of TimedCommandGroup = Arhowk (Jake Niman)
//public class FiveDiscAuton extends TimedCommandGroup {
//
//    public FiveDiscAuton() {
//        // Add Commands here:
//        // add(Command command) //will run the command at beginning with no internal timeout
//        // add(Cmmand command,double start) //will run the command after "start" seconds with no internal timeout
//        // add(Command command, double start,double timeout) //will run the command after "start" seconds with "timeout" seconds of internal timeout
//        // e.g. add(new MyCommand()) //will run at start
//        //      add(new MyCommand2(),5); //will run after 5 seconds
//        //      add(new MyCommamnd3(),10,5); //will run after 10 seconds and terminate after another 5 seconds
//    }
//}