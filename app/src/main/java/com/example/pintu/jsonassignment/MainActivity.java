package com.example.pintu.jsonassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MainActivity extends AppCompatActivity {

    String colorspec;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI
        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        // String contains JSON structure: Colorspecifications

        colorspec = " [\n" +
                " {\n" +
                "   \"color\": \"black\",\n" +
                "   \"category\" : \"hue\",\n" +
                "   \"type\": \"primary\",\n" +
                "   \"code\": {\n" +
                "     \"rgba\": [255, 255, 255, 1],\n" +
                "     \"hex\": \"#000\"\n" +
                "   }\n" +
                "  },\n" +
                "   {\n" +
                "   \"color\": \"white\", \n" +
                "   \"category\": \"value\", \n" +
                "   \"code\": {\n" +
                "   \"rgba\": [0, 0, 0, 1], \n " +
                "   \"hex\": \"#FFF\" \n" +
                "   }\n" +
                "   }, \n" +
                "   {\n" +
                "   \"color\": \"red\", \n" +
                "   \"category\": \"value\", \n" +
                "   \"type\": \"primary\", \n" +
                "   \"code\": {\n" +
                "   \"rgba\": [255, 0, 0, 1], \n " +
                "   \"hex\": \"#FF0\" \n" +
                "   }\n" +
                "   }, \n" +
                "   {\n" +
                "   \"color\": \"blue\", \n" +
                "   \"category\": \"hue\", \n" +
                "   \"type\": \"primary\", \n" +
                "   \"code\": {\n" +
                "   \"rgba\": [0, 0, 255, 1], \n" +
                "   \"hex\": \"#00F\" \n" +
                "   }\n" +
                "   }, \n" +
                "   {\n" +
                "   \"color\": \"yellow\", \n" +
                "   \"category\": \"hue\", \n" +
                "   \"type\": \"primary\", \n" +
                "   \"code\": {\n" +
                "   \"rgba\": [255, 255, 0, 1], \n" +
                "   \"hex\": \"#FF0\" \n" +
                "   }\n" +
                "   }, \n" +
                "  {\n" +
                "    \"color\": \"green\",\n" +
                "    \"category\" : \"hue\",\n" +
                "    \"type\": \"secondary\",\n" +
                "    \"code\": {\n" +
                "       \"rgba\": [0, 255, 0, 1],\n" +
                "       \"hex\": \"#0F0\"\n" +
                "     }\n" +
                "   }\n" +
                " ]";
    }

    /*COUNT button functionality: the application processes the JSON data and writes into the textView a string consisting of
    the concatenation of the color field (i.e. black) of the colors having green component equal to 255.
            */

    public void count(View view) throws JSONException {
        try {
            JSONArray colorspec_array = (JSONArray) new JSONTokener(colorspec).nextValue();
            int count = 0;
            for (int i = 0; i < colorspec_array.length(); i++) {
                JSONObject colors = colorspec_array.getJSONObject(i);
                JSONObject colorcode = colors.getJSONObject("code");
                JSONArray rgba = (JSONArray) colorcode.get("rgba");
                String green_code = Integer.toString(rgba.getInt(1));
                if (green_code.equals("255"))
                    count++;
            }
            textView.setText(Integer.toString(count));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*LIST button functionality: the application processes the JSON data and writes into the textView a string consisting of
    the concatenation of the color field (i.e. black) of the colors having green component equal to 255.
            */
    public void list(View view) throws JSONException {
        try {
            JSONArray colorspec_array = (JSONArray) new JSONTokener(colorspec).nextValue();
            String text = "";
            for (int i = 0; i < colorspec_array.length(); i++) {
                JSONObject colors = colorspec_array.getJSONObject(i);
                JSONObject colorcode = colors.getJSONObject("code");
                JSONArray rgba_code = (JSONArray) colorcode.get("rgba");
                String green_code = Integer.toString(rgba_code.getInt(1));
                if (green_code.equals("255"))
                    text += " " + (String) colors.get("color");
            }
            textView.setText(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*MODIFY button functionality: the application modifies the JSON data, adding a new color (color:
    orange, category: hue, rgba: 255,165,1,0 hex: #FA0), then serializes the JSON data to a string and
    writes it into the textView.*/

    public void modify(View view) throws JSONException {
        try {

            JSONArray colors = (JSONArray) new JSONTokener(colorspec).nextValue();
            JSONObject newcolor = new JSONObject();
            newcolor.put("color", "orange");
            newcolor.put("category", "hue");
            JSONArray rgba_code = new JSONArray();
            rgba_code.put(255);
            rgba_code.put(165);
            rgba_code.put(1);
            rgba_code.put(0);
            newcolor.put("rgba", rgba_code);
            newcolor.put("hex", "#FA0");

            colors.put(newcolor);
            textView.setText(colors.toString(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
