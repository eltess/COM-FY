package comfy;

import org.apache.log4j.Logger;

public class BasePage {

    public static final Logger LOGGER = Logger.getRootLogger();

    public void MathTest() {
       svstr().equals("sss");
    }

    private String str() {
        return "str";
    }
}
