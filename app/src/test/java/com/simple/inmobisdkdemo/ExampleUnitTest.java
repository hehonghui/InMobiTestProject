package com.simple.inmobisdkdemo;

import com.simple.inmobisdkdemo.test.translate.Language;
import com.simple.inmobisdkdemo.test.translate.Translate;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testGetToken() throws Exception {
        Translate.setClientId("newsdog");
        Translate.setClientSecret("7mh3ySOFi3l3/5HQ0iLt1H+Mllbz9S8Bxw1m5SlWFLY=");

        String response = Translate.execute("WIFI नेटवर्क अनुपलब्ध। डेटा बचाने के लिए छवियाँ बंद करें?", Language.HINDI, Language.ENGLISH) ;
        System.out.println(response);
    }
}