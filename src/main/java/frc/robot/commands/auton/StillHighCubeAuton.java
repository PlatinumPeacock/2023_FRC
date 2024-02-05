package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Solenoids;
import frc.robot.subsystems.SwerveDrive;


//use this auton when starting in the middle


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StillHighCubeAuton extends SequentialCommandGroup {
  /** Creates a new HighCubeAuton. */
  public StillHighCubeAuton(Solenoids rotate, Elevator e, Claw c, SwerveDrive s) {
    addCommands(new AutoTopCone(e, 860000, rotate, false));
    addCommands(new AutoReverseIntake(c));
    addCommands(new AutoElevDown(e));
  }
}