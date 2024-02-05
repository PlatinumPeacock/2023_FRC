package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Solenoids;
import frc.robot.subsystems.SwerveDrive;


//use this auton when starting in the middle


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HighConeAuton extends SequentialCommandGroup {
  /** Creates a new HighConeAuton. */
  public HighConeAuton(Solenoids rotate, Elevator e, Solenoids claw, SwerveDrive s) {
    addCommands(new AutoTopCone(e, 860000, rotate, false));
    addCommands(new AutoPneumatics(claw, true));
    addCommands(new AutoElevDown(e));
    addCommands(new AutonDrive(s, Constants.DriveConstants.TIME, Constants.DriveConstants.AUTON_SPEED, 1));
  }
}