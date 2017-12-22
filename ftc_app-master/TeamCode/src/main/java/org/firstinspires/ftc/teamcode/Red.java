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
 * Created by angiecortez on 12/1/17.
 */
@Autonomous(name = "Red", group = "Linear OpMode")

public class Red extends LinearOpMode {

   // This code is specifically for the case in which 9681 is with the Red alliance.


            DcMotor Right;
    DcMotor Left;
    DcMotor armLift;
    /*DcMotor leftHand;
    DcMotor rightHand;*/

    ColorSensor colorSensor;

    Servo servoL;


    @Override

    public void runOpMode() {

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

            //For using the REV color distance sensor to knock off jewel
            /*servoL.setPosition(0);

            Right.setPower(-1);
            Left.setPower(-1);

            sleep(600);

            Right.setPower(0);
            Left.setPower(0);

            if(colorSensor.red() > colorSensor.blue()){

                 Right.setPower(-1);
                Left.setPower(1);

                sleep(600);

                Right.setPower(0);
                Left.setPower(0);

                sleep(600);

                Right.setPower(-1);
                Left.setPower(-1);

                sleep(600);
            }

            else if (colorSensor.blue() > colorSensor.red()) {
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

    }
}
