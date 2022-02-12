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
    dRB = new VictorSPX(Const.DriveRightBack);
    dLF = new WPI_TalonSRX(Const.DriveLeftFront);
    dLB = new VictorSPX(Const.DriveLeftBack);

    dRF.setInverted(true);
    dRB.setInverted(true);
    dRB.follow(dRF);
    dLB.follow(dLF);
    Ddrive = new DifferentialDrive(dLF, dRF);
  }



  public void autonomousInit(){}

  public void teleopInit(){}

  public void disabledInit(){}

  public void testInit(){}

  public void readSensors(){}

  public void applyState(){
    switch(State.driveState){
      case s_mDrive:
        Ddrive.arcadeDrive(State.forSpeed, State.sideSpeed);
        break;
      case s_neutral:
        Ddrive.arcadeDrive(0,0);
        break;



    }
  }


}
