package com.yazlab.TextMerging.controller;


import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Arrays;

public class Compute {
    public static String call(String str1,String str2) {
        
        String str3 = "";
        Integer biggestCount = 0;
        Integer lastI = 0;
        Integer lastJ = 0;
        Integer[][] kelimeMatrisi = new Integer[str1.length()][str2.length()];
        for(int i = 0;i<kelimeMatrisi.length;i++){
            for(int j = 0;j<kelimeMatrisi[i].length;j++){
                kelimeMatrisi[i][j] = 0;
            }
        }

    
        for(int i = 0;i<str1.length();i++){
            for(int j = 0;j<str2.length();j++){
                if(str1.split("")[i].equals(str2.split("")[j])){
                    if(i==0||j==0){
                        kelimeMatrisi[i][j] = 1;
                    }else{
                        kelimeMatrisi[i][j] = kelimeMatrisi[i-1][j-1] +1;
                    }
                    if(kelimeMatrisi[i][j]>=biggestCount){
                        biggestCount = kelimeMatrisi[i][j];
                        lastI = i;
                        lastJ = j;
                    }
    
                }
            }
        }
        for(int i = 0; i<lastI+1-biggestCount;i++){
            str3 += str1.split("")[i];
        }
        for(int i = lastJ-biggestCount+1; i<lastJ+1;i++){
            str3 += str2.split("")[i];
        }
        for(int i = lastJ+1; i<str2.length();i++){
            str3 += str2.split("")[i];
        }
        return str3;
        
    }
    




    

        
}
