package jmyu.ufl.edu.mytodolist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jmyu on 2/18/18.
 */

public class Todo implements Parcelable{

    //public String id;

    public String text;

    public Date remindDate;

    //public boolean done;

    public Todo(String text, Date remindDate) {
        this.text = text;
        this.remindDate = remindDate;
    }

    protected Todo(Parcel in) {
        //id = in.readString();
        text = in.readString();
        remindDate = new Date(in.readLong());
        //done = (in.readByte() == 1) ;
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
        //dest.writeString(id);
        dest.writeString(text);
        dest.writeLong(remindDate.getTime());
        //dest.writeByte((byte) (done ? 1 : 0));
    }
}
