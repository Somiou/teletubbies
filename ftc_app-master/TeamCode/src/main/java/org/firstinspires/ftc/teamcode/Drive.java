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
    DcMotor glyphMotorL;
    DcMotor glyphMotorR;
    DcMotor treadMotorR;
    DcMotor treadMotorL;
   
    public void init(){

        frontRight = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeft = hardwareMap.dcMotor.get("frontLeftMotor");
        backRight = hardwareMap.dcMotor.get("backRightMotor");
        backLeft = hardwareMap.dcMotor.get("backLeftMotor");
        glyphMotorL = hardwareMap.dcMotor.get("glyphMotorL");
        glyphMotorR = hardwareMap.dcMotor.get("glyphMotorR");
        treadMotorL = hardwareMap.dcMotor.get("treadMotorL");
        treadMotorR = hardwareMap.dcMotor.get("treadMotorR");

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
        frontRight.setPower(fLeftPower/2);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setPower(bLeftPower/2);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setPower(fRightPower/2);
        backRight.setPower(bRightPower/2);
        
        //Assignment of motor power in relation to glyph intake mechanism
        glyphMotorR.setDirection(DcMotorSimple.Direction.REVERSE);
        

        if (gamepad1.right_bumper){
            glyphMotorL.setPower(1);
            glyphMotorR.setPower(1);
        }

        else if (gamepad1.left_bumper){
            glyphMotorL.setPower(-1);
            glyphMotorR.setPower(-1);
        }

            else {
                glyphMotorL.setPower(0);
                glyphMotorR.setPower(0);
        }
        
        //Assignment of motor power in relation to glyph
        if (gamepad1.right_trigger){
            treadMotorL.setPower(1);
            treadMotorR.setPower(1);
        }
        
        else if (gamepad1.left_trigger){
            treadMotorL.setPower(-1);
            treadMotorR.setPower(-1);
        }
        
        else {
            treadMotorL.setPower(0);
            treadMotorR.setPower(0);
        

    }


}
