package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Elevator;

public class Extend extends CommandBase {
    Elevator elevator;
    int direction;

  /** Creates a new Rotate. */
  public Extend(Elevator e, int d) {
    elevator = e;
    direction = d;
    addRequirements(elevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevator.extend(Constants.ElevatorConstants.ROTATE_SPEED, direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stopExtend();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
