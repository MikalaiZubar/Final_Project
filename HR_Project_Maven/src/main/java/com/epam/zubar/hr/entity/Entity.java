package com.epam.zubar.hr.entity;

import java.io.Serializable;
/**
 * Abstract class - parent for all entities.
 * @author Mikalay Zubar
 *
 */
public abstract class Entity implements Serializable{


    private static final long serialVersionUID = 1L;
    private int id;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}
