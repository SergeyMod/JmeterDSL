package ru.helpdesk.script.addusers2.helpers;

import java.util.ArrayList;
import java.util.Random;

public class GenerateHelpers {
    public static String get(int lenght) {
        return get(lenght, "1234567890abcdefghiklmnopqrstuvwxyz");
    }
    public static String get(int lenght, String string) {
        Random random = new Random();
        char[] result = new char[lenght];
        for (int i = 0; i < lenght; i++) {
            result[i] = string.charAt(random.nextInt(string.length()));
        }
        return new String(result);
    }
}


