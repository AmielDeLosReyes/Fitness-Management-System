package com.gs.grit.performance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoadTest1.class,
        LoadTest2.class,
        StressTest1.class,
        StressTest2.class
})
public class PerformanceTestSuite {
    // This class can be empty
}
