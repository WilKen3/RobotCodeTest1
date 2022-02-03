package frc.robot.subClass;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subClass.State.*;


public class Shooter {
  
  private WPI_TalonSRX shooterL, shooterR;
  private VictorSPX intakeMotor, intakeF, intakeB;
  private DigitalInput ballSensorF, ballSensorB;
  

  public Shooter(){
    shooterL = new WPI_TalonSRX(5);
    shooterR = new WPI_TalonSRX(4);
    shooterL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    shooterR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    shooterL.config_kP(0, 6, 30);
    shooterR.config_kP(0, 6, 30);

    intakeMotor = new VictorSPX(Const.IntakeMotor);

    intakeF = new VictorSPX(11);
    intakeB = new VictorSPX(15);
    ballSensorF = new DigitalInput(0);
    ballSensorB = new DigitalInput(1);
  }
  
  public void applyState(Intake intake){
    switch(intake){
      case in:
        intake();
        break;
      
      case out:
        outtake();
        break;
      
      case neutral:
        neutral();
        break;
      
      case shoot:
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
    shooterL.set(ControlMode.PercentOutput, Const.ShooterLeftOut);
    shooterR.set(ControlMode.PercentOutput, Const.ShooterRightOut);
    
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
  
