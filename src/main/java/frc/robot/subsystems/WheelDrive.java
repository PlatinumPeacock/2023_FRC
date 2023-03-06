package frc.robot.subsystems;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.math.controller.PIDController;


public class WheelDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private CANCoder encoder;
    private PIDController pidController;

    public WheelDrive(int aM, int sM, int encoder) {
        angleMotor = new CANSparkMax(aM, MotorType.kBrushless);
        speedMotor = new CANSparkMax(sM, MotorType.kBrushless);
        this.encoder = new CANCoder(encoder);
        pidController = new PIDController (1, 0, 0);
        pidController.enableContinuousInput(-Math.PI, Math.PI);
        pidController.setTolerance(encoder);
    }

    public void drive(double speed, double angle) {
        speedMotor.set (speed);

        
        double setpoint = angle * (Constants.MAX_VOLTS * 0.5) + (Constants.MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
    if (setpoint < 0) {
        setpoint = Constants.MAX_VOLTS + setpoint;
    }
    if (setpoint > Constants.MAX_VOLTS) {
        setpoint = setpoint - Constants.MAX_VOLTS;
    }
    //pidController.setTolerance(0.02);
  // pidController.setSetpoint (setpoint);
   //setpoint = encoder.getAbsolutePosition() - angle;
   //pidController.calculate(setpoint);   
    angleMotor.set(.1);
    

    }
}
