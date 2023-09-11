package comfy;

import org.apache.log4j.Logger;

public class BasePage {

    public static final Logger LOGGER = Logger.getRootLogger();

    public void MathTest() {
         str().equals("sss");
    }

    private String str() {
        return "str";
    }
}
