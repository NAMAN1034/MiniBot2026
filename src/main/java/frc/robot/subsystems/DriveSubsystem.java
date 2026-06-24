package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;
//import frc.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

  // motor
  private final PWMSparkMax frontLeftMotor =
      new PWMSparkMax(DriveConstants.FrontLeftCanId);

  private final PWMSparkMax frontRightMotor =
      new PWMSparkMax(DriveConstants.FrontRightCanId);

  private final PWMSparkMax backLeftMotor =
      new PWMSparkMax(DriveConstants.BackLeftCanId);

  private final PWMSparkMax backRightMotor =
      new PWMSparkMax(DriveConstants.BackRightCanId);

  // WPILib mecanum helper
  private final MecanumDrive drive;

  public DriveSubsystem() {

    //motor direction stuff (may need to be changed later)
    frontLeftMotor.setInverted(false);
    backLeftMotor.setInverted(false);
    frontRightMotor.setInverted(false);
    backRightMotor.setInverted(false);

    //drive
    drive = new MecanumDrive(
        frontLeftMotor,
        backLeftMotor,
        frontRightMotor,
        backRightMotor
    );
  }

  /**
   * Main drive method used by DriveCommand
   * @param x sideways (-1 to 1)
   * @param y forward/back (-1 to 1)
   * @param rot rotation (-1 to 1)
   */
  public void drive(double x, double y, double rot) {

    // input shaping
    x = applyDeadband(x);
    y = applyDeadband(y);
    rot = applyDeadband(rot);

    // Optional: cube inputs for finer low-speed control
    x = cubeInput(x);
    y = cubeInput(y);
    rot = cubeInput(rot);

    // WPILib mecanum expects:
    // y = forward, x = strafe, rot = rotation
    drive.driveCartesian(y, x, rot);
  }

  public void stop() {
    drive.stopMotor();
  }

  // INPUT PROCESSING
  private double applyDeadband(double value) {
    return Math.abs(value) < 0.1 ? 0.0 : value;
  }

  private double cubeInput(double value) {
    return value * value * value;
  }

  @Override
  public void periodic() {
    //nothing needed yet
  }
}
