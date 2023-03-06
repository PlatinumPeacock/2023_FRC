package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class SwerveDrive extends SubsystemBase {
    private WheelDrive backRight;
    private WheelDrive backLeft;
    private WheelDrive frontRight;
    private WheelDrive frontLeft;

    public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
    }

    public void drive () {
        XboxController driver = RobotContainer.driverController;
        double x1 = driver.getRawAxis(Constants.ControllerConstants.LEFT_X_AXIS);
        double y1 = driver.getRawAxis(Constants.ControllerConstants.LEFT_Y_AXIS);
        double x2 = driver.getRawAxis(Constants.ControllerConstants.RIGHT_X_AXIS);
       
       
        double r = Math.sqrt((Constants.L * Constants.L) + (Constants.W * Constants.W));
        y1 *= -1;

        double a = x1 - x2 * (Constants.L / r);
        double b = x1 + x2 * (Constants.L / r);
        double c = y1 - x2 * (Constants.W / r);
        double d = y1 + x2 * (Constants.W / r);

        double backRightSpeed = Math.sqrt ((a * a) + (d * d));
        double backLeftSpeed = Math.sqrt ((a * a) + (c * c));
        double frontRightSpeed = Math.sqrt ((b * b) + (d * d));
        double frontLeftSpeed = Math.sqrt ((b * b) + (c * c));

        double backRightAngle = Math.atan2 (a, d) * 180/ Math.PI;
        double backLeftAngle = Math.atan2 (a, c) * 180/ Math.PI;
        double frontRightAngle = Math.atan2 (b, d) * 180/ Math.PI;
        double frontLeftAngle = Math.atan2 (b, c) * 180/ Math.PI;

        backRight.drive (backRightSpeed, backRightAngle);
        backLeft.drive (backLeftSpeed, backLeftAngle);
        frontRight.drive (frontRightSpeed, frontRightAngle);
        frontLeft.drive (frontLeftSpeed, frontLeftAngle);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
