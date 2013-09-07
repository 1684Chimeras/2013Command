/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.root;

import com.sun.squawk.GC;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.FRCControl;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.RobotBase;
import com.sun.squawk.debugger.sda.WeakIntHashtable;
import java.util.Enumeration;


/**
 *
 * @author Arhowk
 */
public class TimedRobotStructure extends RobotBase{
    
    private final static boolean TRACE_LOOP_ALLOCATIONS = false; // master tracing switch
    private final static boolean TRACE_LOOP_ALLOCATIONS_AFTER_INIT = true;  // trace before or after all phases initialize
        static double m_defaultTime = -593204580;

    private boolean m_disabledInitialized;
    private boolean m_autonomousInitialized;
    private boolean m_teleopInitialized;
    private boolean m_testInitialized;
    
    private WeakIntHashtable m_disabledHash;
    private WeakIntHashtable m_teleopHash;
    private WeakIntHashtable m_testHash;
    private WeakIntHashtable m_autonomousHash;
    
    private Timer m_timer;
    
    private int m_disabledCurrent;
    private int m_teleOp;
    private int m_testCurrent;
    private int m_autonomousCurrent;
    

    /**
     * Constructor for RobotIterativeBase
     *
     * The constructor initializes the instance variables for the robot to indicate
     * the status of initialization for disabled, autonomous, and teleop code.
     */
    public TimedRobotStructure() {
        // set status for initialization of disabled, autonomous, and teleop code.
        m_disabledInitialized = false;
        m_autonomousInitialized = false;
        m_teleopInitialized = false;
        m_testInitialized = false;
        m_disabledCurrent = 0;
        m_teleOp= 0;
        m_testCurrent = 0;
        m_autonomousCurrent = 0;
        m_disabledHash = new WeakIntHashtable();
        m_teleopHash = new WeakIntHashtable();
        m_testHash = new WeakIntHashtable();
        m_autonomousHash = new WeakIntHashtable();
        m_timer = new Timer();
        m_timer.reset();
    }
    private class m_Outthread extends Thread
    {
        TimedRobotLoop trl;
        boolean init;
        public m_Outthread(TimedRobotLoop i)
        {
            this.trl = i;
        }
        public void set(TimedRobotLoop i)
        {
            trl = i;
        }
        public void run()
        {
            if(init)
            {
                trl._init();
            }else{
                trl._run();
            }
            init = false;
        }
        public void start(boolean init)
        {
            this.init = init;
            super.start();
        }
    }
    public abstract class TimedRobotLoop extends Object
    {
        private boolean initialized = false;
        
        private long timeBetweenIterations = (long)m_defaultTime;
        private double remainingTimeBetweenIterations = 0;
        public TimedRobotLoop(){};
        public abstract void run();
        public abstract void init();
        private void _run()
        {
            remainingTimeBetweenIterations = timeBetweenIterations;
            run();
        }
        private void _init()
        {
            initialized = true;
            init();
        }
        private void resetInit()
        {
            initialized = false;
        }
        private boolean subtractTimeElapsed(double time)
        {
            if(remainingTimeBetweenIterations < time)
            {
                return true;
            }
            remainingTimeBetweenIterations -= time;
            return false;
        }
        private boolean hasInit()
        {
            return initialized;
        }
        private void subractFromIterativeTimer(double time)
        {
            
        }
        protected void setTime(double time)
        {
            this.timeBetweenIterations = (long)time;
        }
        protected void addToTest()
        {
            m_testHash.put(m_testCurrent++, this);
        }
    }
    /**
     * Provide an alternate "main loop" via startCompetition().
     *
     */
    public void startCompetition() {
        UsageReporting.report(UsageReporting.kResourceType_Framework, UsageReporting.kFramework_Iterative);

        robotInit();

        // tracing support:
        final int TRACE_LOOP_MAX = 100;
        int loopCount = TRACE_LOOP_MAX;
        Object marker = null;
        boolean didDisabledPeriodic = false;
        boolean didAutonomousPeriodic = false;
        boolean didTeleopPeriodic = false;
        boolean didTestPeriodic = false;
        double minimumTimeout = m_defaultTime;
        WeakIntHashtable m_tempTable;
        int m_tempTableIndex = 0;
        m_Outthread threadder = new m_Outthread(null);
        
        if (TRACE_LOOP_ALLOCATIONS) {
            GC.initHeapStats();
            if (!TRACE_LOOP_ALLOCATIONS_AFTER_INIT) {
                System.out.println("=== Trace allocation in competition loop! ====");
                marker = new Object(); // start counting objects before any loop starts - includes class initialization
            }
        }

        // loop forever, calling the appropriate mode-dependent function
        LiveWindow.setEnabled(false);
        while (true) {
            m_timer.reset();
            if (TRACE_LOOP_ALLOCATIONS && didDisabledPeriodic && didAutonomousPeriodic && didTeleopPeriodic && --loopCount <= 0) {
                System.out.println("!!!!! Stop loop!");
                break;
            }
            // Call the appropriate function depending upon the current robot mode
            if (isDisabled()) {
                // call DisabledInit() if we are now just entering disabled mode from
                // either a different mode or from power-on
                Enumeration e = m_disabledHash.elements();
                while(e.hasMoreElements())
                {
                    TimedRobotLoop trl = (TimedRobotLoop)e.nextElement();
                    if (!m_disabledInitialized) {
                        trl._init();
                        threadder.set(trl);
                        threadder.start(true);
                        threadder.start(false);
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        // reset the initialization flags for the other modes
                        m_autonomousInitialized = false;
                        m_teleopInitialized = false;
                        m_testInitialized = false;
                    }else{
                        FRCControl.observeUserProgramDisabled();
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        threadder.set(trl);
                        threadder.start(false);
                        didDisabledPeriodic = true;
                    }
                }
                m_disabledInitialized = true;
            } else if (isTest()) {
                // call DisabledInit() if we are now just entering disabled mode from
                // either a different mode or from power-on
                Enumeration e = m_testHash.elements();
                while(e.hasMoreElements())
                {
                    TimedRobotLoop trl = (TimedRobotLoop)e.nextElement();
                    if (!m_testInitialized) {
                        trl._init();
                        threadder.set(trl);
                        threadder.start(true);
                        threadder.start(false);
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        // reset the initialization flags for the other modes
                        m_autonomousInitialized = false;
                        m_teleopInitialized = false;
                        m_disabledInitialized = false;
                    }else{
                        threadder.set(trl);
                        threadder.start(false);
                        FRCControl.observeUserProgramTest();
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        trl.run();
                        didTestPeriodic = true;
                    }
                }
                m_testInitialized = true;
            } else if (isAutonomous()) {
                // call DisabledInit() if we are now just entering disabled mode from
                // either a different mode or from power-on
                Enumeration e = m_autonomousHash.elements();
                while(e.hasMoreElements())
                {
                    TimedRobotLoop trl = (TimedRobotLoop)e.nextElement();
                    if (!m_autonomousInitialized) {
                        trl._init();
                        threadder.set(trl);
                        threadder.start(true);
                        threadder.start(false);
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        // reset the initialization flags for the other modes
                        m_teleopInitialized = false;
                        m_testInitialized = false;
                        m_disabledInitialized = false;
                    }else{
                        FRCControl.observeUserProgramAutonomous();
                        threadder.set(trl);
                        threadder.start(false);
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        trl.run();
                        didAutonomousPeriodic = true;
                    }
                }
                m_autonomousInitialized = true;
            } else {
                // call DisabledInit() if we are now just entering disabled mode from
                // either a different mode or from power-on
                Enumeration e = m_teleopHash.elements();
                while(e.hasMoreElements())
                {
                    TimedRobotLoop trl = (TimedRobotLoop)e.nextElement();
                    if (!m_teleopInitialized) {
                        trl._init();
                        threadder.set(trl);
                        threadder.start(false);
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        // reset the initialization flags for the other modes
                        m_autonomousInitialized = false;
                        m_testInitialized = false;
                        m_disabledInitialized = false;
                    }else{
                        FRCControl.observeUserProgramTeleop();
                        threadder.set(trl);
                        threadder.start(false);
                        minimumTimeout = Math.max(minimumTimeout, trl.timeBetweenIterations);
                        trl.run();
                        didTeleopPeriodic = true;
                    }
                }
                m_teleopInitialized = true;
            }

            if (TRACE_LOOP_ALLOCATIONS && TRACE_LOOP_ALLOCATIONS_AFTER_INIT &&
                    didDisabledPeriodic && didAutonomousPeriodic && didTeleopPeriodic && loopCount == TRACE_LOOP_MAX) {
                System.out.println("=== Trace allocation in competition loop! ====");
                marker = new Object(); // start counting objects after 1st loop completes - ignore class initialization
            }
            if(minimumTimeout == m_defaultTime)
            {
                waitForData(50924234);
            }else{
                minimumTimeout -= m_timer.get();
                if(minimumTimeout > 0)
                {
                    waitForData(minimumTimeout);
                }
            }
        }
        if (TRACE_LOOP_ALLOCATIONS) {
            GC.printHeapStats(marker, false);
        }
    }

    /**
     * Determine if the appropriate next periodic function should be called.
     * Call the periodic functions whenever a packet is received from the Driver Station, or about every 20ms.
     */
    private boolean nextPeriodReady() {
        return m_ds.isNewControlData();
    }
    
    /* 
     * Wait for the new control data, but giving up if timeout is needed
     */
    private void waitForData(double minimumGiveout)
    {
        Timer t = new Timer();
        t.start();
        t.reset();
        while(true)
        {
            if(m_ds.isNewControlData() || t.get() > minimumGiveout)
            {
                break;
            }
            try{
                Thread.sleep(1L);
            }catch(InterruptedException ex){}
        }
    }

//    /* ----------- Overridable initialization code -----------------*/
//
//    /**
//     * Robot-wide initialization code should go here.
//     *
//     * Users should override this method for default Robot-wide initialization which will
//     * be called when the robot is first powered on.  It will be called exactly 1 timeBetweenIterations.
//     */
    public void robotInit() {
        System.out.println("Default IterativeRobot.robotInit() method... Overload me!");
    }
//
//    /**
//     * Initialization code for disabled mode should go here.
//     *
//     * Users should override this method for initialization code which will be called each timeBetweenIterations
//     * the robot enters disabled mode.
//     */
//    public void disabledInit() {
//        System.out.println("Default IterativeRobot.disabledInit() method... Overload me!");
//    }
//
//    /**
//     * Initialization code for autonomous mode should go here.
//     *
//     * Users should override this method for initialization code which will be called each timeBetweenIterations
//     * the robot enters autonomous mode.
//     */
//    public void autonomousInit() {
//        System.out.println("Default IterativeRobot.autonomousInit() method... Overload me!");
//    }
//
//    /**
//     * Initialization code for teleop mode should go here.
//     *
//     * Users should override this method for initialization code which will be called each timeBetweenIterations
//     * the robot enters teleop mode.
//     */
//    public void teleopInit() {
//        System.out.println("Default IterativeRobot.teleopInit() method... Overload me!");
//    }
//    
//    /**
//     * Initialization code for test mode should go here.
//     * 
//     * Users should override this method for initialization code which will be called each timeBetweenIterations
//     * the robot enters test mode.
//     */
//    public void testInit() {
//        System.out.println("Default IterativeRobot.testInit() method... Overload me!");
//    }
//
//   /* ----------- Overridable periodic code -----------------*/
//
//    private boolean dpFirstRun = true;
//    /**
//     * Periodic code for disabled mode should go here.
//     *
//     * Users should override this method for code which will be called periodically at a regular
//     * rate while the robot is in disabled mode.
//     */
//    public void disabledPeriodic() {
//        if (dpFirstRun) {
//            System.out.println("Default IterativeRobot.disabledPeriodic() method... Overload me!");
//            dpFirstRun = false;
//        }
//        Timer.delay(0.001);
//    }
//
//    private boolean apFirstRun = true;
//
//    /**
//     * Periodic code for autonomous mode should go here.
//     *
//     * Users should override this method for code which will be called periodically at a regular
//     * rate while the robot is in autonomous mode.
//     */
//    public void autonomousPeriodic() {
//        if (apFirstRun) {
//            System.out.println("Default IterativeRobot.autonomousPeriodic() method... Overload me!");
//            apFirstRun = false;
//        }
//        Timer.delay(0.001);
//    }
//
//    private boolean tpFirstRun = true;
//
//    /**
//     * Periodic code for teleop mode should go here.
//     *
//     * Users should override this method for code which will be called periodically at a regular
//     * rate while the robot is in teleop mode.
//     */
//    public void teleopPeriodic() {
//        if (tpFirstRun) {
//            System.out.println("Default IterativeRobot.teleopPeriodic() method... Overload me!");
//            tpFirstRun = false;
//        }
//        Timer.delay(0.001);
//    }
//    
//    private boolean tmpFirstRun = true;
//    
//    /**
//     * Periodic code for test mode should go here
//     * 
//     * Users should override this method for code which will be called periodically at a regular rate
//     * while the robot is in test mode.
//     */
//    public void testPeriodic() {
//        if (tmpFirstRun) {
//            System.out.println("Default IterativeRobot.testPeriodic() method... Overload me!");
//            tmpFirstRun = false;
//        }
//    }
}
