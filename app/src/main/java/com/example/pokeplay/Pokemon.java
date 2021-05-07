package com.example.pokeplay;

public class Pokemon {
    String name;
    String typeString;
    String iconString;
    String url;

    public Pokemon(String name, String typeString, String iconString, String url) {
        this.name = name;
        this.typeString = typeString;
        this.iconString = iconString;
        this.url = url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getIconString() {
        return iconString;
    }

    public void setIconString(String iconString) {
        this.iconString = iconString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", typeString='" + typeString + '\'' +
                ", iconString='" + iconString + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
