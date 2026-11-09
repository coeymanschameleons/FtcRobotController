package org.firstinspires.ftc.teamcode;

import static java.lang.String.format;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Teleop mode code goes here
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="Robot")
public class TeleOp extends RobotOpMode {
    double drive = 0;
    double drive2 = 0;
    double turn = 0;
    double heading = 0;

    double side=0;

    private boolean lastAButtonState = false;

    @Override
    public void init() {
        try {
            robot.init();
        } catch (RobotHardwareInitializationFailed e) {
            telemetryPrinter.error(e, true);
        }
    }

    @Override
    public void init_loop() {

        telemetryPrinter.debug(hardwareMap.appContext.getString(R.string.init_loop));
        if (gamepad1.left_stick_x > 0) {
            side = 1;
        } else {
            side = -1;
        }
    }

    @Override
    public void start() {
        telemetryPrinter.print(hardwareMap.appContext.getString(R.string.starting_teleop));

    }

    @Override
    public void loop() {
        heading = robot.getHeading();
        double lx = gamepad1.left_stick_x;
        double ly = gamepad1.left_stick_y;
        double rx = -gamepad1.right_stick_x;
        boolean a = gamepad1.a;

        if (gamepad1.y) {
            robot.imu.resetYaw();
        }
        if (a && !this.lastAButtonState && !robot.isSpendexerIndexing) {
            robot.isSpendexerIndexing = true;
            robot.timer.reset();
            robot.setPollenWheelPosition(90);

        }

        if (Math.abs(ly) > 0.1) {
            this.drive = ly;
            telemetryPrinter.debug(String.format(hardwareMap.appContext.getString(R.string.drive_equals_s), String.valueOf(this.drive)));
        } else {
            telemetryPrinter.debug(hardwareMap.appContext.getString(R.string.not_enough_push_to_move_fwd_back));
            this.drive = 0;
        }
        if (Math.abs(lx) > 0.5) {
            this.drive2 = lx;
            telemetryPrinter.debug(format(hardwareMap.appContext.getString(R.string.drive2_equals_s), String.valueOf(this.turn)));
        } else {
            telemetryPrinter.debug(hardwareMap.appContext.getString(R.string.not_enough_push_to_move_left_to_right));
            this.drive2 = 0;
        }
        if (Math.abs(rx) > 0.5) {
            this.turn = rx;
            telemetryPrinter.debug(format(hardwareMap.appContext.getString(R.string.turn_equals_s), String.valueOf(this.turn)));
        } else {
            telemetryPrinter.debug(hardwareMap.appContext.getString(R.string.not_enough_push_to_turn));
            this.turn = 0;
        }

        if (robot.isSpendexerIndexing) {
            if (robot.timer.seconds() >= SharedConstants.ROTATION_DURATION_SEC) {
                // Stop spinning exactly at 90 degrees
                robot.isSpendexerIndexing = false;
            }
        }

        telemetryPrinter.print(String.format("Indexing status: %s", robot.isSpendexerIndexing ? "Moving": "Ready"));

        robot.driveField(this.drive, this.drive2, this.turn, heading);
        this.lastAButtonState = a;
    }


    @Override
    public void stop() {
        telemetryPrinter.print(hardwareMap.appContext.getString(R.string.stopping_teleop));
    }
}
