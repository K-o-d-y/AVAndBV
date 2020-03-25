package me.kody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Kody on 25/03/2020 at 18:32.
 */
public class Main {

    /**
     * 获取目标网站信息
     * @param type
     * @param id
     * @return
     * @throws Exception
     */
    public static String get(String type, String id) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://api.bilibili.com/x/web-interface/archive/stat?" + type + "id=" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("AV and BV Code by Kody");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类型!(a/bv)");
        String type = scanner.next();
        System.out.println("请输入AV号或者BV号!");
        String id = scanner.next();
        String line = get(type, id.substring(0, 2).equalsIgnoreCase("AV") ? id.replace(id.substring(0, 2), "") : id);
        String av = line.substring(line.lastIndexOf("\"aid\":"), line.lastIndexOf(",\"bvid\"")).replace("\"aid\":", "");
        String bv = line.substring(line.lastIndexOf("\"bvid\":\""), line.lastIndexOf("\",\"view\"")).replace("\"bvid\":\"", "");
        String str = "AV:" + av + " BV:" + bv;
        System.out.println(str);
    }
}
