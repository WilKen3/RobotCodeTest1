package frc.robot.mode;


import edu.wpi.first.wpilibj.XboxController;
import frc.robot.State;
import frc.robot.State.*;
import frc.robot.subClass.Const;

public class ShooterMode extends Mode {

    @Override
    public void changeMode() {



    }

    @Override
    public void changeState() {
        double lTrigger = driveController.getLeftTriggerAxis();
        double rTrigger = driveController.getRightTriggerAxis();

        if(lTrigger > Const.Deadband) {
            State.armState = ArmState.s_percentOutput;
        }else if(rTrigger > Const.Deadband) {
            State.armState = ArmState.s_percentOutput;
        }else {
            State.armState = ArmState.s_keepPosition;
        }

        
    }

}