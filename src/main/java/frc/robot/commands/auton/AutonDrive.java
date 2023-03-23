package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDrive;

public class AutonDrive extends CommandBase{
    SwerveDrive driveTrain;
    double time;
    private boolean finish = false;
    Timer timer;

    /** Creates a new AutonDrive. */
    public AutonDrive(SwerveDrive dt, double t) {
    driveTrain = dt;
    addRequirements(driveTrain);
    time = t;
    timer = new Timer();
    }

  // Called when the command is initially scheduled.
  //"Some things are ugly, not me, you." - Meta 3-23-23
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() < time)
    {
      driveTrain.driveForward();
    }
    finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
