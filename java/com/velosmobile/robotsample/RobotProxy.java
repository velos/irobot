package com.velosmobile.robotsample;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a factory robot.
 */
class RobotProxy {

    // In an actual system, these would likely be retrieved from the underlying interface.
    private double xPosM;
    private double yPosM;
    private double currentAngleDegrees;

    private IRobot robot;

    // Previously-issued commands for this robot.
    private List<Command> commands;

    /**
     * Initialize a robot. Robots start at the (0, 0) position, facing due north, with
     * no queued orders.
     * @param robot The underlying robot to control.
     */
    public RobotProxy(IRobot robot) {
        this.robot = robot;
        commands = new ArrayList<>();
    }

    /**
     * Advance the robot along its current angle.
     * @param distanceM Meters to advance.
     */
    public void move(double distanceM) {
        Command command = new MoveCommand(distanceM);
        command.execute(this);
        commands.add(command);
    }

    /**
     * Rotates the robot to face a new direction.
     * @param angleDegrees Absolute angle to face in degrees. 0 is due north and corresponds
     *                     to the  positive Y axis, 90 is due east and corresponds to the
     *                     positive X axis.
     */
    public void turn(double angleDegrees) {
        Command command = new TurnCommand(angleDegrees);
        command.execute(this);
        commands.add(command);
    }

    // Could expose Beep() as well if necessary.

    /**
     * Report the current location of the robot.
     * @return X (east-west) coordinate within the factory in meters.
     */
    public double getXPosMeters() {
        return xPosM;
    }

    /**
     * Report the current location of the robot.
     * @return Y (north-south) coordinate within the factory in meters.
     */
    public double getYPosMeters() {
        return yPosM;
    }

    /**
     * Clears any previously issued commands. These will not be replayed in subsequent
     * calls to {@link #replayCommands(RobotProxy)} and {@link #replayCommands(List)}.
     */
    public void resetCommands() {
        commands.clear();
    }

    /**
     * Order another robot to follow all the commands previously issued to this one.
     * @param robot The other robot to control.
     */
    public void replayCommands(RobotProxy robot) {
        for (Command command : commands) {
            command.execute(robot);
        }
    }

    /**
     * Order a collection of robots to follow all commands previously issued to this one.
     * @param robots The robots to control.
     */
    public void replayCommands(List<RobotProxy> robots) {
        for (RobotProxy proxy : robots) {
            replayCommands(proxy);
        }

    }

    // Classes for queued commands.

    private interface Command {
        void execute(RobotProxy proxy);
    }

    private static class MoveCommand implements Command {

        private double distanceM;

        private MoveCommand(double distanceM) {
            this.distanceM = distanceM;
        }

        public void execute(RobotProxy proxy) {
            proxy.xPosM += distanceM * Math.sin(Math.toRadians(proxy.currentAngleDegrees));
            proxy.yPosM += distanceM * Math.cos(Math.toRadians(proxy.currentAngleDegrees));
            // If necessary, convert to the units IRobot expects.
            proxy.robot.Move(distanceM);
        }
    }

    private static class TurnCommand implements Command {

        private double newAngleDegrees;

        private TurnCommand(double newAngleDegrees) {
            this.newAngleDegrees = newAngleDegrees;
        }

        public void execute(RobotProxy proxy) {
            proxy.currentAngleDegrees = newAngleDegrees;
            // If necessary, convert to the units IRobot expects.
            proxy.robot.Turn(newAngleDegrees);
        }

    }

    // Could add a BeepCommand if we wanted to replay those as well.

}

