package frc.robot.subClass;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;**/
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**  import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
import frc.robot.subClass.State.*; */

public class Arm {
    private SensorCollection armPoint;
    private WPI_TalonSRX armMotor;
    

    public Arm(){
        armMotor = new WPI_TalonSRX(Const.ArmMotor);
        armPoint = new SensorCollection(armMotor); 
        armMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, Const.SFSPidIdx, Const.SFSTimeoutMS);
        armMotor.config_kF(Const.FslotIdx, Const.FVofP, Const.FTimeout);
        armMotor.config_kP(Const.PslotIdx, Const.PVofP, Const.PTimeout);
        armMotor.config_kI(Const.IslotIdx, Const.IVofP, Const.ITimeout);
        armMotor.config_kD(Const.DslotIdx, Const.DVofP, Const.DTimeout);
        armMotor.configMaxIntegralAccumulator(Const.MIAslotdx, Const.MIAiaccum, Const.MIATimeout);
        armMotor.setSensorPhase(true);
        armMotor.setInverted(true);
    }

    public double angleToPoint(double Angle) {
        double angleDiff = Angle - Const.LowAngle;
        double angleRange = Const.HighAngle - Const.LowAngle;
        double pointRange = Const.HighPoint - Const.LowPoint;
        return angleDiff*(pointRange/angleRange) + Const.LowPoint;
    }
    
    public double pointToAngle(double Point) {
        double pointDiff = Point - Const.LowPoint;
        double angleRange = Const.HighAngle - Const.LowAngle;
        double pointRange = Const.HighPoint - Const.LowPoint;
        return pointDiff*(angleRange/pointRange) + Const.LowAngle;
        
    }
    
    public double getArmAngle() {
        return pointToAngle(armPoint.getAnalogInRaw());
    }
   
    public void armSet(ControlMode controlMode, double input) {
        armMotor.set(controlMode, input);
    }
   
    public void ArmPIDMove(double Angle) {
        armMotor.set(ControlMode.Position, angleToPoint(Angle),
        DemandType.ArbitraryFeedForward, Const.ArmGCoef*Math.cos(Math.toRadians(getArmAngle())));
    }
   
    public void ArmRelease(){
        armMotor.set(ControlMode.PercentOutput, Const.ArmFedForCoef*Math.cos(Math.toRadians(getArmAngle())));
    }
}
