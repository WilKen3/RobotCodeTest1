package frc.robot.mode;

import edu.wpi.first.wpilibj.XboxController;

public abstract class Mode {
  XboxController driveController, operateController;
  public void addController(XboxController driveController, XboxController operatController) {
    this.driveController = driveController;
    this.operateController = operatController;
  }
  abstract public void changeMode();
  abstract public void changeState();
}