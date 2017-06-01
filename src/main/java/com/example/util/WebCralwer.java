package com.example.util;

import com.example.domain.GameInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.net.ssl.internal.www.protocol.https.HttpsURLConnectionOldImpl;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iljun on 2017-04-23.
 */
@Component
public class WebCralwer{

    public List<GameInfo> naverMain() throws Exception{
        //Http 주소 설정
        HttpGet get = new HttpGet("http://m.sports.naver.com/esports/index.nhn");

        //HttpClient 생성
        HttpClient httpClient = HttpClientBuilder.create().build();

        //실행결과 담기
        HttpResponse response = httpClient.execute(get);

        //Dom구조만 얻어오기
        HttpEntity entity = response.getEntity();

        //CharSet알아내기
        ContentType contentType = ContentType.getOrDefault(entity);
        Charset charset = contentType.getCharset();

        Document doc = null;
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        Elements els = null;
        List<GameInfo> list = null;
        try {
             doc = Jsoup.connect("http://m.sports.naver.com/esports/index.nhn").get();
             doc.charset(charset);
             els = doc.select("#match_box");
            System.out.println(els.toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            if(inputStreamReader!=null)
                inputStreamReader.close();
            if(reader!=null)
                reader.close();
        }
        return list;
    }

    public String lolGame() throws Exception{
        String url = "http://m.sports.naver.com/esports/index.nhn";
        InputStream input = new URL(url).openStream();
        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc.outputSettings().charset().forName("UTF-8").toString());
        Elements els = doc.select("#_match_box_cms_tmp");
        Elements els2 = els.select("ul.game_list");
        Elements aTag = els2.select("a");
        String regx = "lol";
        Pattern pattern = Pattern.compile(regx);
        StringBuilder builder = new StringBuilder();
        for(Element el : aTag){
            Matcher matcher = pattern.matcher(el.attr("href"));
            System.out.println(matcher.find());
            if(matcher.find()){
                builder.append(el.select("span>b").text());
            }
        }
        aTag.attr("href");
        return builder.toString();
    }

    public String test() throws IOException{
        String url = "http://www.skhu.ac.kr/board/boardlist.aspx?curpage=1&bsid=10004&searchBun=51";
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        String contentType = connection.getContentType();
        String encoding = (contentType.toUpperCase().indexOf("UTF-8") >= 0) ? "UTF-8" : "EUC-KR";
        StringBuffer builder = new StringBuffer();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding))) {
            String s;
            while ((s = input.readLine()) != null) {
                builder.append(s);
                builder.append('\n');
            }
            System.out.println(builder.toString());
        }
        return builder.toString();
    }
}
