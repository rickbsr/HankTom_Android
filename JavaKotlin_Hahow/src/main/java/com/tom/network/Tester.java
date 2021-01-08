package com.tom.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Tester {

    public static void main(String[] args) {
        try {
            // 這邊 https 改成 http 的原因是因為憑證，因為政府公開資料平台的憑證單位是行政院，但是行政院，並不在 JVM 中合格名單，所以會報錯
            // 解決方式 1: 將行政院加入 JVM 的憑證名單
            // 解決方式 2: 拿掉 s
            URL url = new URL("http://data.tycg.gov.tw/opendata/datalist/datasetMeta/download?id=f4cc0b12-86ac-40f9-8745-885bddc18f79&rid=0daad6e6-0632-44f5-bd25-5e1de1e9146f");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 取得輸入串流
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = in.readLine();
            StringBuffer json = new StringBuffer();
            while (line != null) {
//                System.out.println(line);
                json.append(line);
                line = in.readLine();
            }
            System.out.println(json.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
