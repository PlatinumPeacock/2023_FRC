package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Solenoids;

public class AutoPneumatics extends CommandBase{
    Solenoids pneumatics;
    boolean on;
    Timer timer;
    private boolean finish = false;

    /** Creates a new Pneumatics. */
    public AutoPneumatics(Solenoids p, boolean o) {
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
        while(timer.get() < 1.5) {
            pneumatics.setSolenoid(on);
        }

        finish = true;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      pneumatics.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return finish;
    }
}
