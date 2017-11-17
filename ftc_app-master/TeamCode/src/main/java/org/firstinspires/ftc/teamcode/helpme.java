package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by angiecortez on 11/13/17.
 */
@TeleOp(name="helpme", group="Tele Op")

    public class helpme extends OpMode{
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;



    int power = 5;

    public float gamepad1LeftJoystickX = gamepad1.left_stick_x;
    public float gamepad1LeftJoystickY = gamepad1.left_stick_y;

        public void init(){
            frontRight = hardwareMap.dcMotor.get("frontRightMotor");
            frontLeft = hardwareMap.dcMotor.get("frontLeftMotor");
            backRight = hardwareMap.dcMotor.get("backRightMotor");
            backLeft = hardwareMap.dcMotor.get("backLeftMotor");
        }
        public void loop(){
                frontRight.setPower(power);
                frontLeft.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(power);
            }
        }



    }
