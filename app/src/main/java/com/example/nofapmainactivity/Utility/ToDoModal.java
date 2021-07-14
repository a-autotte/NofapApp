package com.example.nofapmainactivity.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ToDoModal implements Serializable {
    // Variables of ToDoModal

    private String mToDoText;
    private String mToDoDescription;
    private Date mToDoDate;
    private boolean mToDoHasReminder;
    private int mToDoColor;
    private UUID mToDoId;
    private static final String TODOTEXT = "todotext";
    private static final String TODODESCRIPTION = "tododescription";
    private static final String TODOREMINDER = "todoreminder";
    private static final String TODOCOLOR = "todocolor";
    private static final String TODODATE = "tododate";
    private static final String TODOID = "todoid";

    // Constructors of ToDoModal

    public ToDoModal() {
        this("Do my projects", "Coding my application", new Date(), true);
    }

    public ToDoModal(String body, String description, Date datetime, boolean hasReminder)
    {
        mToDoText = body;
        mToDoDescription = description;
        mToDoDate = datetime;
        mToDoHasReminder = hasReminder;
        mToDoColor = 1677725;
        mToDoId = UUID.randomUUID();
    }

    public ToDoModal(JSONObject jsonObject) throws JSONException {
        mToDoText = jsonObject.getString(TODOTEXT);
        mToDoDescription = jsonObject.getString(TODODESCRIPTION);
        mToDoHasReminder = jsonObject.getBoolean(TODOREMINDER);
        mToDoColor = jsonObject.getInt(TODOCOLOR);
        mToDoId = UUID.fromString(jsonObject.getString(TODOID));

        if (jsonObject.has(TODODATE)) {
            mToDoDate = new Date(jsonObject.getLong(TODODATE));
        }

    }

    // Here we will convert values in a JsonObject
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TODOTEXT, mToDoText);
        jsonObject.put(TODODESCRIPTION, mToDoDescription);
        jsonObject.put(TODOREMINDER, mToDoHasReminder);
        jsonObject.put(TODODATE, mToDoDate);

        if (mToDoDate != null) {
            jsonObject.put(TODODATE, mToDoDate.getTime());
        }

        jsonObject.put(TODOCOLOR, mToDoColor);
        jsonObject.put(TODOID, mToDoId);

        return jsonObject;
    }

    // Methods for ToDoModal

    public String getToDoText() {
        return mToDoText;
    }

    public void setToDoText(String text) {
        this.mToDoText = text;
    }

    public String getToDoDescription() {
        return mToDoDescription;
    }

    public void setToDoDescription(String description) {
        this.mToDoDescription = description;
    }

    public Date getToDoDate() {
        return mToDoDate;
    }

    public void setToDoDate(Date date) {
        this.mToDoDate = date;
    }

    public boolean hasReminder() {
        return mToDoHasReminder;
    }

    public void setToDoHasReminder(boolean reminder) {
        this.mToDoHasReminder = reminder;
    }

    public int getTodoColor() {
        return mToDoColor;
    }

    public void setToDoColor(int color) {
        this.mToDoColor = color;
    }

    public UUID getId() {
        return mToDoId;
    }
}
