package frc.robot.component;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.State;
import frc.robot.subClass.*;
import com.ctre.phoenix.motorcontrol.*;

public class Arm implements Component{
  private SensorCollection armPoint;
  private WPI_TalonSRX armMotor;
  
 
  public Arm(){
    armMotor = new WPI_TalonSRX(Const.ArmMotor);
    armPoint = new SensorCollection(armMotor); 
    armMotor.configAllSettings(Const.armConfig);
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
    DemandType.ArbitraryFeedForward, armOffSet());
  }

  public void ArmPercentOutputDown() {
    if(getArmAngle() == Const.LowAngle) {
      armMotor.set(ControlMode.PercentOutput, Const.Neutral);
    }else if(getArmAngle() < Const.HorizonAngle) {
      armMotor.set(ControlMode.PercentOutput, Const.ArmFedForCoef*Math.cos(Math.toRadians(getArmAngle())));
    }else {
      armMotor.set(ControlMode.PercentOutput, Const.ArmDown*Math.cos(Math.toRadians(getArmAngle())));
    }
  }
  
  public void ArmPercentOutputUp(double input) {
    if(getArmAngle() == Const.HighAngle) {
      armMotor.set(ControlMode.PercentOutput, Const.Neutral);
    } else{
      armMotor.set(ControlMode.PercentOutput, input*Const.ArmControl);
    }
  }

  public void ArmKeepPosition() {
    double currentPosition = armPoint.getAnalogInRaw();
    armMotor.set(ControlMode.Position, currentPosition, 
    DemandType.ArbitraryFeedForward, armOffSet());


  }
 
  public double armOffSet(){
    double armAngle = getArmAngle();
    double angleToForce = Math.cos(Math.toRadians(armAngle));
    return angleToForce*Const.ArmGCoef;
  }

  public void ArmRelease(){
    armMotor.set(ControlMode.PercentOutput, Const.ArmFedForCoef*Math.cos(Math.toRadians(getArmAngle())));
  }
  
  public void autonomousInit(){}
    
  public void teleopInit(){}

  public void disabledInit(){}

  public void testInit(){}

  public void readSensors(){
    State.armAngle = getArmAngle();
    
  }

  public void applyState(){
    switch (State.armState) {
      case s_armPID:
        ArmPIDMove(State.armPidTargetAngle);
        break;
      case s_release:
        ArmRelease();
        break;
      case s_keepPosition:
        ArmKeepPosition();
        break;
      case s_percentOutputUp:
        ArmPercentOutputUp(State.ArmOutputUp);
        break;
      case s_percentOutputDown:
        ArmPercentOutputDown();
        break;
        } 
    }
  }

