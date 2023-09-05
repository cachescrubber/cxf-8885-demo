package com.example.demo;

import com.sun.management.UnixOperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTest {
  public static void printOpenFileDescriptorCount() {

    // open fd start
    OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
    ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
    if (os instanceof UnixOperatingSystemMXBean) {

      scheduled.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
          System.out.println("opened files count :" + ((UnixOperatingSystemMXBean) os).getOpenFileDescriptorCount());
        }
      }, 100, 200, TimeUnit.MILLISECONDS);
    }
    // open fd end
  }

  @Test
  void testContextLoads() {
  }
}
