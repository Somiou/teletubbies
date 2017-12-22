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

    DcMotor rightBack;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor leftFront;
    //DcMotor leftHand;
    //DcMotor rightHand;
   // DcMotor armLift;
    Servo servoX;
    Servo servoRelic;
    Servo servoTR;
    Servo servoBR;
    Servo servoTL;
    Servo servoBL;


    public void init(){

        rightBack = hardwareMap.dcMotor.get("rightBackMotor");
        rightFront = hardwareMap.dcMotor.get("rightFrontMotor")
        leftBack = hardwareMap.dcMotor.get("leftBackMotor");
        leftFront = hardwareMap.dcMotor.get("leftFrontMotor");
        //wheels
        
        /*rightHand = hardwareMap.dcMotor.get("rightHandMotor");
        leftHand = hardwareMap.dcMotor.get("leftHandMotor");
        //armLift = hardwareMap.dcMotor.get("armLiftMotor")*/
        
        servoX = hardwareMap.servo.get("servoX");
        servoRelic = hardwareMap.servo.get("servoRelic");
        servoTR = harwareMap.servo.get("servoTR");
        servoBR = hardwareMap.servo.get("servoBR");
        servoTL = hardwareMap.servo.get("servoTL");
        servoBL = harwareMap.servo.get("servoBL");

        Right.setDirection(DcMotorSimple.Direction.REVERSE);
        leftHand.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    
    public void test() {
        
    }

    public void holonomic() {
        
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
