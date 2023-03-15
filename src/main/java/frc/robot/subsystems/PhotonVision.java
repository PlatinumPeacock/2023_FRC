package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class PhotonVision extends SubsystemBase{
    
    PhotonCamera camera;


    /** Creates a new PhotonVision. */
    public PhotonVision() {

        camera = new PhotonCamera(getName());
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
