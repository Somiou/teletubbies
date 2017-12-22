package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by angiecortez on 11/28/17.
 */
@TeleOp(name="Drive", group="Tele Op")

public class Drive extends OpMode{

    DcMotor Right;
    DcMotor leftHand;
    DcMotor rightHand;
    DcMotor Left;
   // DcMotor armLift;
    Servo servoL;
    Servo servoR;


    public void init(){

        Right = hardwareMap.dcMotor.get("rightMotor");
        Left = hardwareMap.dcMotor.get("leftMotor");
        rightHand = hardwareMap.dcMotor.get("rightHandMotor");
        leftHand = hardwareMap.dcMotor.get("leftHandMotor");
        //armLift = hardwareMap.dcMotor.get("armLiftMotor")
        servoL = hardwareMap.servo.get("servoL");
        servoR = hardwareMap.servo.get("servoR");

        Right.setDirection(DcMotorSimple.Direction.REVERSE);
        leftHand.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        float gamepad1LeftJoystickY = gamepad1.left_stick_y;
        float gamepad1RightJoystickY = gamepad1.right_stick_y;
        //float gamepad2LeftJoystickY = gamepad2.left_stick_y;
        boolean gamepad2Y = gamepad2.y;
        boolean leftBumper = gamepad2.left_bumper;
        boolean rightBumper = gamepad2.right_bumper;

        Right.setPower(gamepad1RightJoystickY/2);
        Left.setPower(gamepad1LeftJoystickY/2);
        //armLift.setPower(gamepad2LeftJoystick);



        if(gamepad2Y){
            servoL.setPosition(1);
            servoR.setPosition(0);
        }
        else {
            servoL.setPosition(0);
            servoR.setPosition(1);
        }


        if (rightBumper) {
            rightHand.setPower(1);
            leftHand.setPower(1);

        } else if (leftBumper) {
            leftHand.setPower(-1);
            rightHand.setPower(-1);

        } else {
            rightHand.setPower(0);
            leftHand.setPower(0);
        }

    }
}
