package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private long THRESHOLD;

    public FindSlowTestExtension(long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        ExtensionContext.Store store = getStore(extensionContext);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Method requiredTestMethod = extensionContext.getRequiredTestMethod();
        SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);

        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        ExtensionContext.Store store = getStore(extensionContext);
        Long start_time = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - start_time;
        if (duration > THRESHOLD && annotation == null)
            System.out.printf("Please consider mark method [%s] with @SLowTest.\n", testMethodName);
    }

    private ExtensionContext.Store getStore(ExtensionContext extensionContext) {
        String testClassName = extensionContext.getRequiredTestClass().getName();
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        return extensionContext.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }
}
