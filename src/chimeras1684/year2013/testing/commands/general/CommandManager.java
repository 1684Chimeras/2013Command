/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.general;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 * @author Arhowk
 */
public class CommandManager {
    //<editor-fold desc="Conditions" defaultstate="collapsed">
    //<editor-fold desc="Base" defaultstate="collapsed">
    public static abstract class Condition
    {
        public abstract boolean get();
    }
        //</editor-fold>
    public static class EncoderCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        Encoder b;
        double min;
        double max;
        public EncoderCondition(Encoder a, double xmin, double xmax)
        {
            b=a;
            min=xmin;
            max=xmax;
        }
        public boolean get()
        {
            return b.get() > min && b.get() < max;
        }
        //</editor-fold>
    }
    public static class RelayCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        Relay b;
        Relay.Value v;
        public RelayCondition(Relay a, Relay.Value v)
        {
            b=a;
            this.v=v;
        }
        public boolean get()
        {
            return b.get() == v;
        }
        //</editor-fold>
    }
    public static class PneumaticCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        Solenoid b;
        boolean v;
        public PneumaticCondition(Solenoid a, boolean value)
        {
            b=a;
            v=value;
        }
        public boolean get()
        {
            return b.get() == v;
        }
        //</editor-fold>
    }
    public static class JoystickCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        Joystick b;
        int axis;
        double min;
        double max;
        public JoystickCondition(Joystick a, int axis, double min, double max)
        {
            b=a;
            this.axis = axis;
            this.min=min;
            this.max=max;
        }
        public boolean get()
        {
            return b.getRawAxis(axis) > min && b.getRawAxis(axis) < min;
        }
        //</editor-fold>
    }
    public static class JoystickButtonCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        Joystick b;
        int index;
        public JoystickButtonCondition(Joystick a, int button)
        {
            b=a;
            index=button;
        }
        public boolean get()
        {
            return b.getRawButton(index);
        }
        //</editor-fold>
    }
    public static class AnalogCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        AnalogChannel b;
        double min;
        double max;
        public AnalogCondition(int channel, double min, double max)
        {
            b=new AnalogChannel(channel);
            this.min=min;
            this.max=max;
        }
        public AnalogCondition(AnalogChannel a, double min, double max)
        {
            b=a;
            this.min=min;
            this.max=max;
        }
        public boolean get()
        {
            return b.getAverageVoltage()> min && b.getAverageVoltage()< max;
        }
        //</editor-fold>
    }
    public static class DigitalCondition extends Condition {
        //<editor-fold desc="Internal" defaultstate="collapsed">
        DigitalInput b;
        boolean value;
        public DigitalCondition(DigitalInput a, boolean is)
        {
            b=a;
            value=is;
        }
        public DigitalCondition(int channel, boolean is)
        {
            b=new DigitalInput(channel);
            value=is;
        }
        public boolean get()
        {
            return b.get() == value;
        }
        //</editor-fold>
    }
    public static class TriggerCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        Trigger t;
        public TriggerCondition(Trigger t){
            this.t = t;
        }
        public boolean get(){
            return t.get();
        }
        //</editor-fold>
    }
    public static class ModeCondition extends Condition{
        //<editor-fold desc="Internal" defaultstate="collapsed">
        boolean telop;
        boolean auton;
        boolean test;
        
        public ModeCondition(boolean telop, boolean auton, boolean test){
            this.telop = telop;
            this.auton = auton;
            this.test = test;
        }
        public boolean get(){
            return ((telop) ? DriverStation.getInstance().isOperatorControl() : true) &&
                    ((auton) ? DriverStation.getInstance().isAutonomous() : true)&&
                    ((test) ? DriverStation.getInstance().isTest() : true);
        }
        //</editor-fold>
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Internal" defaultstate="collapsed">
    public static class m_controller
    {
        boolean usesSpeedController;
        boolean usesRelay;
        boolean usesSolenoid;
        
        SpeedController scont;
        Relay relay;
        Solenoid solen;
        
        public m_controller(SpeedController s)
        {
            usesSpeedController = true;
            scont = s;
        }
        public m_controller(Relay s)
        {
            usesRelay = true;
            relay = s;
        }
        public m_controller(Solenoid s)
        {
            usesSolenoid = true;
            solen = s;
        }
        
        public void set(double value)
        {
            if(!usesSpeedController)
            {
                if(usesSolenoid)
                {
                    solen.set(value > 0);
                }else{
                    if(value == 0){
                        relay.set(Relay.Value.kOff);
                    }else if(value == 1){
                        relay.set(Relay.Value.kOn);
                    }else if(value > 0){
                        relay.set(Relay.Value.kForward);
                    }else if(value < 0){
                        relay.set(Relay.Value.kReverse);
                    }
                }
            }else{
                scont.set(value);
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="Main Command Handler" defaultstate="collapsed">
    public static class CommandHandler extends chimeras1684.year2013.testing.root.CommandBase
    {
        m_controller motor;
        double speed;
        Condition[] conditions;
        int condIndex = 0;
        InputType input;
        InputScaleType scale;
        protected CommandHandler(m_controller m, double speed)
        {
            this.speed = speed;
            motor = m;
            conditions = new Condition[20];
        }
        protected CommandHandler(m_controller m, InputType p){
            motor = m;
            input=p;
            conditions = new Condition[20];
        }
//        public CommandHandler(m_controller m);
//        {
//            this.i = i;
//            cont = m;
//            cond = new Condition[20];
//        }
        protected CommandHandler add(Condition c)
        {
            conditions[condIndex++] = c;
            return this;
        }
        protected CommandHandler add(InputScaleType c)
        {
            scale = c;
            return this;
        }
        protected CommandHandler add(InputType c)
        {
            input = c;
            return this;
        }
        public void run(double speed, boolean checkConditions)
        {
            int i = 0;
            boolean b = true;
            if(checkConditions){
                while(conditions[i] != null && !b){
                    b = conditions[i].get() && b;

                }
            }
            if(b) motor.set(speed);
        }
        protected void execute()
        {
            run((input == null) ? (scale == null ? speed : scale.scaleInput(speed)) : (scale == null ? input.getInput() : scale.scaleInput(input.getInput())), true);
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="Commands" defaultstate="collapsed">
    public static CommandHandler solenoid(Solenoid s, boolean setTo)
    {
        return new CommandHandler(new m_controller(s),setTo ? 1 : -1);
    }
    public static CommandHandler motor(SpeedController m, double value)
    {
        return new CommandHandler(new m_controller(m),value);
    }
    public static CommandHandler relay(Relay r, Relay.Value v)
    {
        return new CommandHandler(new m_controller(r), v == Relay.Value.kOn ? 1 : -1);
    }
    public static CommandHandler solenoid(Solenoid s, InputType i)
    {
        return new CommandHandler(new m_controller(s),i);
    }
    public static CommandHandler motor(SpeedController m, InputType i)
    {
        return new CommandHandler(new m_controller(m),i);
    }
    public static CommandHandler relay(Relay r, InputType i)
    {
        return new CommandHandler(new m_controller(r), i);
    }
    public static CommandHandler solenoid(int channel, boolean setTo)
    {
        return new CommandHandler(new m_controller(new Solenoid(channel)),setTo ? 1 : -1);
    }
    public static CommandHandler motor(int channel, double value)
    {
        return new CommandHandler(new m_controller(new Jaguar(channel)),value);
    }
    public static CommandHandler relay(int channel, Relay.Value v)
    {
        return new CommandHandler(new m_controller(new Relay(channel)), v == Relay.Value.kOn ? 1 : -1);
    }
    public static CommandHandler solenoid(int channel, InputType i)
    {
        return new CommandHandler(new m_controller(new Solenoid(channel)),i);
    }
    public static CommandHandler motor(int channel, InputType i)
    {
        return new CommandHandler(new m_controller(new Jaguar(channel)),i);
    }
    public static CommandHandler relay(int channel, InputType i)
    {
        return new CommandHandler(new m_controller(new Relay(channel)), i);
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Creation" defaultstate="collapsed">
    public static Condition c_relay(Relay R, Relay.Value v)
    {
        return new RelayCondition(R,v);
    }
    public static Condition c_encoder(Encoder e, double min, double max)
    {
        return new EncoderCondition(e,min,max);
    }
    public static Condition c_joystick(Joystick j, int axis, double min, double max)
    {
        return new JoystickCondition(j,axis,min,max);
    }
    public static Condition c_joystickButton(Joystick j, int button)
    {
        return new JoystickButtonCondition(j,button);
    }
    public static Condition c_analog(AnalogChannel a, double min, double max)
    {
        return new AnalogCondition(a,min,max);
    }
    public static Condition c_digital(DigitalInput i)
    {
        return new DigitalCondition(i,true);
    }
    public static Condition c_relay(int channel, Relay.Value v)
    {
        return new RelayCondition(new Relay(channel),v);
    }
    public static Condition c_encoder(int channel, double min, double max)
    {
        return new EncoderCondition(new Encoder(channel,channel),min,max);
    }
    public static Condition c_joystick(int channel, int axis, double min, double max)
    {
        return new JoystickCondition(new Joystick(channel),axis,min,max);
    }
    public static Condition c_joystickButton(int channel, int button)
    {
        return new JoystickButtonCondition(new Joystick(channel),button);
    }
    public static Condition c_analog(int channel, double min, double max)
    {
        return new AnalogCondition(new AnalogChannel(channel),min,max);
    }
    public static Condition c_digital(int channel)
    {
        return new DigitalCondition(new DigitalInput(channel),true);
    }
    public static Condition c_mode(boolean telop, boolean auton, boolean test)
    {
        return new ModeCondition(telop, auton, test);
    }
    public static Condition c_trigger(Trigger t)
    {
        return new TriggerCondition(t);
    }
    
    public static InputType i_relay(Relay r){
        return new RelayInput(r);
    }
    public static InputType i_digital(DigitalInput i){
        return new DigitalIn(i);
    }
    public static InputType i_encoder(Encoder a, boolean usesDistance){
        return new EncoderInput(a,usesDistance);
    }
    public static InputType i_pneumatic(Solenoid a){
        return new PneumaticInput(a);
    }
    public static InputType i_joystick(Joystick j, int axis){
        return new JoystickInput(j, axis);
    }
    public static InputType i_button(Joystick j, int button){
        return new ButtonInput(j, button);
    }
    public static InputType i_analog(AnalogChannel a, boolean usesVoltage){
        return new AnalogInput(a,usesVoltage);
    }
    
    public static InputType i_relay(int channel){
        return new RelayInput(new Relay(channel));
    }
    public static InputType i_digital(int channel){
        return new DigitalIn(new DigitalInput(channel));
    }
    public static InputType i_encoder(int channel, boolean usesDistance){
        return new EncoderInput(new Encoder(channel,channel),usesDistance);
    }
    public static InputType i_pneumatic(int channel){
        return new PneumaticInput(new Solenoid(channel));
    }
    public static InputType i_joystick(int channel, int axis){
        return new JoystickInput(new Joystick(channel), axis);
    }
    public static InputType i_button(int channel, int button){
        return new ButtonInput(new Joystick(channel), button);
    }
    public static InputType i_analog(int channel, boolean usesVoltage){
        return new AnalogInput(new AnalogChannel(channel),usesVoltage);
    }
    //</editor-fold> 
    
    //<editor-fold desc="Input" defaultstate="collapsed">
    public static abstract class InputScaleType{
        public abstract double scaleInput(double input);
    }
    public static abstract class InputType{
        public abstract double getInput();
    }
    public static class FlatInputScale extends InputScaleType{
        double scale;
        public FlatInputScale(double scale){
            this.scale = scale;
        }
        public double scaleInput(double input) {
            return input*scale;
        }
        
    }
    
    public static class RelayInput extends InputType{
        Relay r;
        public RelayInput(Relay r){
            this.r = r;
        }
        public double getInput(){
            return (r.get() == Relay.Value.kForward) ? 1 : ((r.get() == Relay.Value.kReverse) ? -1 : 0);
        }
    }
    public static class DigitalIn extends InputType{
        DigitalInput r;
        public DigitalIn(DigitalInput r){
            this.r = r;
        }
        public double getInput(){
            return (r.get()) ? 1 : 0;
        }
    }
    public static class EncoderInput extends InputType{
        Encoder a;
        boolean usesDistance;
        public EncoderInput(Encoder a, boolean usesDistance){
            this.a = a;
        }
        public double getInput(){
            return (usesDistance) ? a.getDistance() : a.get();
        }
    }
    public static class PneumaticInput extends InputType{
        Solenoid a;
        boolean usesDistance;
        boolean usesVoltage;
        public PneumaticInput(Solenoid a){
            this.a = a;
        }
        public double getInput(){
            return a.get() ? 1 : 0;
        }
    }
    public static class JoystickInput extends InputType{
        Joystick j;
        int axis;
        public JoystickInput(Joystick j, int axis){
            this.j = j;
            this.axis = axis;
        }
        public double getInput(){
            
            return j.getRawAxis(axis);
        }
    }
    public static class ButtonInput extends InputType{
        Joystick j;
        int button;
        public ButtonInput(Joystick j, int button){
            this.j = j;
            this.button = button;
        }
        public double getInput(){
            return j.getRawButton(button) ? 1 : 0;
        }
    }
    public static class AnalogInput extends InputType
    {
        AnalogChannel b;
        boolean usesVoltage;
        public AnalogInput(AnalogChannel a, boolean usesVoltage)
        {
            b=a;
            this.usesVoltage = usesVoltage;
        }
        public double getInput()
        {
            return (usesVoltage) ? b.getAverageVoltage() : b.getValue();
        }
    }
    //</editor-fold>
    
    public static void hello(){
        CommandHandler c = motor(1, 0.5);
        c.add(c_analog(1, 1, -1));
        c.add(i_analog(2, false));
    }
}
