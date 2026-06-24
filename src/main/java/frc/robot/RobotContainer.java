// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  //subsystems
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  //controller
  private final CommandXboxController driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();

    //default driving to run when no other commands are using the drive subsystem
    driveSubsystem.setDefaultCommand(
        new DriveCommand(
            driveSubsystem,
            () -> driverController.getLeftY(),
            () -> driverController.getLeftX(),
            () -> driverController.getRightX()
        )
    );
  }

  private void configureBindings() {

    //intake right trigger
    driverController.rightTrigger()
        .whileTrue(new IntakeCommand(intakeSubsystem, true));

    //eject left trigger
    driverController.leftTrigger()
        .whileTrue(new IntakeCommand(intakeSubsystem, false));
  }

  public Command getAutonomousCommand() {
    return null; //we'll add simple auto later
  }
}
