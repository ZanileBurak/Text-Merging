package com.yazlab.TextMerging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.yazlab.TextMerging.controller.Compute;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import ch.qos.logback.core.joran.conditional.ElseAction;

@Controller
public class IndexController {

    Long totalTime;
    String[] text2;
    String str;

    @GetMapping("/")
    public String Main() {
        return "MainPage";
    }

    @RequestMapping(value = "/entry", params = "submit")
    public String TextEntry(@RequestParam("text") String[] text, Model model) {
        str = text[0];
        text2 = text;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < text.length - 1; i++) {
            Compute pro = new Compute();
            str = pro.call(str, text[i + 1]);
            model.addAttribute("txt", str);
        }
        long endTime = System.currentTimeMillis();

        totalTime = endTime - startTime;
        String zaman = ("Gecen zaman: " + totalTime + "ms");
        model.addAttribute("zaman", zaman);
        return "MainPage";
    }

    @RequestMapping(value = "/entry", params = "done")
    public String TextSave(@RequestParam("text") String[] text, Model model) {
        return "DonePage";
    }

    @PostMapping("/done")
    public String Done() {
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://admin:1234@cluster0.psonqsi.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase myDatabase = mongoClient.getDatabase("myDatabase");
        MongoCollection<Document> myCollection = myDatabase.getCollection("myCollection");
        String JS ="{ \"metinler\":\""+Arrays.toString(text2)+"\", \"cikti\":\""+str+"\",\"sure\":\""+totalTime+" ms\"}";
        myCollection.insertOne(Document.parse(JS));
        return "DonePage";
    }
}