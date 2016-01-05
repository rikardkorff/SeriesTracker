package seriestacker.models;

/**
 * Created by rkorff on 2015-07-12.
 */
public class Movie {
    private int Id;
    private String Name;
    private Genre Genre;
    private int Year;
    private String Plot;

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

    public Genre getGenre(){
        return this.Genre;
    }

    public void setGenre(Genre genre){
        this. Genre = genre;
    }

    public int getYear(){
        return this.Year;
    }

    public void setYear(int year){
        this.Year = year;
    }

    public String getPlot(){
        return this.Plot;
    }

    public void setPlot(String plot){
        this.Plot = plot;
    }

    public Movie(){

    }

    public Movie(int id){
        this();
        this.setId(id);
    }
}
