package org.firstinspires.ftc.teamcode;

/**
 * Teleop mode code goes here
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="Robot")
public class TeleOp extends RobotOpMode {
    double drive = 0;
    double turn = 0;
    double heading = 0;

    @Override
    public void init() {
        telemetryPrinter.helloWorld("Chameleons");
        try {
            robot.init();
        } catch (RobotHardwareInitializationFailed e) {
            telemetryPrinter.error(e);
            telemetryPrinter.print("This is a fatal error; fixing the code may be necessary");
        }
    }

    @Override
    public void init_loop() {
        telemetryPrinter.debug("INIT_LOOP");
    }

    @Override
    public void start() {
        telemetryPrinter.print("Starting TeleOp...");

    }

    @Override
    public void loop() {
        heading = robot.getHeading();
        double lx = gamepad1.left_stick_x;
        double ly = gamepad1.left_stick_y;


        if (Math.abs(ly) > 0.1) {
            this.drive = -ly;
            telemetryPrinter.debug("drive = "+ String.valueOf(this.drive));
        } else {
            telemetryPrinter.debug("Not enough push to move fwd/back");
            this.drive = 0;
        }
        if (Math.abs(lx) > 0.5) {
            this.turn = lx;
            telemetryPrinter.debug("turn = "+String.valueOf(this.turn));
        } else {
            telemetryPrinter.debug("Not enough push to turn");
            this.turn = 0;
        }

        robot.driveRobot(this.drive, this.turn);

    }

    @Override
    public void stop() {
        telemetryPrinter.print("Stopping TeleOp...");
    }
}
