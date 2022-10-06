import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlTest2 {
    public ArrayList<String> getImgList(String keyword, String color) {
        String url = "https://www.google.com/search";
        HashMap<String, String> inputData = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        inputData.put("q", keyword);
        inputData.put("tbm", "isch");
        inputData.put("tbs", "ic:specific,isc:" + color);
        Document doc = null;
        try {
            doc = getDoc(url, inputData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements els = doc.getElementsByClass("rg_meta notranslate");
        for (Element el : els) {
            list.add(getImg(el));
        }
        return list;
    }

    public  Document getDoc(String url, Map<String, String> inputData) throws IOException {
        return Jsoup.connect(url).data(inputData).get();
    }

    public  String getImg(Element el) {
        String html = el.html();
        Pattern p = Pattern.compile("\"ou\":\"(\\S*)\",\"");
        Matcher m = p.matcher(html);
        if(m.find()) {
            return m.group(1);
        }
        return null;
    }

    //https://github.com/EunnaYoo/Project_ColorfulCat
}