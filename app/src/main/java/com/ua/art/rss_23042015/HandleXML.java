package com.ua.art.rss_23042015;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class HandleXML {

    private String title = "title";
    private String link = "link";
    private String description = "description";

    private boolean titleFlag = false;
    private boolean itemFlag = false;
    private boolean linkFlag = false;
    private boolean descriptionFlag = false;
    private boolean languageFlag = false;
    private boolean copyrightFlag = false;
    private boolean guidFlag = false;


    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    public HandleXML(String url){
        this.urlString = url;
    }
    public String getTitle(){
        return title;
    }
    public String getLink(){
        return link;
    }
    public String getDescription(){
        return description;
    }
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        //base.baseXmlNewsList.add("3");
        int event;
        String text=null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals("title")) titleFlag = true;
                        if(name.equals("item")) itemFlag = true;
                        if(name.equals("link")) linkFlag = true;
                        if(name.equals("description")) descriptionFlag = true;
                        if(name.equals("language")) languageFlag = true;
                        if(name.equals("copyright")) copyrightFlag = true;
                        if(name.equals("guid")) guidFlag = true;
                        break;
                    case XmlPullParser.TEXT:
                        if ((titleFlag == true)&&(linkFlag = true)) base.baseXmlNewsList.add(text);
//                        if(name.equals("title")){
//                            base.baseXmlNewsList.add(text);
//                        }
                        break;
                    case XmlPullParser.END_TAG:

                        if(name.equals("title")) titleFlag = false;
                        if(name.equals("item")) itemFlag = false;
                        if(name.equals("link")) linkFlag = false;
                        if(name.equals("description")) descriptionFlag = false;
                        if(name.equals("language")) languageFlag = false;
                        if(name.equals("copyright")) copyrightFlag = false;
                        if(name.equals("guid")) guidFlag = false;

//                        if ()
//                        else if(name.equals("link")){
//                            link = text;
//                        }
//                        else if(name.equals("description")){
//                            description = text;
//                        }
//                        else{
//                        }




                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();
                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);
                    parseXMLAndStoreIt(myparser);
                    stream.close();
                } catch (Exception e) {
                }
            }
        });
        thread.start();
    }
}