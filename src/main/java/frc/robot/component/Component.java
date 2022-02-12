package frc.robot.component;


public interface Component {
   
    void autonomousInit();
    
    void teleopInit();

    void disabledInit();

    void testInit();

    void readSensors();

    void applyState();
}