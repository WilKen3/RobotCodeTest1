package frc.robot.component;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.State;
import frc.robot.subClass.Const;

public class Drive implements Component {

  private WPI_TalonSRX dRF, dLF;
  private VictorSPX dRB, dLB;
  private DifferentialDrive Ddrive;
  Drive drive;
  SensorCollection dRFsensor, dLFsensor;

  public Drive(){
    dRF = new WPI_TalonSRX(Const.DriveRightFront);
    dRB = new VictorSPX(Const.DriveRightBack);
    dLF = new WPI_TalonSRX(Const.DriveLeftFront);
    dLB = new VictorSPX(Const.DriveLeftBack);

    dRF.setInverted(true);
    dRB.setInverted(true);
    dRB.follow(dRF);
    dLB.follow(dLF);
    Ddrive = new DifferentialDrive(dLF, dRF);

   
    dRF.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Const.SFSPidIdx, Const.SFSTimeoutMS);
    dLF.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Const.SFSPidIdx, Const.SFSPidIdx);

    
  }



  public void autonomousInit(){}

  public void teleopInit(){}

  public void disabledInit(){}

  public void testInit(){}

  public void readSensors(){
    State.dRFSpeed = dRF.getSelectedSensorVelocity();
    State.dLFSpeed = dLF.getSelectedSensorVelocity();
  }

  public void applyState(){
    switch(State.driveState){
      case s_mDrive:
        Ddrive.arcadeDrive(State.forSpeed, State.sideSpeed);
        break;
      case s_sDrive:
        Ddrive.arcadeDrive(State.forSpeed*Const.driveSpeedSlow, State.sideSpeed*Const.driveSpeedSlow);
        break;
      case s_neutral:
        Ddrive.arcadeDrive(Const.neutral,Const.neutral);
        break;



    }
  }


}
