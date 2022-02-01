package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot.Intake;



public class Shooter {
  
  private WPI_TalonSRX shooterL, shooterR;

  Shooter(){
    shooterL = new WPI_TalonSRX(5);
    shooterR = new WPI_TalonSRX(4);
    shooterL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    shooterR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    shooterL.config_kP(0, 6, 30);
    shooterR.config_kP(0, 6, 30);
  }

  
  
  public void ShooterState(Robot state){
    switch(state.intake){
      case in:{
        shooterL.set(ControlMode.PercentOutput, -Const.IntakeSpeed);
        shooterR.set(ControlMode.PercentOutput, Const.IntakeSpeed);
        break;
        }
      case out:{
        shooterL.set(ControlMode.PercentOutput, -Const.OuttakeSpeed);
        shooterR.set(ControlMode.PercentOutput, Const.OuttakeSpeed);
        break;
      }
      case neutral:{
        shooterL.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        shooterR.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        break;
      }
      case shoot:{
        shooterL.set(ControlMode.PercentOutput, Const.ShooterLeftOut);
        shooterR.set(ControlMode.PercentOutput, Const.ShooterRightOut);
      }
    }

  }
}
