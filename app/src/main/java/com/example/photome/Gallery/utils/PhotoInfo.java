package com.example.photome.Gallery.utils;

public class PhotoInfo {

    public String name;
    public String path;
    public long time;

    public PhotoInfo(String path, String name, long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }

    @Override
    public boolean equals(Object object) {
        try {
            PhotoInfo other = (PhotoInfo) object;
            return this.path.equalsIgnoreCase(other.path);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(object);
    }


    @Override
    public String toString() {
        String s = "PhotoInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                '}';
        return s;
    }
}
