package org.firstinspires.ftc.teamcode;

public class RobotHardwareInitializationFailed extends RuntimeException {
    public RobotHardwareInitializationFailed(String message) {
        super(String.format("Robot hardware initialization failed: %s", message));
    }
}
