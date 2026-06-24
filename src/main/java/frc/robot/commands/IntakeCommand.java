package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeCommand extends Command {

  private final IntakeSubsystem intakeSubsystem;
  private final boolean isIntaking;

  public IntakeCommand(IntakeSubsystem intakeSubsystem, boolean isIntaking) {
    this.intakeSubsystem = intakeSubsystem;
    this.isIntaking = isIntaking;

    addRequirements(intakeSubsystem);
  }

  @Override
  public void execute() {
    if (isIntaking) {
      intakeSubsystem.intake();
    } else {
      intakeSubsystem.eject();
    }
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false; // runs while trigger is held
  }
}
