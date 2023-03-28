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

  //extend out direction = 1, retract direction = -1
  public void extend(double speed, int direction)
  {
     if ((getElevatorPosition()<1000) && direction ==-1)
     {
      extendMotor.set(ControlMode.PercentOutput, 0 * direction);
     }
     else{
    extendMotor.set(ControlMode.PercentOutput, speed * direction);
     }
  }

  public double getElevatorPosition() 
  {
    return extendMotor.getSelectedSensorPosition();
  }


  public void stop()
  {
    extendMotor.set(ControlMode.PercentOutput, 0);
  }



}
