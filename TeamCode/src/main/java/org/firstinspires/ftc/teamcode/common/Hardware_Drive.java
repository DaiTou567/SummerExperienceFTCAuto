package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
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


    //creating sensors
    public BNO055IMU imu;

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

        //initializing imu
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        //motor directions
        lFront.setDirection(DcMotorEx.Direction.REVERSE);
        lBack.setDirection(DcMotorEx.Direction.REVERSE);
        rFront.setDirection(DcMotorEx.Direction.FORWARD);
        rBack.setDirection(DcMotorEx.Direction.FORWARD);

        // set all motors to zero power
        lFront.setPower(0);
        rFront.setPower(0);
        lBack.setPower(0);
        rBack.setPower(0);

        // set all motors to run without encoders
        // may want to use RUN_USING_ENCODERS if encoders are installed
        lFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        lBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

    }
}
