package seriestacker.models;

/**
 * Created by rkorff on 2015-07-12.
 */
public class Genre {
    private int Id;
    private String Name;

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

    public Genre(){

    }

    public Genre(int id){
        this();
        this.setId(id);
    }
}
