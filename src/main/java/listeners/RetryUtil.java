package listeners;

import framework_constants.ConfigConstants;
import framework_constants.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.ConfigFileManager;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class RetryUtil implements IRetryAnalyzer {

    private ConfigFileManager configFileManager;
    private AtomicInteger COUNTER;

    public RetryUtil() {
        configFileManager = ConfigFileManager.getInstance(FilePath.COMMON_DATA);
        COUNTER = new AtomicInteger(0);
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        int maxRetryCount = 0;
            try {
                maxRetryCount = configFileManager.getProperty(ConfigConstants.RETRY_COUNT) == null ? 0 : Integer.parseInt(configFileManager.getProperty(ConfigConstants.RETRY_COUNT));
                log.info("Retry count:" + maxRetryCount);
            } catch (Exception e) {
                maxRetryCount = 0;
            }

        boolean value = !iTestResult.isSuccess() && COUNTER.incrementAndGet() <= maxRetryCount;
        return value;
    }
}