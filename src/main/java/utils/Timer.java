package utils;

/**
 * Timer class that can start and stop a timer and return the recorded time.
 */
public class Timer {
  private long time; // Saved time

  /**
   * Timer constructor.
   */
  public Timer() {
    time = 0;
  }

  /**
   * Start timer.
   */
  public void start() {
    time = System.nanoTime();
  }

  /**
   * Stop timer.
   */
  public void stop() {
    time = (System.nanoTime() - time) / 1_000;
  }

  /**
   * Get record time.
   *
   * @return recorded time
   */
  public long getTime() {
    return time;
  }
}