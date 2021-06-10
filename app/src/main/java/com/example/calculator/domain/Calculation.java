package com.example.calculator.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Calculation implements Parcelable {


    private final List<String> data = new ArrayList<>();
    private String result;

    public Calculation(String result) {
        this.result = result;
    }

    public void clearData() {
        while (data.size() != 0) {
            data.remove(0);
        }
    }

    public void stringToChar(String strEnterText) {
        char[] chrEnterdText = strEnterText.toCharArray();
        for (char c : chrEnterdText) {
            String s = Character.toString(c);
            data.add(s);
        }
    }


    public String counter() {

        double rslt = 0d;

        for (int i = 0; i < data.size(); i++) {
            if (data.contains("\u00F7")) {
                int index = data.indexOf("\u00F7");
                rslt = Double.valueOf(data.get(index - 1)) / Double.valueOf(data.get(index + 1));
            } else if (data.contains("\u00D7")) {
                int index = data.indexOf("\u00D7");
                rslt = Double.valueOf(data.get(index - 1)) * Double.valueOf(data.get(index + 1));
            } else if (data.contains("-")) {
                int index = data.indexOf("-");
                int lastIndex = data.lastIndexOf("-");
                if (index == 0) {
                    rslt = 0.0 - Double.valueOf(data.get(index + 1));
                } else if ((lastIndex - 2 > 0) && (data.get(lastIndex - 2).equals("-"))) {
                    rslt = Double.valueOf(data.get(lastIndex + 1)) + Double.valueOf(data.get(lastIndex - 1));
                } else {
                    rslt = Double.valueOf(data.get(index - 1)) - Double.valueOf(data.get(index + 1));
                }
            } else if (data.contains("+")) {
                int index = data.indexOf("+");
                rslt = Double.valueOf(data.get(index - 1)) + Double.valueOf(data.get(index + 1));
            }
        }

        result = Double.toString(rslt);
        return result;
    }

    protected Calculation(Parcel in) {
        result = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
    }

    public static final Creator<Calculation> CREATOR = new Creator<Calculation>() {
        @Override
        public Calculation createFromParcel(Parcel in) {
            return new Calculation(in);
        }

        @Override
        public Calculation[] newArray(int size) {
            return new Calculation[size];
        }
    };
}
