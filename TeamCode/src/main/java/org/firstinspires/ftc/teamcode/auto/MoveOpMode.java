package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.auto.ConstantHeading;
import org.firstinspires.ftc.teamcode.common.Hardware_Drive;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.common.Constants;

@Autonomous(name = "Summer Experience Route", group = "Routes")
public abstract  class MoveOpMode extends LinearOpMode{
    //declaring local class objects

    Hardware_Drive robot = new Hardware_Drive();
    Constants constants = new Constants();
    ConstantHeading constHeading = new ConstantHeading(this);



    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        constHeading.drive(1,2,3);
    }
}
