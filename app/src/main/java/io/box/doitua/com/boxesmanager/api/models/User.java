package io.box.doitua.com.boxesmanager.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class User extends Response implements Serializable {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("boxes")
    private List<Box> boxes;

    @Expose
    @SerializedName("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject data = super.toJson();
        data.put("name", name);
        data.put("email", email);
        JSONArray boxes = new JSONArray();
        if (this.boxes != null) {
            if (this.boxes.size() > 0) {
                for (Box b : this.boxes) {
                    boxes.put(b.toJson());
                }
            }
        }
        data.put("boxes", boxes);
        return data;
    }
}
