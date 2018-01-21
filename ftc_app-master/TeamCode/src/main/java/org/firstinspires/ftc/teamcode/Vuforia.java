/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="Vuforia", group ="OpMode")
public class Vuforia extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";
    
    OpenGLMatrix lastLocation = null;
    double tX; //X value of the distance from the target of our robot
    double tY; //Y value of the distance from the target of our robot
    double tZ; //Z value of the dist from target of our robot

    double rX; //X value extracted from rotational values between target and robot
    double rY; //Y value for rotation
    double rZ; //Z value for rotation

    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AVO4i6L/////AAAAmS3NIQor/U/hoS3IOi1+kj4127qnClMIEjRXKzXGuSQqFWDEHRth5DX5O92pZlUwFq8ANd87CIcf8FRU27m8ra1BsSrjRjtdBCJ0Vd5FOK3deAG1casus06ri5LA1QIojCfPm3A+JXZ1t6J7CRT8O74hA25Ga2VdJaFXX/bZdw0z2gr3N3UHNElZMO9E1h5Qt/+IqcH6SP5iUJSXrKuY0ls6ENV1BCskVvWARWIYI5CELGzK4xYBdtlYYo7O4A+pnZXFuKuoIVzUw8OC4kkCRewJ7a/BsnBJ2DhQfkmm8/HeOc0w1a5m0/hMbrDIqkGsuOo28j0gWTtRTx+2CLOPdMde8nDd2Re77aL7KUDBkoeK";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose(); //Get Positional Values

                telemetry.addData ("Pose", format (pose));

                if (pose != null){
                    VectorF trans = pose.getTranslation ();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES) ;

                    tX = trans.get (0);
                    tY = trans.get (1);
                    tZ = trans.get (2);
                    //Get distance from Robot to Target

                    rX = rot.firstAngle;
                    rY = rot.secondAngle;
                    rZ = rot.thirdAngle;
                    //get rotation factors of target from robot
                }
                
                if(vuMark == RelicRecoveryVuMark.LEFT)
                { //Test to see if Left image and give directions
                    telemetry.addData ("VuMark is", "LEFT");
                    telemetry.addData ("X =", tX);
                    telemetry.addData ("Y =", tY);
                    telemetry.addData ("Z =", tZ);
                }
                else if (vuMark == RelicRecoveryVuMark.RIGHT)
                { //Test to see if Right image and give directions
                    telemetry.addData ("VuMark is", "RIGHT");
                    telemetry.addData ("X =", tX);
                    telemetry.addData ("Y =", tY);
                    telemetry.addData ("Z =", tZ);
                }
                else if (vuMark == RelicRecoveryVuMark.CENTER)
                {
                    telemetry.addData ("VuMark is", "CENTER");
                    telemetry.addData ("X =", tX);
                    telemetry.addData ("Y =", tY);
                    telemetry.addData ("Z =", tZ);
                }

  

            } else {
                telemetry.addData("VuMark", "not visible");
            }
            telemetry.update();
        }
    }

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
