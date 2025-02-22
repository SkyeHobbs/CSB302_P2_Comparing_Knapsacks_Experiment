package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

  @Test
  void getTime() throws InterruptedException {
    Timer timer = new Timer();
    timer.start();
    timer.stop();
    assertTrue(timer.getTime() >= 0);

    timer.start();
    Thread.sleep(100); // Sleep for 100 milliseconds
    timer.stop();
    assertTrue(timer.getTime() >= 100);
  }
  }