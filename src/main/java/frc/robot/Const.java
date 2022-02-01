package frc.robot;

public class Const {
    // motor 
    public static final int DriveRightFront = 2;
    public static final int DriveLeftFront = 6;
    public static final int DriveRightBack = 12;
    public static final int DriveLeftBack = 13;
    public static final int DriveController = 0;
    public static final int IntakeMotor = 14;
    public static final int ArmMotor = 3;
    // Deadband and intakemotor
    public static final double SetAngle = 0;
    public static final double Deadband = 0.03;
    public static final double IntakeSpeed = 0.5;
    public static final double OuttakeSpeed = -0.5;
    public static final double IntakeNeutral = 0;
    public static final double ShooterLeftOut = 0.7;
    public static final double ShooterRightOut = -0.7;
    // angle to point, point to angle
    public static final int LowAngle = -32;
    public static final int HighAngle = 86;
    public static final int LowPoint = 181;
    public static final int HighPoint = 516;
    // Arm, Anti-gravity (Coef = coeffiecient, FF = freefall, G = gravity)
    public static final double ArmGCoef = 0.13;
    public static final double ArmFFCoef = -0.04;
    // PDIF (V = value)
    public static final int FslotIdx = 0;
    public static final double FVofP = 0;
    public static final int FTimeout = 30;

    public static final int PslotIdx = 0;
    public static final double PVofP = 6.125;
    public static final int PTimeout = 30;

    public static final int IslotIdx = 0;
    public static final double IVofP = 0.005;
    public static final int ITimeout = 30;

    public static final int DslotIdx = 0;
    public static final double DVofP = 250;
    public static final int DTimeout = 30;

    public static final int MIAslotdx = 0;
    public static final double MIAiaccum = 30000;
    public static final int MIATimeout = 30;

    // feedback sensor
    public static final int SFSPidIdx = 0;
    // 0 又は 1
    public static final int SFSTimeoutMS = 30;
    
}
