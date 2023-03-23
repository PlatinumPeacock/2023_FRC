package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.Timer;

public class AutoExtend extends CommandBase {
    Elevator elevator;
    double heightPoint;
    Timer timer;
    private boolean finish = false;

  /** Creates a new Extend. */
  public AutoExtend(Elevator e, int h) {
    elevator = e;
    heightPoint = h;
    addRequirements(elevator);
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.reset();
    timer.start();
    while(timer.get() < 4) {
    if (elevator.getElevatorPosition() > (heightPoint + 5000))
        elevator.extend(0.6, -1);
    else if (elevator.getElevatorPosition() < (heightPoint - 5000)) 
        elevator.extend(0.6, 1);
    else 
        elevator.stop();    
    }
    
    finish = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
