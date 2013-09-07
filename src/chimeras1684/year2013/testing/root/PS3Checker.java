/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 *
 * @author Arhowk
 */
public class PS3Checker extends SendableChooser{
    static Integer optionA;
    static Integer optionB;
    static boolean isInitialized = false;
    
    private static void init()
    {
        if(!isInitialized)
        {
            isInitialized = true;
            optionA = new Integer(1);
            optionB = new Integer(2);
        }
    }
    public PS3Checker(boolean isOperator){
        init();
        if(isOperator){
            addDefault("Operator Isn't PS3", optionB);
            addObject("Operator Is PS3", optionA);
        }else{
            addDefault("Driver Isn't PS3", optionB);
            addObject("Driver Is PS3", optionA);
        }
    }
    public boolean isPS3(){
        try{
             return getSelected() == optionA;
        }catch(Exception e){return false;}
    }
}
