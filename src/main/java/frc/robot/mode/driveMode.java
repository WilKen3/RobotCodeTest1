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
          State.armState = ArmState.k_armPID;
          State.armPidTargetAngle = 0;
        }else {
          State.armState = ArmState.k_release;
        }
        
        if(driveController.getRightBumper()){
          State.shooterState = ShooterState.intake;
        }else if(driveController.getLeftBumper()){
          State.shooterState = ShooterState.outTake;
        }else if(driveController.getLeftTriggerAxis()>Const.Deadband){
          State.shooterState = ShooterState.shooter;
        }else {
          State.shooterState = ShooterState.neutral;
        }
        State.forSpeed = driveController.getLeftY();
        State.sideSpeed = driveController.getRightX();

        if(Math.abs(State.forSpeed) > 0 || Math.abs(State.sideSpeed) > 0){
          State.driveState = DriveState.mDrive;
        }else {
          State.driveState = DriveState.neutral;
        }
    }
    
}
