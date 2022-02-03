package frc.robot.subClass;

import edu.wpi.first.wpilibj.XboxController;

public class State {
  XboxController driveController;

  public static enum Intake {
    in,
    out,
    neutral,
    shoot
  }
  public Intake intake; 
  
  public double forwardSpeed =driveController.getLeftY();
  public double rotationSpeed = driveController.getRightX();

  public void StateInit(){
    //何書けばいいか分からん
  }
}


