package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.SensorCollection;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;**/
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
    intakeMotor = new VictorSPX(Const.IntakeMotor);
    arm = new Arm();
    intakeF = new VictorSPX(11);
    intakeB = new VictorSPX(15);
    shooterL = new WPI_TalonSRX(5);
    shooterR = new WPI_TalonSRX(4);

    
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
 
  enum IntakeSpeed {
    out,
    in,
    neutral
  }
  IntakeSpeed intakeSpeed;

  enum IntakeBelt { 
    out,
    in,
    neutral
}
IntakeBelt intakeBelt;

  enum ShooterMotor {
    in,
    out,
    neutral
  }
  ShooterMotor shooterMotor;
  
  @Override
  public void teleopPeriodic() {

    boolean APression = driveController.getAButton();
    System.out.println(APression);
    if(APression) {
      arm.ArmPIDMove(Const.SetAngle);
    }else {
      arm.ArmRelease();
    }
  
    // boolean armMotorInput = driveController.getBButton();
    // if(armMotorInput == true){
    //   arm.armSet(ControlMode.PercentOutput,-0.5);
    // } else {
    //   arm.armSet(ControlMode.PercentOutput,-0.1);
    // }


    double forwardSpeed =driveController.getLeftY();
    double rotationSpeed = driveController.getRightX();
    drive.arcadeDrive(-forwardSpeed,rotationSpeed);

    double tCL = driveController.getLeftTriggerAxis();
    double tCR = driveController.getRightTriggerAxis();
    
    if(tCL > Const.Deadband){
      intakeSpeed = IntakeSpeed.in;
    }else if(tCR > Const.Deadband){
      intakeSpeed = IntakeSpeed.out;
    }else{
      intakeSpeed = IntakeSpeed.neutral;
    }
    switch(intakeSpeed) {
      case out:
        intakeMotor.set(ControlMode.PercentOutput, Const.IntakeSpeed);
        break;
      case in:
        intakeMotor.set(ControlMode.PercentOutput, Const.OuttakeSpeed);
        break;
      case neutral:
        intakeMotor.set(ControlMode.PercentOutput, Const.IntakeNeutral);
        break;
    }

    if (driveController.getLeftBumper()){
      intakeBelt = IntakeBelt.out;
    }else if(driveController.getRightBumper()) {
      intakeBelt = IntakeBelt.in;
    }else {
      intakeBelt = IntakeBelt.neutral;
    }
    switch(intakeBelt) {
      case out:
        intakeF.set(ControlMode.PercentOutput, 0.5);
        intakeB.set(ControlMode.PercentOutput, 0.5);
        break;
      case in:
        intakeF.set(ControlMode.PercentOutput, -0.5);
        intakeB.set(ControlMode.PercentOutput, -0.5);
        break;
      case neutral:
        intakeF.set(ControlMode.PercentOutput,0);
        intakeB.set(ControlMode.PercentOutput,0);

    }
    
    if (driveController.getLeftBumper()) {
      shooterMotor = ShooterMotor.out;
    }else if(driveController.getRightBumper()) {
      shooterMotor = ShooterMotor.in;
    }else {
      shooterMotor = ShooterMotor.neutral;
    }
    switch(shooterMotor) {
      case out:
        shooterR.set(ControlMode.PercentOutput,0.5);
        shooterL.set(ControlMode.PercentOutput, -0.5);
        break;
      case in:
        shooterR.set(ControlMode.PercentOutput, -0.5);
        shooterL.set(ControlMode.PercentOutput, 0.5);
        break;
      case neutral:
        shooterR.set(ControlMode.PercentOutput, 0);
        shooterL.set(ControlMode.PercentOutput, 0);
        break;
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