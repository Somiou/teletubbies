package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
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

    Servo servoJ; //Jewel servo
    Servo servoRelicL; // Relic mechanism
    Servo servoRelicR; // Relic mechanism
    Servo servoTR; //Top right clamp
    Servo servoBR; //Bottom right glyph
    Servo servoTL; //Top left glyph
    Servo servoBL; //Bottom left glyph


    public void init(){

        frontRight = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeft = hardwareMap.dcMotor.get("frontLeftMotor");
        backRight = hardwareMap.dcMotor.get("backRightMotor");
        backLeft = hardwareMap.dcMotor.get("backLeftMotor");

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
        frontRight.setPower(fLeftPower);
        backLeft.setPower(bLeftPower);
        frontRight.setPower(fRightPower);
        backRight.setPower(bRightPower);



    }


}
