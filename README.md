# iCrawly

## Concept

A *very* simple ORM-framework that's capable of using the internet as a datasource.

**Disclaimer: ** As it stands this is very proof-of-concept, and not very stable -but please feel free to check it out and play around with it.

## Example
Let's say http://www.news-example.com contained the following string of HTML:

```html
<a href="/articles/handsome-man-creates-new-way-to-crawl-web" class="headline"><h1>A handsome man creates a new way to crawl the web!</h1></a>

<a href="/articles/icrawly-a-huge-success" class="headline"><h1>iCrawly a huge success!</h1></a>
```

Using iCrawly you can represent each of those headlines using a java object, using simple annotations:

```java
@Elements(selector = ".headline")
public class Headline {

    @Element(selector = "h1")
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
```
**Note:** that using the pure @Element defaults to the elements text, and not specifying a selector selects the current element(the "headline").

This map could then be used like this:

```java
public class Runner {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ICrawly<Headline> crawler = new ICrawly<>();
        List<Headline> headlines = crawler.get("http://www.news-example.com", Headline.class);
        for (Headline headline : headlines) {
            System.out.println("text: " + headline.getText());
            System.out.println("link: " + headline.getLink());
        }
    }
}
```

Which would (hopefully) result in the following output:
```
text: A handsome man creates a new way to crawl the web!
link: http://www.news-example.com/articles/handsome-man-creates-new-way-to-crawl-web
text: iCrawly a huge success!c
link: http://www.news-example.com/articles/icrawly-a-huge-success
```
