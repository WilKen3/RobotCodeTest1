package frc.robot.component;


import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;



import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subClass.Const;
import frc.robot.State;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter implements Component {
  
  private WPI_TalonSRX shooterL, shooterR;
  private VictorSPX intakeMotor, intakeF, intakeB;
  private DigitalInput ballSensorF, ballSensorB;
  //private SensorCollection shooterLsensor, shooterRsensor;

  public Shooter(){
    shooterL = new WPI_TalonSRX(Const.shooterL);
    shooterR = new WPI_TalonSRX(Const.shooterR);
    
    //shooterLsensor = new SensorCollection(shooterL);
    shooterL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Const.SFSPidIdx, Const.SFSTimeoutMS);
    shooterL.setSelectedSensorPosition(Const.ShooterInitialPosition);
    shooterL.config_kP(Const.PslotIdx, Const.kPshooterL, Const.SFSTimeoutMS);
    shooterL.config_kI(Const.IslotIdx, Const.kIshooterL, Const.SFSTimeoutMS);
    shooterL.config_kD(Const.DslotIdx, Const.kDshooterL, Const.SFSTimeoutMS);
    //shooterL.configMaxIntegralAccumulator(0, 30000,30);

    // shooterRsensor = new SensorCollection(shooterR);
    shooterR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Const.SFSPidIdx, Const.SFSTimeoutMS);
    shooterR.setSelectedSensorPosition(Const.ShooterInitialPosition);
    shooterR.config_kP(Const.PslotIdx, Const.kPshooterR, Const.SFSTimeoutMS);
    shooterR.config_kI(Const.IslotIdx, Const.kIshooterR, Const.SFSTimeoutMS);
    shooterR.config_kD(Const.DslotIdx, Const.kDshooterR, Const.SFSTimeoutMS);
    //shooterR.configMaxIntegralAccumulator(0, 30000,30);
    

    intakeMotor = new VictorSPX(Const.IntakeMotor);

    intakeF = new VictorSPX(11);
    intakeB = new VictorSPX(15);
    ballSensorF = new DigitalInput(0);
    ballSensorB = new DigitalInput(1);
  }
  
  public void autonomousInit(){}
    
  public void teleopInit(){}

  public void disabledInit(){}

  public void testInit(){}

  public void readSensors(){
    State.shooterLspeed = getVelocityL();
    State.shooterRspeed = getVelocityR();
  }

  public void applyState(){
    switch(State.shooterState){
      case s_intake:
        intake();
        break;
      
      case s_outTake:
        outtake();
        break;
      
      case s_neutral:
        neutral();
        break;
      
      case s_shooter:
        shoot();
        break;
    }

  
}

  public double getVelocityR(){
    return(shooterR.getSelectedSensorVelocity(0));
  }
  
  public double getVelocityL(){
    return(shooterL.getSelectedSensorVelocity(0));
  }

  public void shoot(){
    shooterL.set(ControlMode.Velocity, -5000);
    shooterR.set(ControlMode.Velocity, 5000);
    
    intakeMotor.set(ControlMode.PercentOutput, Const.IntakeNeutral);

    intakeB.set(ControlMode.PercentOutput, 1);
    intakeF.set(ControlMode.PercentOutput, 1);
  }

  public void intake(){
    boolean SF = ballSensorF.get();
    boolean SB = ballSensorB.get();

    shooterL.set(ControlMode.PercentOutput, -Const.IntakeSpeed);
    shooterR.set(ControlMode.PercentOutput, Const.IntakeSpeed);

    intakeMotor.set(ControlMode.PercentOutput, Const.IntakeSpeed);  

    intakeF.set(ControlMode.PercentOutput, -1);
        if(!SB){
          intakeB.set(ControlMode.PercentOutput, 0);
        } else if(!SF) {
          intakeB.set(ControlMode.PercentOutput, -1);
        } else {
          intakeB.set(ControlMode.PercentOutput, 0);
        }

  }

  public void outtake(){
    shooterL.set(ControlMode.PercentOutput, -Const.OuttakeSpeed);
    shooterR.set(ControlMode.PercentOutput, Const.OuttakeSpeed);

    intakeMotor.set(ControlMode.PercentOutput, Const.OuttakeSpeed);

    intakeB.set(ControlMode.PercentOutput, 1);
    intakeF.set(ControlMode.PercentOutput, 1);
  }
  
  public void neutral(){
    shooterL.set(ControlMode.PercentOutput, Const.IntakeNeutral);
    shooterR.set(ControlMode.PercentOutput, Const.IntakeNeutral);

    intakeMotor.set(ControlMode.PercentOutput, Const.IntakeNeutral);

    intakeB.set(ControlMode.PercentOutput, Const.IntakeNeutral);
    intakeF.set(ControlMode.PercentOutput, Const.IntakeNeutral);
  }

}
