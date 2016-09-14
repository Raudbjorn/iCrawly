import annotations.Attribute;
import annotations.Element;
import annotations.Elements;

/**
 * Created by sveinbjorn on 2016-08-21.
 */
@Elements(selector = ".fgc")
public class Headline {

    @Element
    String text;

    @Element
    @Attribute(name = "href")
    String link;

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }
}
