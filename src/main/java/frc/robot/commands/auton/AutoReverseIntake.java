package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;


import frc.robot.subsystems.Claw;

public class AutoReverseIntake extends CommandBase{
    Claw claw;
    int direction;
    Timer timer;
    private boolean finish = false;

    /** Creates a new AutoReverseIntake. */
    public AutoReverseIntake(Claw c) {
      claw = c;
      addRequirements(claw);
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
      while(timer.get() < 0.5) {
        claw.intake(0.4, 1);   
      }
      
      finish = true;
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      claw.stop();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return finish;
    }
}
