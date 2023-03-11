package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Elevator extends SubsystemBase{
  VictorSPX rotateMotor;
  VictorSPX extendMotor;

  /** Creates a new Elevator. */
  public Elevator() {
    rotateMotor = new VictorSPX(Constants.ElevatorConstants.ROTATE_ELEVATOR);
    extendMotor = new VictorSPX(Constants.ElevatorConstants.EXTEND_ELEVATOR);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //direction should be either -1 or 1 to set motor forward or reverse
  public void rotate(double speed, int direction)
  {
    rotateMotor.set(ControlMode.PercentOutput, speed * direction);
  }


  public void extend(double speed, int direction)
  {
    extendMotor.set(ControlMode.PercentOutput, speed * direction);
  }


  public void stopRotate()
  {
    rotateMotor.set(ControlMode.PercentOutput, 0);
  }

  public void stopExtend()
  {
    extendMotor.set(ControlMode.PercentOutput, 0);
  }

}
