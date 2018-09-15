package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by angiecortez on 11/28/17.
 */
@TeleOp(name="Drive", group="Tele Op")

public class Drive extends OpMode{

    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor treadL;
    DcMotor treadR;
    DcMotor glyphMotor;

    Servo servoJ;

    public void init(){
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        treadL = hardwareMap.dcMotor.get("treadL");
        treadR = hardwareMap.dcMotor.get("treadR");
        glyphMotor = hardwareMap.dcMotor.get("glyphMotor");
        servoJ = hardwareMap.servo.get("servoJ");

    }

        public void loop() {

            //In place of motor power, gamestick position is used
            float move = -gamepad1.left_stick_y;
            float rotation = gamepad1.right_stick_x;
            float crabWalk = -gamepad1.left_stick_x;

            //Wheels: Holonomic drive formula uses values of gamestick position to move
            double fLeftPower = Range.clip(move + rotation - crabWalk, -1.0, 1.0);
            double bLeftPower = Range.clip(move + rotation + crabWalk, -1.0, 1.0);
            double fRightPower = Range.clip(move - rotation + crabWalk, -1.0, 1.0);
            double bRightPower = Range.clip(move - rotation - crabWalk, -1.0, 1.0);

            //Assignment of motor power in relation to wheels
            frontLeft.setPower(fLeftPower/.5);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setPower(bLeftPower/.5);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setPower(fRightPower/.5);
            backRight.setPower(bRightPower/.5);



            treadR.setDirection(DcMotorSimple.Direction.REVERSE);

            treadL.setPower(gamepad2.left_trigger);
            treadR.setPower(gamepad2.left_trigger);
            treadL.setPower(-gamepad2.right_trigger);
            treadR.setPower(-gamepad2.right_trigger);

            glyphMotor.setPower(-gamepad2.right_stick_y/2);

            if(gamepad2.x){
                servoJ.setPosition(0.0);
            }
                else{
                    servoJ.setPosition(1.0);
            }

        }
    }
