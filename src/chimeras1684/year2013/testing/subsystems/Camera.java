//
//package chimeras1684.year2013.testing.subsystems;
//
//import chimeras1684.year2013.testing.commands.general.DriveTrainCommands;
//import chimeras1684.year2013.testing.root.MathExtra;
//import edu.wpi.first.wpilibj.camera.AxisCamera;
//import edu.wpi.first.wpilibj.camera.AxisCameraException;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.image.BinaryImage;
//import edu.wpi.first.wpilibj.image.ColorImage;
//import edu.wpi.first.wpilibj.image.CriteriaCollection;
//import edu.wpi.first.wpilibj.image.NIVision;
//import edu.wpi.first.wpilibj.image.NIVisionException;
//import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
//
///**
// * @author Arhowk
// */
//public class Camera extends SubsystemBase{
//    
//    AxisCamera axisCamera;      // the axis axisCamera object (connected to the switch)
//    CriteriaCollection cc;      // the criteria for doing the particle filter operation
//    private static final double boundingError = 20;
//    private static final double smallScoreRatio = 0;
//    private int tick = 0;
//    private int lastError = -1;
//    private int tickCount = 0;
//    private static Camera instance;
//    private double offBoardCount;
//    private DriveTrainCommands.Spin spin;
//    
//    public void initDefaultCommand(){}
//    public static Camera getInstance(){
//        instance = (instance == null) ? new Camera() : instance;
//        return instance;
//    }
//    private Camera() {
//        instance = (instance == null) ? this : instance;
//        offBoardCount = 0;
//     //   axisCamera = AxisCamera.getInstance();  // get an instance ofthe axisCamera
//     //   axisCamera.writeResolution(AxisCamera.ResolutionT.k160x120);
//      //  subMap.subMap.driveTrain = drive;
//        
//        cc = new CriteriaCollection();      // create the criteria for the particle filter
//        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 0, 400, false);
//        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 0, 400, false); 
//        spin = new DriveTrainCommands.Spin(0, 15, true);
//     //   axisCamera.writeBrightness(50);
//     //   axisCamera.writeColorLevel(100);
//    }
//    public void autonomous(boolean horizontal, boolean vertical){
//        autonomousWithHTML(horizontal,vertical);
//    }
//    public void autonomousWithRoboRealm(boolean rotateRobot, boolean horizontal, boolean vertical){
//        try{
//            double d[] = MathExtra.stringToDoubleArray(SmartDashboard.getString("BFR"));
//            if(rotateRobot){
//                System.out.println("d3 : " + d[3]);
//                System.out.println("d7 : " + d[7]);
//                System.out.println("combined : " + ((d[3] + d[7]) / 2));
//                forceTowardsVector(320,(int)(((d[2] + d[6]) / 2) - 160), 8, horizontal, vertical, (int)((d[3] + d[7]) / 2) - 120, 240);
//            }
//        }catch(TableKeyNotDefinedException ex){
//     //      autonomousWithoutRoboRealm();
//        }
//        
//    }
//    public void autonomousWithHTML(boolean horizontal, boolean vertical){
//        try{
////            double xerror = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterTableXError));
////            double xwidth = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterTableXWidth));
////            double yerror = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterTableXError));
////            double yheight = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterTableYHeight));
////            forceTowardsVector((int) xwidth, (int) (xerror), 8, horizontal, vertical, yerror, yheight);
//            double tilt = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterTiltError));
//            double rotation = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterRotation));
//            double offboardcount = Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterOffBoardCount));
//            if(offboardcount != offBoardCount){
//                autonomousWithRoboRealm(true, horizontal, vertical);
//            }else{
//                spin.setSpin(rotation);
//                if(!spin.isRunning()){
//                    spin.start();
//                }
//                subMap.shooter.setDeckTilt(subMap.shooter.calculateDeckTilt() + tilt);
//            }
//        }catch(TableKeyNotDefinedException ex){
//            autonomousWithRoboRealm(true, horizontal, vertical);
//        }
//    }
//    public double getHTMLData(int index){
//        try{
//            if(index == 1){
//                return Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterTiltError));
//            }else if(index == 2){
//                return Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterRotation));
//            }else if(index == 3){
//                return Double.parseDouble(subMap.targeterTableIn.getString(stringMap.targeterOffBoardCount));
//            }
//            return 0;
//        }catch(TableKeyNotDefinedException ex){
//            return 0;
//        }
//    }
//    private boolean forceTowardsVector(int xwidth, int xerror, double errorLimit, boolean horizontal, boolean vertical, double yerror, double ywidth){
//        System.out.println(xerror);
//        int tempError = xwidth;
//        if(lastError != -1){
//            xwidth = (xwidth + lastError) / 2;
//        }
//        lastError = tempError;
//        if(horizontal){
//            if(xerror < 1){
//                //Take the X error
//                //Times it by the proportinal constant on the dashboard
//                //Divide it by thr width to obtain the proportion for the motors
//                //Take the square root of that to bump the curve up
//                //Limit the motors to 0.4 to stop the motors from going too heavily
////                System.out.println("-xerror : " + -xerror);
////                System.out.println("after factored : " + (-xerror * (SmartDashboard.getNumber("P - X Error"))));
////                System.out.println("div width : " + ((-xerror * (SmartDashboard.getNumber("P - X Error")))*-1) / xwidth);
////                System.out.println("squared : " + (Math.sqrt((-xerror * (SmartDashboard.getNumber("P - X Error"))) / xwidth) *-1));
////                System.out.println("final : " + -MathExtra.limit((/*Math.sqrt*/((-xerror * (SmartDashboard.getNumber("P - X Error"))) / xwidth) *-1), 0.4, -0.4));
//                subMap.driveTrain.arcadeDrive(0, -MathExtra.limit((/*Math.sqrt*/((-xerror * (SmartDashboard.getNumber("P - X Error"))) / xwidth) *-1), 0.4, -0.4),false);
//            }else{
//                //subMap.driveTrain.arcadeDrive(0, MathExtra.limit((((xerror + xerror) - (xwidth + xerror)) * SmartDashboard.getNumber(stringMap.targeterKPString)) - SmartDashboard.getNumber(stringMap.targeterKIString), -0.27, 0.27), false);
//            
////                System.out.println("xerror : " + xerror);
////                System.out.println("after factored : " + (-xerror * (SmartDashboard.getNumber("P - X Error"))));
////                System.out.println("div width : " + ((xerror * (SmartDashboard.getNumber("P - X Error")))) / xwidth);
////                System.out.println("squared : " + Math.sqrt(((xerror * (SmartDashboard.getNumber("P - X Error")))) / xwidth));
////                System.out.println("final : " + -MathExtra.limit(/*Math.sqrt*/(((xerror * (SmartDashboard.getNumber("P - X Error")))) / xwidth), 0.4, -0.4));
//                subMap.driveTrain.arcadeDrive(0, -MathExtra.limit(/*Math.sqrt*/(((xerror * (SmartDashboard.getNumber("P - X Error")))) / xwidth), 0.4, -0.4),false);}
//        }
//        if(vertical){
//            double current = subMap.shooter.calculateDeckTilt();
////            double current = subMap.shooter.calcu();
//            if(Math.abs(yerror) > errorLimit){
//                subMap.shooter.setDeckTilt(current + (yerror / (ywidth / (SmartDashboard.getNumber("P - Y Error")))));
//            }
//        }
//        return false;
//    }
//    
//    //ALL SYSTEMS FAILURE - REVERTING TO LABVIEW
//    public void autonomousWithoutRoboRealm() {
//            try {
//                /**
//                 * Do the image capture with the axisCamera and apply the algorithm described above. This
//                 * sample will either get images from the axisCamera or from an image file stored in the top
//                 * level directory in the flash memory on the cRIO. The file name in this case is "10ft2.jpg"
//                 * 
//                 */
//                ColorImage image = axisCamera.getImage();     // comment if using stored images
//             //   ColorImage image;                           // next 2 lines read image from flash on cRIO
//              //  image =  new RGBImage("/10ft2.jpg");
//                BinaryImage thresholdImage = image.thresholdRGB(0,50,200,255,200,255);   // keep only red objects thr
//                BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);  // remove small artifacts
//                BinaryImage convexHullImage = thresholdImage.convexHull(false);          // fill in occluded rectangles
//                BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // find filled in rectangles
//               
//                ParticleAnalysisReport[] position = new ParticleAnalysisReport[5];
//                ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results
//                for (int i = 0; i < reports.length; i++) {  
//                    ParticleAnalysisReport r = reports[i];
//                    if( (double) r.boundingRectWidth / (double) r.boundingRectHeight > smallScoreRatio ){
//                        
//                        if(position[0] != null){
//                            if(position[0].boundingRectLeft + position[0].boundingRectWidth > r.boundingRectLeft + r.boundingRectWidth){
//                                position[2] = position[0];
//                                position[0] = r;
//                            }else{
//                                position[2] = r;
//                            }
//                        }else{
//                            position[0] = r;
//                        }
//                    }else {
//                        if(position[1] != null){
//                            if(r.boundingRectHeight * r.boundingRectWidth > position[1].boundingRectHeight * position[1].boundingRectWidth){
//                                position[1] = r;
//                            }else{
//                               position[2] = r;
//                            }
//                        }else{
//                            position[1] = r;
//                        }
//                    }
//                }
//                if(position[0] != null){
////                    forceTowardsVector(80,position[0].center_mass_x,SmartDashboard.getNumber(stringMap.errorString), true, false, 0, 160);
//                    
//                }
//              //  FoV();
//                  
//                filteredImage.free();
//                convexHullImage.free();
//                bigObjectsImage.free();
//                thresholdImage.free();
//                image.free();
//                
//            } catch (AxisCameraException ex) {        // this is needed if the axisCamera.getImage() is called
//    //            ex.printStackTrace();
//                System.out.println("[CRIO] No Camera Image");
//            } catch (NIVisionException ex) {
//                System.out.println("[CRIO] No Camera Image");
//     //           ex.printStackTrace();
//            }
//    }
//
//}
//        
