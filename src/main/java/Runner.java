import java.util.List;

/**
 * Created by sveinbjorn on 2016-08-28.
 */
public class Runner {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ICrawly<BlandAd> crawler = new ICrawly<>();
        List<BlandAd> headlines = crawler.get("https://bland.is/solutorg/raftaeki/?categoryId=6", BlandAd.class);
        for (BlandAd ad : headlines) {
            System.out.println("text: " + ad.getTitle());
            System.out.println("link: " + ad.getLink());
        }
    }
}
