package com.velosmobile.robotsample;

import java.util.List;

/**
 * Represents a factory robot.
 */
class RobotProxy {

    /**
     * Initialize a robot. Robots start at the (0, 0) position, facing due north, with
     * no queued orders.
     * @param robot The underlying robot to control.
     */
    public RobotProxy(IRobot robot) {

    }

    /**
     * Advance the robot along its current angle.
     * @param distanceM Meters to advance.
     */
    public void move(double distanceM) {

    }

    /**
     * Rotates the robot to face a new direction.
     * @param angleDegrees Absolute angle to face in degrees. 0 is due north and corresponds
     *                     to the  positive Y axis, 90 is due east and corresponds to the
     *                     positive X axis.
     */
    public void turn(double angleDegrees) {

    }

    // Could expose Beep() as well if necessary.

    /**
     * Report the current location of the robot.
     * @return X (east-west) coordinate within the factory in meters.
     */
    public double getXPosMeters() {
        return 0d;
    }

    /**
     * Report the current location of the robot.
     * @return Y (north-south) coordinate within the factory in meters.
     */
    public double getYPosMeters() {
        return 0d;
    }

    /**
     * Clears any previously issued commands. These will not be replayed in subsequent
     * calls to {@link #replayCommands(RobotProxy)} and {@link #replayCommands(List)}.
     */
    public void resetCommands() {

    }

    /**
     * Order another robot to follow all the commands previously issued to this one.
     * @param robot The other robot to control.
     */
    public void replayCommands(RobotProxy robot) {

    }

    /**
     * Order a collection of robots to follow all commands previously issued to this one.
     * @param robots The robots to control.
     */
    public void replayCommands(List<RobotProxy> robots) {

    }
}
