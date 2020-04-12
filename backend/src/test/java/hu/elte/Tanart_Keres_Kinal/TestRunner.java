package hu.elte.Tanart_Keres_Kinal;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UserEntityTest.class, Tanart_Keres_KinalTest.class);

        System.out.println("Total number of tests: " + result.getRunCount());
        System.out.println("Total number of tests failed: " + result.getFailureCount());

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
