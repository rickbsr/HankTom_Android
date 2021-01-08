package com.tom.io;

import java.io.*;

public class ReadTester {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        filereader();
//        inputstream();
    }

    private static void filereader() {
        try {
            FileReader fr = new FileReader("data.txt");
            int n = fr.read();
            while (n != -1) {
                System.out.print((char) n);
                n = fr.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inputstream() {
        // 直接輸入檔名，代表在目前專案資料夾底下
        File file = new File("data.txt");
        System.out.println(file.exists()); // 是否存在
        System.out.println(file.getAbsoluteFile()); // 絕對路徑

        try {
            FileInputStream is = new FileInputStream(file);
            int n = is.read();
            while (n != -1) {
                System.out.print((char) n);
                n = is.read();
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            // 輸入類別
            FileInputStream is = new FileInputStream(file);
            int n = is.read();
            System.out.println(n);
            System.out.println(is.read()); //  若沒有資料等於 -1
            System.out.println(is.read());
            System.out.println(is.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("message");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("File open success");
    }
}
