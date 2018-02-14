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

    DcMotor backRight;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor frontLeft; //wheels
    DcMotor treadR;
    DcMotor treadL;

    CRServo glyphServo;
    Servo servoJ; //jewel servo
    

  public static final String TAG = "Vuforia VuMark Sample";

        OpenGLMatrix lastLocation = null;

        /**
         * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
         * localization engine.
         */
        VuforiaLocalizer vuforia;


    public void runOpMode() {

        backRight = hardwareMap.dcMotor.get("backRightMotor");
        frontRight = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeft = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeft = hardwareMap.dcMotor.get("backLeftMotor");
        treadL = hardwareMap.dcMotor.get("treadL");
        treadR = hardwareMap.dcMotor.get("treadL")

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("sensor_color_distance");

        glyphServo = hardwareMap.crservo.get("glyphServo");
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
            
            if (colorSensor.red() > colorSensor.blue()){
                frontRight.setPower(move);
                frontLeft.setPower(move);
                backRight.setPower(move);
                backLeft.setPower(move);
                
            }
            else if(colorSensor.blue() > colorSensor.red()){
                frontRight.setPower(-move);
                frontLeft.setPower(-move);
                backRight.setPower(-move);
                backLeft.setPower(-move);
            }
            else{
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                backLeft,setPower(0);
            }

           
        }


    }
}
