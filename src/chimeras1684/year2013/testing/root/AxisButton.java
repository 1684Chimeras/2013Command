/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Arhowk
 */
public class AxisButton extends AdvancedButton{
    protected double m_lowerBound;
    protected double m_upperBound;
    protected int m_axisNumber;
    protected Joystick m_joystick;
    public AxisButton(Joystick joystick, int axisNumber, double lowerBound, double upperBound) {
        super(joystick,axisNumber, 1);
        m_joystick = joystick;
        m_lowerBound = lowerBound;
        m_upperBound = upperBound;
        m_axisNumber = axisNumber;
    }
    public boolean isPressed(){
        return m_joystick.getRawAxis(m_axisNumber) >= m_lowerBound && m_joystick.getRawAxis(m_axisNumber) <= m_upperBound;
    }
}
