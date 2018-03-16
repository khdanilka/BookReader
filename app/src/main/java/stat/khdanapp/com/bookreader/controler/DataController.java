package stat.khdanapp.com.bookreader.controler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import stat.khdanapp.com.bookreader.R;
import stat.khdanapp.com.bookreader.model.BookCardView;

/**
 * Created by android on 3/16/18.
 */

public class DataController {

    public static ArrayList<BookCardView> readSharedPref(Activity activity){
        Context context = activity;
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        Map<String,?> m = sharedPref.getAll();
        ArrayList<BookCardView> hm = new ArrayList<>();
        int imageId = R.drawable.a3;
        for (Map.Entry<String, ?> entry : m.entrySet())
        {
            if (imageId == R.drawable.a3) imageId = R.drawable.a4;
            else imageId = R.drawable.a3;
            hm.add(new BookCardView(imageId,entry.getKey(),(String) entry.getValue(),R.drawable.ic_favorite_black_24dp));
        }
        return hm;
    }

    public static boolean containsSharedPref(Activity activity,String str){
        Context context = activity;
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        return sharedPref.contains(str);
    }

    public static void writeSharedPref(Activity activity,String author,String str){
        Context context = activity;
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(str, author);
        editor.apply();
    }

    public static void removeSharedPref(Activity activity,String str){
        Context context = activity;
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.shared_pref_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(str);
        editor.apply();
    }


}
