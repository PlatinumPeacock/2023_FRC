package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class LimeLight extends SubsystemBase{
    
    NetworkTable table;
    double rotation;

    //limelight variables
    public static double tx; //x of target
    public static double ty; //y of target
    public static double ta; //area of target
    public static double tv; //whether limelight has a target
    public static boolean hasTarget; //whether limelight has a target

    /** Creates a new LimeLight. */
    public LimeLight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    public void updateLimeLightTracking(){
        tv = table.getEntry("tv").getDouble(0);
        tx = table.getEntry("tx").getDouble(0);
        ty = table.getEntry("ty").getDouble(0);
        ta = table.getEntry("ta").getDouble(0);
        hasTarget = tv != 0;
      }

    public void adjustToTarget(int pipeline) {
        //switch between pipelines. 0 = reflective tape, 1 = apriltag
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);

        updateLimeLightTracking();

        if (hasTarget) {
            rotation = tx;
            //acceptable amount of error
            //if (tx < 2 && tx > -2) // this is the tolerance to target alignment
               // rotation = 0;
            //else
             if (rotation <= -2)
                rotation = -90;
            else if (rotation >=2)
                rotation = 90;
                else 
                rotation = 0;
        }
        else {
            rotation = 0;
        }
    }


    public double getRotation() {
        return rotation;
    }
}
