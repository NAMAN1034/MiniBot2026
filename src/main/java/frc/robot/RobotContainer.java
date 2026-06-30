package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {

    //controllers
    private final CommandXboxController driverController =
            new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

    //subsystems
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

    public RobotContainer() {

        // Default drive command
        driveSubsystem.setDefaultCommand(
            new DriveCommand(
                driveSubsystem,
                () -> -driverController.getLeftY(),//forward/backward
                () -> driverController.getLeftX(),//strafe
                () -> driverController.getRightX()//rotation
            )
        );

        configureBindings();
    }

    private void configureBindings() {

        //right trigger is intake
        driverController.rightTrigger()
                .whileTrue(new IntakeCommand(intakeSubsystem, true));

        //left trigger is eject
        driverController.leftTrigger()
                .whileTrue(new IntakeCommand(intakeSubsystem, false));
    }

    public Command getAutonomousCommand() {
        return new Auto1(driveSubsystem, intakeSubsystem);
    }
}
