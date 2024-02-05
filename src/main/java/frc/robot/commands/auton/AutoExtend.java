package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.ElevSetHeight;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class AutoExtend extends CommandBase {
    Elevator elevator;
    double heightPoint;
    double heightInches = 0;
    Timer timer;
    double PosErr;
    PIDController pidController = new PIDController(.15, 0, 0);
    private boolean finish = false;

  /** Creates a new Extend. */
  public AutoExtend(Elevator e, int h) {
    elevator = e;
    heightPoint = h;
    heightInches = h/Constants.ElevatorConstants.elevatorTicksToInches;
    addRequirements(elevator);
    pidController.setSetpoint(heightInches);
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    elevator.setSpeed(pidController.calculate(elevator.getElevatorPosition()/Constants.ElevatorConstants.elevatorTicksToInches));
    
    //   */
    //   if (elevator.getElevatorPosition() > (heightPoint + 5000))
    //     elevator.extend(0.3, -1);
    // else if (elevator.getElevatorPosition() < (heightPoint - 5000)) 
    //   elevator.extend(0.8, 1);
    // else 
    // elevator.extend(0.4, 1);;    
    //}
    if(timer.get() > 2)
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
