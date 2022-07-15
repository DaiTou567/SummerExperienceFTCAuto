package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware_Drive {
    //declaring motors
    public DcMotorEx lFront = null;
    public DcMotorEx rFront = null;
    public DcMotorEx lBack = null;
    public DcMotorEx rBack = null;

    //declaring servos
    public Servo intake = null;
    public CRServo  continuousServo = null;

    HardwareMap access = null;

    public Hardware_Drive(){

    }



}


