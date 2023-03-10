package frc.robot;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.WheelDrive;
import frc.robot.commands.DriveWithJoysticksTrial;

public class RobotContainer {
    
    public static XboxController driverController = new XboxController(Constants.ControllerConstants.DRIVE_CONTROLLER);
    //private final DriveSubsystem robotDrive = new DriveSubsystem(); 
    public static final WheelDrive backRight = new WheelDrive (Constants.DriveConstants.REAR_RIGHT_TURNING, Constants.DriveConstants.REAR_RIGHT_DRIVE, Constants.DriveConstants.REAR_RIGHT_ENCODER);
    public static final WheelDrive backLeft = new WheelDrive (Constants.DriveConstants.REAR_LEFT_TURNING, Constants.DriveConstants.REAR_LEFT_DRIVE, Constants.DriveConstants.REAR_LEFT_ENCODER);
    public static final WheelDrive frontRight = new WheelDrive (Constants.DriveConstants.FRONT_RIGHT_TURNING, Constants.DriveConstants.FRONT_RIGHT_DRIVE, Constants.DriveConstants.FRONT_RIGHT_ENCODER);
    public static final WheelDrive frontLeft = new WheelDrive (Constants.DriveConstants.FRONT_LEFT_TURNING, Constants.DriveConstants.FRONT_LEFT_DRIVE, Constants.DriveConstants.FRONT_LEFT_ENCODER);

    public static final WPI_Pigeon2 pigeon2 = new WPI_Pigeon2(Constants.DriveConstants.PIGEON2);
    
    public static final SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft, pigeon2);
    
    public RobotContainer() {

        DriveWithJoysticksTrial dtt = new DriveWithJoysticksTrial(swerveDrive);
        swerveDrive.setDefaultCommand(dtt);

        //robotDrive.setDefaultCommand(drive());
        configureButtonBindings();
    }

    private void configureButtonBindings() { 
        
    }

    public void teleopInitFunc() {

    }    
}