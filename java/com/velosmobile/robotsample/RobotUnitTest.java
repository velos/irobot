package com.velosmobile.robotsample;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Acceptance tests for moving multiple robots within a factory.
 */
public class RobotUnitTest {

    private static final double TEST_DELTA = 0.0001d;

    private RobotProxy robotOne;
    private RobotProxy robotTwo;
    private RobotProxy robotThree;

    @Before
    public void init() {
        robotOne = new RobotProxy(new DummyRobot());
        robotTwo = new RobotProxy(new DummyRobot());
        robotThree = new RobotProxy(new DummyRobot());
    }

    @Test
    public void initialPosition() throws Exception {
        assertEquals(0d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(0d, robotOne.getYPosMeters(), TEST_DELTA);
    }


    @Test
    public void basicMovement() throws Exception {
        robotOne.turn(0d);
        robotOne.move(42d);
        assertEquals(0d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(42d, robotOne.getYPosMeters(), TEST_DELTA);

        robotOne.turn(90d);
        robotOne.move(20d);
        assertEquals(20d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(42d, robotOne.getYPosMeters(), TEST_DELTA);
    }


    @Test
    public void separateMovement() throws Exception {
        robotOne.turn(180d);
        robotOne.move(10d);

        robotTwo.turn(270d);
        robotTwo.move(10d);

        assertEquals(0d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(-10d, robotOne.getYPosMeters(), TEST_DELTA);

        assertEquals(-10d, robotTwo.getXPosMeters(), TEST_DELTA);
        assertEquals(0d, robotTwo.getYPosMeters(), TEST_DELTA);
    }

    @Test
    public void replaySingle() throws Exception {
        robotOne.turn(0d);
        robotOne.move(10d);

        robotOne.turn(90d);
        robotOne.move(10d);

        robotOne.turn(180d);
        robotOne.move(20d);

        robotOne.replayCommands(robotTwo);

        assertEquals(10d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(-10d, robotOne.getYPosMeters(), TEST_DELTA);

        assertEquals(10d, robotTwo.getXPosMeters(), TEST_DELTA);
        assertEquals(-10d, robotTwo.getYPosMeters(), TEST_DELTA);
    }

    @Test
    public void replayAfterReset() throws Exception {
        robotOne.turn(0d);
        robotOne.move(10d);

        robotOne.resetCommands();

        robotOne.turn(90d);
        robotOne.move(10d);

        robotOne.turn(180d);
        robotOne.move(20d);

        robotOne.replayCommands(robotTwo);

        assertEquals(10d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(-10d, robotOne.getYPosMeters(), TEST_DELTA);

        assertEquals(10d, robotTwo.getXPosMeters(), TEST_DELTA);
        assertEquals(-20d, robotTwo.getYPosMeters(), TEST_DELTA);
    }

    @Test
    public void replayMultiple() throws Exception {
        robotOne.turn(90d);
        robotOne.move(5d);

        robotOne.turn(0d);
        robotOne.move(10d);

        robotOne.turn(270d);
        robotOne.move(10d);

        List<RobotProxy> robots = new ArrayList<>(2);
        robots.add(robotTwo);
        robots.add(robotThree);
        robotOne.replayCommands(robots);

        assertEquals(-5d, robotOne.getXPosMeters(), TEST_DELTA);
        assertEquals(10d, robotOne.getYPosMeters(), TEST_DELTA);

        assertEquals(-5d, robotTwo.getXPosMeters(), TEST_DELTA);
        assertEquals(10d, robotTwo.getYPosMeters(), TEST_DELTA);

        assertEquals(-5d, robotTwo.getXPosMeters(), TEST_DELTA);
        assertEquals(10d, robotTwo.getYPosMeters(), TEST_DELTA);
    }
}

