package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {

    private final DriveSubsystem driveSubsystem;

    //live joystick inputs for x, y, and rotation
    private final DoubleSupplier xSpeedSupplier;//strafe
    private final DoubleSupplier ySpeedSupplier;//forward/back
    private final DoubleSupplier rotSupplier;//rotation

    //limit full speed during tests
    private static final double MAX_SPEED = 1.0;

    public DriveCommand(
            DriveSubsystem driveSubsystem,
            DoubleSupplier xSpeedSupplier,
            DoubleSupplier ySpeedSupplier,
            DoubleSupplier rotSupplier) {

        this.driveSubsystem = driveSubsystem;
        this.xSpeedSupplier = xSpeedSupplier;
        this.ySpeedSupplier = ySpeedSupplier;
        this.rotSupplier = rotSupplier;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {

        //read joystick values every loop
        double xSpeed = xSpeedSupplier.getAsDouble();
        double ySpeed = ySpeedSupplier.getAsDouble();
        double rot = rotSupplier.getAsDouble();

        xSpeed *= MAX_SPEED;
        ySpeed *= MAX_SPEED;
        rot *= MAX_SPEED;

        //send to drivetrain
        driveSubsystem.drive(xSpeed, ySpeed, rot);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        //default drive should run forever
        return false;
    }
}
