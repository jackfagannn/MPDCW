package com.coursework.jackf.mpdcw;

/**
 *
 * Name- Jack Fagan
 * Matriculation Number- S1423745
 *
 *
 *
 */
        import android.annotation.SuppressLint;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.ActionBarContainer;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.os.Bundle;
        import android.widget.ViewSwitcher;


        import org.xmlpull.v1.XmlPullParser;
        import org.xmlpull.v1.XmlPullParserException;
        import org.xmlpull.v1.XmlPullParserFactory;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.StringReader;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.ArrayList;
        import java.util.LinkedList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String url1 = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
    private String url2 = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    private String url3 = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    private String url4 = "http://floodline.sepa.org.uk/feed/";
    private LinkedList<XmlFile> alist;
    private ArrayList<String> menuItems;
    private TextView urlInput;
    private Button Button1;
    private Button Button2;
    private Button Button3;
//    private Button Button4;
    private EditText itemSearch;
    private String result = "";
//    private TextView urlTitles;
//    private TextView urlDescs;
//    private TextView urlPubs;
//    private ViewSwitcher aViewSwitch;
//    private ListView listUrl;
//    ArrayList<String> listTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlInput = (TextView) findViewById(R.id.urlInput);
        itemSearch = (EditText) findViewById(R.id.filter);
        Button1 = (Button) findViewById(R.id.Button1);
        Button1.setOnClickListener(this);
        Button2 = (Button) findViewById(R.id.Button2);
        Button2.setOnClickListener(this);
        Button3 = (Button) findViewById(R.id.Button3);
        Button3.setOnClickListener(this);
//        Button4 = (Button) findViewById(R.id.Button4);
//        Button4.setOnClickListener(this);


    } // End of onCreate

    private LinkedList<XmlFile> parseXml(String xmlToParse)
    {

        alist = new LinkedList<XmlFile>();
        XmlFile xFile = new XmlFile();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlToParse));
            int eventType = xpp.getEventType();

            if (!alist.isEmpty())
            {
                alist.clear();
            }
            while (eventType != XmlPullParser.END_DOCUMENT){
//                if(eventType == XmlPullParser.START_DOCUMENT) {
//                    System.out.println("Start document");
//                    Log.e("MyTag","Start document");
//                }
                //found a start tag
                if (eventType == XmlPullParser.START_TAG) {
                    System.out.println("Start tag " + xpp.getName());

                    //Check which tag we have
                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        alist = new LinkedList<XmlFile>();
                    }

                    else if (xpp.getName().equalsIgnoreCase("item")) {
                        Log.e("MyTag", "Item Start Tag Found");
                        xFile = new XmlFile();
                    }

                    else if (xpp.getName().equalsIgnoreCase("title")) {

                        String temp = xpp.nextText();

                        Log.e("MyTag", "Title is " + temp);
                        xFile.setTitle(temp);
                    }

                    else if (xpp.getName().equalsIgnoreCase("description")) {

                        String temp = xpp.nextText();

                          Log.e("MyTag", "Description is " + temp);
                        temp = temp.replaceAll("<br />", "\n \n")
                                .replaceAll("Date: ", "Date: \n")
                                .replaceAll("Information: ", "Information: \n")
                                .replaceAll("Traffic Management:", "\n\nTraffic Management: \n")
                                .replaceAll("Works:", "Work Information: \n");
                        xFile.setDescription(temp);
                        xFile.setDescription(temp);
                    }

                    else if (xpp.getName().equalsIgnoreCase("link")) {

                        String temp = xpp.nextText();

                        Log.e("MyTag", "Link is " + temp);
                        xFile.setLink(temp);
                    }


                    else if (xpp.getName().equalsIgnoreCase("georssPoint")) {

                        String temp = xpp.nextText();

                        Log.e("MyTag", "Geo Point is " + temp);
                        xFile.setGeorssPoint(temp);
                    }

                    else if (xpp.getName().equalsIgnoreCase("author")) {

                        String temp = xpp.nextText();

                          Log.e("MyTag", "Author is " + temp);
                        xFile.setAuthor(temp);
                    }

                    else if (xpp.getName().equalsIgnoreCase("comment")) {
                        //Now just get the associated text
                        String temp = xpp.nextText();

                        Log.e("MyTag", "Comment is " + temp);
                        xFile.setComment(temp);
                    }

                    else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                        //Now just get the associated text
                        String temp = xpp.nextText();

                         Log.e("MyTag", "Pub Date is " + temp);
                        xFile.setPubDate(temp);
                    }
                }

                else if(eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        Log.e("MyTag", "feed is " + xFile.toString());
                        System.out.print("Feed Is: " + xFile.toString());
                        if (xFile.getTitle().contains(itemSearch.getText().toString()) || xFile.getTitle().contains(itemSearch.getText().toString())) {
                            alist.add(xFile);
                        }
                    }

                    else if (xpp.getName().equalsIgnoreCase("channel")) {
                        int size;
                        size = alist.size();
                        Log.e("MyTag", "channel size is " + size);
                    }
                }
                //Get the next event
                eventType = xpp.next();
            } // End of while

            System.out.println("End Document");

            return alist;
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag", "Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag", "IO error during parsing");
        }

        Log.e("MyTag", "End document");

        return alist;
    }




   public void onClick(View aview) {
       startProgress(aview);
   }

//    public void onClick(View aview) {
//        startProgress();
//    }
//
//    public void startProgress() {
//        // Run network access on a separate thread;
//        new Thread(new Task(url1)).start();
//    }


//
//        switch (aview.getId()) {
//
//            case R.id.Button1:
////                startProgress1();
//                new Thread(new Task(url1)).start();
//                break;
//
//            case R.id.Button2:
////                startProgress2();
//                new Thread(new Task(url2)).start();
//                break;

//            case R.id.Button3:
//                startProgress3();
////                new Thread(new Task(url3)).start();
//                break;
//
//            case R.id.Button4:
//                startProgress4();
////                new Thread(new Task(url4)).start();
//                break;
//

    public void startProgress(View btn) {
//         Run network access on a separate thread;
        if (btn.getId() == R.id.Button1) {
            new Thread(new Task(url1)).start();


        } else if (btn.getId() == R.id.Button2)
        {
            new Thread(new Task(url2)).start();
        }
        else if (btn.getId() == R.id.Button3) {
            new Thread(new Task(url3)).start();
        }
//        else if (btn.getId() == R.id.Button4) {
//            new Thread(new Task(url4)).start();
//        }

    }


//        new Thread(new Task(url2)).start();
//        new Thread(new Task(url3)).start();
//        new Thread(new Task(url4)).start();
//    }
//    public void startProgress2() {
////         Run network access on a separate thread;
//        new Thread(new Task(url2)).start();
//    }
//    public void startProgress3() {
////         Run network access on a separate thread;
//        new Thread(new Task(url3)).start();
//    }
//       public void startProgress4() {
////         Run network access on a separate thread;
//        new Thread(new Task(url4)).start();
//    }

    //
    // Need separate thread to access the internet resource over network
    // Other neater solutions should be adopted in later iterations.
    class Task implements Runnable {
        private String url;

        public Task(String aurl) {
            url = aurl;
        }

        @Override
        public void run() {

            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";


            Log.e("MyTag", "in run");

            try {
                Log.e("MyTag", "in try");
                aurl = new URL(url);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

              if (!result.equals("")) { result = ""; }
                while ((inputLine = in.readLine()) != null) {
                    result = result + inputLine;

                    //testing that we have the right xml url
//                    Log.e("MyTag InputLine", inputLine);

                }
                in.close();
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception");
            }

            // Now that you have the xml data you can parse it

            final LinkedList<XmlFile> alist;
//            new XmlFile();

           alist = parseXml(result);

            if (alist != null) {
                Log.e("MyTag", "List is not null");
                for (Object o : alist)
                {
                   Log.e("MyTag", o.toString());
//                   urlInput.append(o.toString());
                }
            } else {
                Log.e("MyTag", "List is null");
            }


            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");

                    if(alist != null) {
                        urlInput.setText("");

                        for (Object o : alist) {
                            urlInput.append(o.toString());
                        }

                        /*for(XmlFile item: alist) {
                        menuItems.add(item.getTitle())
                         } */

                    }
                 

//                    urlInput.setText(result);


                    //urlInput.setText(alist);

                }
            });
        }
    }
}