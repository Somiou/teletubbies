package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
    DcMotor armLift;

    int power = 1;

    public void init(){

        Right = hardwareMap.dcMotor.get("rightMotor");
        Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Left = hardwareMap.dcMotor.get("leftMotor");
        armLift = hardwareMap.dcMotor.get("armLiftMotor");
        rightHand = hardwareMap.dcMotor.get("rightHandMotor");
        leftHand = hardwareMap.dcMotor.get("leftHandMotor");
        //note which way the motors are placed and change direction accordingly

    }

    public void loop() {
        float gamepad1LeftJoystickY = gamepad1.left_stick_y;
        float gamepad1RightJoystickY = gamepad1.right_stick_y;
        float gamepad2LeftJoystickY = gamepad2.left_stick_y;
        boolean leftBumper = gamepad2.left_bumper;
        boolean rightBumper = gamepad2.right_bumper;

        Right.setPower(gamepad1LeftJoystickY);
        Left.setPower(gamepad1RightJoystickY);
        armLift.setPower(gamepad2LeftJoystickY);

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
