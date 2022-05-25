package com.example.abacusup.Custom;

import android.util.Xml;

import com.example.abacusup.Custom.util.JsonProcessor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {
    public static enum ContentType {CONTENT_XML, CONTENT_JSON}

    private static final String LOG_TAG =
            com.example.abacusup.Custom.NetworkUtils.class.getSimpleName();

    private ContentType CONTENT;
    private HashMap<ContentType, String> ContentMap;
    private String encoding = null;

    public NetworkUtils() {
        this.ContentMap = new HashMap<>();
        this.ContentMap.put(ContentType.CONTENT_XML, "application/xml");
        this.ContentMap.put(ContentType.CONTENT_JSON, "application/json");
    }

    public void setEncoding(Xml.Encoding enc) {
        this.encoding = enc.toString();
    }

    public void setContentType(ContentType content) {
        this.CONTENT = content;
    }

    public String Get(String BASE_URL) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream;
        InputStreamReader inputStreamReader = null;
        String resultJSONString = null;
        try {
            if (this.encoding == null || this.encoding.equals("")) {
                throw new Exception("Charset not initialized");
            } else if (this.CONTENT != ContentType.CONTENT_JSON && this.CONTENT != ContentType.CONTENT_XML) {
                throw new Exception("Content-Type not initialized");
            }
            URL requestURL = new URL(BASE_URL);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", ContentMap.get(this.CONTENT));
            urlConnection.setRequestProperty("Charset", this.encoding);
            urlConnection.setReadTimeout(400000);
            urlConnection.setConnectTimeout(400000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                resultJSONString = sb.toString();
            } else {
                resultJSONString = "[{\"StatusCode\":\""
                        + urlConnection.getResponseCode() + "\", \"ResponseMessage\":\""
                        + urlConnection.getResponseMessage() + "\"}]";
            }
        } catch (Exception e) {
            resultJSONString = "[{\"Error\":\""
                    + e.getMessage() + "\"}]";
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultJSONString;
    }

    public String HttpsGet(String BASE_URL) {
        HttpsURLConnection urlConnection = null;
        InputStream inputStream;
        InputStreamReader inputStreamReader = null;
        String resultJSONString = null;
        try {
            if (this.encoding == null || this.encoding.equals("")) {
                throw new Exception("Charset not initialized");
            } else if (this.CONTENT != ContentType.CONTENT_JSON && this.CONTENT != ContentType.CONTENT_XML) {
                throw new Exception("Content-Type not initialized");
            }
            URL requestURL = new URL(BASE_URL);
            urlConnection = (HttpsURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", ContentMap.get(this.CONTENT));
            urlConnection.setRequestProperty("Charset", this.encoding);
            urlConnection.setReadTimeout(400000);
            urlConnection.setConnectTimeout(400000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                resultJSONString = sb.toString();
            } else {
                resultJSONString = "[{\"StatusCode\":\""
                        + urlConnection.getResponseCode() + "\", \"ResponseMessage\":\""
                        + urlConnection.getResponseMessage() + "\"}]";
            }
        } catch (Exception e) {
            resultJSONString = "[{\"Error\":\""
                    + e.getMessage() + "\"}]";
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultJSONString;
    }

    public String HttpsPost(String BASE_URL, Object postData) {
        HttpsURLConnection urlConnection = null;
        InputStream inputStream;
        InputStreamReader inputStreamReader = null;
        String inputJson;
        String resultJSONString = null;
        try {
            if (this.encoding == null || this.encoding.equals("")) {
                throw new Exception("Charset not initialized");
            } else if (this.CONTENT != ContentType.CONTENT_JSON && this.CONTENT != ContentType.CONTENT_XML) {
                throw new Exception("Content-Type not initialized");
            }
            URL requestURL = new URL(BASE_URL);
            RegisterData data = (RegisterData) postData;
            inputJson = JsonProcessor.parsePostDataToJson(data);
            urlConnection = (HttpsURLConnection) requestURL.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", ContentMap.get(this.CONTENT));
            urlConnection.setRequestProperty("Charset", this.encoding);
            urlConnection.setReadTimeout(400000);
            urlConnection.setConnectTimeout(400000);
            urlConnection.getOutputStream().write(inputJson.getBytes());
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                resultJSONString = sb.toString();
            } else {
                resultJSONString = "[{\"StatusCode\":\""
                        + urlConnection.getResponseCode() + "\", \"ResponseMessage\":\""
                        + urlConnection.getResponseMessage() + "\"}]";
            }
        } catch (Exception e) {
            resultJSONString = "[{\"Error\":\""
                    + e.getMessage() + "\"}]";
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultJSONString;
    }

    public String Post(String BASE_URL, Object postData) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream;
        InputStreamReader inputStreamReader = null;
        String inputJson;
        String resultJSONString = null;
        try {
            if (this.encoding == null || this.encoding.equals("")) {
                throw new Exception("Charset not initialized");
            } else if (this.CONTENT != ContentType.CONTENT_JSON && this.CONTENT != ContentType.CONTENT_XML) {
                throw new Exception("Content-Type not initialized");
            }
            URL requestURL = new URL(BASE_URL);
            RegisterData data = (RegisterData) postData;
            inputJson = JsonProcessor.parsePostDataToJson(data);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", ContentMap.get(this.CONTENT));
            urlConnection.setRequestProperty("Charset", this.encoding);
            urlConnection.setReadTimeout(400000);
            urlConnection.setConnectTimeout(400000);
            urlConnection.getOutputStream().write(inputJson.getBytes());
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                resultJSONString = sb.toString();
            } else {
                resultJSONString = "[{\"StatusCode\":\""
                        + urlConnection.getResponseCode() + "\", \"ResponseMessage\":\""
                        + urlConnection.getResponseMessage() + "\"}]";
            }
        } catch (Exception e) {
            resultJSONString = "[{\"Error\":\""
                    + e.getMessage() + "\"}]";
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultJSONString;
    }

}