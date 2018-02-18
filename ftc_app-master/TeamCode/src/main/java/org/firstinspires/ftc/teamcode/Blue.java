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

import java.util.Locale;
/**
 * Created by angiecortez on 2/16/18.
 */
@Autonomous(name = "Red", group = "Linear OpMode")

public class Blue extends LinearOpMode {

    // This code is specifically for the case in which 9681 is with the Blue alliance.


    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;
    DcMotor treadL;
    DcMotor treadR;
    DcMotor glyphMotor;

    ColorSensor colorSensor;
    Servo servoJ;


    @Override

    public void runOpMode() {


        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        treadL = hardwareMap.dcMotor.get("treadL");
        treadR = hardwareMap.dcMotor.get("treadR");
        glyphMotor = hardwareMap.dcMotor.get("glyphMotor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        servoJ = hardwareMap.servo.get("servoJ");


        waitForStart();
        colorSensor.red();
        colorSensor.green();
        colorSensor.blue();

       /*
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVO4i6L/////AAAAmS3NIQor/U/hoS3IOi1+kj4127qnClMIEjRXKzXGuSQqFWDEHRth5DX5O92pZlUwFq8ANd87CIcf8FRU27m8ra1BsSrjRjtdBCJ0Vd5FOK3deAG1casus06ri5LA1QIojCfPm3A+JXZ1t6J7CRT8O74hA25Ga2VdJaFXX/bZdw0z2gr3N3UHNElZMO9E1h5Qt/+IqcH6SP5iUJSXrKuY0ls6ENV1BCskVvWARWIYI5CELGzK4xYBdtlYYo7O4A+pnZXFuKuoIVzUw8OC4kkCRewJ7a/BsnBJ2DhQfkmm8/HeOc0w1a5m0/hMbrDIqkGsuOo28j0gWTtRTx+2CLOPdMde8nDd2Re77aL7KUDBkoeK";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

       */

        while (opModeIsActive()) {

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            servoJ.setPosition(-1);

            //to knock off red jewel
            servoJ.setPosition(0);

            if(colorSensor.red() > colorSensor.blue()){
                frontRight.setPower(.30);
                frontLeft.setPower(.30);
                backRight.setPower(.30);
                backLeft.setPower(.30);

                sleep(500);

                servoJ.setPosition(1);

                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);

                sleep(38000);


            }

            else if(colorSensor.blue() > colorSensor.red()){
                frontRight.setPower(-.30);
                frontLeft.setPower(-.30);
                backRight.setPower(-.30);
                backLeft.setPower(-.30);

                sleep(500);

                servoJ.setPosition(1);

                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft.setPower(0);

                sleep(38000);
            }

            else {
                backRight.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                frontLeft.setPower(0);
                sleep(38000);

            }


            //Vuforia
           /*RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose(); //Get Positional Values

                telemetry.addData ("Pose", format (pose));

                if (pose != null){
                    VectorF trans = pose.getTranslation ();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES) ;

                    tX = trans.get (0);
                    tY = trans.get (1);
                    tZ = trans.get (2);
                    //Get distance from Robot to Target

                    rX = rot.firstAngle;
                    rY = rot.secondAngle;
                    rZ = rot.thirdAngle;
                    //get rotation factors of target from robot
                }

                if(vuMark == RelicRecoveryVuMark.LEFT)
                { //Test to see if Left image and give directions
                    telemetry.addData ("VuMark is", "LEFT");
                    telemetry.addData ("X =", tX);
                    telemetry.addData ("Y =", tY);
                    telemetry.addData ("Z =", tZ);
                    telemetry.addData ("rX =", rX);
                    telemetry.addData ("rY =", rY);
                    telemetry.addData ("rZ =", rZ);

                }
                else if (vuMark == RelicRecoveryVuMark.RIGHT)
                { //Test to see if Right image and give directions
                    telemetry.addData ("VuMark is", "RIGHT");
                    telemetry.addData ("X =", tX);
                    telemetry.addData ("Y =", tY);
                    telemetry.addData ("Z =", tZ);
                    telemetry.addData ("rX =", rX);
                    telemetry.addData ("rY =", rY);
                    telemetry.addData ("rZ =", rZ);
                }
                else if (vuMark == RelicRecoveryVuMark.CENTER)
                {
                    telemetry.addData ("VuMark is", "CENTER");
                    telemetry.addData ("X =", tX);
                    telemetry.addData ("Y =", tY);
                    telemetry.addData ("Z =", tZ);
                    telemetry.addData ("rX =", rX);
                    telemetry.addData ("rY =", rY);
                    telemetry.addData ("rZ =", rZ);
                }



            } else {
                telemetry.addData("VuMark", "not visible");
            }
           */

        }

        backRight.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        frontLeft.setPower(0);

        sleep(500);
    }
}
