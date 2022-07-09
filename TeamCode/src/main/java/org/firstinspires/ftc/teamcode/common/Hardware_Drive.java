package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware_Drive {

    //creating motor objects
    public DcMotorEx lFront = null;
    public DcMotorEx rFront = null;
    public DcMotorEx lBack = null;
    public DcMotorEx rBack = null;

    public DcMotorEx duckWheel = null;
    public DcMotorEx lifter = null;

    //creating servo objects
    public Servo intake = null;
    public CRServo continiousServo = null;

    //declaring opmode members
    HardwareMap hwMap =  null;
    Constants constants = new Constants();

    //empty constructor, defaults all to null
    public Hardware_Drive(){

    }

    //initialize hardware
    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;

        //initialize motors
        lFront  = hwMap.get(DcMotorEx.class, "left_front");
        rFront  = hwMap.get(DcMotorEx.class, "right_front");
        lBack  = hwMap.get(DcMotorEx.class, "left_back");
        rBack  = hwMap.get(DcMotorEx.class, "right_back");

        duckWheel = hwMap.get(DcMotorEx.class, "carousel");
        lifter = hwMap.get(DcMotorEx.class, "lifter");

        //motor directions
        lFront.setDirection(DcMotorEx.Direction.FORWARD);
        lBack.setDirection(DcMotorEx.Direction.FORWARD);
        rFront.setDirection(DcMotorEx.Direction.FORWARD);
        rBack.setDirection(DcMotorEx.Direction.FORWARD);

        duckWheel.setDirection(DcMotorEx.Direction.REVERSE);
        lifter.setDirection(DcMotorEx.Direction.REVERSE);

        // set all motors to zero power
        lFront.setPower(0);
        rFront.setPower(0);
        lBack.setPower(0);
        rBack.setPower(0);

        duckWheel.setPower(0);
        lifter.setPower(0);

        // set all motors to run without encoders
        // may want to use RUN_USING_ENCODERS if encoders are installed
        lFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        duckWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }
}
