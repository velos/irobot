# irobot
Simple unit testing example.

# Building and running
Use command-line javac with junit 4.x+, or import into an IDE of your choosing. The tests should all succeed.

# Notes

If we needed an IRobot instance to interface with other elements of the 3rd party API, I would have made RobotProxy implement IRobot and directly delegated the Move and Turn commands. In my current implementation, I chose to rename the methods to match our preferred conventions for capitalization.

I assume that there would be some method available, in the third-party API or elsewhere, to retrieve a robot's current position so we can verify that move commands were executed correctly. In the current implementation, I manually implement these.

Because commands are currently not exposed outside of RobotProxy, I made those private static inner classes. If we wanted to access them externally, such as to store or view them, they would be exposed as separate public classes.

