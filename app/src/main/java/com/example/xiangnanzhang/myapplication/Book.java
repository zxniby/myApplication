package com.example.xiangnanzhang.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XiangnanZhang on 16/09/15.
 */
public class Book implements Parcelable {
    private int bId;
    private String bName;
    private String bPublisher;


    public Book(int id, String name, String publisher){
        this.bId = id;
        this.bName = name;
        this.bPublisher = publisher;
    }

    public Book(){}


    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbPublisher() {
        return bPublisher;
    }

    public void setbPublisher(String bPublisher) {
        this.bPublisher = bPublisher;
    }

    @Override
    public void writeToParcel(Parcel out, int flag){
        out.writeInt(bId);
        out.writeString(bName);
        out.writeString(bPublisher);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>(){
        @Override
        public Book createFromParcel(Parcel source){
            Book book = new Book();
            book.bId = source.readInt();
            book.bName = source.readString();
            book.bPublisher = source.readString();
            return book;
        }

        @Override
        public Book[] newArray(int size){
            return new Book[size];
        }

    };

}
