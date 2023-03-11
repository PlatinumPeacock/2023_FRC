package frc.robot;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public final class Constants {
    public static final class ControllerConstants {
        public static final int DRIVE_CONTROLLER = 0;
        public static final int OPERATOR_CONTROLLER = 1;
        public static final int LEFT_X_AXIS = 0;
        public static final int LEFT_Y_AXIS = 1;
        public static final int RIGHT_X_AXIS = 4;
    }

    public static final class ElevatorConstants {
        //port numbers for elevator motors
        public static final int ROTATE_ELEVATOR = 3;
        public static final int EXTEND_ELEVATOR = 4;
        //elevator motor speeds
        public static final double ROTATE_SPEED = 0.5;
        public static final double EXTEND_SPEED = 0.5;
        }


    //swerve drive constants
    public static final double L = 0.56515; //length between axles
    public static final double W = 0.56515; //width between axles
    public static final int PIGEON_ID = 2;
    public static final double MAX_VOLTS = 8;

    public static final class DriveConstants {
        // Driving Parameters - Note that these are not the maximum capable speeds of
        // the robot, rather the allowed maximum speeds
        public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second
    
        public static final double kDirectionSlewRate = 1.2; // radians per second
        public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%)
        public static final double kRotationalSlewRate = 2.0; // percent per second (1 = 100%)
    
    
        // Angular offsets of the modules relative to the chassis in radians
        public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
        public static final double kFrontRightChassisAngularOffset = 0;
        public static final double kBackLeftChassisAngularOffset = Math.PI;
        public static final double kBackRightChassisAngularOffset = Math.PI / 2;
    

        //old constants
        //motor and encoder ports
        public static final int FRONT_LEFT_DRIVE = 16;
        public static final int REAR_LEFT_DRIVE = 15;
        public static final int FRONT_RIGHT_DRIVE = 10;
        public static final int REAR_RIGHT_DRIVE = 13;
    
        public static final int FRONT_LEFT_TURNING = 17;
        public static final int REAR_LEFT_TURNING = 14;
        public static final int FRONT_RIGHT_TURNING = 11;
        public static final int REAR_RIGHT_TURNING = 12;
    
        public static final int FRONT_LEFT_ENCODER = 5;
        public static final int REAR_LEFT_ENCODER = 4;
        public static final int FRONT_RIGHT_ENCODER = 6;
        public static final int REAR_RIGHT_ENCODER = 3;

        public static final int PIGEON2 = 2;
    
        public static final double kFrontLeftAngleZero = 79.45;
        public static final double kRearLeftAngleZero = 121.38;
        public static final double kFrontRightAngleZero = -104.68;
        public static final double kRearRightAngleZero = 23.54;
    
        public static final boolean kFrontLeftTurningEncoderReversed = false;
        public static final boolean kRearLeftTurningEncoderReversed = false;
        public static final boolean kFrontRightTurningEncoderReversed = false;
        public static final boolean kRearRightTurningEncoderReversed = false;
    
        public static final boolean kFrontLeftDriveEncoderReversed = false;
        public static final boolean kRearLeftDriveEncoderReversed = false;
        public static final boolean kFrontRightDriveEncoderReversed = true;
        public static final boolean kRearRightDriveEncoderReversed = true;
    
        public static final double kTrackWidth = 0.56515;
        // Distance between centers of right and left wheels on robot
        public static final double kWheelBase = 0.56515;
        // Distance between front and back wheels on robot
        public static final SwerveDriveKinematics kDriveKinematics =
            new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));
    
        public static final boolean kGyroReversed = false;
    
    
        // Values to scale joystick inputs to desired states.
        public static final double kMaxSpeedMetersPerSecond = 4.5; // LOCKED IN
        public static final double kMaxRotationalSpeed =
            3 * Math.PI;
    
        // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
        // These characterization values MUST be determined either experimentally or theoretically
        // for *your* robot's drive.
        // The SysId tool provides a convenient method for obtaining these values for your robot.
        public static final double ksVolts = 0.73394;
        public static final double kvVoltSecondsPerMeter = 2.4068;
        public static final double kaVoltSecondsSquaredPerMeter = 0.28749;
    
        public static final double ksTurning = 0.77; // LOCKED IN!  -----  old 0.66202
        public static final double kvTurning = 0.75; //0.75 // 3.0052
        public static final double kaTurning = 0; // Default to zero
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI * 2;
      }

      public static final class ModuleConstants {

        public static final double kDriveGearRatio = 7.13;
    
        public static final double kPModuleTurnController = 8.1; //8.3 // TUNE: 8.2142
        public static final double kIModuleTurnController = 0; // DO NOT USE
        public static final double kDModuleTurnController = 0; // TUNE
    
        // Acceleration could be 8pi to make module get anywhere in 0.5 seconds.
        // Will never reach max velocity, so it can be right at the "top" of the triangle.
        // In this case, that would be 2pi.
    
        public static final double kMaxModuleAngularSpeedRadiansPerSecond = 3 * Math.PI;
        public static final double kMaxModuleAngularAccelerationRadiansPerSecondSquared = 6 * Math.PI;
    
        public static final double kPModuleDriveController = 1; // TUNE
        public static final double kIModuleDriveController = 0; // DO NOT USE
        public static final double kDModuleDriveController = 0;
    
    
        public static final int kDriveFXEncoderCPR = 2048;
        public static final int kTurningCANcoderCPR = 4096;
        public static final double kWheelDiameterMeters = 0.1016; // 4 inches
        public static final double kWheelCircumferenceMeters =
            kWheelDiameterMeters * Math.PI; // C = D * pi
        public static final double kDrivetoMetersPerSecond =
            (10 * kWheelCircumferenceMeters) / (kDriveGearRatio * 2048);
      
     
    }
}
