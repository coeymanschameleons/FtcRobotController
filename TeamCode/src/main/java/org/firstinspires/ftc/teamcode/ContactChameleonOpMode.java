package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Show the copyright info
 */
@TeleOp(name="About Coeymans Chameleons Robot Controller", group="Copyright")
public class ContactChameleonOpMode extends RobotOpMode {
    private ContactChameleon chameleon = null;

    public void init() {
        chameleon = new ContactChameleon();
    }

    public void start() {
        telemetryPrinter.print(hardwareMap.appContext.getString(R.string.starting_contactchameleon));

    }

    public void loop() {
        telemetryPrinter.print(chameleon.getContactInfo());
    }

    public void stop() {
        telemetryPrinter.print(hardwareMap.appContext.getString(R.string.stopping_contactchameleon));
    }
}
