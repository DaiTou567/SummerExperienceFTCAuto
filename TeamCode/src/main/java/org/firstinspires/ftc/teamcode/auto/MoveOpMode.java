package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.auto.ConstantHeading;
import org.firstinspires.ftc.teamcode.common.Hardware_Drive;
import org.firstinspires.ftc.teamcode.common.Constants;


@Autonomous(name = "Test Path", group = "Drive")

public abstract class MoveOpMode extends LinearOpMode {
    //declaring local class objects
    Hardware_Drive hDrive = new Hardware_Drive();
    Constants constants = new Constants();
    ConstantHeading constantHeading;

    @Override
    public void runOpMode() throws InterruptedException {
        constantHeading = new ConstantHeading(this);

        waitForStart();

        constantHeading.drive(1 , 2, 4);
    }
}
