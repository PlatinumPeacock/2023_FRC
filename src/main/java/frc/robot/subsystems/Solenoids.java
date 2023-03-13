package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Solenoids extends SubsystemBase {
    Solenoid leftSolenoid;
    Solenoid rightSolenoid;

    /** Creates a new Elevator. */
    public Solenoids() {
        leftSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.PneumaticsConstants.LEFT_SOLENOID);
        rightSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.PneumaticsConstants.RIGHT_SOLENOID);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    //close = true for cone, close = false for cube
    public void setSolenoid(boolean close)
    {
        leftSolenoid.set(close);
        rightSolenoid.set(close);
    }

}
