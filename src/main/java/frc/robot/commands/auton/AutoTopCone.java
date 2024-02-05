package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Solenoids;
import edu.wpi.first.wpilibj.Timer;

public class AutoTopCone extends CommandBase {
    Solenoids pneumatics;
    boolean on;
    Elevator elevator;
    double heightPoint;
    Timer timer;
    double PosErr;
    private boolean finish = false;

  /** Creates a new Extend. */
  public AutoTopCone(Elevator e, int h, Solenoids p, boolean o) {
   
    elevator = e;
    heightPoint = h;
    addRequirements(elevator);
    pneumatics = p;
    on = o;
    addRequirements(pneumatics);
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

    while(timer.get() < 2) {
    elevator.extend(0.6, 1);
    } 

    while(timer.get() < 4) {
        elevator.extend(0.6, 1); 
        pneumatics.setSolenoid(on);
    }
    
    finish = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stop();
    pneumatics.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}