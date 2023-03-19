package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

public class SwerveDrive extends SubsystemBase {
    private WheelDrive backRight;
    private WheelDrive backLeft;
    private WheelDrive frontRight;
    private WheelDrive frontLeft;
    private WPI_Pigeon2 pigeon2;
    private LimeLight limeLight;
    private double x1;
    private double y1;
    private double x2;

    public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft, WPI_Pigeon2 pigeon2, LimeLight l) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.pigeon2 = pigeon2;
        limeLight = l;
    }

    public void drive () {
        XboxController driver = RobotContainer.driverController;
        boolean adjustToTargetButton = driver.getAButton();

        //use camera to adjust to a target when A is held on driver controller
        if (adjustToTargetButton) {
            //driveToTarget();
            //return;
            limeLight.adjustToTarget();
            double forward = limeLight.getForward();
            double rotation = limeLight.getRotation();
            x1 = 0;
            y1 = 0;
            x2 = rotation;
        }
        else {
        x1 = driver.getRawAxis(Constants.ControllerConstants.LEFT_X_AXIS);
        y1 = driver.getRawAxis(Constants.ControllerConstants.LEFT_Y_AXIS);
        x2 = driver.getRawAxis(Constants.ControllerConstants.RIGHT_X_AXIS);
        }

        if (x1 < 0.05 && x1 > -0.05) {
            x1 = 0;
        }
        if (x2 < 0.05 && x2 > -0.05) {
            x2 = 0;
        }
        if (y1 < 0.05 && y1 > -0.05) {
            y1 = 0;
        }

       
        double r = Math.sqrt((Constants.DriveConstants.L * Constants.DriveConstants.L) + (Constants.DriveConstants.W * Constants.DriveConstants.W));
        //y1 *= -1;
        //x2 *= -1;

        int yawOffset = -87;

        double theta = pigeon2.getYaw() + yawOffset;
        theta = theta*Math.PI/180;

        double temp = y1 * Math.cos(theta) + x1 * Math.sin(theta);
        x1 = -y1 * Math.sin(theta) + x1 * Math.cos(theta);
        y1 = temp;

        double a = x1 - x2 * (Constants.DriveConstants.L / r);
        double b = x1 + x2 * (Constants.DriveConstants.L / r);
        double c = y1 - x2 * (Constants.DriveConstants.W / r);
        double d = y1 + x2 * (Constants.DriveConstants.W / r);

        double frontRightSpeed = Math.sqrt ((a * a) + (c * c));
        double backLeftSpeed = Math.sqrt ((a * a) + (d * d));
        double backRightSpeed = Math.sqrt ((b * b) + (c * c));
        double frontLeftSpeed = Math.sqrt ((b * b) + (d * d));

        double frontRightAngle = Math.atan2 (a, c) * 180/ Math.PI ; //arctan(+1) = 45 -45
        double backLeftAngle = Math.atan2 (a, d) * 180/ Math.PI; //arctan(-1) = -45 45
        double backRightAngle = Math.atan2 (b, c) * 180/ Math.PI; //arctan(+1) = 45 135
        double frontLeftAngle = Math.atan2 (b, d) * 180/ Math.PI; //arctan(-1) = -45 -135

        //set speed to be slower when it is adjusting to a target
        if (adjustToTargetButton) {
            frontRightSpeed = driver.getRawAxis(Constants.ControllerConstants.LEFT_Y_AXIS) * -Constants.LimeLightConstants.AUTO_DRIVE_SPEED;

            frontRightAngle *= Constants.LimeLightConstants.AUTO_ROTATE_SPEED;
            frontLeftAngle *= Constants.LimeLightConstants.AUTO_ROTATE_SPEED;
            backRightAngle *= Constants.LimeLightConstants.AUTO_ROTATE_SPEED;
            backLeftAngle *= Constants.LimeLightConstants.AUTO_ROTATE_SPEED;
        }

        //set all speeds the same but different angles
        frontRight.drive (frontRightSpeed, frontRightAngle);
        frontLeft.drive (frontRightSpeed, backLeftAngle);
        backRight.drive (frontRightSpeed, backRightAngle);
        backLeft.drive (frontRightSpeed, frontLeftAngle);
    
    
    }

    //code based on example from LimeLight website using arcade drive
    //probably won't work because each wheel is set to the same rotation
    public void driveToTarget() {
        limeLight.adjustToTarget();
        double forward = limeLight.getForward();
        double rotation = limeLight.getRotation();
        frontRight.drive(forward, rotation);
        frontLeft.drive(forward, rotation);
        backRight.drive(forward, rotation);
        backLeft.drive(forward, rotation);
    } 

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
