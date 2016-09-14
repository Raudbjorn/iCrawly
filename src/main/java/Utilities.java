import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Created by sveinbjorn on 2016-08-21.
 */
public class Utilities {

    private static String log4jConfPath = "log4j.properties";
    static {
        PropertyConfigurator.configure(log4jConfPath);
    }

    private Utilities() {
    }

    public static Logger getLogger(Class clazz) {
        return Logger.getLogger(clazz);
    }

    /*
    public static Optional<String> makeHttpRequest(String url) {
        if(!url.startsWith("http://")){
            url = "http://" + url;
        }
        driver.get(url);
        return Optional.of(driver.getPageSource());
    }
    */

}
