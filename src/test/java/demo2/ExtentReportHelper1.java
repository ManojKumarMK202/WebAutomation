//package demo2;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
////import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.CodeLanguage;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
////import com.aventstack.extentreports.reporter.ExtentSparkReporter;
////import com.aventstack.extentreports.reporter.configuration.Theme;
////import com.aventstack.extentreports.reporter.configuration.ViewName;
//import org.testng.annotations.Test;
//
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.util.Arrays;
//
//public class ExtentReportHelper1 {
//
//    @Test
//    public void extentTest() throws IOException {
//        ExtentReports reports = new ExtentReports();
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html");
//        /*  For logging of failed report
//        ExtentSparkReporter failedSparkReporter = new ExtentSparkReporter("failed_index.html").filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
//         */
////        sparkReporter.config().setTheme(Theme.DARK);
////        sparkReporter.config().setDocumentTitle("Automation Report");
////        sparkReporter.config().setReportName("Report Demo");
////        reports.attachReporter(sparkReporter, failedSparkReporter);
//        reports.attachReporter(sparkReporter);
//        /*  As these values can be changed by any one so  we use xml and json file to store these values    */
//        /* From XML File*/
////        final File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\demo2\\extentConfig.xml");
////        sparkReporter.loadXMLConfig(file);
////        reports.attachReporter(sparkReporter);
//        /*  From json file  */
////        final File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\demo2\\extentConfig.xml");
//
//        ExtentTest extentTest = reports.createTest("Login Test");
//        extentTest.pass("Login test started successfully");
//        extentTest.pass("URL is loaded");
//        extentTest.pass("Values entered properly");
//        /*Logging json or xml*/
//        String jsoncode = "{\"menu\": {\n" +
//                "  \"id\": \"file\",\n" +
//                "  \"value\": \"File\",\n" +
//                "  \"popup\": {\n" +
//                "    \"menuitem\": [\n" +
//                "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
//                "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
//                "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
//                "    ]\n" +
//                "  }\n" +
//                "}}";
//        extentTest.info(jsoncode); /*   It doesnot look well */
//        extentTest.info("<pre>"+ jsoncode.replace("\n", "<br>")+"<pre>"); /*Its look lengthy*/
//        extentTest.info(MarkupHelper.createCodeBlock(jsoncode, CodeLanguage.JSON)); /*  Nice way of coloring was added */
//
//        String xmlcode = "<widget>\n" +
//                "    <debug>on</debug>\n" +
//                "    <window title=\"Sample Konfabulator Widget\">\n" +
//                "        <name>main_window</name>\n" +
//                "        <width>500</width>\n" +
//                "        <height>500</height>\n" +
//                "    </window>\n" +
//                "    <image src=\"Images/Sun.png\" name=\"sun1\">\n" +
//                "        <hOffset>250</hOffset>\n" +
//                "        <vOffset>250</vOffset>\n" +
//                "        <alignment>center</alignment>\n" +
//                "    </image>\n" +
//                "    <text data=\"Click Here\" size=\"36\" style=\"bold\">\n" +
//                "        <name>text1</name>\n" +
//                "        <hOffset>250</hOffset>\n" +
//                "        <vOffset>100</vOffset>\n" +
//                "        <alignment>center</alignment>\n" +
//                "        <onMouseUp>\n" +
//                "            sun1.opacity = (sun1.opacity / 100) * 90;\n" +
//                "        </onMouseUp>\n" +
//                "    </text>\n" +
//                "</widget>";
//
//        extentTest.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));
//
//        ExtentTest extentTest1 = reports.createTest("Home Test");
//        extentTest1.pass("Login test started successfully");
//        extentTest1.pass("URL is loaded");
//        extentTest1.fail("Values entered properly");
//
//        reports.flush();
//        Desktop.getDesktop().browse(new File("index.html").toURI());
//
//    }
//
//    @Test
//    public void extentTest1() throws IOException {
//        /*  Customization of extent report*/
//        /*
//        1. Changing order
//        2. Remove some menu
//        3. Highlight particular log line
//         */
//        ExtentReports reports = new ExtentReports();
//        /* satisfying 1 and 2 */
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html").viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY}).apply();
//        /*  For logging of failed report
//        ExtentSparkReporter failedSparkReporter = new ExtentSparkReporter("failed_index.html").filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
//         */
////        sparkReporter.config().setTheme(Theme.DARK);
////        sparkReporter.config().setDocumentTitle("Automation Report");
////        sparkReporter.config().setReportName("Report Demo");
////        reports.attachReporter(sparkReporter, failedSparkReporter);
//        reports.attachReporter(sparkReporter);
//        /*  As these values can be changed by any one so  we use xml and json file to store these values    */
//        /* From XML File*/
////        final File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\demo2\\extentConfig.xml");
////        sparkReporter.loadXMLConfig(file);
////        reports.attachReporter(sparkReporter);
//        /*  From json file  */
////        final File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\demo2\\extentConfig.xml");
//
//        ExtentTest extentTest = reports.createTest("Login Test");
//        extentTest.pass("Login test started successfully");
//        extentTest.pass("URL is loaded");
//        extentTest.pass("Values entered properly");
//        /*Logging json or xml*/
//        String jsoncode = "{\"menu\": {\n" +
//                "  \"id\": \"file\",\n" +
//                "  \"value\": \"File\",\n" +
//                "  \"popup\": {\n" +
//                "    \"menuitem\": [\n" +
//                "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
//                "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
//                "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
//                "    ]\n" +
//                "  }\n" +
//                "}}";
//        extentTest.info(jsoncode); /*   It doesnot look well */
//        extentTest.info("<pre>"+ jsoncode.replace("\n", "<br>")+"<pre>"); /*Its look lengthy*/
//        extentTest.info(MarkupHelper.createCodeBlock(jsoncode, CodeLanguage.JSON)); /*  Nice way of coloring was added */
//
//        String xmlcode = "<widget>\n" +
//                "    <debug>on</debug>\n" +
//                "    <window title=\"Sample Konfabulator Widget\">\n" +
//                "        <name>main_window</name>\n" +
//                "        <width>500</width>\n" +
//                "        <height>500</height>\n" +
//                "    </window>\n" +
//                "    <image src=\"Images/Sun.png\" name=\"sun1\">\n" +
//                "        <hOffset>250</hOffset>\n" +
//                "        <vOffset>250</vOffset>\n" +
//                "        <alignment>center</alignment>\n" +
//                "    </image>\n" +
//                "    <text data=\"Click Here\" size=\"36\" style=\"bold\">\n" +
//                "        <name>text1</name>\n" +
//                "        <hOffset>250</hOffset>\n" +
//                "        <vOffset>100</vOffset>\n" +
//                "        <alignment>center</alignment>\n" +
//                "        <onMouseUp>\n" +
//                "            sun1.opacity = (sun1.opacity / 100) * 90;\n" +
//                "        </onMouseUp>\n" +
//                "    </text>\n" +
//                "</widget>";
//
//        extentTest.info(MarkupHelper.createCodeBlock(xmlcode, CodeLanguage.XML));
//        /* satisfying  highlighting logs    */
//        extentTest.info(MarkupHelper.createLabel("extent test passed", ExtentColor.GREEN));
//
//        ExtentTest extentTest1 = reports.createTest("Home Test");
//        extentTest1.pass("Login test started successfully");
//        extentTest1.pass("URL is loaded");
//        /* To print list or map values  */
//        /* This will take multiple lines of code to a single line   */
//        Arrays.asList(new String[] {"Selenium","Appium","REST","API"}).forEach(extentTest1::info);
//        extentTest1.info(MarkupHelper.createOrderedList(Arrays.asList(new String[] {"Selenium","Appium","REST","API"})).getMarkup());
//
//        extentTest1.fail("Values entered properly");
//        /* satisfying  highlighting logs    */
//        extentTest1.info(MarkupHelper.createLabel("extent test passed", ExtentColor.RED));
//
//        reports.flush();
//        Desktop.getDesktop().browse(new File("index.html").toURI());
//
//    }
//
//    @Test
//    public void attachingLogo() throws IOException {
//        ExtentReports reports = new ExtentReports();
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html");
//        /* From XML File*/
//        final File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\demo2\\extentConfig.xml");
//        sparkReporter.loadXMLConfig(file);
//        reports.attachReporter(sparkReporter);
//
//        ExtentTest extentTest1 = reports.createTest("Home Test");
//        extentTest1.pass("Login test started successfully");
//        extentTest1.pass("URL is loaded");
//
//        reports.flush();
//        Desktop.getDesktop().browse(new File("index.html").toURI());
//    }
//}
