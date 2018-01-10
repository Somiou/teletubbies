package org.firstinspires.ftc.teamcode;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer

import java.util.Locale;
/**
 * Created by angiecortez on 12/1/17.
 */
@Autonomous(name = "Blue", group = "Linear OpMode")

public class Blue extends LinearOpMode {

//This code is specifically for the case in which 9681 is with the Blue alliance.
    
    public static final String TAG = "Vuforia Navigation";

    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    DcMotor Right;
    DcMotor Left;
    DcMotor armLift;
    /*DcMotor leftHand;
    DcMotor rightHand;*/

    ColorSensor colorSensor;

    Servo servoL;


    @Override

    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AcjH1In/////AAAAmYwGXyS19k5kphquE2greh1PddtVginGOpWXxzcDoQ3vKFIwk1DXl+zJZOzldi+1m6zYq4UnEyLXyBJQjY6U/S3gNcOg055cHawm3EI2P0HtVbx8OFuBnyOGZPylg+3GWex1Q/XR4Agsxv+3OHYQP8g5N4IqFe3lUmaqVmDYzS5xn3ndKhdUOgqb91bklCgx+u4Rh7p/58OMSeP29z1MIDBHzQ+Ym+ycUh6B6D2B19GZhk83IPTFGRio99alSqziokPrghonSUj0sZaM3uUQJq3PP1OS5ouMS8Y9OY8td7N6Sp8AhcRnAUDahgAdJnuSlG8AmW6IPBaEB9TT50/MpBpHLKZi03/01sCJuzKnox0i";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables stonesAndChips = this.vuforia.loadTrackablesFromAsset("StonesAndChips");
        VuforiaTrackable redTarget = stonesAndChips.get(0);
        redTarget.setName("RedTarget");  // Stones
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(stonesAndChips);

        float mmPerInch        = 25.4f;
        float mmBotWidth       = 18 * mmPerInch;            // ... or whatever is right for your robot
        float mmFTCFieldWidth  = (12*12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels

        OpenGLMatrix redTargetLocationOnField = OpenGLMatrix
                /* Then we translate the target off to the RED WALL. Our translation here
                is a negative translation in X.*/
                .translation(-mmFTCFieldWidth/2, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* First, in the fixed (field) coordinate system, we rotate 90deg in X, then 90 in Z */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 90, 0));
        redTarget.setLocation(redTargetLocationOnField);
        RobotLog.ii(TAG, "Red Target=%s", format(redTargetLocationOnField));

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(mmBotWidth/2,0,0)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, -90, 0, 0));
        RobotLog.ii(TAG, "phone=%s", format(phoneLocationOnRobot));

        ((VuforiaTrackableDefaultListener)redTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        ((VuforiaTrackableDefaultListener)blueTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);

        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        /** Start tracking the data sets we care about. */
        stonesAndChips.activate();



        Right = hardwareMap.dcMotor.get("rightMotor");
        Left = hardwareMap.dcMotor.get("leftMotor");
        armLift = hardwareMap.dcMotor.get("armLiftMotor");
        /*leftHand = hardwareMap.dcMotor.get("leftHandMotor");
        rightHand = hardwareMap.dcMotor.get("rightHandMotor");*/
        Right.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("sensor_color_distance");

        servoL = hardwareMap.servo.get("servoL");
        

        waitForStart();
        colorSensor.red();
        colorSensor.green();
        colorSensor.blue();

        while (opModeIsActive()) {

            for (VuforiaTrackable trackable : allTrackables) {
                /**
                 * getUpdatedRobotLocation() will return null if no new information is available since
                 * the last time that call was made, or if the trackable is not currently visible.
                 * getRobotLocation() will return null if the trackable is not currently visible.
                 */
                telemetry.addData(trackable.getName(), ((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible() ? "Visible" : "Not Visible");    //

                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
            }
            /**
             * Provide feedback as to where the robot was last located (if we know).
             */
            if (lastLocation != null) {
                //  RobotLog.vv(TAG, "robot=%s", format(lastLocation));
                telemetry.addData("Pos", format(lastLocation));
            } else {
                telemetry.addData("Pos", "Unknown");
            }
            telemetry.update();

            //For using the REV color distance sensor to knock off jewel
            /*servoL.setPosition(0);

            Right.setPower(-1);
            Left.setPower(-1);

            sleep(600);

            Right.setPower(0);
            Left.setPower(0);

            if(colorSensor.blue() > colorSensor.red()){

                Right.setPower(-1);
                Left.setPower(1);

                sleep(600);

                Right.setPower(0);
                Left.setPower(0);

                sleep(600);

                Right.setPower(1);
                Left.setPower(1);

                sleep(600);

            }

            else if (colorSensor.red() > colorSensor.blue()) {
                Left.setPower(-1);
                Right.setPower(1);

                sleep(600);

                Right.setPower(0);
                Left.setPower(0);

                sleep(600);

                Right.setPower(-1);
                Left.setPower(-1);

                sleep(600);
            }

            else {
                Left.setPower(0);
                Right.setPower(0);
            }*/

            //for parking in the crypt

            Right.setPower(-0.5);
            Left.setPower(-0.5);

            sleep(600);

            Right.setPower(0);
            Left.setPower(0);

            sleep(600);
        }


        String format(OpenGLMatrix transformationMatrix) {
            return transformationMatrix.formatAsTransform();
    }
}
