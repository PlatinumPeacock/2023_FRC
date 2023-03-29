package frc.robot;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.WheelDrive;
import frc.robot.subsystems.Solenoids;
import frc.robot.commands.DriveWithJoysticksTrial;
import frc.robot.commands.ElevSetHeight;
import frc.robot.commands.Extend;
import frc.robot.commands.Intake;
import frc.robot.commands.Intake_Reverse;
import frc.robot.commands.Pneumatics;
import frc.robot.commands.auton.AutonomousOne;
import frc.robot.commands.auton.MiddleAuton;
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

    //create LimeLight
    public static final LimeLight limeLight = new LimeLight();
    
    //create swerve drive
    public static final SwerveDrive swerveDrive = new SwerveDrive (backRight, backLeft, frontRight, frontLeft, pigeon2, limeLight);

    //create all subsystem objects
    private final Elevator elevator;
    private final Claw claw;
    private final Solenoids clawPneumatics;
    private final Solenoids elevatorPneumatics;

    //create all repeatCommands (because the whileHeld() method no longer exists)
    private final RepeatCommand extendOut;
    private final RepeatCommand extendIn;
    private final RepeatCommand topConeHeight;
    private final RepeatCommand middleConeHeight;
    private final RepeatCommand shelfHeight;
    private final RepeatCommand intake;
    private final RepeatCommand intakeReverse;

    //create all pneumatics commands
    private final Pneumatics cube;
    private final Pneumatics cone;
    private final Pneumatics rotateForward;
    private final Pneumatics rotateBackward;

    private final Compressor comp;

    SendableChooser<Command> chooser = new SendableChooser<>();
    
    public RobotContainer() {

        comp = new Compressor(1, PneumaticsModuleType.CTREPCM);
        

        //new drive object and teleop drive command
        DriveWithJoysticksTrial dtt = new DriveWithJoysticksTrial(swerveDrive);
        swerveDrive.setDefaultCommand(dtt);

        //new elevator object and all elevator commands
        elevator = new Elevator();
        extendOut = new RepeatCommand(new Extend(elevator, 1, 1));
        extendOut.addRequirements(elevator);
        extendIn =  new RepeatCommand(new Extend(elevator, -1, 0.7)); //speed previously was 0.5
        extendIn.addRequirements(elevator);
        topConeHeight = new RepeatCommand(new ElevSetHeight(elevator, 306000));
        topConeHeight.addRequirements(elevator);
        middleConeHeight = new RepeatCommand(new ElevSetHeight(elevator, Constants.ElevatorConstants.MIDDLE_HEIGHT));//was 300000
        middleConeHeight.addRequirements(elevator);
        shelfHeight = new RepeatCommand(new ElevSetHeight(elevator, Constants.ElevatorConstants.SHELF_HEIGHT)); //was 301000
        shelfHeight.addRequirements(elevator);

        //new claw object and all intake commands
        claw = new Claw();
        intake = new RepeatCommand(new Intake(claw, 1));
        intake.addRequirements(claw);
        intakeReverse = new RepeatCommand(new Intake_Reverse(claw, -1));
        intake.addRequirements(claw);

        //new solenoids objects and all pneumatics commands
        clawPneumatics = new Solenoids(Constants.PneumaticsConstants.CLAW_LEFT_SOLENOID, Constants.PneumaticsConstants.CLAW_RIGHT_SOLENOID);
        elevatorPneumatics = new Solenoids(Constants.PneumaticsConstants.ELEVATOR_LEFT_SOLENOID, Constants.PneumaticsConstants.ELEVATOR_RIGHT_SOLENOID);
        cube = new Pneumatics(clawPneumatics, false);
        cone = new Pneumatics(clawPneumatics, true);
        rotateForward = new Pneumatics(elevatorPneumatics, false);
        rotateBackward = new Pneumatics(elevatorPneumatics, true);

        AutonomousOne autonOne = new AutonomousOne(elevatorPneumatics, elevator, clawPneumatics, swerveDrive);
        MiddleAuton middleAuton = new MiddleAuton(elevatorPneumatics, elevator, clawPneumatics);
        
        //Add choices as options here
        //Default option
        chooser.setDefaultOption("Autonomous One", autonOne);
        chooser.addOption("Middle Autonomous", middleAuton);
 
        //Add choice to smart dashboard
        SmartDashboard.putData("Autonomous", chooser);


        configureButtonBindings();
    }

    private void configureButtonBindings() { 

        //elevator buttons
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
        
        Trigger rotateForwardButton = operatorController.rightBumper();
        rotateForwardButton.whileTrue(rotateForward);

        Trigger rotateBackwardButton = operatorController.leftBumper();
        rotateBackwardButton.whileTrue(rotateBackward);

        Trigger topConeButton = operatorController.povUp();
        topConeButton.whileTrue(topConeHeight);

        Trigger middleConeButton = operatorController.povDown();
        middleConeButton.whileTrue(middleConeHeight);

        Trigger shelfButton = operatorController.povLeft();
        shelfButton.whileTrue(shelfHeight);
    }

    public void teleopInitFunc() {

    }    

    public Command getAutonomousCommand() {
      //An ExampleCommand will run in autonomous
      return chooser.getSelected();
    }
}