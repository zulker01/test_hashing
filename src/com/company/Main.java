package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static int prime = 769;
    static int m = 100;

    static int primary_a = 11;
    static int primary_b = 15;
    static  ArrayList<String> s1 = new ArrayList<>();
    static  ArrayList<String> s2 = new ArrayList<>();
    static ArrayList<Integer> key = new ArrayList<>();

    static ArrayList<ArrayList<Integer> > x
            = new ArrayList<ArrayList<Integer> >();

    public static void main(String[] args) {
	// write your code here



        for(int i=0;i<m;i++)
        {
            x.add(new ArrayList<Integer>());
        }
    /*
        s1.add("faito");
        s1.add("mairo");
        s1.add("lakka");
        s1.add("dacca");
        s1.add("fappa");
        s1.add("lalla");
        s1.add("akka");
        s1.add("adda");
        s1.add("acca");
      */
        String test = "";
        int p = 0;
        try {
            File myObj = new File("E:\\intellij project\\test_hashing\\src\\com\\company\\BengaliDictionary_17.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = data.substring(1,data.length());
                int wordStart = 0;
                int wordEnd = data.indexOf("|");
                String word = data.substring(wordStart,wordEnd);
                String banglaWord = data.substring(wordEnd+1,data.length());
                s1.add(word);
                s2.add(banglaWord);
                System.out.println(data);
                p++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("total line : "+p);
        int testKey,hashvalue;
        for(int i=0;i<s1.size();i++)
        {
            test = s1.get(i);
            testKey = getAscii(test);
            key.add(testKey);
            hashvalue = hash(primary_a,primary_b,testKey);
            if(x.get(hashvalue).contains(testKey))
            {
                continue;
            }
            else
                x.get(hashvalue).add(testKey);
        }
        for(int i=0;i<m;i++)
        {
            System.out.println(x.get(i));
        }
        //implement perfect hashing :
        perfect_hashing();


    }

    private static void perfect_hashing() {
        for(int i=0;i<m;i++)
        {
            if(x.get(i).size()<=1)
            {
                continue;
            }
            else
            {
                //findPerfectAB()
                Set<Integer> s =
                        new HashSet<Integer>();
                int len = x.get(i).size();
                while(true)
                {
                    int a = (int) (Math.random()%20);
                    int b = (int) (Math.random()%20);
                    for(int j=0;j<len;j++)
                    {
                        s.add(hash(a,b,x.get(i).get(j)));
                    }
                    if(s.size()==len)
                    {
                        System.out.println("congratulations for i = "+i+" ab is "+a+" "+b);
                        break;

                    }
                }

            }
        }
    }
    private static void retrieve(String word)
    {
        int key = getAscii(word);
        int primaryHash = hash(primary_a,primary_b,key);
        //find a,b for primary hash

    }

    public static int getAscii(String word)  {
        int sum = 0;
        int len = word.length();
        for(int i=0; i<word.length(); i++)
        {
            int asciiValue = word.charAt(i);
            sum = (int) (sum+ asciiValue*Math.pow(26,len-i-1));
            //System.out.println(str.charAt(i) + "=" + asciiValue);
        }
        System.out.println("ASCII of " + word + "=" + sum);
        return sum;
    }

    public static int hash(int a,int b,int key)
    {
        int hash = (((a*key)+b)%prime)%m;
        if(hash<0) hash = -1*hash;
        return hash;
    }
}
