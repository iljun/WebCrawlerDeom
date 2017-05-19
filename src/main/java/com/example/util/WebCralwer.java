package com.example.util;

import com.example.domain.GameInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iljun on 2017-04-23.
 */
@Component
public class WebCralwer{

    public List<GameInfo> naverMain() throws Exception{
        //Http 주소 설정
        HttpPost post = new HttpPost("http://www.leagueoflegends.co.kr/?m=esports&mod=esports");

        //HttpClient 생성
        HttpClient httpClient = HttpClientBuilder.create().build();

        //실행결과 담기
        HttpResponse response = httpClient.execute(post);

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
            //DOM 데이터 reader에 삽입
            reader = new BufferedReader(inputStreamReader = new InputStreamReader(entity.getContent(), charset));

            //Dom 데이터 담기
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }



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

    public void lolGame() throws Exception{
        String url = "http://www.leagueoflegends.co.kr/?m=esports&mod=esports";
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.select(".dt-contents");
        Elements els2 = els.select(".main_game > .team-a");
        System.out.println(els.toString());
        System.out.println("-------------------------");
        System.out.println(els2.toString());
        for(Element element : els) {
            Elements test = element.getElementsByTag("dt");
            System.out.println(test.get(0).text());
        }
    }
}
