package frc.robot.mode;

import edu.wpi.first.wpilibj.XboxController;

public abstract class Mode {
  XboxController driveController, operateController;
  public void addController(XboxController driveController, XboxController operateController) {
    this.driveController = driveController;
    this.operateController = operateController;
  }
  abstract public void changeMode();
  abstract public void changeState();
}