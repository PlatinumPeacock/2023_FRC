package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Solenoids;
import frc.robot.subsystems.SwerveDrive;


//use this auton when starting in the middle


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HighCubeBalanceAuton extends SequentialCommandGroup {
  /** Creates a new HighCubeBalanceAuton. */
  public HighCubeBalanceAuton(Solenoids rotate, Elevator e, Claw c, SwerveDrive s) {
    addCommands(new AutoTopCone(e, 860000, rotate, false)); //4 sec
    addCommands(new AutoReverseIntake(c)); //0.5 sec
    addCommands(new AutoElevDown(e)); //1.5 sec
    addCommands(new AutoPneumatics(rotate, true)); //1.5
    addCommands(new AutonDrive(s, 3, 0.6, 1)); //3 sec
    addCommands(new AutonDrive(s, 2.805, 0.4, -1)); //2.815 sec changed after match trial
    addCommands(new AutoLockWheels(s, 1.695)); //1.695 sec
  }
}