package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Teleop mode code goes here
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="Robot")
public class TeleOp extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Hello", "Chameleons");
    }

    @Override
    public void init_loop() {
        telemetry.addData("Hello", "Chameleons during init_loop");
    }

    @Override
    public void start() {
        telemetry.addData(">", "Starting AutoMode...");
    }

    @Override
    public void loop() {
        telemetry.addData("Hello", "Chameleons on the bot");
    }

    @Override
    public void stop() {
        telemetry.addData(">", "Stopping AutoMode...");
    }
}
