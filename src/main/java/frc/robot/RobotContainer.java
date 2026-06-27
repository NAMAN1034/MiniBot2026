package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.Constants;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {

    // Controllers
    private final CommandXboxController driverController =
            new CommandXboxController(Constants.kDriverControllerPort);

    // Subsystems
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    public RobotContainer() {

        // Default drive command
        driveSubsystem.setDefaultCommand(
            new DriveCommand(
                driveSubsystem,
                () -> -driverController.getLeftY(),   // Forward/Backward
                () -> driverController.getLeftX(),    // Strafe
                () -> driverController.getRightX()    // Rotation
            )
        );

        configureBindings();
    }

    private void configureBindings() {

        // Right trigger -> Intake
      /*driverController.rightTrigger()
                .whileTrue(new IntakeCommand(intakeSubsystem, true));

        // Left trigger -> Eject
        driverController.leftTrigger()
                .whileTrue(new IntakeCommand(intakeSubsystem, false));
    }

    public Command getAutonomousCommand() {
        return new AutoCommand(driveSubsystem, intakeSubsystem);
    }*/
}
