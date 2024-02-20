package listeners;

import framework_constants.ConfigConstants;
import framework_constants.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import utils.ConfigFileManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Slf4j
public class AnnotationListener implements IAnnotationTransformer {

    private static ConfigFileManager configFileManager = ConfigFileManager.getInstance(FilePath.COMMON_DATA);

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        //Set the invocation count for tests
        int invocationCount = (configFileManager.getProperty(ConfigConstants.INVOCATION_COUNT).isEmpty() && configFileManager.getProperty(ConfigConstants.INVOCATION_COUNT).equals("")) ? 1 : Integer.parseInt(configFileManager.getProperty(ConfigConstants.INVOCATION_COUNT));
        annotation.setInvocationCount(invocationCount);

        //Sets the retry analyzer
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            annotation.setRetryAnalyzer(RetryUtil.class);
        }
    }
}
