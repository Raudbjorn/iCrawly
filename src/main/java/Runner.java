import java.util.List;

/**
 * Created by sveinbjorn on 2016-08-28.
 */
public class Runner {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ICrawly<Headline> crawler = new ICrawly<>();
        List<Headline> headlines = crawler.get("www.mbl.is", Headline.class);
        for (Headline headline : headlines) {
            System.out.println("text: " + headline.getText());
            System.out.println("link: " + headline.getLink());
        }
    }
}
