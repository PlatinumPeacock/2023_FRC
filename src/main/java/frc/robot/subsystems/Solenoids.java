package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Solenoids extends SubsystemBase {
    Solenoid leftSolenoid;
    Solenoid rightSolenoid;

    /** Creates a new Solenoids. */
    public Solenoids() {
        leftSolenoid = new Solenoid(Constants.PneumaticsConstants.PCM, PneumaticsModuleType.CTREPCM, Constants.PneumaticsConstants.LEFT_SOLENOID);
        rightSolenoid = new Solenoid(Constants.PneumaticsConstants.PCM, PneumaticsModuleType.CTREPCM, Constants.PneumaticsConstants.RIGHT_SOLENOID);
    }

    //close = true for cone, 
    //close = false for cube
    public void setSolenoid(boolean close) {
        leftSolenoid.set(close);
        rightSolenoid.set(!close);
    }

    public void stop() {
        leftSolenoid.set(false);
        rightSolenoid.set(false);
    }

}
