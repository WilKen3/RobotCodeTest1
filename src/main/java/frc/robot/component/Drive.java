package frc.robot.component;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.State;
import frc.robot.subClass.Const;

public class Drive implements Component {
  
  WPI_TalonSRX dRF, dLF;
  VictorSPX dRB, dLB; 
  DifferentialDrive Ddrive;
  Drive drive;

  public Drive(){
    dRF = new WPI_TalonSRX(Const.DriveRightFront);
    dLF = new WPI_TalonSRX(Const.DriveLeftFront);
    dRB = new VictorSPX(Const.DriveRightBack);
    dLB = new VictorSPX(Const.DriveLeftBack);
    dLB.follow(dLF);
    dRF.setInverted(true);
    dRB.setInverted(true);
    dRB.follow(dRF);
    Ddrive = new DifferentialDrive(dLF, dRF);
  }
  
  
  
  public void autonomousInit(){}
    
  public void teleopInit(){}

  public void disabledInit(){}

  public void testInit(){}

  public void readSensors(){}

  public void applyState(){
    switch(State.driveState){
      case mDrive:
        Ddrive.arcadeDrive(State.forSpeed, State.sideSpeed);
        break;
      case neutral:
        Ddrive.arcadeDrive(0,0);
        break;


      
    }
  }
  

}
