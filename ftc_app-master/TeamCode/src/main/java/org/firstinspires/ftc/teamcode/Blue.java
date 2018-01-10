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
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


import java.util.Locale;
/**
 * Created by angiecortez on 12/1/17.
 */
@Autonomous(name = "Blue", group = "Linear OpMode")

public class Blue extends LinearOpMode {

//This code is specifically for the case in which 9681 is with the Blue alliance.

    DcMotor rightBack;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor leftFront; //wheels

    Servo servoJ; //jewel servo
    Servo servoRelicL;
    Servo servoRelicR;
    Servo servoTR; //top right glyph
    Servo servoBR; //bottom right glyph
    Servo servoTL; //top lefft glyph
    Servo servoBL; //bottom left glyph

  public static final String TAG = "Vuforia VuMark Sample";

        OpenGLMatrix lastLocation = null;

        /**
         * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
         * localization engine.
         */
        VuforiaLocalizer vuforia;


    public void runOpMode() {

        rightBack = hardwareMap.dcMotor.get("rightBackMotor");
        rightFront = hardwareMap.dcMotor.get("rightFrontMotor");
        leftFront = hardwareMap.dcMotor.get("leftFrontMotor");
        leftBack = hardwareMap.dcMotor.get("leftBack");

        Right.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("sensor_color_distance");

        servoJ = hardwareMap.servo.get("servoJ");
        servoRelicL = hardwareMap.servo.get("servoRelicL");
        servoRelicR = hardwareMap.servo.get("servoRelicR");
        servoTR = hardwareMap.servo.get("servoTR");
        servoBR = hardwareMap.servo.get("servoBR");
        servoTL = hardwareMap.servo.get("servoTL");
        servoBL = hardwareMap.servo.get("servoBL");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "AcjH1In/////AAAAmYwGXyS19k5kphquE2greh1PddtVginGOpWXxzcDoQ3vKFIwk1DXl+zJZOzldi+1m6zYq4UnEyLXyBJQjY6U/S3gNcOg055cHawm3EI2P0HtVbx8OFuBnyOGZPylg+3GWex1Q/XR4Agsxv+3OHYQP8g5N4IqFe3lUmaqVmDYzS5xn3ndKhdUOgqb91bklCgx+u4Rh7p/58OMSeP29z1MIDBHzQ+Ym+ycUh6B6D2B19GZhk83IPTFGRio99alSqziokPrghonSUj0sZaM3uUQJq3PP1OS5ouMS8Y9OY8td7N6Sp8AhcRnAUDahgAdJnuSlG8AmW6IPBaEB9TT50/MpBpHLKZi03/01sCJuzKnox0i";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();


        relicTrackables.activate();


        waitForStart();
        colorSensor.red();
        colorSensor.green();
        colorSensor.blue();
        relicTrackables.activate();

        while (opModeIsActive()) {
            
            //knocking off jewel
            servoJ.setPosition(0);
            
            if (colorSensor.blue() > colorSensor.red()){
                rightFront.setPower(1);
                
            }
            
            
        

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

             vuMark == "Right" then{
                    
                }

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);


            }
            else {
                telemetry.addData("VuMark", "not visible");
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
            }

            //for parking in the crypt

            Right.setPower(-0.5);
            Left.setPower(-0.5);

            sleep(600);

            Right.setPower(0);
            Left.setPower(0);

            sleep(600);*/
        }


        String format(OpenGLMatrix transformationMatrix) {
            return transformationMatrix.formatAsTransform();
    }
}
