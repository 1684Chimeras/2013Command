/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.general;

import chimeras1684.year2013.testing.root.CommandBase;

/*
 *
 * @author Arhowk
 */
public class DiscHandlerCommands {
    public static class RollerIn extends CommandBase {
        protected void execute() {
            System.out.println("exec");
                subMap.arm.rollerIn();
        }
        protected void end(){
            subMap.arm.rollerOff();
        }
        protected void interrupted(){
            subMap.arm.rollerOff();
        }
    }
    public static class ArmDown extends CommandBase {
        protected void execute() {
            System.out.println("exec");
                subMap.arm.armDown();
                subMap.arm.armLoadIn();
        }
    }
    public static class ArmLoadIn extends CommandBase {
        protected void execute() {
                subMap.arm.armLoadIn();
        }
    }
    public static class ArmLoadOut extends CommandBase {
        protected void execute() {
            subMap.arm.armLoadOut();
        }
    }
    public static class ArmLoadPosition extends CommandBase {
        protected void execute() {
                subMap.arm.armLoadPosition();
        }
    }
    public static class ArmToggle extends CommandBase {
        protected void execute() {
//            if(subMap.arm.armDown.get()){
//                subMap.arm.armUp();
//            }else if(subMap.arm.armUp.get()){
//                subMap.arm.armDown();
//            }
        }
    }
    public static class ArmUp extends CommandBase {
        protected void execute() {
                subMap.arm.armUp();
        }
    }
    public static class HopperDown extends CommandBase {
        protected void execute() {
            subMap.discHandler.hopperDown();
        }
    }
    public static class HopperUp extends CommandBase {
        protected void execute() {
            subMap.discHandler.hopperUp();
        }
    }
    public static class Queue extends CommandBase {
        protected void execute() {
            subMap.discHandler.queue();
        }
    }


}
