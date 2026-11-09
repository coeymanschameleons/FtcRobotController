package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Arrays;

/**
 * See org.firstinspires.ftc.robotcontroller.external.samples.externalhardware.RobotHardware for the FTC example
 */
public class RobotHardware {
    private RobotOpMode opMode = null;
    private DcMotor frontLeftDrive   = null;
    private DcMotor frontRightDrive  = null;
    private DcMotor backLeftDrive   = null;
    private DcMotor backRightDrive  = null;
    private Servo spendexerWheel = null;
    public boolean isSpendexerIndexing = false;
    IMU imu = null;
    private IMU.Parameters parameters = null;
    private ElapsedTime uptime = new ElapsedTime();
    public ElapsedTime timer = new ElapsedTime();

    public RobotHardware(RobotOpMode opMode1) {
        opMode = opMode1;
    }

    private DcMotor getDcMotor(String id) {
        DcMotor motor = opMode.hardwareMap.get(DcMotor.class, id);
        return motor;
    }

    private Servo getServo(String id) {
        Servo servo = opMode.hardwareMap.get(Servo.class, id);
        return servo;
    }

    private IMU getImu(String id) {
        final IMU imu2 = opMode.hardwareMap.get(IMU.class, id);
        return imu2;
    }

    private void _init() throws Exception {
        // set up motors
        frontLeftDrive = this.getDcMotor("frontLeft");
        frontRightDrive = this.getDcMotor("frontRight");
        backLeftDrive = this.getDcMotor("backLeft");
        backRightDrive = this.getDcMotor("backRight");
        spendexerWheel = this.getServo(opMode.hardwareMap.appContext.getString(R.string.pollen_servo));
        // set up imu
        imu = getImu("imu");
        parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));
        imu.initialize(parameters);

        // set directions

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        spendexerWheel.setDirection(Servo.Direction.FORWARD);
        uptime.reset(); // do not reset after
        timer.reset();
    }

    public double getHeading(){
        return -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void setDrivePowerOnAllFours(double frontLeft, double frontRight, double backLeft, double backRight) {
        // set left drive
        frontLeftDrive.setPower(frontLeft);
        backLeftDrive.setPower(backLeft);

        // set right drive
        frontRightDrive.setPower(frontRight);
        backRightDrive.setPower(backRight);
    }

    public void setDrivePower(double left, double right) {
        setDrivePowerOnAllFours(left, right, left, right);
    }

    public void setArmPower(double power) {
        // stub, fill in later
        this.setPollenWheelPosition(power);
    }

    public void setPollenWheelPosition(double angle) {
        spendexerWheel.setPosition((angle - 180) / 360);
    }

    public double getPollenWheelPosition(){
        double rawPosition = spendexerWheel.getPosition();
        double _360degPosition = rawPosition * 360;
        double _180degPosition;
        _180degPosition = rawPosition - 180;

        return _180degPosition;
    }

    public void driveRobot(double drive, double turn) {
        double left = drive + turn;
        double right = drive - turn;

        double max = Math.max(Math.abs(left), Math.abs(right));

        if (max > 1.0) {
            left /= max;
            right /= max;
        }

        setDrivePower(left, right);
    }

    public void driveField(double lx, double ly, double rx, double heading) {
        double rotx = (lx*Math.cos(heading))+(ly*Math.sin(heading));
        double roty = (-(lx*Math.sin(heading)))+(ly*Math.cos(heading));
        // Normalize motor powers
        double denominator = Math.max(
                Math.abs(roty) +
                        Math.abs(rotx) +
                        Math.abs(rx), 1.0
        );

        double frontLeft = ((roty+rotx)+rx) / denominator;
        double backLeft = ((roty-rotx)+rx) / denominator;
        double frontRight = ((roty-rotx)-rx) / denominator;
        double backRight = ((roty+rotx)-rx) / denominator;

        setDrivePowerOnAllFours(frontLeft,frontRight,backLeft,backRight);
    }

    /**
     *
     * @param offset offset
     */
    public void setHandPositions (double offset) {
//        offset = Range.clip(offset, -0.5, 0.5);
        // set positions, here is the ftc sample code
        /*
         leftHand.setPosition(MID_SERVO + offset);
        rightHand.setPosition(MID_SERVO - offset);
         */
    }

    public void init() throws RobotHardwareInitializationFailed{
        opMode.telemetryPrinter.print(opMode.hardwareMap.appContext.getString(R.string.initializing_hardware_message));
        try {
            this._init();
        } catch (Exception e) {
            opMode.telemetryPrinter.print(opMode.hardwareMap.appContext.getString(R.string.hardware_initialization_failed));
            throw new RobotHardwareInitializationFailed(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
