/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;
import chimeras1684.year2013.testing.maps.StringMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author Arhowk
 */
public class XboxController {
    public Joystick controller;
        AdvancedButton a;
//    Button a;
        AdvancedButton b;
        AdvancedButton x;
        AdvancedButton y;
        AdvancedButton bumperLeft;
        AdvancedButton bumperRight;
        AdvancedButton stop;
        AdvancedButton start;
        AdvancedButton buttonLeftJoystick;
        AdvancedButton buttonRightJoystick;
        AdvancedButton thumbPadRight;
        AdvancedButton thumbPadLeft;
        AdvancedButton thumbPadTop;
        AdvancedButton thumbPadBottom;
        AdvancedButton leftTrigger;
        AdvancedButton rightTrigger;
        private AxisInheritable pS3ThumbPadRight;
        private AxisInheritable pS3ThumbPadLeft;
        private AxisInheritable pS3ThumbPadTop;
        private AxisInheritable pS3ThumbPadBottom;
        private ButtonInheritable pS3LeftTrigger;
        private ButtonInheritable pS3RightTrigger;
        private ButtonInheritable pS3A;
        private ButtonInheritable pS3B;
        private ButtonInheritable pS3Stop;
        private ButtonInheritable pS3Start;
        private ButtonInheritable pS3ButtonLeftJoystick;
        private ButtonInheritable pS3ButtonRightJoystick;
        private AxisInheritable xBoxThumbPadRight;
        private AxisInheritable xBoxThumbPadLeft;
        private AxisInheritable xBoxLeftTrigger;
        private AxisInheritable xBoxRightTrigger;
        private ButtonInheritable xBoxA;
        private ButtonInheritable xBoxB;
        private ButtonInheritable xBoxStop;
        private ButtonInheritable xBoxStart;
        private ButtonInheritable xBoxButtonLeftJoystick;
        private ButtonInheritable xBoxButtonRightJoystick;
        private AdvancedButton nullButton;
        private XboxController.StringCollector indexer;
        private ButtonInheritable nullInheritable;
        boolean isPS3;
    
    public XboxController(int index, boolean isPS3){
        controller =            new Joystick          (index                   );
        nullInheritable =       new ButtonInheritable (controller, 0           );
        nullButton =            new AdvancedButton    (controller, 0, 1        );
        this.isPS3 = isPS3;
        
        x =                      new AdvancedButton   (controller, 3, 1        );
        y =                      new AdvancedButton   (controller, 4, 1        );
        bumperLeft =             new AdvancedButton   (controller, 5, 1        );
        bumperRight =            new AdvancedButton   (controller, 6, 1        );
        thumbPadLeft =           new AdvancedButton   (controller, 0, 1        );
        thumbPadRight =          new AdvancedButton   (controller, 0, 1        );
        thumbPadTop =            new AdvancedButton   (controller, 0, 1        );
        thumbPadBottom =         new AdvancedButton   (controller, 0, 1        );
        leftTrigger =            new AdvancedButton   (controller, 0, 1        );
        rightTrigger =           new AdvancedButton   (controller, 0, 1        ); 
        a =                      new AdvancedButton   (controller, 1, 1        );
        b =                      new AdvancedButton   (controller, 2, 1        );
        stop =                   new AdvancedButton   (controller, 7, 1        );
        start =                  new AdvancedButton   (controller, 8, 1        );
        buttonLeftJoystick =     new AdvancedButton   (controller, 9, 1        );
        buttonRightJoystick =    new AdvancedButton   (controller, 10, 1        );
        
        xBoxThumbPadRight =      new AxisInheritable  (controller, 6,  1,   1  );
        xBoxThumbPadLeft =       new AxisInheritable  (controller, 6, -1,  -1  );
        xBoxLeftTrigger =        new AxisInheritable  (controller, 3, 0.2,  1  );
        xBoxRightTrigger =       new AxisInheritable  (controller, 3, -1,  -0.2);
        xBoxA =                  new ButtonInheritable(controller, 1           );
        xBoxB =                  new ButtonInheritable(controller, 2           );
        xBoxStop =               new ButtonInheritable(controller, 7           );
        xBoxStart =              new ButtonInheritable(controller, 8           );
        xBoxButtonLeftJoystick = new ButtonInheritable(controller, 9           );
        xBoxButtonRightJoystick= new ButtonInheritable(controller, 10          );
        
        pS3ThumbPadRight =       new AxisInheritable  (controller, 5,  1,   1  );
        pS3ThumbPadLeft =        new AxisInheritable  (controller, 5, -1,  -1  );
        pS3ThumbPadTop =         new AxisInheritable  (controller, 6, -1,  -1  );
        pS3ThumbPadBottom =      new AxisInheritable  (controller, 6,  1,   1  );
        pS3LeftTrigger =         new ButtonInheritable(controller, 7           );
        pS3RightTrigger =        new ButtonInheritable(controller, 8           );
        pS3A =                   new ButtonInheritable(controller, 2           );
        pS3B =                   new ButtonInheritable(controller, 1           );
        pS3Stop =                new ButtonInheritable(controller, 9           );
        pS3Start =               new ButtonInheritable(controller, 10          );
        pS3ButtonLeftJoystick =  new ButtonInheritable(controller, 11          );
        pS3ButtonRightJoystick=  new ButtonInheritable(controller, 12          );
        if(isPS3){  
            thumbPadLeft.           setInheritable(pS3ThumbPadLeft);
            thumbPadRight.          setInheritable(pS3ThumbPadRight);
            thumbPadTop.            setInheritable(pS3ThumbPadTop);
            thumbPadBottom.         setInheritable(pS3ThumbPadBottom);
            leftTrigger.            setInheritable(pS3LeftTrigger);
            rightTrigger.           setInheritable(pS3RightTrigger); 
            a.                      setInheritable(pS3A);
            b.                      setInheritable(pS3B);
            stop.                   setInheritable(pS3Stop);
            start.                  setInheritable(pS3Start);
            buttonLeftJoystick.     setInheritable(pS3ButtonLeftJoystick);
            buttonRightJoystick.    setInheritable(pS3ButtonRightJoystick);
        }else{
            thumbPadLeft.           setInheritable(xBoxThumbPadLeft);
            thumbPadRight.          setInheritable(xBoxThumbPadRight);
            thumbPadTop.            setInheritable(nullInheritable);
            thumbPadBottom.         setInheritable(nullInheritable);
            leftTrigger.            setInheritable(xBoxLeftTrigger);
            rightTrigger.           setInheritable(xBoxRightTrigger); 
            a.                      setInheritable(xBoxA);
            b.                      setInheritable(xBoxB);
            stop.                   setInheritable(xBoxStop);
            start.                  setInheritable(xBoxStart);
            buttonLeftJoystick.     setInheritable(xBoxButtonLeftJoystick);
            buttonRightJoystick.    setInheritable(xBoxButtonRightJoystick);
        }
        indexer =            new XboxController.StringCollector();
        this.isPS3 = isPS3;
    }
    private class ButtonInheritable extends AdvancedButton.Inheritable{
        Joystick j;
        int button;
        public ButtonInheritable(Joystick j, int button){
            this.j = j;
            this.button = button;
        }                                         
        public boolean isPressed(){
            boolean b = j.getRawButton(button);
            return b;
        }
    }
    private class AxisInheritable extends AdvancedButton.Inheritable{
        Joystick j;
        int axis;
        double lower;
        double upper;
        public AxisInheritable(Joystick j, int axis, double lower, double upper){
            this.j = j;
            this.axis = axis;
            this.lower = lower;
            this.upper = upper;
        }
        public final boolean isPressed(){
            boolean b = j.getRawAxis(axis) >= lower && j.getRawAxis(axis) <= upper;
            return b;
        }
    }
    public double getLeftStickRotate() {
        
        return controller.getRawAxis(1);
    }
    public double getLeftStickMove() {
        if(isPS3){
            return -controller.getRawAxis(2);
        }else{
            return controller.getRawAxis(2);
        }
    }
    public double getTriggers() {
        if(!isPS3){
            return controller.getRawAxis(3);
        }
        return Math.PI;
    }
    public double getRightStickRotate() {
        return controller.getRawAxis(4);
    }
    public double getRightStickMove() {
        if(isPS3){
            return -controller.getRawAxis(4);
        }else{
            return controller.getRawAxis(5);
        }
    }
    public boolean a(){                   return a.isPressed                    ();}
    public boolean b(){                   return b.isPressed                    ();}
    public boolean x(){                   return x.isPressed                    ();}
    public boolean y(){                   return y.isPressed                    ();}
    public boolean start(){               return start.isPressed                ();}
    public boolean stop(){                return stop.isPressed                 ();}
    public boolean buttonLeftJoystick(){  return buttonLeftJoystick.isPressed   ();}
    public boolean buttonRightJoystick(){ return buttonRightJoystick.isPressed  ();}
    public boolean bumperLeft(){          return bumperLeft.isPressed           ();}
    public boolean bumperRight(){         return bumperRight.isPressed          ();}
    public boolean thumbPadRight(){       return thumbPadRight.isPressed        ();}
    public boolean thumbPadLeft(){        return thumbPadLeft.isPressed         ();}
    public boolean leftTrigger(){         return rightTrigger.isPressed         ();}
    public boolean rightTrigger(){        return leftTrigger.isPressed          ();}
//    public AdvancedButton searchByString(String s){
//        for(int i = 1; i < 25; i++){
//            try{
//                if(indexer.strings[i].equalsIgnoreCase(s)) return indexer.buttons[i];
//            }catch(NullPointerException ex){
//                System.out.println("ERROR : INVALID VALUE REGISTERED FOR A SEARCH VALUE33 : " + s);
//                return b;
//            }catch(ArrayIndexOutOfBoundsException ex){
//                System.out.println("ERROR : INVALID VALUE REGISTERED FOR A SEARCH VALUE44 : " + s);
//                return b;
//            }
//        }
//        return indexer.buttons[18];
//    }
    public void wipeCurrentCommands(){
//        for(int i = 1; i < 21; i++){
//            if(indexer.buttons[i] != null){
//                indexer.buttons[i].cancelCommands();
//                indexer.buttons[i].whileHeld(null);
//                indexer.buttons[i].whenActive(null);
//                indexer.buttons[i].whenInactive(null);
//            }
//        }
    }
    public void setControllerType(boolean toPS3){
        if(isPS3){
            this.isPS3 = true;
            thumbPadLeft.           setInheritable(pS3ThumbPadLeft);
            thumbPadRight.          setInheritable(pS3ThumbPadRight);
            thumbPadTop.            setInheritable(pS3ThumbPadTop);
            thumbPadBottom.         setInheritable(pS3ThumbPadBottom);
            leftTrigger.            setInheritable(pS3LeftTrigger);
            rightTrigger.           setInheritable(pS3RightTrigger); 
            a.                      setInheritable(pS3A);
            b.                      setInheritable(pS3B);
            stop.                   setInheritable(pS3Stop);
            start.                  setInheritable(pS3Start);
            buttonLeftJoystick.     setInheritable(pS3ButtonLeftJoystick);
            buttonRightJoystick.    setInheritable(pS3ButtonRightJoystick);
        }else{
            this.isPS3 = false;
            thumbPadLeft.           setInheritable(xBoxThumbPadLeft);
            thumbPadRight.          setInheritable(xBoxThumbPadRight);
            thumbPadTop.            setInheritable(nullInheritable);
            thumbPadBottom.         setInheritable(nullInheritable);
            leftTrigger.            setInheritable(xBoxLeftTrigger);
            rightTrigger.           setInheritable(xBoxRightTrigger); 
            a.                      setInheritable(xBoxA);
            b.                      setInheritable(xBoxB);
            stop.                   setInheritable(xBoxStop);
            start.                  setInheritable(xBoxStart);
            buttonLeftJoystick.     setInheritable(xBoxButtonLeftJoystick);
            buttonRightJoystick.    setInheritable(xBoxButtonRightJoystick);
        }
    }
    public void setButtonModes(int mode){
        for(int i = 1; i < 21; i++){
            if(indexer.buttons[i] != null){
                indexer.buttons[i].setMode(mode);
            }
        }
    }
    private class StringCollector{
        public String strings[];
        public AdvancedButton buttons[];
        public boolean enabled = false;
        public StringCollector(){
            start();
        }
        public void start(){
            if(!enabled){
              buttons = new AdvancedButton[21];
              strings = new String[21];
              //strings = new String[20];
              strings[1] = "A";
              buttons[1] = a;
              strings[2] = "B";
              buttons[2] = b;
              strings[3] = "x";
              buttons[3] = x;
              strings[4] = "Y";
              buttons[4] = y;
              strings[5] = "LEFT BUMPER";
              buttons[5] = bumperLeft;
              strings[6] = "RIGHT BUMPER";
              buttons[6] = bumperRight;
              strings[8] = "START";
              buttons[8] = start;
              strings[7] = "STOP";
              buttons[7] = stop;
              strings[9] = "Left Joystick";
              buttons[9] = buttonLeftJoystick;
              strings[10] = "Right Joystick";
              buttons[10] = buttonRightJoystick;
              strings[11] = "Thumb Right";
              buttons[11] = thumbPadRight;
              strings[12] = "Thumb Left";
              buttons[12] = thumbPadLeft;
              strings[13] ="Left Trigger";
              buttons[13] = leftTrigger;
              strings[14] = "Right Trigger";
              buttons[14] = rightTrigger;
               enabled = true;
            }
            
        }
    }
}
