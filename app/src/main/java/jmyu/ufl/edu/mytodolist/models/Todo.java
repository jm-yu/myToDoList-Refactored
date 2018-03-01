package jmyu.ufl.edu.mytodolist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jmyu on 2/18/18.
 */

public class Todo implements Parcelable{

    public String id;

    public String text;

    public Date remindDate;

    public boolean done;

    public Todo(String text, Date remindDate) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.remindDate = remindDate;
        this.done = false;
    }

    protected Todo(Parcel in) {
        id = in.readString();
        text = in.readString();
        long date = in.readLong();
        remindDate = (date == 0) ? null : new Date(date);
        done = (in.readByte() == 1) ;
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(text);
        dest.writeLong((remindDate != null) ? remindDate.getTime() : 0);
        dest.writeByte((byte) (done ? 1 : 0));
    }
}

