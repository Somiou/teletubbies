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


    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;
    DcMotor glyphMotorL;
    DcMotor glyphMotorR;

    ColorSensor colorSensor;

    Servo servoJ;

    @Override

    public void runOpMode() {

        
        telemetry.update();
        waitForStart();

        frontRight = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeft = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeft = hardwareMap.dcMotor.get("backLeftMotor");
        backLeft = hardwareMap.dcMotor.get("leftHandMotor");
        glyphMotorL = hardwareMap.dcMotor.get("glyphMotorL");
        glyphMotorR = hardwareMap.dcMotor.get("glyphMotorR");
        Right.setDirection(DcMotorSimple.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("sensor_color_distance");

        servoJ = hardwareMap.servo.get("servoJ");


        waitForStart();
        colorSensor.red();
        colorSensor.green();
        colorSensor.blue();

        while (opModeIsActive()) {
           
           //to knock off blue jewel

           servoJ.setPosition(0);
           
           if(colorSensor.blue() > colorSensor.red()){
              frontRight.setPower(1);
              frontLeft.setPower(1);
              backRight.setPower(1);
              backLeft.setPower(1);
           }
           
           else if(colorSensor.red() > colorSensor.blue()){
              backRight.setPower(-1);
              frontLeft.setPower(-1);
              frontRight.setPower(-1);
              backLeft.setPower(-1);
           }
           
           else {
              backRight.setPower(0);
              backLeft.setPower(0);
              frontRight.setPower(0);
              frontLeft.setPower(0);
           }
           
           
           
        }
    }
}
