package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private final PWMSparkMax intakeMotor;

  //speed tuning
  private static final double IntakeSpeed = 1.0;
  private static final double EjectSpeed = -1.0;

  public IntakeSubsystem() {
    intakeMotor = new PWMSparkMax(DriveConstants.IntakeMotorCanId);

    intakeMotor.set(0.0);
  }

  /**
   * Runs intake forward (pulls game pieces in)
   */
  public void intake() {
    intakeMotor.set(IntakeSpeed);
  }

  /**
   * Reverses intake (ejects game pieces)
   */
  public void eject() {
    intakeMotor.set(EjectSpeed);
  }

  /**
   * Stops motor completely
   */
  public void stop() {
    intakeMotor.set(0.0);
  }

  @Override
  public void periodic() {
    //nothing needed yet
  }
}
