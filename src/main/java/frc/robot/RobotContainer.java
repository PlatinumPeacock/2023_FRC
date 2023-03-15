package frc.robot;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.WheelDrive;
import frc.robot.subsystems.Solenoids;
import frc.robot.commands.DriveWithJoysticksTrial;
import frc.robot.commands.Extend;
import frc.robot.commands.Intake;
import frc.robot.commands.Pneumatics;
import frc.robot.commands.Rotate;
import edu.wpi.first.wpilibj.Compressor;


public class RobotContainer {
    
    //xbox controllers
    public static XboxController driverController = new XboxController(Constants.ControllerConstants.DRIVE_CONTROLLER);
    public static CommandXboxController operatorController = new CommandXboxController(Constants.ControllerConstants.OPERATOR_CONTROLLER);

    //create each wheel 
    public static final WheelDrive backRight = new WheelDrive (Constants.DriveConstants.REAR_RIGHT_TURNING, Constants.DriveConstants.REAR_RIGHT_DRIVE, Constants.DriveConstants.REAR_RIGHT_ENCODER);
    public static final WheelDrive backLeft = new WheelDrive (Constants.DriveConstants.REAR_LEFT_TURNING, Constants.DriveConstants.REAR_LEFT_DRIVE, Constants.DriveConstants.REAR_LEFT_ENCODER);
    public static final WheelDrive frontRight = new WheelDrive (Constants.DriveConstants.FRONT_RIGHT_TURNING, Constants.DriveConstants.FRONT_RIGHT_DRIVE, Constants.DriveConstants.FRONT_RIGHT_ENCODER);
    public static final WheelDrive frontLeft = new WheelDrive (Constants.DriveConstants.FRONT_LEFT_TURNING, Constants.DriveConstants.FRONT_LEFT_DRIVE, Constants.DriveConstants.FRONT_LEFT_ENCODER);

    public static final WPI_Pigeon2 pigeon2 = new WPI_Pigeon2(Constants.DriveConstants.PIGEON2);
    
    //create swerve drive
    public static final SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft, pigeon2);

    //create all subsystem objects
    private final Elevator elevator;
    private final Claw claw;
    private final Solenoids clawPneumatics;

    //create all repeatCommands (because the whileHeld() method no longer exists)
    private final RepeatCommand rotateForward;
    private final RepeatCommand rotateBackward;
    private final RepeatCommand extendOut;
    private final RepeatCommand extendIn;
    private final RepeatCommand intake;
    private final RepeatCommand intakeReverse;

    //create all pneumatics commands
    private final Pneumatics cube;
    private final Pneumatics cone;

    private final Compressor comp;
    
    public RobotContainer() {

        comp = new Compressor(1, PneumaticsModuleType.CTREPCM);
        

        //new drive object and teleop drive command
        DriveWithJoysticksTrial dtt = new DriveWithJoysticksTrial(swerveDrive);
        swerveDrive.setDefaultCommand(dtt);

        //new elevator object and all elevator commands
        elevator = new Elevator();
        rotateForward = new RepeatCommand(new Rotate(elevator, 1));
        rotateBackward = new RepeatCommand(new Rotate(elevator, -1));
        extendOut = new RepeatCommand(new Extend(elevator, 1));
        extendOut.addRequirements(elevator);
        extendIn =  new RepeatCommand(new Extend(elevator, -1));
        extendIn.addRequirements(elevator);

        //new claw object and all intake commands
        claw = new Claw();
        intake = new RepeatCommand(new Intake(claw, 1));
        intake.addRequirements(claw);
        intakeReverse = new RepeatCommand(new Intake(claw, -1));
        intake.addRequirements(claw);

        //new solenoids object and all pneumatics commands
        clawPneumatics = new Solenoids();
        //clawPneumatics.stop();
        cube = new Pneumatics(clawPneumatics, false);
        cone = new Pneumatics(clawPneumatics, true);


        configureButtonBindings();
    }

    private void configureButtonBindings() { 

        //elevator buttons
        Trigger rotateForwardButton = operatorController.rightBumper();
        rotateForwardButton.whileTrue(rotateForward);

        Trigger rotateBackwardButton = operatorController.leftBumper();
        rotateBackwardButton.whileTrue(rotateBackward);

        Trigger extendOutButton = operatorController.y();
        extendOutButton.whileTrue(extendOut);

        Trigger extendInButton = operatorController.x();
        extendInButton.whileTrue(extendIn);


        //intake buttons
        Trigger intakeButton = operatorController.a();
        intakeButton.whileTrue(intake);

        Trigger intakeReverseButton = operatorController.b();
        intakeReverseButton.whileTrue(intakeReverse);

        //pneumatics buttons
        Trigger cubeButton = operatorController.back();
        cubeButton.whileTrue(cube);

        Trigger coneButton = operatorController.start();
        coneButton.whileTrue(cone);
    }

    public void teleopInitFunc() {

    }    
}