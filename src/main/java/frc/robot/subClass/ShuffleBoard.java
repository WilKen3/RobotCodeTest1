package frc.robot.subClass;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.component.*;
import frc.robot.State;

public class ShuffleBoard {
  Shooter shooter;
  Arm arm;
  
  public void ShuffleOutput(){
    SmartDashboard.putNumber("ShooterVelocityL", State.shooterLspeed);
    SmartDashboard.putNumber("VelocityR", State.shooterRspeed);
    SmartDashboard.putNumber("VelocityDiff",State.shooterLspeed+State.shooterRspeed);
    SmartDashboard.putNumber("ArmAngle", State.armAngle);
    SmartDashboard.putNumber("DriveRightSpeed", State.dRFSpeed);
    SmartDashboard.putNumber("DriveLeftSpeed", State.dLFSpeed);
    

  }
}
