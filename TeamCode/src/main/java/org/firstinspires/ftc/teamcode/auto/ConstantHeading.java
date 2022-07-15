package org.firstinspires.ftc.teamcode.auto;

import static android.os.SystemClock.sleep;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import org.firstinspires.ftc.teamcode.common.Hardware_Drive;
import org.firstinspires.ftc.teamcode.common.MathConstantHeading;
import org.firstinspires.ftc.teamcode.common.Constants;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.common.TurnPIDController;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class ConstantHeading {
    private final LinearOpMode linearOpMode;
    //declaring local opmode members


    Hardware_Drive robot = new Hardware_Drive();
    HardwareMap hardwareMap;
    MathConstantHeading constHeading = new MathConstantHeading();
    Constants constants = new Constants();



    //drivetrain constants
    static final double     COUNTS_PER_MOTOR_REV    = 537.7;    // eg: TETRIX Motor Encoder
    static final double     MAX_VELOCITY_DT         = 2700;
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   =  (96.0/25.4);     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public ConstantHeading(LinearOpMode plinear){

        linearOpMode = plinear;
        hardwareMap = linearOpMode.hardwareMap;

        robot = new Hardware_Drive();

        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        linearOpMode.telemetry.addData("Status", "Waiting...");
        linearOpMode.telemetry.update();


        robot.lFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.lFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linearOpMode.telemetry.addData("Status", "Waiting on Camera");
        linearOpMode.telemetry.update();
    }

    public void constantHeading(){
        
    }

    public void constantHeading(double speed, double xPose, double yPose){
        //calling functions from MathConstantHeading
        constHeading.setTarget(xPose, yPose);
        constHeading.coordinateToVector();
        double distance = constHeading.getDistance();
        double targetAngle = constHeading.getAngle();

        TurnPIDController pidTurn = new TurnPIDController(targetAngle, 0.01, 0, 0.003);


        //declaring local variables
        double xVector = constHeading.getXVector();
        double yVector = constHeading.getYVector();

        //add and subtract values
        double ratioAddPose = yVector + xVector;
        double ratioSubtractPose = yVector -  xVector;
        double addPose = (ratioAddPose * COUNTS_PER_INCH * distance);
        double subtractPose = (ratioSubtractPose * COUNTS_PER_INCH * distance);

        //new target clicks for motors
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        if(linearOpMode.opModeIsActive()){
            //calculate new target and input into motor controller
            newLeftFrontTarget = (int)(robot.lFront.getCurrentPosition() + addPose);
            newLeftBackTarget = (int)(robot.lFront.getCurrentPosition() + subtractPose);
            newRightFrontTarget = (int)(robot.lFront.getCurrentPosition() + subtractPose);
            newRightBackTarget = (int)(robot.lFront.getCurrentPosition() + subtractPose);

            robot.lFront.setTargetPosition(newLeftFrontTarget);
            robot.rFront.setTargetPosition(newRightFrontTarget);
            robot.lBack.setTargetPosition(newLeftBackTarget);
            robot.rBack.setTargetPosition(newRightBackTarget);

            // turn on RUN_TO_POSITION
            robot.lFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.lBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            while(linearOpMode.opModeIsActive()){
                double angleCorrection = pidTurn.update(getAbsoluteAngle());

                robot.lFront.setVelocity((speed * constants.maxVelocityDT * ratioAddPose) - (speed * angleCorrection * constants.maxVelocityDT));
                robot.rFront.setVelocity((speed * constants.maxVelocityDT * ratioSubtractPose) + (speed * angleCorrection * constants.maxVelocityDT));
                robot.lBack.setVelocity((speed * constants.maxVelocityDT * ratioSubtractPose) - (speed * angleCorrection * constants.maxVelocityDT));
                robot.rBack.setVelocity((speed * constants.maxVelocityDT * ratioAddPose) + (speed * angleCorrection * constants.maxVelocityDT));
            }

            // stop all motion;
            robot.lFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.rFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.lBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.rBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // turn off RUN_TO_POSITION
            robot.lFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.lBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public double getAbsoluteAngle() {
        return robot.imu.getAngularOrientation(
                AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES
        ).firstAngle;
    }
}

