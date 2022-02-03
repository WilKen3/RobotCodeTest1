package frc.robot.subClass;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;




public class Drive {

  WPI_TalonSRX dRF, dLF;
  VictorSPX dRB, dLB; 
  DifferentialDrive drive;

  
  Drive(){
      dRF = new WPI_TalonSRX(Const.DriveRightFront);
      dLF = new WPI_TalonSRX(Const.DriveLeftFront);
      dRB = new VictorSPX(Const.DriveRightBack);
      dLB = new VictorSPX(Const.DriveLeftBack);
      dLB.follow(dLF);
      dRF.setInverted(true);
      dRB.setInverted(true);
      dRB.follow(dRF);
      drive = new DifferentialDrive(dLF, dRF);
    }
  public void drive(double forwardSpeed, double rotationSpeed){
    drive.arcadeDrive(-forwardSpeed,rotationSpeed);
  }
    

}
