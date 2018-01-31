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
    DcMotor glyphMotorR;
    DcMotor glyphMotorL;

    Servo servoJ; //jewel servo
    

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
        leftBack = hardwareMap.dcMotor.get("leftBackMotor");
        glyphMotorL = hardwareMap.dcMotor.get("glyphMotorL");
        glyphMotorR = hardwareMap.dcMotor.get("glyphMotorR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("sensor_color_distance");

        servoJ = hardwareMap.servo.get("servoJ");

        telemetry.update();

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
            else (colorSensor.red() > colorSensor.blue()){
                
            }
            

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


    }
}
