package com.java_technology;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Program
{
    public static void main( String[] args )
    {
        if(args.length == 0){
            System.out.println("Please specify an URL to a file");
            return;
        }
        String urlString = args[0];
        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);
        try {
            URL url = new URL(urlString);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
            System.out.println("File Downloaded Successfully!");
        } catch (MalformedURLException e) {
            System.out.println("The URL entered is not valid!");
        } catch (IOException e) {
            System.out.println("Error in downloading the file!");
        }
    }
}
