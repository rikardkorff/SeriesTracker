package seriestracker.models;

import java.nio.file.Path;
import java.util.Date;

/**
 * Created by rkorff on 2015-07-12.
 */
public class Movie {
    private int Id;
    private String Name;
    private Genre Genre;
    private int Year;
    private String Plot;
    private Path FileName;
    private Boolean Watched;
    private Date WatchDate;

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

    public Path getFileName(){
    	return this.FileName;
    }

    public void setFileName(Path filename){
    	this.FileName = filename;
    }

    public Boolean getWatched(){
    	return this.Watched;
    }

    public void setWatched(Boolean watched){
    	this.Watched = watched;
    }

    public Date getWatchDate(){
    	return this.WatchDate;
    }

    public void setWatchDate(Date watchDate){
    	this.WatchDate = watchDate;
    }

    public Movie(){

    }

    public Movie(int id){
        this();
        this.setId(id);
    }
}
