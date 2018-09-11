package io.box.doitua.com.boxesmanager.api.models;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import io.box.doitua.com.boxesmanager.App;
import io.box.doitua.com.boxesmanager.R;

public class Box extends Response {

    public static final Integer SMALL = 0;
    public static final Integer MEDIUM = 1;
    public static final Integer LARGE = 2;

    public static final int[] SMALL_BOX_COLORS = {Color.RED, Color.BLUE, Color.YELLOW};
    public static final int[] MEDIUM_BOX_COLORS = {
            Color.RED,
            Color.YELLOW,
            ResourcesCompat.getColor(App.getInstance().getMResources(), R.color.colorPurple, null),
            Color.GREEN
    };
    public static final int[] LARGE_BOX_COLORS = {
            Color.GREEN,
            ResourcesCompat.getColor(App.getInstance().getMResources(), R.color.colorOrange, null),
            Color.BLUE
    };

    public static final String[] SMALL_COLOR_LABELS = {"red", "blue", "yellow"};
    public static final String[] MEDIUM_COLOR_LABELS = {
            "red",
            "yellow",
            "purple",
            "green"
    };
    public static final String[] LARGE_COLOR_LABELS = {
            "green",
            "orange",
            "blue"
    };

    @Expose
    @SerializedName("height")
    private int height;
    @Expose
    @SerializedName("width")
    private int width;
    @Expose
    @SerializedName("length")
    private int length;
    @Expose
    @SerializedName("color")
    private String color;
    @Expose
    @SerializedName("size")
    private String size;
    @Expose
    @SerializedName("isNamed")
    private boolean isNamed;


    public Box(int height, int width, int length) {
        this.height = height;
        this.width = width;
        this.length = length;
        size = "custom";
    }

    public Box(String size) {
        switch (size){
            case "small": {
                height = 15;
                width = 15;
                length = 15;
                this.size = "small";
                break;
            }
            case "medium": {
                height = 100;
                width = 25;
                length = 25;
                this.size = "medium";
                break;
            }
            case "large": {
                height = 75;
                width = 75;
                length = 75;
                this.size = "large";
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown size param passed or null");
        }
    }

    public Box(Integer size) {
        switch (size) {
            case 0: {
                height = 15;
                width = 15;
                length = 15;
                this.size = "small";
                break;
            }
            case 1: {
                height = 100;
                width = 25;
                length = 25;
                this.size = "medium";
                break;
            }
            case 2: {
                height = 75;
                width = 75;
                length = 75;
                this.size = "large";
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown size param passed or null");
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isNamed() {
        return isNamed;
    }

    public void setNamed(boolean named) {
        isNamed = named;
    }

    public String getColorName() {
        return color;
    }

    public Integer getColor(String colorName) {
        switch (colorName) {
            case "red": {
                return Color.RED;
            }
            case "blue": {
                return Color.BLUE;
            }
            case "purple": {
                return  ResourcesCompat.getColor(App.getInstance().getMResources(), R.color.colorPurple, null);
            }
            case "yellow": {
                return Color.YELLOW;
            }
            case "orange": {
                return  ResourcesCompat.getColor(App.getInstance().getMResources(), R.color.colorOrange, null);
            }
            case "green": {
                return Color.GREEN;
            }
            default:
                return Color.GRAY;
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("height", height);
        data.put("width", width);
        data.put("length", length);
        data.put("color", color);
        data.put("size", size);
        data.put("isNamed", isNamed);
        return data;
    }
}
