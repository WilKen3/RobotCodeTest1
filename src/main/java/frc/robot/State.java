package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.mode.*;
import frc.robot.subClass.Const;

public class State {
    public static Modes mode;
    //ステート
    public static ArmState armState;
    public static ShooterState shooterState;
    public static DriveState driveState;
    //アームのアングル設定
    public static double armPidTargetAngle;
    public static double armAngle;
    //センサー類：値の読み取り用
    public static double shooterLspeed, shooterRspeed;
    public static double dRFSpeed, dLFSpeed;
    public static double forSpeed, sideSpeed;
    //アームの位置
    public static double ArmOutputUp, ArmOutputDown;
    
    
    public static void StateInit() {
        XboxController driveController = new XboxController(Const.DriveControllerPort);
        XboxController operateController = new XboxController(Const.OperateControllerPort);
        for (Modes modes : Modes.values()) {
            modes.mode.addController(driveController, operateController);
        }

        stateReset();
    }

    public static void stateReset() {//set parameters to 0
      armState = ArmState.s_release;
      armPidTargetAngle = Const.LowAngle;
      shooterState = ShooterState.s_neutral;
      driveState = DriveState.s_neutral;
    }

    public enum ArmState {
      s_armPID,
      s_release,
      s_keepPosition,
      s_percentOutputUp,
      s_percentOutputDown
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

    public enum Modes {
      /** k_drive(parameter), the parameter is a constructor of DriveMode.java
       * complicating enums require a semi-colon at the end
       * gives name to Mode(class)
      */
        k_drive(new DriveMode()),
        k_shooter(new ShooterMode());

        private final Mode mode;   

        Modes(Mode mode) {         
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