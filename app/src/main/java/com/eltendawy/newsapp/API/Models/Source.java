
package com.eltendawy.newsapp.API.Models;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
@SuppressWarnings("unused")
public class Source implements Parcelable {

    @Expose
    private String category;
    @Expose
    private String country;
    @Expose
    private String description;
    @Expose
    private String id;
    @Expose
    private String language;
    @Expose
    private String name;
    @Expose
    private String url;

    protected Source(Parcel in) {
        category = in.readString();
        country = in.readString();
        description = in.readString();
        id = in.readString();
        language = in.readString();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Source> CREATOR = new Creator<Source>() {
        @Override
        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
        }
    };

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(country);
        dest.writeString(description);
        dest.writeString(id);
        dest.writeString(language);
        dest.writeString(name);
        dest.writeString(url);
    }
}
