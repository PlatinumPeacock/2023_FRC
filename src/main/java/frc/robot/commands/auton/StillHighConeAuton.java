package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Solenoids;


//use this auton when starting in the middle


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StillHighConeAuton extends SequentialCommandGroup {
  /** Creates a new MiddleAuton. */
  public StillHighConeAuton(Solenoids rotate, Elevator e, Solenoids claw) {
    addCommands(new AutoTopCone(e, 860000, rotate, false));
    addCommands(new AutoPneumatics(claw, true));
    addCommands(new AutoElevDown(e));
  }
}