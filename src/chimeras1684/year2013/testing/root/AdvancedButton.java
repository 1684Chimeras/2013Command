package chimeras1684.year2013.testing.root;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.Random;

/*
 * @author Arhowk
 */
public class AdvancedButton extends Button{
    protected Joystick m_joystick;
    protected int m_buttonNumber;
    boolean pressed;
    protected boolean m_wasPressed;
    private Command dummy;
    protected boolean active = false;
    protected Command m_whileHeld[];
    protected Command m_whenPressed[];
    protected Command m_whenReleased[];
    protected Command m_togglePressed[];
    protected Command m_toggleReleased[];
    protected Command m_whileReleased[];
    
    private int numberOfModes;
    
    private int currentMode;
    
    private Inheritable currentInheritable;
    public void inherit(Joystick j, int buttonNumber){
        m_joystick = j;
        m_buttonNumber = buttonNumber;
    }
    public AdvancedButton(Joystick joystick, int buttonNumber, int numberOfModes) {
        m_joystick = joystick;
        m_buttonNumber = buttonNumber;
        m_whileHeld = new Command[numberOfModes+1];
        m_whenPressed = new Command[numberOfModes+1];
        m_whenReleased = new Command[numberOfModes+1];
        m_togglePressed = new Command[numberOfModes+1];
        m_toggleReleased = new Command[numberOfModes+1];
        m_whileReleased = new Command[numberOfModes+1];
        this.numberOfModes = numberOfModes + 1;
        currentMode = 0;
        initDummy();
    }
    public static abstract class Inheritable{
        public abstract boolean isPressed();
    }
//    protected AdvancedButton(){
//        initDummy();
//    }
    public void setInheritable(Inheritable i){
        currentInheritable = i;
    }
    protected boolean isPressed(){
        return m_joystick.getRawButton(m_buttonNumber);
    }
    
    public void setMode(int mode){
        if(mode >= numberOfModes){
            throw new IndexOutOfBoundsException("Mode cannot be greater than or equal to the max mode for this button");
        }else if(mode < 0){
            throw new IndexOutOfBoundsException("Mode cannot be less than 0");
        }
        currentMode = mode;
    }
    private void initDummy(){
        dummy = new Command(){
            protected void initialize() {}
            protected void execute() {
                if(currentInheritable != null){
                    pressed = currentInheritable.isPressed();
                }else{
                    pressed = isPressed();  
                }
//                currentMode = 0;
                if(DriverStation.getInstance().isOperatorControl() || DriverStation.getInstance().isTest()){
                    Random r = new Random();
                    if(m_whileHeld[currentMode] != null){
                        if(!m_whileHeld[currentMode].isRunning() && pressed){
                            System.out.println("whileheld start");
                            m_whileHeld[currentMode].start();
                        }else if(m_whileHeld[currentMode].isRunning() && !pressed){
                            m_whileHeld[currentMode].cancel();
                        }
                    }
                    if(m_whileReleased[currentMode] != null){
                        if(m_whileReleased[currentMode].isRunning() && pressed){
                            m_whileReleased[currentMode].cancel();
                        }else if(!m_whileReleased[currentMode].isRunning() && !pressed){
                            m_whileReleased[currentMode].start();
                        }
                    }
                    if(pressed && !m_wasPressed){
                        System.out.println("pressed");
                        if(m_whenPressed[currentMode] != null){
                            System.out.println("start");
                            m_whenPressed[currentMode].start();
                        }
                        if(m_togglePressed[currentMode] != null){
                            System.out.println("toggle isnt null");
                            if(m_togglePressed[currentMode].isRunning()){
                                System.out.println("cancel toggle");
                                m_togglePressed[currentMode].cancel();
                            }else{
                                System.out.println("start toggle");
                                m_togglePressed[currentMode].start();
                            }
                        }
                    }else if(!pressed && m_wasPressed){
                        if(m_whenReleased[currentMode] != null){
                            m_whenReleased[currentMode].start();
                        }

                        if(m_toggleReleased[currentMode] != null){
                            if(m_toggleReleased[currentMode].isRunning()){
                                m_toggleReleased[currentMode].cancel();
                            }else{
                                m_toggleReleased[currentMode].start();
                            }
                        }
                    }
                    m_wasPressed = pressed;
                }
            }

            protected boolean isFinished() {;return false;}
            protected void end() {
                System.out.println("5");}
            protected void interrupted() {
                System.out.println("6");}
        };
        dummy.start();
        System.out.println("started dummy");
        pressed = false;
        m_wasPressed = false;
    }
    /*
     * @deprecated Use something else instead
     */
    public boolean get(){return false;}
    public void whileHeld(final Command command, int mode){
        if(command == null){
            return;
        }
        if(m_whileHeld[mode] != null){
            m_whileHeld[mode].cancel();
        }
        m_whileHeld[mode] = command;
        
    }
    public void whenPressed(final Command command, int mode){
        if(m_whenPressed[mode] != null){
            m_whenPressed[mode].cancel();
        }
        m_whenPressed[mode] = command;
    }
    public void whenReleased(final Command command, int mode){
        if(m_whenReleased[mode] != null){
            m_whenReleased[mode].cancel();
        }
        m_whenReleased[mode] = command;
    }
    public void whileActive(final Command command, int mode){
        if(command == null){
            return;
        }
        if(m_whileHeld[mode] != null){
            m_whileHeld[mode].cancel();
        }
        m_whileHeld[mode] = command;
    }
    public void whenActive(final Command command, int mode){
        if(m_whenPressed[mode] != null){
            m_whenPressed[mode].cancel();
        }
        m_whenPressed[mode] = command;
    }
    public void whenInactive(final Command command, int mode){
        if(m_whenReleased[mode] != null){
            m_whenReleased[mode].cancel();
        }
        m_whenReleased[mode] = command;
    }
    public void whileReleased(final Command command, int mode){
        if(m_whileReleased[mode] != null){
            m_whileReleased[mode].cancel();
        }
        m_whileReleased[mode] = command;
    }
    public void togglePressed(final Command command, int mode){
        if(m_togglePressed[mode] != null){
            m_togglePressed[mode].cancel();
        }
        m_togglePressed[mode] = command;
    }
    public void toggleReleased(final Command command, int mode){
        if(m_toggleReleased[mode] != null){
            m_toggleReleased[mode].cancel();
        }
        m_toggleReleased[mode] = command;
    }
    public void cancelCommands(int mode){
        if(m_togglePressed[mode] != null){
            m_togglePressed[mode].cancel();
        }
        if(m_toggleReleased[mode] != null){
            m_toggleReleased[mode].cancel();
        }
        if(m_whenPressed[mode] != null){
            m_whenPressed[mode].cancel();
        }
        if(m_whenReleased[mode] != null){
            m_whenReleased[mode].cancel();
        }
        if(m_whileHeld[mode] != null){
            m_whileHeld[mode].cancel();
        }
        if(m_whileReleased[mode] != null){
            m_whileReleased[mode].cancel();
        }
    }
    public void whileHeld(final Command command){
        if(command == null){
            return;
        }
        if(m_whileHeld[0] != null){
            m_whileHeld[0].cancel();
        }
        m_whileHeld[0] = command;
        
    }
    public void whenPressed(final Command command){
        if(m_whenPressed[0] != null){
            m_whenPressed[0].cancel();
        }
        m_whenPressed[0] = command;
    }
    public void whenReleased(final Command command){
        if(m_whenReleased[0] != null){
            m_whenReleased[0].cancel();
        }
        m_whenReleased[0] = command;
    }
    public void whileActive(final Command command){
        if(command == null){
            return;
        }
        if(m_whileHeld[0] != null){
            m_whileHeld[0].cancel();
        }
        m_whileHeld[0] = command;
    }
    public void whenActive(final Command command){
        if(m_whenPressed[0] != null){
            m_whenPressed[0].cancel();
        }
        m_whenPressed[0] = command;
    }
    public void whenInactive(final Command command){
        if(m_whenReleased[0] != null){
            m_whenReleased[0].cancel();
        }
        m_whenReleased[0] = command;
    }
    public void whileReleased(final Command command){
        if(m_whileReleased[0] != null){
            m_whileReleased[0].cancel();
        }
        m_whileReleased[0] = command;
    }
    public void togglePressed(final Command command){
        if(m_togglePressed[0] != null){
            m_togglePressed[0].cancel();
        }
        m_togglePressed[0] = command;
    }
    public void toggleReleased(final Command command){
        if(m_toggleReleased[0] != null){
            m_toggleReleased[0].cancel();
        }
        m_toggleReleased[0] = command;
    }
    public void cancelCommands(){
        if(m_togglePressed[0] != null){
            m_togglePressed[0].cancel();
        }
        if(m_toggleReleased[0] != null){
            m_toggleReleased[0].cancel();
        }
        if(m_whenPressed[0] != null){
            m_whenPressed[0].cancel();
        }
        if(m_whenReleased[0] != null){
            m_whenReleased[0].cancel();
        }
        if(m_whileHeld[0] != null){
            m_whileHeld[0].cancel();
        }
        if(m_whileReleased[0] != null){
            m_whileReleased[0].cancel();
        }
    }
}
