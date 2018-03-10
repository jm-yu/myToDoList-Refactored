package jmyu.ufl.edu.mytodolist.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by jmyu on 3/9/18.
 */

public class ModelUtils {

    private static Gson gson = new Gson();
    private static String PREF_NAME = "models";

    public static void save(Context context, String key, Object object){
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String jsonString = gson.toJson(object);
        sharedPref.edit().putString(key, jsonString).commit();
    }
    public static <T> T read(Context context, String key, TypeToken<T> typeToken){
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return gson.fromJson(sharedPref.getString(key,""), typeToken.getType());
    }
}
