package frc.robot;

public final class Constants {

    public static final class ControllerConstants {
        public static final int DRIVE_CONTROLLER = 0;
        public static final int OPERATOR_CONTROLLER = 1;
        public static final int LEFT_X_AXIS = 0;
        public static final int LEFT_Y_AXIS = 1;
        public static final int RIGHT_X_AXIS = 4;
    }

    public static final class ElevatorConstants {
        //port numbers for elevator motor
        public static final int EXTEND_ELEVATOR = 18;
        //elevator motor speeds
        public static final double ROTATE_SPEED = 1;
        public static final double EXTEND_SPEED = 0.25;
    }

    public static final class ClawConstants {
        //port numbers for intake motors
        public static final int LEFT_CLAW = 20;
        public static final int RIGHT_CLAW = 21;
        //claw motors speed
        public static final double CLAW_SPEED = 1;
    }

    public static final class PneumaticsConstants {
        //port numbers for pneumatics
        public static final int PCM = 1;
        public static final int CLAW_LEFT_SOLENOID = 0;
        public static final int CLAW_RIGHT_SOLENOID = 1;
        public static final int ELEVATOR_LEFT_SOLENOID = 2;
        public static final int ELEVATOR_RIGHT_SOLENOID = 3;
    }

    public static final class LimeLightConstants {
        public static final double AUTO_DRIVE_SPEED = 0.1;
        public static final double AUTO_ROTATE_SPEED = 0.1;
        public static final double DESIRED_TARGET_AREA = 0.9; // area of the target when the robot reaches desired distance
    }



    public static final class DriveConstants {

        //swerve drive constants
        public static final double L = 0.56515; //length between axles // Distance between front and back wheels on robot
        public static final double W = 0.56515; //width between axles // Distance between centers of right and left wheels on robot
        public static final int PIGEON_ID = 2;
        public static final double MAX_VOLTS = 8;   

        // Angular offsets of the modules relative to the chassis in radians
        /* 
        public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
        public static final double kFrontRightChassisAngularOffset = 0;
        public static final double kBackLeftChassisAngularOffset = Math.PI;
        public static final double kBackRightChassisAngularOffset = Math.PI / 2;
        */

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
    
      }
      
}
