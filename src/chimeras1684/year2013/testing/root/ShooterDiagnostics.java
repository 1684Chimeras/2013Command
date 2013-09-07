/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;

/**
 *
 * @author Arhowk
 */
public class ShooterDiagnostics extends Diagnostics.DiagnosticsInstance{
    public ShooterDiagnostics(){
        setDescription("Shooter Tilt : $NUMB", 1);
        setDescription("Shooter RPM : $NUMB", 2);
        setDescription("Shooter Encoder : $NUMB", 3);
    }
    protected double getAtLine(int i){
        if(i == 1){
            return subMap.shooter.calculateDeckTilt();
        }else if(i == 2){
//            return subMap.shooter.accumulatedTilt;
        }else if(i == 3){
//            return subMap.shooter.getScaledTiltEncoder();
        }
        return 0;
    }
}
