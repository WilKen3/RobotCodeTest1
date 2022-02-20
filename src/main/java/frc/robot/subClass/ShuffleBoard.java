package frc.robot.subClass;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.component.*;

public class ShuffleBoard {
  Shooter shooter;
  Arm arm;
  
  public void ShuffleOutput(){
    SmartDashboard.putNumber("VelocityL", shooter.getVelocityL());
    SmartDashboard.putNumber("VelocityR", shooter.getVelocityR());
    SmartDashboard.putNumber("VelocityDiff",shooter.getVelocityL()+shooter.getVelocityR());
    SmartDashboard.putNumber("ArmAngle", arm.getArmAngle());
    // SmartDashboard.
    

  }
}
