package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Solenoids extends SubsystemBase {
    Solenoid leftSolenoid;
    Solenoid rightSolenoid;

    /** Creates a new Solenoids. */
    public Solenoids(int left, int right) {
        leftSolenoid = new Solenoid(Constants.PneumaticsConstants.PCM, PneumaticsModuleType.CTREPCM, left);
        rightSolenoid = new Solenoid(Constants.PneumaticsConstants.PCM, PneumaticsModuleType.CTREPCM, right);
    }

    //on = true for cone and backward elevator rotation, 
    //on = false for cube and forward elevator rotation
    public void setSolenoid(boolean on) {
        leftSolenoid.set(on);
        rightSolenoid.set(!on);
    }

    public void stop() {
        leftSolenoid.set(false);
        rightSolenoid.set(false);
    }

}
