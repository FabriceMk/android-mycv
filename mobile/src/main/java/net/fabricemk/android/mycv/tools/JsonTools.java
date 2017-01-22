package net.fabricemk.android.mycv.tools;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Tools related to JSON reading, processing...
 */
public class JsonTools {

    private static String LOGTAG = JsonTools.class.getSimpleName();

    /**
     * Reads a JSON resource file
     * @param resources Android resources
     * @param id the JSON resource ID
     * @return the JSON as a string
     */
    public static String readLocal(Resources resources, int id) {
        InputStream resourceReader = resources.openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e(LOGTAG, "Unhandled exception while using JsonTools", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e(LOGTAG, "Unhandled exception while using JsonTools", e);
            }
        }

        return writer.toString();
    }

    /**
     * Builds an object from a JSON string using GSON mapping
     * @param type the type of the object to be instantiated into
     * @param jsonString the JSON string
     * @param <T> the type of the object to be instantiated into
     * @return An object
     */
    public static <T> T constructUsingGson(Class<T> type, String jsonString) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, type);
    }
}
