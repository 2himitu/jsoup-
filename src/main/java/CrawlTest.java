import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CrawlTest {
    public static void main(String[] args) throws Exception {

        String URL = "https://newtoki153.com/webtoon?toon=%EC%9D%BC%EB%B0%98%EC%9B%B9%ED%88%B0";

        Document doc = Jsoup.connect(URL).get();
        //System.out.println(doc);
        //Elements elements = doc.getElementsByClass("list-cotainer");
        //System.out.println(doc.text());  //text 출력
        //System.out.println(doc.html());  //html 출력

        Elements elements = doc.select("div[class=\" img-item\"]");

        Elements elements1 = elements.select("img[alt=\"\"]");
        for (int i = 0; i < elements.size(); i++) {
            String t = elements.get(i).select("a").text();
            URL url = new URL(elements1.get(i).select("img").get(0).attr("src"));
            InputStream is = url.openStream();
            String fileName = "C:/marpple/Webtoon/ "+t+".jpg";
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                int b;
                while ((b = is.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
            }catch (Exception e){

            }
            System.out.println(i+"번 :: 작업 완료");
        }
    }
}