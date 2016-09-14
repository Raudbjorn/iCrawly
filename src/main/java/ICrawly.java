import annotations.Attribute;
import annotations.Element;
import annotations.Elements;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sveinbjorn on 2016-08-21.
 */
public class ICrawly<T> {
    private HtmlUnitDriver driver = new HtmlUnitDriver();
    WebDriverWait wait = new WebDriverWait(driver, 15);;
    private static Logger logger = Utilities.getLogger(ICrawly.class);


    public List<T> get(String url, Class clazz) throws InstantiationException, IllegalAccessException {
        getUrl(url);
        Elements elementsAnnotation = (Elements) clazz.getDeclaredAnnotation(Elements.class);
        if (elementsAnnotation != null) {
            return parseMap(clazz);
        } else {
            String message = "No @Elements annotation on class " + clazz.getSimpleName();
            logger.fatal(message);
            throw new IllegalArgumentException(message);
        }
    }

    private List<T> parseMap(Class map) throws IllegalAccessException, InstantiationException {
        Field[] mapFields = map.getDeclaredFields();
        List<WebElement> elements = getElementsForClass(map);
        List<T> result = new ArrayList<>();
        for (WebElement element : elements) {
            T elementBox = (T) map.newInstance();
            for (Field field : mapFields) {
                field.setAccessible(true);
                Element elementAnnotation = field.getDeclaredAnnotation(Element.class);
                if (elementAnnotation != null) {
                    //SG: TODO: Lower line should made to work before javascript support is turned on.
                    //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(elementAnnotation.selector())));
                    WebElement fieldElement;
                    if (elementAnnotation.selector().isEmpty()){
                       fieldElement = element;
                    } else {
                        fieldElement = element.findElement(By.cssSelector(elementAnnotation.selector()));
                    }
                    Attribute attributeAnnotation = field.getDeclaredAnnotation(Attribute.class);
                    if (attributeAnnotation != null) {
                        field.set(elementBox, fieldElement.getAttribute(attributeAnnotation.name()));
                    } else {
                        //defaults to element text -maybe
                        field.set(elementBox, fieldElement.getText());
                    }
                }
                field.setAccessible(false);
            }
            result.add(elementBox);
        }
        return result;
    }

    private List<WebElement> getElementsForClass(Class clazz) {
        Elements elements = (Elements) clazz.getAnnotation(Elements.class);
        if (elements != null) {
            return driver.findElements(By.cssSelector(elements.selector()));
        }
        return null;
    }

    private void getUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }

}
