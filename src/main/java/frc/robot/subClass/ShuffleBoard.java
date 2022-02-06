package frc.robot.subClass;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.component.Shooter;

public class ShuffleBoard {
  Shooter shooter;
  ShuffleBoard(){
  }
  public void ShuffleOutput(){
    SmartDashboard.putNumber("VelocityL", shooter.getVelocityL());
    SmartDashboard.putNumber("VelocityR", shooter.getVelocityR());
    SmartDashboard.putNumber("VelocityDiff",shooter.getVelocityL()+shooter.getVelocityR());

  }
}
