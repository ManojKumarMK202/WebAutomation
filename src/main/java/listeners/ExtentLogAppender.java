package listeners;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.aventstack.extentreports.ExtentTest;
import report_manager.ExtentReportHelper;

public class ExtentLogAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected synchronized void append(ILoggingEvent iLoggingEvent) {
        ExtentTest extentTest = ExtentReportHelper.getExtentTestThreadLocal();
        String log = iLoggingEvent.getFormattedMessage();

        if (extentTest == null || log == null || log.trim().isEmpty())
            return;

        switch (iLoggingEvent.getLevel().toInt()) {
                case Level.DEBUG_INT:
//                    extentTest.pass(log);
                    return;
                case Level.ERROR_INT:
                    extentTest.fail(log);
                    return;
                case Level.INFO_INT:
                default:
                    extentTest.pass(log);
        }
    }
}
