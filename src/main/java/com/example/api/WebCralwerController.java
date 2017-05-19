package com.example.api;

import com.example.domain.GameInfo;
import com.example.util.WebCralwer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by iljun on 2017-04-23.
 */
@RestController
@RequestMapping("/")
public class WebCralwerController {

    @Autowired
    private WebCralwer webCralwer;

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/gameInfo", method = RequestMethod.GET)
    public List<GameInfo> naverMain() throws Exception{
        return webCralwer.naverMain();
    }

    @RequestMapping(value = "/gameInfo2", method = RequestMethod.GET)
    public void sport() throws Exception{
        System.out.println("lolGame start");
        webCralwer.lolGame();
        System.out.println("lolGame end");
    }
}
