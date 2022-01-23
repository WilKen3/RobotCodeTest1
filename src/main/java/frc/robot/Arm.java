package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;**/
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.XboxController;
/**import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; **/


public class Arm {
    private SensorCollection armPoint;
    private WPI_TalonSRX armMotor;

    Arm(){
        armMotor = new WPI_TalonSRX(3);
        armPoint = new SensorCollection(armMotor); 
        armMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 30);
        armMotor.config_kF(0, 0, 30);
        armMotor.config_kP(0, 8, 30);
        armMotor.config_kI(0, 0.01, 30);
        armMotor.config_kD(0,10,30);
        armMotor.configMaxIntegralAccumulator(0,0,30);
        armMotor.setSensorPhase(true);
        armMotor.setInverted(true);
    }

    public double angleToPoint(double Angle) {
        double angleDiff = Angle - (-32);
        double angleRange = 86 - (-32);
        double pointRange = 516 - 181;
        return angleDiff*(pointRange/angleRange) + 181;
    }
    public double pointToAngle(double Point) {
        double pointDiff = Point - 181;
        double angleRange = 86 - (-32);
        double pointRange = 516 - 181;
        return pointDiff*(angleRange/pointRange) + (-32);
        
    }
    public double getArmAngle() {
        return pointToAngle(armPoint.getAnalogInRaw());
    }
    public void armSet(ControlMode controlMode, double input) {
        armMotor.set(controlMode, input);
    }
    public void ArmPIDMove(double Angle) {
        armMotor.set(ControlMode.Position, angleToPoint(Angle),
        DemandType.ArbitraryFeedForward, 0.13*Math.cos(Math.toRadians(getArmAngle())));
    }
    public void ArmRelease(){
        armMotor.set(ControlMode.PercentOutput, 0.05);
    }
}
