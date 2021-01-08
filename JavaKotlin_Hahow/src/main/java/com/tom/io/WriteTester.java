package com.tom.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTester {

    public static void main(String[] args) throws IOException {
        File jkDir = new File("D:\\jk");
        jkDir.mkdir(); // .mkdirs(); 建立資料夾
        // 也可以使用絕對路徑： D:\\jk\\output.txt
        FileWriter fw = new FileWriter("output.txt");
        fw.write("abc"); // 資料要到一定程度才會寫入，不然只會放在緩衝區
        fw.flush(); // 所以要靠 flush 來清緩存
        fw.close(); // 關閉資源
    }
}
