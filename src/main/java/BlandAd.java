import annotations.Attribute;
import annotations.Element;
import annotations.Elements;

/**
 * Created by sveinbjorn on 2016-08-28.
 */
@Elements(selector = ".classifiedentry")
public class BlandAd {

    @Element(selector = ".clickEntry")
    private String title;

    @Element(selector = ".clickEntry")
    @Attribute(name = "href")
    private String link;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
