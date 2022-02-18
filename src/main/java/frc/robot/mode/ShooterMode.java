package frc.robot.mode;


//import edu.wpi.first.wpilibj.XboxController;
import frc.robot.State;
import frc.robot.State.*;
import frc.robot.subClass.Const;

public class ShooterMode extends Mode {

    @Override
    public void changeMode() {



    }
    
    @Override
    public void changeState() {
        

        if( driveController.getLeftTriggerAxis() > Const.Deadband) {
            State.armState = ArmState.s_percentOutputUp;
        }else if(driveController.getRightTriggerAxis() > Const.Deadband) {
            State.armState = ArmState.s_percentOutputDown;
        }else {
            State.armState = ArmState.s_keepPosition;
        }
        State.lTrigger = driveController.getLeftTriggerAxis();
        State.rTrigger = driveController.getRightTriggerAxis();
        
    }

}