package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.mode.*;
import frc.robot.subClass.Const;

public class State {
    public static Modes mode;

    public static ArmState armState;
    public static ShooterState shooterState;
    public static DriveState driveState;
    public static double armPidTargetAngle;
    public static double forSpeed;
    public static double sideSpeed;
    
    public static void StateInit() {
        XboxController driveController = new XboxController(Const.DriveControllerPort);
        XboxController operateController = new XboxController(Const.OperateControllerPort);
        for (Modes modes : Modes.values()) {
            modes.mode.addController(driveController, operateController);
        }

        stateReset();
    }

    public static void stateReset() {//set parameters to 0
      armState = ArmState.k_release;
      armPidTargetAngle = Const.LowAngle;
      shooterState = ShooterState.neutral;
      driveState = DriveState.neutral;
    }

    public enum ArmState {
      k_armPID,
      k_release,
    }
    public enum ShooterState {
      intake,
      outTake,
      shooter,
      neutral
    }
    public enum DriveState {
      mDrive,
      neutral
    }

    public enum Modes {
        k_drive(new DriveMode());  //k_drive(parameter), the parameter is a constructor of DriveMode.java
                                   //complicating enums require a semi-colon at the end
        private final Mode mode;   //gives name to Mode(class)
        Modes(Mode mode) {         //
           this.mode = mode;
        }

        public void changeMode() {
            this.mode.changeMode();
        }

        public void changeState() {
            this.mode.changeState();
        }
    }
    
}