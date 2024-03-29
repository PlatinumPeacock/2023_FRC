package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Solenoids;

public class Pneumatics extends CommandBase{
    Solenoids pneumatics;
    boolean on;

    /** Creates a new Pneumatics. */
    public Pneumatics(Solenoids p, boolean o) {
      pneumatics = p;
      on = o;
      addRequirements(pneumatics);
      // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      pneumatics.setSolenoid(on);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      pneumatics.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
