# irobot
Simple unit testing example.

# Building and running
Use command-line javac with junit 4.x+, or import into an IDE of your choosing. The tests are expected to fail as the implementation has not been completed.

# Notes

If we needed an IRobot instance to interface with other elements of the 3rd party API, I would have made RobotProxy implement IRobot and directly delegated the Move and Turn commands. In my current implementation, I chose to rename the methods to match our preferred conventions for capitalization.

I assume that there would be some method available, in the third-party API or elsewhere, to retrieve a robot's current position so we can verify that move commands were executed correctly.
