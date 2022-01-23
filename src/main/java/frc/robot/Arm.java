package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;**/
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
/**import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; **/


public class Arm {
    SensorCollection armPoint;
    WPI_TalonSRX armMotor;

    Arm(){
        armMotor = new WPI_TalonSRX(3);
        armPoint = new SensorCollection(armMotor);
    }
    
    public double angleToPoint(double Angle) {
        double angleDiff = Angle - (-32);
        double angleRange = 86 - (-32);
        double pointRange = 516 - 181;
        return angleDiff*(pointRange/angleRange) + 181;
        
    }

}
