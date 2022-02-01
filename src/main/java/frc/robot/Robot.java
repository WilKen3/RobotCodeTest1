package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
/**import com.ctre.phoenix.motorcontrol.FeedbackDevice;
 import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;**/
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
/**import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; **/
import edu.wpi.first.wpilibj.drive.

DifferentialDrive;

public class Robot extends TimedRobot {
WPI_TalonSRX dRF, dLF, shooterL, shooterR;
VictorSPX dRB, dLB, intakeMotor, intakeF, intakeB; 
DifferentialDrive drive;
XboxController driveController;
Arm arm;
DigitalInput ballSensorF, ballSensorB;

  
  
  @Override
  public void robotInit() {
    dRF = new WPI_TalonSRX(Const.DriveRightFront);
    dLF = new WPI_TalonSRX(Const.DriveLeftFront);
    dRB = new VictorSPX(Const.DriveRightBack);
    dLB = new VictorSPX(Const.DriveLeftBack);
    dLB.follow(dLF);
    dRF.setInverted(true);
    dRB.setInverted(true);
    dRB.follow(dRF);
    driveController = new XboxController(Const.DriveController);
    drive = new DifferentialDrive(dLF, dRF);
    arm = new Arm();
    ballSensorF = new DigitalInput(0);
    ballSensorB = new DigitalInput(1);
    intakeMotor = new VictorSPX(Const.IntakeMotor);

    intakeF = new VictorSPX(11);
    intakeB = new VictorSPX(15);
    shooterL = new WPI_TalonSRX(5);
    shooterR = new WPI_TalonSRX(4);
    shooterL.config_kP(0, 6, 30);
    shooterR.config_kP(0, 6, 30);

    
  }
  @Override
  public void robotPeriodic(){
   
  }

  @Override
  public void autonomousInit() {

  }
  @Override
  public void autonomousPeriodic() {
 
    
    System.out.println(arm.getArmAngle());
    // arm.ArmPIDMove(45);
    
    
  }
  @Override


  public void teleopInit() {}
  enum Intake {
    in,
    out,
    neutral,
    shoot
  }
  Intake intake;
  
  @Override
  public void teleopPeriodic() {

    //arm control
    boolean APression = driveController.getAButton();
    System.out.println(APression);
    if(APression) {
      arm.ArmPIDMove(0);
    }else {
      arm.ArmRelease();
    }

    // drive train
    double forwardSpeed =driveController.getLeftY();
    double rotationSpeed = driveController.getRightX();
    drive.arcadeDrive(-forwardSpeed,rotationSpeed);

   
    //intake belt using sensors 
    boolean SF = ballSensorF.get();
    boolean SB = ballSensorB.get();
    
    if(driveController.getRightBumper()){
      intake = Intake.in;
    } else if(driveController.getLeftBumper()){
      intake = Intake.out;
    } else if(driveController.getLeftTriggerAxis()>0.3){
      intake = Intake.shoot;
    } else {
      intake = Intake.neutral;
    }
    switch (intake) {
      case in:
        intakeMotor.set(ControlMode.PercentOutput, Const.IntakeSpeed);
        shooterL.set(ControlMode.PercentOutput, Const.IntakeSpeed);
        shooterR.set(ControlMode.PercentOutput, -Const.IntakeSpeed);
        intakeF.set(ControlMode.PercentOutput, -1);
        if(!SB){
          intakeB.set(ControlMode.PercentOutput, 0);
          intakeF.set(ControlMode.PercentOutput, 0);
        } else if(!SF) {
          intakeB.set(ControlMode.PercentOutput, -1);
        } else {
          intakeB.set(ControlMode.PercentOutput, 0);
          intakeF.set(ControlMode.PercentOutput, 0);
        }
        break;
      case out:
        intakeMotor.set(ControlMode.PercentOutput, Const.OuttakeSpeed);
        shooterL.set(ControlMode.PercentOutput, Const.OuttakeSpeed);
        shooterR.set(ControlMode.PercentOutput, -Const.OuttakeSpeed);
        intakeB.set(ControlMode.PercentOutput, 1);
        intakeF.set(ControlMode.PercentOutput, 1);
        break;
      case neutral:
        intakeMotor.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        shooterL.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        shooterR.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        intakeB.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        intakeF.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        break;    
      case shoot:
        shooterL.set(ControlMode.PercentOutput, Const.ShooterLeftOut);
        shooterR.set(ControlMode.PercentOutput, Const.ShooterRightOut);
        intakeB.set(ControlMode.PercentOutput, 1);
        intakeF.set(ControlMode.PercentOutput, 1);
    }
  }
  @Override
  public void disabledInit() {}
  @Override
  public void disabledPeriodic() {}
  @Override
  public void testInit() {}
  @Override
  public void testPeriodic() {}
}