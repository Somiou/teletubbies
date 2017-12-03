package org.firstinspires.ftc.teamcode;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
@Autonomous(name = "Blue", group = "Sensor")

public class Blue extends LinearOpMode {

    DcMotor Right;
    DcMotor Left;
    DcMotor armLift;
    DcMotor leftHand;
    DcMotor rightHand;

    ColorSensor colorSensor;

    Servo stick;

    @Override
    public void runOpMode() {
        Right = hardwareMap.dcMotor.get("rightMotor");
        Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Left = hardwareMap.dcMotor.get("leftMotor");
        armLift = hardwareMap.dcMotor.get("armLiftMotor");
        leftHand = hardwareMap.dcMotor.get("leftHandMotor");
        rightHand = hardwareMap.dcMotor.get("rightHandMotor");

        colorSensor = hardwareMap.colorSensor.get("sensor_color_distance");

        stick = hardwareMap.servo.get("servo");


        waitForStart();
        /*need to decide whether to use while statements or simply follow the sequential order. It has been made known that the while
        statements are used with sensors and human input. Is it possible to use both? We need to be certain of the tasks the robot needs
        to complete
        */
      while (opModeIsActive()){
        


    }
}
