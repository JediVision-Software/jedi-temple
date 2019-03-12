package com.forcelate.configuration.suite;

import com.forcelate.acceptance.ForcelateAcceptanceReport;
import com.forcelate.acceptance.domain.processing.FrameworkType;
import com.forcelate.acceptance.exception.AcceptanceException;
import com.forcelate.tests.resource.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserResource.class
})
@Slf4j
public class AccSuite {
    @AfterClass
    public static void afterClass() {
        try {
            ForcelateAcceptanceReport.execute(FrameworkType.SPRING_BOOT_V1);
        } catch (AcceptanceException e) {
            System.out.println("Acceptance error after suite execution: " + e.getMessage());
        }
    }
}