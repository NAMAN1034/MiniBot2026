package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    // Single motor for intake flywheel
    private final SparkMax intakeMotor;

    // Speed constants (tune during testing)
    private static final double INTAKE_SPEED = 0.8;
    private static final double EJECT_SPEED = -0.8;

    public IntakeSubsystem() {

        intakeMotor = new SparkMax(Constants.DriveConstants.kIntakeMotorID, MotorType.kBrushless);

        // Basic config for stable motor behavior
        SparkMaxConfig config = new SparkMaxConfig();

        // Ensure consistent direction setup (tune if reversed)
        config.inverted(false);

        intakeMotor.configure(
                config,
                ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters
        );
    }

    /**
     * Pull game piece into robot
     */
    public void intake() {
        intakeMotor.set(INTAKE_SPEED);
    }

    /**
     * Push game piece out of robot
     */
    public void eject() {
        intakeMotor.set(EJECT_SPEED);
    }

    /**
     * Stop intake motor completely
     */
    public void stop() {
        intakeMotor.set(0);
    }
}