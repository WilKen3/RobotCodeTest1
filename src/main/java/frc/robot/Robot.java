package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.component.Arm;
import frc.robot.component.Drive;
import frc.robot.component.Shooter;

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
ShuffleBoard shuffleBoard;
XboxController driveController;
  
  @Override
  public void robotInit() {
    driveController = new XboxController(Const.DriveController);
    arm = new Arm();
    shooter = new Shooter();
    state = new State();    
    drive = new Drive();

  }
  
  @Override
  public void robotPeriodic() {}
  @Override
  public void autonomousInit() {}
  @Override
  public void autonomousPeriodic() {}
  @Override
  public void teleopInit() {}
  
  @Override
  public void teleopPeriodic() {
    
    //arm control
    //intake belt using sensors 

    state.changeState();

    shuffleBoard.ShuffleOutput();

    shooter.applyState(state.intake);

    arm.applyState(state.armState);

    drive.drive(state.forwardSpeed, state.rotationSpeed);
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


