package frc.robot.mode;
import frc.robot.State;
import frc.robot.State.*;
import frc.robot.subClass.Const;

public class DriveMode extends Mode {

    @Override
    public void changeMode() {

    }

    @Override
    public void changeState() {
        if(driveController.getAButton()) {
          State.armState = ArmState.s_armPID;
          State.armPidTargetAngle = 0;
        }else {
          State.armState = ArmState.s_release;
        }
        
        if(driveController.getRightBumper()){
          State.shooterState = ShooterState.s_intake;
        }else if(driveController.getLeftBumper()){
          State.shooterState = ShooterState.s_outTake;
        }else if(driveController.getLeftTriggerAxis()>Const.Deadband){
          State.shooterState = ShooterState.s_shooter;
        }else {
          State.shooterState = ShooterState.s_neutral;
        }
        State.forSpeed = driveController.getLeftY();
        State.sideSpeed = driveController.getRightX();

        if(Math.abs(State.forSpeed) > 0 || Math.abs(State.sideSpeed) > 0){
          State.driveState = DriveState.s_mDrive;
        }else {
          State.driveState = DriveState.s_neutral;
        }
    }
    
}
