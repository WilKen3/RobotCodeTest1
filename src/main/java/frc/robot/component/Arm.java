package frc.robot.component;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.State;
import frc.robot.mode.*;
import frc.robot.subClass.*;
import com.ctre.phoenix.motorcontrol.*;

public class Arm implements Component{
  private SensorCollection armPoint;
  private WPI_TalonSRX armMotor;
  DriveMode dMode;
 
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
  
  public void autonomousInit(){}
    
  public void teleopInit(){}

  public void disabledInit(){}

  public void testInit(){}

  public void readSensors(){}

  public void applyState(){
    switch (State.armState) {
      case k_armPID:
        ArmPIDMove(State.armPidTargetAngle);
        break;
      case k_release:
        ArmRelease();
        break;
    }
  }
}

