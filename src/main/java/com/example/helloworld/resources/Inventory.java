package com.example.helloworld.resources;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.eclipse.jetty.http.HttpParser.State.URI;

/**
 * Created by vbathula on 10/1/16.
 */
public class Inventory {

    List<Mobile> mobiles;
    HashMap<String, Mobile> map;

    public Inventory() {
        this.mobiles = new ArrayList<Mobile>();
        this.map = new HashMap<String, Mobile>();
    }

//    public static void main(String[] args) throws IOException, URISyntaxException {
//
//        Inventory i = new Inventory();
//
//        i.fill();
//        i.delete("123");
//    }

    public List<Mobile> get(){

        return this.mobiles;
    }

    public void fill() throws IOException {

        //ClassLoader cl = getClass().getClassLoader();
        File actualFile = new File("Inventory.csv");

        BufferedReader br = new BufferedReader(new FileReader(actualFile));

        boolean firstLine =true;
        String line;

        while((line=br.readLine())!=null && line.trim().length() > 0){
            line=line.trim();

            if(firstLine) {
                firstLine=false;
                continue;
            }

            String[] mobileColumns = line.split(",",-1);

            for(int i=0;i<mobileColumns.length;i++){

                if(mobileColumns[i].isEmpty())
                    mobileColumns[i]=null;
            }

            Mobile mobile = new Mobile(mobileColumns[0],mobileColumns[1],mobileColumns[2],mobileColumns[3],mobileColumns[4],mobileColumns[5],mobileColumns[6],mobileColumns[7]);
            map.put(mobileColumns[2],mobile);
            mobiles.add(mobile);
        }

        br.close();
    }

    public Mobile get(String IMEI){

        return map.get(IMEI);
    }

    public List<Mobile> getMobilesWithStatus(String status){

        List<Mobile> mobiles = new ArrayList<Mobile>();

        for (Mobile mobile:
                this.mobiles) {

            if(mobile.status.equals(status))
                mobiles.add(mobile);

        }

        return mobiles;
    }

    public void add(Mobile mobile) throws IOException {

        map.put(mobile.getImeiNumber(),mobile);
        mobiles.add(mobile);

        File f = new File("Inventory.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));

        bw.write(mobile.toString());

        bw.close();

    }

    public void delete(String IMEINumber) throws IOException, URISyntaxException {

        Mobile mobile =map.get(IMEINumber);
        map.put(IMEINumber,null);
        mobiles.remove(mobile);

        if(mobile != null)
            deleteInFile(mobile);
        else
            System.out.println("The record does not exist");

    }

    public void delete(Mobile mobile) throws IOException, URISyntaxException {

        map.put(mobile.getImeiNumber(),null);
        mobiles.remove(mobile);

        if(mobile != null)
            deleteInFile(mobile);
        else
            System.out.println("The record does not exist");


    }

    private void deleteInFile(Mobile mobile) throws IOException, URISyntaxException {

       // ClassLoader cl = getClass().getClassLoader();
        File actualFile = new File("Inventory.csv");
        File tempFile = new File("temp.tmp");

        BufferedReader br = new BufferedReader(new FileReader(actualFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while((line=br.readLine())!=null ){

            line = line.trim();

            if(line.equals(mobile.toString().trim())) {
                continue;
            }

            bw.write(line + "\n");
        }

        br.close();
        bw.close();


        if(actualFile.delete())
            if(tempFile.renameTo(actualFile))
                System.out.println("SUCCESS !!!");

    }
}
