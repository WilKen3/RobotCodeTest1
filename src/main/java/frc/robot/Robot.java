package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**import com.ctre.phoenix.motorcontrol.FeedbackDevice;
 import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.drive.
DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; **/

import frc.robot.subClass.*;

public class Robot extends TimedRobot {

Shooter shooter;
State state;
Arm arm;
Drive drive;
XboxController driveController;
  
  @Override
  public void robotInit() {
    driveController = new XboxController(Const.DriveController);
    arm = new Arm();
    shooter = new Shooter();
    state = new State();    
  }
  
  @Override
  public void robotPeriodic() {}
  
  @Override
  public void autonomousInit() {}
  
  @Override
  public void autonomousPeriodic() {/**System.out.println(arm.getArmAngle());    // arm.ArmPIDMove(45); **/}
  
  @Override
  public void teleopInit() {}

  
  @Override
  public void teleopPeriodic() {
    
    //arm control
    
    System.out.println(driveController.getAButton());
    if(driveController.getAButton()) {
      arm.ArmPIDMove(0);
    }else {
      arm.ArmRelease();
    }

    // drive train
    
    //drive.drive(state.forwardSpeed, state.rotationSpeed);
   
    //intake belt using sensors 
    

      if(driveController.getRightBumper()){
        state.intake = State.Intake.in;
      } else if(driveController.getLeftBumper()){
        state.intake = State.Intake.out;
      } else if(driveController.getLeftTriggerAxis()>0.3){
        state.intake = State.Intake.shoot;
      } else {
        state.intake = State.Intake.neutral;
      }
    shooter.applyState(state.intake);

    SmartDashboard.putNumber("VelocityL", shooter.getVelocityL());
    SmartDashboard.putNumber("VelocityR", shooter.getVelocityR());
    SmartDashboard.putNumber("VelocityDiff",shooter.getVelocityL()+shooter.getVelocityR());
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


