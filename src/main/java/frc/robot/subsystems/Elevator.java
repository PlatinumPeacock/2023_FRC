package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator extends SubsystemBase{
  TalonSRX extendMotor;
  Faults faults = new Faults();

  /** Creates a new Elevator. */
  public Elevator() {
    extendMotor = new TalonSRX(Constants.ElevatorConstants.EXTEND_ELEVATOR);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void extend(double speed, int direction)
  {
    extendMotor.set(ControlMode.PercentOutput, speed * direction);
  }


  public void stop()
  {
    extendMotor.set(ControlMode.PercentOutput, 0);
  }

}
