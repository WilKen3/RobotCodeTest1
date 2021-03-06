package frc.robot.mode;

import frc.robot.State;
import frc.robot.State.*;
import frc.robot.subClass.Const;

public class ShooterMode extends Mode {

    @Override
    public void changeMode() {
        if(driveController.getBackButton()) {
            State.mode = Modes.k_drive;
        }
    }
    
    @Override
    public void changeState() {
        
        State.ArmOutputUp = driveController.getLeftTriggerAxis();
        State.ArmOutputDown = driveController.getRightTriggerAxis();
        if( driveController.getLeftTriggerAxis() > Const.Deadband) {
            State.armState = ArmState.s_percentOutputUp;
        }else if(driveController.getRightTriggerAxis() > Const.Deadband) {
            State.armState = ArmState.s_percentOutputDown;
        }else {
            State.armState = ArmState.s_keepPosition;
        }


       
        State.forSpeed = driveController.getLeftY();
        State.sideSpeed = driveController.getRightX();
        if(Math.abs(State.forSpeed) > 0 || Math.abs(State.sideSpeed) > 0){
            State.driveState = DriveState.s_sDrive;
          }else {
            State.driveState = DriveState.s_neutral;
          }
    }

}