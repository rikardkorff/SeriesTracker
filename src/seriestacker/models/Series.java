package seriestacker.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rkorff on 2015-07-11.
 */
public class Series {
    private int Id;
    private String Name;
    private Collection<Season> Seasons;

    public int getId(){
        return this.Id;
    }

    private void setId(int id){
        this.Id = id;
    }

    public String getName(){
        return this.Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public Collection<Season> getSeasons(){
        return this.getSeasons();
    }

    public void setSeasons(Collection<Season> seasons){
        this.Seasons = seasons;
    }

    public Series(){

    }

    public Series(int id){
        this();
        this.setId(id);
    }
}
