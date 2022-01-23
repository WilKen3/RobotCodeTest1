package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
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
WPI_TalonSRX dRF, dLF, armMotor;
VictorSPX dRB, dLB, intakeMotor; 
DifferentialDrive drive;
XboxController driveController;
SensorCollection armEncoder;

  
  
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
    armMotor = new WPI_TalonSRX(3);

    armEncoder = new SensorCollection(armMotor);
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 30);
    armMotor.config_kF(0, 0, 30);
    armMotor.config_kP(0, 8, 30);
    armMotor.config_kI(0, 0.01, 30);
    armMotor.config_kD(0,10,30);
    armMotor.configMaxIntegralAccumulator(0,0,30);
    armMotor.setSensorPhase(true);
    armMotor.setInverted(true);
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

    System.out.print(armEncoder.getAnalogInRaw());
  }
  @Override
  public void teleopInit() {}
  int i =0;
  int k =0;
 


  @Override
  public void teleopPeriodic() {
  
    boolean armMotorInput = driveController.getBButton();
    if(armMotorInput == true){
      armMotor.set(ControlMode.PercentOutput,-);
    } else {
      armMotor.set(ControlMode.PercentOutput,-0.1);
    }



    double forwardSpeed =driveController.getLeftY();
    double rotationSpeed = driveController.getRightX();
    drive.arcadeDrive(-forwardSpeed,rotationSpeed);

    double intakeMotorInputL = driveController.getLeftTriggerAxis();
    double intakeMotorInputR = driveController.getRightTriggerAxis();
    if(intakeMotorInputL>0.03 && intakeMotorInputR>0.03){
      intakeMotor.set(ControlMode.PercentOutput,-intakeMotorInputL*0.1);
    } else if(intakeMotorInputL>0.03 && intakeMotorInputR<0.03){
      intakeMotor.set(ControlMode.PercentOutput,-intakeMotorInputL*0.1);
    } else if(intakeMotorInputL<0.03 && intakeMotorInputR>0.03){
      intakeMotor.set(ControlMode.PercentOutput,intakeMotorInputR*0.1);
    } else{
      intakeMotor.set(ControlMode.PercentOutput,0);
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