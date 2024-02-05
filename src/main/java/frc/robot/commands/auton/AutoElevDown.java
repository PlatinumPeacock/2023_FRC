package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class AutoElevDown extends CommandBase {
    Elevator elevator;
    Timer timer;
    private boolean finish = false;

  /** Creates a new Extend. */
  public AutoElevDown(Elevator e) {
    elevator = e;
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
    while(timer.get() < 1.5) {
        elevator.elevatorDown();
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