package frc.robot.subClass;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class Const {
    //Deadband
    public static final double Deadband = 0.2;

    //PORTS
    //ControllerPort(コントローラーのポート)
    public static final int DriveControllerPort = 0;
    public static final int OperateControllerPort = 1;
    
    // motor 
    public static final int DriveController = 0;
    public static final int DriveRightFront = 2;
    public static final int DriveLeftFront = 6;
    public static final int DriveRightBack = 12;
    public static final int DriveLeftBack = 13;
    
    public static final int IntakeMotor = 14;
    public static final int IntakeSensorF = 0;
    public static final int IntakeSensorB = 1;
    public static final int ArmMotor = 3;
    public static final int shooterR = 4;
    public static final int shooterL = 5;
    public static final int IntakeBeltF = 11;
    public static final int IntakeBeltB = 15;

    // intakemotor
    public static final double IntakeSpeed = -0.3;
    public static final double OuttakeSpeed = 0.3;
    public static final double ShooterLeftOut = -0.7;
    public static final double ShooterRightOut = 0.7;

    public static final double ArmControl = 0.5;

    //driveSpeed
    public static final double driveSpeedSlow = 0.5;
    public static final double Neutral = 0;
    
    // angle to point, point to angle
    public static final int LowAngle = -32;
    public static final int HighAngle = 86;
    public static final int HorizonAngle = 0;
    public static final int LowPoint = 181;
    public static final int HighPoint = 516;

    // Arm, Anti-gravity (Coef = coeffiecient, FF = freefall, G = gravity)
    public static final double ArmGCoef = 0.13;
    public static final double ArmFedForCoef = 0.05;
    public static final double ArmDown = 0.01;

    public static final double PMaxOutput = 1;
    public static final double NoOutput = 0;
    public static final double MMaxOutput = -1;

    public static final double ShooterSpeedL = -5000;
    public static final double ShooterSpeedR = 5000;
 
    // feedback sensor
    // 0 又は 1
    public static final int PrimaryClosedLoop = 0;
    public static final int AuxiliaryClosedLoop = 1;
    public static final int ShooterInitialPosition = 0;

    
    public static final TalonSRXConfiguration armConfig = new TalonSRXConfiguration();
    public static final TalonSRXConfiguration shooterLConfig = new TalonSRXConfiguration();
    public static final TalonSRXConfiguration shooterRConfig = new TalonSRXConfiguration();
    public static final TalonSRXConfiguration dRConfig = new TalonSRXConfiguration();
    public static final TalonSRXConfiguration dLConfig = new TalonSRXConfiguration();

    public static void ConstInit(){
        armConfig.slot0.kP = 6.125;
        armConfig.slot0.kI = 0.005;
        armConfig.slot0.kD = 250;
        armConfig.slot0.kF = 0;
        armConfig.slot0.maxIntegralAccumulator = 30000;
        armConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.Analog;

        shooterLConfig.slot0.kP = 0.05;
        shooterLConfig.slot0.kI = 0.0005;
        shooterLConfig.slot0.kD = 0.5;
        shooterLConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;

        shooterRConfig.slot0.kP = 0.05;
        shooterRConfig.slot0.kI = 0.0005;
        shooterRConfig.slot0.kD = 0.1;
        shooterRConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;

        dRConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        dLConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
    }



}


