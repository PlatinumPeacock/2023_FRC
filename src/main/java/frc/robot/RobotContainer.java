package frc.robot;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.WheelDrive;
import frc.robot.commands.DriveWithJoysticksTrial;
import frc.robot.commands.Extend;
import frc.robot.commands.Rotate;


public class RobotContainer {
    
    //xbox controllers
    public static XboxController driverController = new XboxController(Constants.ControllerConstants.DRIVE_CONTROLLER);
    public static XboxController operatorController = new XboxController(Constants.ControllerConstants.OPERATOR_CONTROLLER);

    //create each wheel 
    public static final WheelDrive backRight = new WheelDrive (Constants.DriveConstants.REAR_RIGHT_TURNING, Constants.DriveConstants.REAR_RIGHT_DRIVE, Constants.DriveConstants.REAR_RIGHT_ENCODER);
    public static final WheelDrive backLeft = new WheelDrive (Constants.DriveConstants.REAR_LEFT_TURNING, Constants.DriveConstants.REAR_LEFT_DRIVE, Constants.DriveConstants.REAR_LEFT_ENCODER);
    public static final WheelDrive frontRight = new WheelDrive (Constants.DriveConstants.FRONT_RIGHT_TURNING, Constants.DriveConstants.FRONT_RIGHT_DRIVE, Constants.DriveConstants.FRONT_RIGHT_ENCODER);
    public static final WheelDrive frontLeft = new WheelDrive (Constants.DriveConstants.FRONT_LEFT_TURNING, Constants.DriveConstants.FRONT_LEFT_DRIVE, Constants.DriveConstants.FRONT_LEFT_ENCODER);

    public static final WPI_Pigeon2 pigeon2 = new WPI_Pigeon2(Constants.DriveConstants.PIGEON2);
    
    //create swerve drive
    public static final SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft, pigeon2);

    private final Elevator elevator;
    
    public RobotContainer() {

        DriveWithJoysticksTrial dtt = new DriveWithJoysticksTrial(swerveDrive);
        swerveDrive.setDefaultCommand(dtt);

        elevator = new Elevator();

        //robotDrive.setDefaultCommand(drive());
        configureButtonBindings();
    }

    private void configureButtonBindings() { 
        //elevator buttons
        JoystickButton rotateForward = new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);
        rotateForward.whileTrue(new Rotate(elevator, 1));

        JoystickButton rotateBackward = new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
        rotateBackward.whileTrue(new Rotate(elevator, -1));

        JoystickButton extendOut = new JoystickButton(operatorController, XboxController.Button.kY.value);
        extendOut.whileTrue(new Extend(elevator, -1));

        JoystickButton extendIn = new JoystickButton(operatorController, XboxController.Button.kX.value);
        extendIn.whileTrue(new Extend(elevator, -1));

    }

    public void teleopInitFunc() {

    }    
}