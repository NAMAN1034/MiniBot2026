package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {

    private final IntakeSubsystem intakeSubsystem;
    private final boolean isIntaking;

    public IntakeCommand(IntakeSubsystem intakeSubsystem, boolean isIntaking) {

        this.intakeSubsystem = intakeSubsystem;
        this.isIntaking = isIntaking;

        // This ensures only one intake command runs at a time
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        // Start action immediately when button is pressed
        if (isIntaking) {
            intakeSubsystem.intake();
        } else {
            intakeSubsystem.eject();
        }
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}