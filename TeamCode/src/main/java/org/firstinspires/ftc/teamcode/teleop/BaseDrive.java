package org.firstinspires.ftc.teamcode.teleop;

import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.positioning.Button;

import org.firstinspires.ftc.teamcode.common.Hardware_Drive;
import org.firstinspires.ftc.teamcode.common.Constants;

@TeleOp(name = "Base Drive", group = "Drive")
public class BaseDrive extends OpMode {
    Hardware_Drive robot = new Hardware_Drive();
    Constants constants = new Constants();

    private ElapsedTime runtime = new ElapsedTime();

    View relativeLayout;


    @Override
    public void init(){
        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");

        runtime.reset();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start(){
    }

    @Override
    public void loop(){
        UpdatePlayer1();
    }

    void UpdatePlayer1(){
        double drivePower = DriveTrainSpeed();

        DriveTrainBase(drivePower);
        DriveTrainSpeed();
        DriveMicroAdjust(0.4);
        //OscillateServo();
    }

    void DriveTrainBase(double drivePower){
        double directionY = -Math.pow(gamepad1.left_stick_y, 1); //Forward
        double directionX = Math.pow(gamepad1.left_stick_x, 1);
        double directionR = Math.pow(gamepad1.right_stick_x, 1); //Turn


        robot.lFront.setPower((directionY + directionX + directionR) * drivePower);
        robot.rFront.setPower((directionY - directionX - directionR) * drivePower);
        robot.lBack.setPower((directionY - directionX + directionR) * drivePower);
        robot.rBack.setPower((directionY + directionX - directionR) * drivePower);

    }

    void DriveMicroAdjust(double power){
        if (gamepad1.dpad_up){
            robot.lFront.setPower(power);
            robot.rFront.setPower(power);
            robot.lBack.setPower(power);
            robot.rBack.setPower(power);
        }
        else if (gamepad1.dpad_down){
            robot.lFront.setPower(-power);
            robot.rFront.setPower(-power);
            robot.lBack.setPower(-power);
            robot.rBack.setPower(-power);
        }
        else if (gamepad1.dpad_right){
            robot.lFront.setPower(power);
            robot.rFront.setPower(-power);
            robot.lBack.setPower(-power);
            robot.rBack.setPower(power);
        }
        else if (gamepad1.dpad_left){
            robot.lFront.setPower(-power);
            robot.rFront.setPower(power);
            robot.lBack.setPower(power);
            robot.rBack.setPower(-power);
        }

        if (gamepad1.left_trigger == 1){
            robot.lFront.setPower(-power);
            robot.rFront.setPower(power);
            robot.lBack.setPower(-power);
            robot.rBack.setPower(power);
        }
        else if (gamepad1.right_trigger == 1){
            robot.lFront.setPower(power);
            robot.rFront.setPower(-power);
            robot.lBack.setPower(power);
            robot.rBack.setPower(-power);
        }
    }

    double DriveTrainSpeed(){
        double drivePower = 0.75;



        if (gamepad1.right_bumper)
            drivePower = 1;
        else if (gamepad1.left_bumper)
            drivePower = 0.25;


        return drivePower;
    }

    @Override
    public void stop(){
    }
}

