package tdsa2.com.br.gbooks.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ricardo on 02/09/2015.
 */
public class ControlaSharedPreferences {

    public static void saveString(Context ctx, String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(key, value);
        ed.commit();
    }

    public static void saveBoolean(Context ctx, String key, boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(key, value);
        ed.commit();
    }

    public static boolean getBoolean(Context ctx, String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        return sp.getBoolean(key, false);
    }

}
