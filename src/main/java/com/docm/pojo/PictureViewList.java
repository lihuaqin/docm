package com.docm.pojo;

import java.util.*;

import com.docm.vo.Node;


public class PictureViewList
{
    private List<Node> pictureViewList;
    private int index;
    
    public List<Node> getPictureViewList() {
        return this.pictureViewList;
    }
    
    public void setPictureViewList(final List<Node> pictureViewList) {
        this.pictureViewList = pictureViewList;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
}
