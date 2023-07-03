package org.firstinspires.ftc.teamcode.teleop;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Hardware_Drive;
import org.firstinspires.ftc.teamcode.common.Button;

@TeleOp(name = "Base Drive", group = "Drive")

public class BaseDrive extends OpMode {
    Hardware_Drive robot = new Hardware_Drive();
    private ElapsedTime runtime = new ElapsedTime();

    Button clawButton = new Button();
    Boolean clawClosed = false;

    double clawClosePos = 0;
    double clawOpenPos = 0.7;

    @Override
    public void init() {
        robot.init(hardwareMap);

        telemetry.addData("Say", "Hello Driver");
        runtime.reset();
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        UpdatePlayer1();
        UpdateButton();
        telemetry.addData("Servo Position: ", robot.claw.getPosition());
    }

    void UpdatePlayer1(){
        double drivePower = DriveTrainSpeed();

        DriveTrainBase(drivePower);
        DriveTrainSpeed();
        CloseClaw();
    }

    void UpdateButton(){
        clawButton.update(gamepad1.x);
    }

    void DriveTrainBase(double drivePower){
        double directionX = Math.pow(gamepad1.left_stick_x, 1); //Strafe
        double directionY = -Math.pow(gamepad1.left_stick_y, 1); //Forward
        double directionR = Math.pow(gamepad1.right_stick_x, 1); //Turn


        robot.lFront.setPower((directionY + directionR + directionX) * drivePower);
        robot.rFront.setPower((directionY - directionR - directionX) * drivePower);
        robot.lBack.setPower((directionY + directionR - directionX) * drivePower);
        robot.rBack.setPower((directionY - directionR + directionX) * drivePower);
    }

    void CloseClaw(){
        if(clawButton.is(Button.State.TAP)){
            if(clawClosed){
                robot.claw.setPosition(clawClosePos);
            } else {
                robot.claw.setPosition(clawOpenPos);
            }
            clawClosed = !clawClosed;
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
    public void stop() {
    }
}
