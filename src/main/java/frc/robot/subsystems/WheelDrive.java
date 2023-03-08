package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

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
        
        pidController = new PIDController (0.1, 0, 0);
        pidController.enableContinuousInput(-180, 180);
        pidController.setTolerance(10);

    }


    public void drive(double speed, double angle) {
        speedMotor.set (speed);
         
        
        double setpoint = angle;
        
        /*
        setpoint = angle * (Constants.MAX_VOLTS * 0.5) + (Constants.MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
    if (setpoint < 0) {
        setpoint = Constants.MAX_VOLTS + setpoint;
    }
    if (setpoint > Constants.MAX_VOLTS) {
        setpoint = setpoint - Constants.MAX_VOLTS;
    }
    */
    


    //pidController.setTolerance(0.02);
    pidController.setSetpoint (setpoint);
    
    if (!pidController.atSetpoint()) {
        //angleMotor.set(0.1 * pidController.calculate((encoder.getPosition() / 4096) * 360, setpoint));
        //angleMotor.set(0.1 * pidController.calculate(encoder.getPosition(), setpoint));
    }
    System.out.println((0.1 * pidController.calculate(encoder.getPosition(), setpoint)));
    //angleMotor.setVoltage(feedforward.calculate);

   //setpoint = encoder.getAbsolutePosition() - angle;
   //pidController.calculate(setpoint);
    //System.out.println(pidController.calculate((encoder.getPosition() / 4096) * 180, setpoint));
    }
}
