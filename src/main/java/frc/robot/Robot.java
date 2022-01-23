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
WPI_TalonSRX dRF, dLF;
VictorSPX dRB, dLB, intakeMotor; 
DifferentialDrive drive;
XboxController driveController;
Arm arm;

  
  
  @Override
  public void robotInit() {
    dRF = new WPI_TalonSRX(2);
    dLF = new WPI_TalonSRX(6);
    dRB = new VictorSPX(12);
    dLB = new VictorSPX(13);
    dLB.follow(dLF);
    dRF.setInverted(true);
    dRB.setInverted(true);
    dRB.follow(dRF);
    driveController = new XboxController(0);
    drive = new DifferentialDrive(dLF, dRF);
    intakeMotor = new VictorSPX(14);
    arm = new Arm();

    
  }
  @Override
  public void robotPeriodic(){
   
  }

  @Override
  public void autonomousInit() {

  }
  @Override
  public void autonomousPeriodic() {
    //  i = i++ % 250;
    
    // if(i < 150) { 
    //   drive.arcadeDrive(0.5, 0.0);
    // } else if(i < 250){
    //  drive.arcadeDrive(-0.5,0.0);
    // }
    
    System.out.println(arm.getArmAngle());
    // arm.ArmPIDMove(45);
    
    
  }
  @Override
  public void teleopInit() {}
  int i =0;
  int k =0;
  enum IntakeSpeed {
    out,
    in,
    neutral
  }
  IntakeSpeed intakeSpeed;


  
  @Override
  public void teleopPeriodic() {

    boolean APression = driveController.getAButton();
    System.out.println(APression);
    if(APression) {
      arm.ArmPIDMove(0);
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
    
     if(tCL > 0.03){
       intakeSpeed = IntakeSpeed.in;
     }else if(tCR > 0.03){
       intakeSpeed = IntakeSpeed.out;
     }else{
       intakeSpeed = IntakeSpeed.neutral;
     }
     
    switch(intakeSpeed) {
      case out:
        intakeMotor.set(ControlMode.PercentOutput, 0.5);
        break;
      case in:
        intakeMotor.set(ControlMode.PercentOutput, -0.5);
        break;
      case neutral:
        intakeMotor.set(ControlMode.PercentOutput, 0);
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