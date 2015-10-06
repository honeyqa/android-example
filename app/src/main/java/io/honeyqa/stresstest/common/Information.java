package io.honeyqa.stresstest.common;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author seunoh on 2014. 05. 06..
 */
@Data
@Accessors(prefix = "m")
public class Information {

    @SerializedName("EXCEPTION_MESSAGE")
    private String mExceptionMessage;


    @SerializedName("MILLIS")
    private long mMillis;

    public String getExceptionMessage() {
        return mExceptionMessage;
    }

    public void setExceptionMessage(String mExceptionMessage) {
        this.mExceptionMessage = mExceptionMessage;
    }

    public long getMillis() {
        return mMillis;
    }

    public void setMillis(long mMillis) {
        this.mMillis = mMillis;
    }
}
