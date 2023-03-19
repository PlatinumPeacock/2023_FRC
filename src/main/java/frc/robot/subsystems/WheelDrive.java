package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;

import com.ctre.phoenix.sensors.CANCoder;


public class WheelDrive {
    private CANSparkMax angleMotor;
    private CANSparkMax speedMotor;
    private CANCoder encoder;
    private PIDController pidController;
    

    public WheelDrive(int aM, int sM, int encoder) {
        angleMotor = new CANSparkMax(aM, MotorType.kBrushless);
        speedMotor = new CANSparkMax(sM, MotorType.kBrushless);
        this.encoder = new CANCoder(encoder);
        
        
        
        pidController = new PIDController (0.005, 0, 0);
        pidController.enableContinuousInput(-180, 180);
        pidController.setTolerance(1);

    }


    public void drive(double speed, double angle) {
        
        speedMotor.set (speed);
         
        
        double setpoint = angle;
        pidController.setSetpoint (setpoint);
        double cmd = -1 * pidController.calculate(encoder.getAbsolutePosition(), setpoint);
        //double kp = 0.005;
        //double cmd = -1 * ((setpoint - encoder.getAbsolutePosition()) * kp);
        
       
        angleMotor.set(MathUtil.clamp(cmd, -1, 1));
    /* 
        if (!pidController.atSetpoint()) {
            
        } 
        else {
            angleMotor.set(0);
        }*/
        
    }

}
