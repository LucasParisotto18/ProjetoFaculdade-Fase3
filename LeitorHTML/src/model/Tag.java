/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lucas
 */
public class Tag<T> {

    private String tag;
    private int count;

    public Tag(String tag) {
        this.tag = tag;
        this.count += 1;
    }

    public Tag(String tag, int count) {
        this.tag = tag;
        this.count = count;
    }

    public Tag(){
    }

    public String getTag() {
        return tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isFechamento() {
        return tag.contains("/");
    }

    @Override
    public String toString() {
        return "Tag: " + tag + ", Count: " + count;
    }

    
    
}
