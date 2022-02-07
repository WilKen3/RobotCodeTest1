package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subClass.Const;

public class State {
  XboxController driveController;
  State state;
  
 
  public static enum Intake {
    in,
    out,
    neutral,
    shoot
  }
  public Intake intake; 

  public static enum ArmState {
    setAngle,
    release
  }
  public ArmState armState;
  State(){
    driveController = new XboxController(Const.DriveController);
  }
  
  
  public double forwardSpeed;  
  public double rotationSpeed; 

  public void StateInit(){
    intake = Intake.neutral;
  }
  public void changeState(){
    if(driveController.getRightBumper()){
      intake = Intake.in;
    } else if(driveController.getLeftBumper()){
      intake = Intake.out;
    } else if(driveController.getLeftTriggerAxis()>0.3){
      intake = Intake.shoot;
    } else {
      intake = Intake.neutral;
    }

    if(driveController.getAButton()) {
      armState = ArmState.setAngle;
    } else {
      armState = ArmState.release;
    }
    forwardSpeed = driveController.getLeftY();
    rotationSpeed = driveController.getRightX();
  }

}


