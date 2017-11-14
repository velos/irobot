package com.velosmobile.robotsample;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RobotUnitTest {

    private RobotProxy robotOne;
    private RobotProxy robotTwo;
    private RobotProxy robotThree;


    @Before
    public void init() {
        robotOne = new RobotProxy(null);
        robotTwo = new RobotProxy(null);
        robotThree = new RobotProxy(null);
    }

    @Test
    public void initialPosition() throws Exception {
        assertEquals(0d, robotOne.getXPosMeters(), 0d);
        assertEquals(0d, robotOne.getYPosMeters(), 0d);
    }


    @Test
    public void basicMovement() throws Exception {
        robotOne.turn(0d);
        robotOne.move(42d);
        assertEquals(0d, robotOne.getXPosMeters(), 0d);
        assertEquals(42d, robotOne.getYPosMeters(), 0d);

        robotOne.turn(90d);
        robotOne.move(20d);
        assertEquals(20d, robotOne.getXPosMeters(), 0d);
        assertEquals(42d, robotOne.getYPosMeters(), 0d);
    }


    @Test
    public void separateMovement() throws Exception {
        robotOne.turn(180d);
        robotOne.move(10d);

        robotTwo.turn(270d);
        robotTwo.move(10d);

        assertEquals(0d, robotOne.getXPosMeters(), 0d);
        assertEquals(-10d, robotOne.getYPosMeters(), 0d);

        assertEquals(-10d, robotTwo.getXPosMeters(), 0d);
        assertEquals(0d, robotTwo.getYPosMeters(), 0d);
    }

    @Test
    public void replaySingle() throws Exception {
        robotOne.turn(0d);
        robotOne.move(10d);

        robotOne.turn(90d);
        robotOne.move(10d);

        robotOne.turn(180d);
        robotOne.move(20d);

        robotTwo.replayCommands(robotOne);

        assertEquals(10d, robotOne.getXPosMeters(), 0d);
        assertEquals(-10d, robotOne.getYPosMeters(), 0d);

        assertEquals(10d, robotTwo.getXPosMeters(), 0d);
        assertEquals(-10d, robotTwo.getYPosMeters(), 0d);
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

        robotTwo.replayCommands(robotOne);

        assertEquals(10d, robotOne.getXPosMeters(), 0d);
        assertEquals(-10d, robotOne.getYPosMeters(), 0d);

        assertEquals(10d, robotTwo.getXPosMeters(), 0d);
        assertEquals(-20d, robotTwo.getYPosMeters(), 0d);
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

        assertEquals(-5d, robotOne.getXPosMeters(), 0d);
        assertEquals(10d, robotOne.getYPosMeters(), 0d);

        assertEquals(-5d, robotTwo.getXPosMeters(), 0d);
        assertEquals(10d, robotTwo.getYPosMeters(), 0d);

        assertEquals(-5d, robotTwo.getXPosMeters(), 0d);
        assertEquals(10d, robotTwo.getYPosMeters(), 10d);
    }
}
