package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevSetHeight extends CommandBase {
    Elevator elevator;
    double heightPoint;

  /** Creates a new Extend. */
  public ElevSetHeight(Elevator e, int h) {
    elevator = e;
    heightPoint = h;
    addRequirements(elevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (elevator.getElevatorPosition() > (heightPoint + 15000))
        {elevator.extend(0.6, -1);
      }
    else if (elevator.getElevatorPosition() < (heightPoint - 15000)) 
        {elevator.extend(0.6, 1);
       }
    else 
        elevator.stop();    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
