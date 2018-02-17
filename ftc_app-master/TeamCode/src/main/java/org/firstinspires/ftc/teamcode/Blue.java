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
@Autonomous(name = "Blue", group = "Linear OpMode")

public class Blue extends LinearOpMode {

//This code is specifically for the case in which 9681 is with the Blue alliance.

    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor glyphMotor;
    DcMotor treadL;
    DcMotor treadR;

    Servo servoJ;

    ColorSensor colorSensor;


    @Override

    public void runOpMode() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        glyphMotor = hardwareMap.dcMotor.get("glyphMotor");
        treadL = hardwareMap.dcMotor.get("treadL");
        treadR = hardwareMap.dcMotor.get("treadR");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        

        waitForStart();
        colorSensor.red();
        colorSensor.green();
        colorSensor.blue();

        while (opModeIsActive()) {

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            servoJ.setPosition(0);

            if(colorSensor.blue() > colorSensor.red()){
                frontRight.setPower(-1);
                frontLeft.setPower(-1);
                backRight.setPower(-1);
                backLeft.setPower(-1);
                sleep(500);
            }

            else if(colorSensor.red() > colorSensor.blue()){
                frontRight.setPower(1);
                frontLeft.setPower(1);
                backRight.setPower(1);
                backLeft.setPower(1);
                sleep(500);
            }

            else {
                backRight.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                frontLeft.setPower(0);
            }

            servoJ.setPosition(1);
        }

    }
}
