package com.velosmobile.robotsample;

/**
 * An interface from a third-party library.
 */
public interface IRobot {
    void Move(Double distance);

    void Turn(Double angle);

    void Beep();
}
