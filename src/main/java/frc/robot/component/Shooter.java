package frc.robot.component;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.subClass.Const;
import frc.robot.State;

public class Shooter implements Component {
  
  private WPI_TalonSRX shooterL, shooterR;
  private VictorSPX intakeMotor, intakeF, intakeB;
  private DigitalInput ballSensorF, ballSensorB;
  

  public Shooter(){
    shooterL = new WPI_TalonSRX(Const.shooterL);
    shooterR = new WPI_TalonSRX(Const.shooterR);
    
    shooterL.configAllSettings(Const.shooterLConfig);
    // shooterL.setSelectedSensorPosition(Const.ShooterInitialPosition);
    shooterR.configAllSettings(Const.shooterRConfig);
    // shooterR.setSelectedSensorPosition(Const.ShooterInitialPosition);

    intakeMotor = new VictorSPX(Const.IntakeMotor);

    intakeF = new VictorSPX(Const.IntakeBeltF);
    intakeB = new VictorSPX(Const.IntakeBeltB);
    ballSensorF = new DigitalInput(Const.IntakeSensorF);
    ballSensorB = new DigitalInput(Const.IntakeSensorB);
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
    return(shooterR.getSelectedSensorVelocity(Const.PrimaryClosedLoop));
  }
  
  public double getVelocityL(){
    return(shooterL.getSelectedSensorVelocity(Const.PrimaryClosedLoop));
  }

  public void shoot(){
    shooterL.set(ControlMode.Velocity, Const.ShooterSpeedL);
    shooterR.set(ControlMode.Velocity, Const.ShooterSpeedR);
    
    intakeMotor.set(ControlMode.PercentOutput, Const.Neutral);

    intakeB.set(ControlMode.PercentOutput, Const.PMaxOutput);
    intakeF.set(ControlMode.PercentOutput, Const.PMaxOutput);
  }

  public void intake(){
    boolean SensorFront = ballSensorF.get();
    boolean SensorBack = ballSensorB.get();

    shooterL.set(ControlMode.PercentOutput, -Const.IntakeSpeed);
    shooterR.set(ControlMode.PercentOutput, Const.IntakeSpeed);

    intakeMotor.set(ControlMode.PercentOutput, Const.IntakeSpeed);  

    intakeF.set(ControlMode.PercentOutput, Const.MMaxOutput);
        if(!SensorBack){
          intakeB.set(ControlMode.PercentOutput, Const.NoOutput);
        } else if(!SensorFront) {
          intakeB.set(ControlMode.PercentOutput, Const.MMaxOutput);
        } else {
          intakeB.set(ControlMode.PercentOutput, Const.NoOutput);
        }

  }

  public void outtake(){
    shooterL.set(ControlMode.PercentOutput, -Const.OuttakeSpeed);
    shooterR.set(ControlMode.PercentOutput, Const.OuttakeSpeed);

    intakeMotor.set(ControlMode.PercentOutput, Const.OuttakeSpeed);

    intakeB.set(ControlMode.PercentOutput, Const.PMaxOutput);
    intakeF.set(ControlMode.PercentOutput, Const.PMaxOutput);
  }
  
  public void neutral(){
    shooterL.set(ControlMode.PercentOutput, Const.Neutral);
    shooterR.set(ControlMode.PercentOutput, Const.Neutral);

    intakeMotor.set(ControlMode.PercentOutput, Const.Neutral);

    intakeB.set(ControlMode.PercentOutput, Const.Neutral);
    intakeF.set(ControlMode.PercentOutput, Const.Neutral);
  }

}
