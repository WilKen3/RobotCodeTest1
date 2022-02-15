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

    public static void 
    
    
    stateReset() {//set parameters to 0
      armState = ArmState.s_release;
      armPidTargetAngle = Const.LowAngle;
      shooterState = ShooterState.s_neutral;
      driveState = DriveState.s_neutral;
    }

    public enum ArmState {
      s_armPID,
      s_release,
      s_keepPosition,
      s_percentOutput
    }
    
    public enum ShooterState {
      s_intake,
      s_outTake,
      s_shooter,
      s_neutral
    }
    public enum DriveState {
      s_mDrive,
      s_sDrive,
      s_neutral
    }
    public enum ModeChange{
      s_driveMode,
      s_shooterMode
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