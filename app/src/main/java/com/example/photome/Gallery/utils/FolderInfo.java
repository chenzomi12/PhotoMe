package com.example.photome.Gallery.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contain the folder base info including folder name, number of photo in folder.
 */
public class FolderInfo {
    public String name;
    public String path;
    public PhotoInfo photoInfo;
    public List<PhotoInfo> photoInfoList;

    @Override
    public boolean equals(Object obj) {
        try{
            FolderInfo other = (FolderInfo) obj;
            return this.path.equalsIgnoreCase(other.path);
        }catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        String s = "FolderInfo{" +
                "name=" + name + '\'' +
                ", path=" + path + '\'' +
                ", photoInfo=" + photoInfo +
                ", photoInfoList=" + photoInfoList +
                "}";
        return s;
    }
}
