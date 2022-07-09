package org.firstinspires.ftc.teamcode.common;

public class MathConstantHeading {
    double targetX = 0;
    double targetY = 0;
    double distance = 0;
    double xVector = 0;
    double yVector = 0;

    public void setTarget(double xPose, double yPose){
        targetX = xPose;
        targetY = yPose;
    }

    public double getDistance(){
        distance = Math.sqrt(Math.pow(targetX,2) + Math.pow(targetY,2));
        return distance;
    }

    public double getAngle(){
        double angle = Math.atan2(targetX, targetY);
        return angle;
    }

    public void coordinateToVector(){
        xVector = targetX / distance;
        yVector = targetY /distance;
    }

    public double getXVector(){
        return xVector;
    }

    public double getYVector(){
        return yVector;
    }
}
