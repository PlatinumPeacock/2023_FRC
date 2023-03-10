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

    public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft, WPI_Pigeon2 pigeon2) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.pigeon2 = pigeon2;
    }

    public void drive () {
        XboxController driver = RobotContainer.driverController;
        double x1 = (int)(driver.getRawAxis(Constants.ControllerConstants.LEFT_X_AXIS) * 100) / 100;
        double y1 = (int)(driver.getRawAxis(Constants.ControllerConstants.LEFT_Y_AXIS) * 100) / 100;
        double x2 = (int)(driver.getRawAxis(Constants.ControllerConstants.RIGHT_X_AXIS) * 100) / 100;

       x1 = 0;
       x2 = 0;
       y1 = 0;
       
        double r = Math.sqrt((Constants.L * Constants.L) + (Constants.W * Constants.W));
        y1 *= -1;

        double theta = pigeon2.getYaw();

        double temp = y1 * Math.cos(theta) + x1 * Math.sin(theta);
        x1 = -y1 * Math.sin(theta) + x1 * Math.cos(theta);
        y1 = temp;

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

        //backRight.drive (backRightSpeed, backRightAngle);
        //backLeft.drive (backLeftSpeed, backLeftAngle);
        //frontRight.drive (frontRightSpeed, frontRightAngle);
        frontLeft.drive (frontLeftSpeed, frontLeftAngle);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
