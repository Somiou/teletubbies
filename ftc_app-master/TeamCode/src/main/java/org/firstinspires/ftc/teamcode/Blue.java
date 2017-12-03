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
    
    int power =1;
    
public void driveForward(int power, int time){
    
    Right.setPower(power);
    Left.setPower(power);
    sleep(time);
    
}
    
public void stopRobot(){
    Right.setPower(0.0);
    Left.setPower(0.0);
    
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
       
        colorSensor.red();
        colorSensor.blue();
        colorSensor.green();
        
      while (opModeIsActive()){
          
          stick.setPosition(0.0);
          
            Right.setPower(-1);
            Left.setPower(-1);
              sleep(1000);
          
          if (colorSensor.blue() > colorSensor.red()){
              telemetry.addData("blue");
              telemetry.update();
            
            Right.setPower(1);
            Left.setPower(-1);
            sleep(1000);
          }
          
          else{
              telemetry.addData("red");
              telemetry.update();
              
              Right.setPower(-1);
              Left.setPower(1);
              sleep(1000);
          }   
        
          
      }    
        


    }
}
