package org.usfirst.frc.team399.commands;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {

    public WaitCommand(double timeout) {
        setTimeout(timeout);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
