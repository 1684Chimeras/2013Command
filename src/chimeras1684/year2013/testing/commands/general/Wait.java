/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.general;

import chimeras1684.year2013.testing.root.CommandBase;


/**
 *
 * @author Arhowk
 */
public class Wait extends CommandBase {
    public Wait(double timeout) {
        setTimeout(timeout);
    }
}
