package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
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
    //store previous angle when joysticks are at zero
    private double fR0;
    private double fL0;
    private double bR0;
    private double bL0;
    private double rotation;
    private double frontRightSpeed;
    private double frontLeftSpeed;
    private double backRightSpeed;
    private double backLeftSpeed;
    private double frontRightAngle;
    private double frontLeftAngle;
    private double backRightAngle;
    private double backLeftAngle;
    private double theta;

    public SwerveDrive (WheelDrive backRight, WheelDrive backLeft, WheelDrive frontRight, WheelDrive frontLeft, WPI_Pigeon2 pigeon2, LimeLight l) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.pigeon2 = pigeon2;
        limeLight = l;
        fR0 = 0;
        fL0 = 0;
        bR0 = 0;
        bL0 = 0;
    }

    public void drive () {
        
        XboxController driver = RobotContainer.driverController;
        boolean adjustToTapeButton = driver.getAButton();
        boolean adjustToApriltagRightButton = driver.getBButton();
        boolean adjustToApriltagLeftButton = driver.getXButton();
        boolean stay45 = driver.getLeftBumper();
        boolean halfSpeed = driver.getRightBumper();
        boolean setThetaZeroButton = driver.getYButton();

        
        if (setThetaZeroButton) {
            setThetaZero();
        }
        else {            
        theta = pigeon2.getYaw() + Constants.DriveConstants.yawOffset;
        }
            
            

        //use camera to adjust to a target when A, B, or X are held on driver controller
        if (adjustToTapeButton) {
            limeLight.adjustToTarget(0);
            
            rotation = limeLight.getRotation();
            theta = Math.asin(Math.sin(theta*Math.PI/180))*180/Math.PI;
            if (theta < 5 && theta >-175) {
                x1=0;
                y1=0;
                x2 =0;
            }
            else if (theta < 175) { 
                x1 = 0;
                y1 = 0;
                x2 = 1;
            }
            else if (theta > -5) {
                x1 = 0;
                y1 = 0;
                x2 = -1;
            }
            else {
                x1 = 0;
                y1 = 0;
                x2 = 0;
            }
        }
        else if (adjustToApriltagRightButton || adjustToApriltagLeftButton) {
            limeLight.adjustToTarget(1);
            rotation = limeLight.getRotation();

            theta = Math.asin(Math.sin(theta*Math.PI/180))*180/Math.PI;
            if (theta < 5 && theta >-5) {
                x1=0;
                y1=0;
                x2 =0;
            }
            else if (theta < 5) { 
                x1 = 0;
                y1 = 0;
                x2 = 1;
            }
            else if (theta > -5){
                x1 = 0;
                y1 = 0;
                x2 = -1;
            }
            else {
                x1 = 0;
                y1 = 0;
                x2 = 0;
            }
        }
        else {
            x1 = driver.getRawAxis(Constants.ControllerConstants.LEFT_X_AXIS);
            y1 = driver.getRawAxis(Constants.ControllerConstants.LEFT_Y_AXIS);
            x2 = driver.getRawAxis(Constants.ControllerConstants.RIGHT_X_AXIS);
        }

        if ((!adjustToTapeButton && !adjustToApriltagRightButton && !adjustToApriltagLeftButton) || x2 == 1 || x2 ==-1) {

            if (x1 < 0.05 && x1 > -0.05) {
                x1 = 0;
            }
            if (x2 < 0.1 && x2 > -0.1) {
                x2 = 0;
            }
            if (y1 < 0.05 && y1 > -0.05) {
                y1 = 0;
            }

       
        double r = Math.sqrt((Constants.DriveConstants.L * Constants.DriveConstants.L) + (Constants.DriveConstants.W * Constants.DriveConstants.W));
        //y1 *= -1;
        //x2 *= -1;

        theta = theta*Math.PI/180;

        double temp = y1 * Math.cos(theta) + x1 * Math.sin(theta);
        x1 = -y1 * Math.sin(theta) + x1 * Math.cos(theta);
        y1 = temp;

        double a = x1 - x2 * (Constants.DriveConstants.L / r);
        double b = x1 + x2 * (Constants.DriveConstants.L / r);
        double c = y1 - x2 * (Constants.DriveConstants.W / r);
        double d = y1 + x2 * (Constants.DriveConstants.W / r);

        frontRightSpeed = 0.5 * Math.sqrt ((a * a) + (c * c));
        backLeftSpeed = 0.5 * Math.sqrt ((a * a) + (d * d));
        backRightSpeed = 0.5 * Math.sqrt ((b * b) + (c * c));
        frontLeftSpeed = 0.5 * Math.sqrt ((b * b) + (d * d));

        frontRightAngle = Math.atan2 (a, c) * 180/ Math.PI ; //arctan(+1) = 45 -45
        backLeftAngle = Math.atan2 (a, d) * 180/ Math.PI; //arctan(-1) = -45 45
        backRightAngle = Math.atan2 (b, c) * 180/ Math.PI; //arctan(+1) = 45 135
        frontLeftAngle = Math.atan2 (b, d) * 180/ Math.PI; //arctan(-1) = -45 -135

        }

        //set speed to be slower when it is adjusting to a target
        
        else {
            if (rotation == 0)
                frontRightSpeed = 0;
            else
                frontRightSpeed = -Constants.LimeLightConstants.AUTO_DRIVE_SPEED;

            
            frontRightAngle = rotation;
            frontLeftAngle = rotation;
            backRightAngle = rotation;
            backLeftAngle = rotation;

        }

        

        if (frontRightAngle != 0 || stay45) {
            //save the current angles so that the wheels do not return to zero when the joysticks are at zero
            fR0 = frontRightAngle;
            fL0 = frontLeftAngle;
            bR0 = backRightAngle;
            bL0 = backLeftAngle;
            
            if (halfSpeed) {
                frontRight.drive (frontRightSpeed * 0.4, frontRightAngle);
                frontLeft.drive (frontRightSpeed * 0.4, backLeftAngle);
                backRight.drive (frontRightSpeed * 0.4, backRightAngle);
                backLeft.drive (frontRightSpeed * 0.4, frontLeftAngle);
            }
            else if (stay45)
                hold45();
            else {
                /* // attempt to fix drifting after first competition
                frontRight.drive (frontRightSpeed, frontRightAngle);
                frontLeft.drive (frontLeftSpeed, backLeftAngle);
                backRight.drive (backRightSpeed, backRightAngle);
                backLeft.drive (backLeftSpeed, frontLeftAngle);
                */
            
                //set all speeds the same but different angles
                frontRight.drive (frontRightSpeed, frontRightAngle);
                frontLeft.drive (frontRightSpeed, backLeftAngle);
                backRight.drive (frontRightSpeed, backRightAngle);
                backLeft.drive (frontRightSpeed, frontLeftAngle);
            }
        }
        else {
            //if the joysticks are at zero, set angle motors to the previous angles instead of returning to zero2
            frontRight.drive (frontLeftSpeed, fR0);
            frontLeft.drive (frontLeftSpeed, bL0);
            backRight.drive (frontLeftSpeed, bR0);
            backLeft.drive (frontLeftSpeed, fL0);
        }
    
    
    }

    public void setThetaZero() {
        theta = 0;
    }

    public void driveForward() {
        frontRight.drive (-Constants.DriveConstants.AUTON_SPEED, 0);
        frontLeft.drive (-Constants.DriveConstants.AUTON_SPEED, 0);
        backRight.drive (-Constants.DriveConstants.AUTON_SPEED, 0);
        backLeft.drive (-Constants.DriveConstants.AUTON_SPEED, 0);
    }

    //direction = 1 for forward, -1 for backward
    public void driveForward(double speed, int direction) {
        frontRight.drive (-speed * direction, 0);
        frontLeft.drive (-speed * direction, 0);
        backRight.drive (-speed * direction, 0);
        backLeft.drive (-speed * direction, 0);
    }

    public void hold45() {
        frontRight.drive (0, -45);
        frontLeft.drive (0, 45);
        backRight.drive (0, -135);
        backLeft.drive (0, 135);
    }

    public void stop()
    {
        frontRight.drive (0, 0);
        frontLeft.drive (0, 0);
        backRight.drive (0, 0);
        backLeft.drive (0, 0);
    }
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
