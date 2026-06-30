package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class Auto1 extends Command {

    private final DriveSubsystem driveSubsystem;
    private final IntakeSubsystem intakeSubsystem;

    private final Timer timer = new Timer();

    private static final double DRIVE_SPEED = 0.5; 
    private static final double DURATION = 5.0;//seconds

    public Auto1(DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem) {

        this.driveSubsystem = driveSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(driveSubsystem, intakeSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();

        // Start intake immediately
        intakeSubsystem.intake();
    }

    @Override
    public void execute() {
        // Drive forward while intake runs
        driveSubsystem.drive(0, DRIVE_SPEED, 0);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop everything safely
        driveSubsystem.stop();
        intakeSubsystem.stop();

        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(DURATION);
    }
}