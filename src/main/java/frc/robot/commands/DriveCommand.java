package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.DoubleSupplier;

public class DriveCommand extends Command {

  private final DriveSubsystem driveSubsystem;

  private final DoubleSupplier xSupplier;
  private final DoubleSupplier ySupplier;
  private final DoubleSupplier rotSupplier;

  public DriveCommand(
      DriveSubsystem driveSubsystem,
      DoubleSupplier xSupplier,
      DoubleSupplier ySupplier,
      DoubleSupplier rotSupplier
  ) {

    this.driveSubsystem = driveSubsystem;
    this.xSupplier = xSupplier;
    this.ySupplier = ySupplier;
    this.rotSupplier = rotSupplier;
    addRequirements(driveSubsystem);
  }

  @Override
  public void execute() {

    // RAW INPUTS
    double x = xSupplier.getAsDouble();
    double y = ySupplier.getAsDouble();
    double rot = rotSupplier.getAsDouble();
    double maxSpeed = 1.0; //can be adjusted for speed scaling

    //controller axes fix for xbox
    y = -y;

    // Optional: keep strafe consistent
    // (depends on driver preference)
    x = x;

    x *= maxSpeed;
    y *= maxSpeed;
    rot *= maxSpeed;

    //sends to subsystem
    driveSubsystem.drive(x, y, rot);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false; // default drive command always runs
  }
}
