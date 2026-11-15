package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Autonomous mode code goes here
 */
@Autonomous(name="Autonomous Mode", group="Robot")
public class AutoMode extends RobotOpMode {

    @Override
    public void init() {
        telemetryPrinter.helloWorld(hardwareMap.appContext.getString(R.string.teamname_short));
    }

    @Override
    public void init_loop() {
        telemetryPrinter.helloWorld(hardwareMap.appContext.getString(R.string.hello_who_during_init_loop));
    }

    @Override
    public void start() {
        telemetryPrinter.print(hardwareMap.appContext.getString(R.string.starting_automode));

    }

    @Override
    public void loop() {
        telemetryPrinter.helloWorld(hardwareMap.appContext.getString(R.string.chameleons_on_the_bot));
    }

    @Override
    public void stop() {
        telemetryPrinter.print(hardwareMap.appContext.getString(R.string.stopping_automode));
    }

}
