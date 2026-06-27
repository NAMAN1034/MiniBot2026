package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    private final SparkMax frontLeft;
    private final SparkMax frontRight;
    private final SparkMax rearLeft;
    private final SparkMax rearRight;

    private final MecanumDrive drive;

    public DriveSubsystem() {

        frontLeft = new SparkMax(DriveConstants.kFrontLeftID, MotorType.kBrushless);
        frontRight = new SparkMax(DriveConstants.kFrontRightID, MotorType.kBrushless);
        rearLeft = new SparkMax(DriveConstants.kRearLeftID, MotorType.kBrushless);
        rearRight = new SparkMax(DriveConstants.kRearRightID, MotorType.kBrushless);

        SparkMaxConfig leftConfig = new SparkMaxConfig();
        SparkMaxConfig rightConfig = new SparkMaxConfig();

        leftConfig.inverted(false);
        rightConfig.inverted(true);

        frontLeft.configure(leftConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        rearLeft.configure(leftConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        frontRight.configure(rightConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        rearRight.configure(rightConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        drive = new MecanumDrive(
                frontLeft,
                rearLeft,
                frontRight,
                rearRight
        );

        drive.setDeadband(0.05);
        drive.setSafetyEnabled(true);
    }

    public void drive(double xSpeed, double ySpeed, double rot) {
        drive.driveCartesian(xSpeed, ySpeed, rot);
    }

    public void stop() {
        drive.driveCartesian(0, 0, 0);
    }
}
