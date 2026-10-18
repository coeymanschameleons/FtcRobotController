package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name="Autonomous Mode", group="Robot")
public class AutoMode extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Hello", "Chameleons");
    }

    @Override
    public void loop() {
        telemetry.addData("Hello", "Chameleons on the bot");
    }
}
