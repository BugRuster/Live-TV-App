package com.app.dbug.radioservices;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class URLParser {

    @SuppressLint("DefaultLocale")
    public static String getUrl(String url) {
        String mUrl = url.toUpperCase();
        if (mUrl.endsWith(".FLAC")) {
            return url;
        } else if (mUrl.endsWith(".MP3")) {
            return url;
        } else if (mUrl.endsWith(".WAV")) {
            return url;
        } else if (mUrl.endsWith(".M4A")) {
            return url;
        } else if (mUrl.contains(".PLS")) {
            PlsParser mPls = new PlsParser();
            LinkedList<String> urls = mPls.getRawUrl(url);
            if ((urls.size() > 0)) {
                return urls.get(0);
            }
        } else if (mUrl.contains(".M3U")) {
            M3uParser mM3u = new M3uParser();
            LinkedList<String> urls = mM3u.getRawUrl(url);
            if ((urls.size() > 0)) {
                return urls.get(0);
            }
        } else if (mUrl.endsWith(".ASX")) {
            AsxParser mAsx = new AsxParser();
            LinkedList<String> urls = mAsx.getRawUrl(url);
            if ((urls.size() > 0)) {
                return urls.get(0);
            }
        } else {
            URLConnection conn = getConnection(url);
            if (conn != null) {
                String mContentDisposition = conn.getHeaderField("Content-Disposition");
                Log.d("INFO", "Requesting: " + url + " Headers: " + conn.getHeaderFields());
                String mContentType = conn.getContentType();
                if (mContentType != null) {
                    mContentType = mContentType.toUpperCase();
                }
                if (mContentDisposition != null && mContentDisposition.toUpperCase().contains("M3U")) {
                    M3uParser m3u = new M3uParser();
                    LinkedList<String> urls = m3u.getRawUrl(conn);
                    if (urls.size() > 0) {
                        return urls.getFirst();
                    }
                } else if (mContentDisposition != null && mContentDisposition.toUpperCase().contains("PLS")) {
                    PlsParser pls = new PlsParser();
                    LinkedList<String> urls = pls.getStreamingUrl(conn);
                    if (urls.size() > 0) {
                        return urls.getFirst();
                    }
                } else if (mContentType != null && mContentType.contains("AUDIO/X-SCPLS")) {
                    return url;
                } else if (mContentType != null && mContentType.contains("VIDEO/X-MS-ASF")) {
                    AsxParser asx = new AsxParser();
                    LinkedList<String> urls = asx.getRawUrl(url);
                    if ((urls.size() > 0)) {
                        return urls.get(0);
                    }
                    PlsParser pls = new PlsParser();
                    urls = pls.getRawUrl(url);
                    if ((urls.size() > 0)) {
                        return urls.get(0);
                    }
                } else if (mContentType != null && mContentType.contains("AUDIO/MPEG")) {
                    return url;
                } else if (mContentType != null && mContentType.contains("AUDIO/X-MPEGURL")) {
                    M3uParser m3u = new M3uParser();
                    LinkedList<String> urls = m3u.getRawUrl(url);
                    if ((urls.size() > 0)) {
                        return urls.get(0);
                    }
                } else {
                    Log.d("INFO", "not found");
                }
            }
        }
        return url;
    }

    private static URLConnection getConnection(String url) {
        URLConnection mUrl;
        try {
            mUrl = new URL(url).openConnection();
            return mUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
