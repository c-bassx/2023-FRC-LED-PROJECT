// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.server.PathPlannerServer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.LED.LEDState;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private LED led = new LED();
  private Joystick joystick = new Joystick(1);
  private JoystickButton yellowLED = new JoystickButton(joystick, 1);
  private JoystickButton purpleLED = new JoystickButton(joystick, 2);
  private JoystickButton led3 = new JoystickButton(joystick, 3);
  private JoystickButton led4 = new JoystickButton(joystick, 4);
  private JoystickButton led5 = new JoystickButton(joystick, 5);
  private JoystickButton led6 = new JoystickButton(joystick, 6);
  private JoystickButton led7 = new JoystickButton(joystick, 7);
  private JoystickButton led12= new JoystickButton(joystick, 12);
  
  public static CTREConfigs ctreConfigs;

  private Command m_autonomousCommand;

  private RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public void configureButtonBindings(){
    yellowLED.onTrue(new InstantCommand(() -> led.setState(LEDState.YELLOW)));
    purpleLED.onTrue(new InstantCommand(() -> led.setState(LEDState.PURPLE)));
    led3.onTrue(new InstantCommand(() -> led.setState(LEDState.RAINBOW)));
    led4.onTrue(new InstantCommand(() -> led.setState(LEDState.NONBINARY)));
    led5.onTrue(new InstantCommand(() -> led.setState(LEDState.LESBIAN)));
    led6.onTrue(new InstantCommand(() -> led.setState(LEDState.TRANS)));
    led7.onTrue(new InstantCommand(() -> led.setState(LEDState.BI)));
    led12.toggleOnTrue(new LED.BlinkLED(led).repeatedly());
  }

  @Override
  public void robotInit() {
    ctreConfigs = new CTREConfigs();
    robotContainer = new RobotContainer();

    PathPlannerServer.startServer(5811); // TODO: remove after testing completed
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    // schedule the autonomous command if it exists
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    } else DriverStation.reportError("Unable to get autonomous command.", false);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
    robotContainer.teleopInit();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
