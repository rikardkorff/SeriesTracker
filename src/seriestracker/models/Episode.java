package seriestracker.models;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rkorff on 2015-07-11.
 */
public class Episode {
    private int Id;
    private int EpisodeNo;
    private String Description;
    private Path FileName;
    private boolean Watched;
    private Date WatchDate;

    public int getId(){
        return this.Id;
    }

    private void setId(int id){
        this.Id = id;
    }

    public int getEpisodeNo(){
        return this.EpisodeNo;
    }

    public void setEpisodeNo(int episodeNo){
        this.EpisodeNo = episodeNo;
    }

    public String getDescription(){
        return this.Description;
    }

    public void setDescription(String description){
        this.Description = description;
    }

    public Path getFileName(){
        return this.FileName;
    }

    public void setFileName(Path fileName){
        this.FileName = fileName;
    }

    public boolean getWatched(){
        return this.Watched;
    }

    public void setWatched(boolean watched){
        this.Watched = watched;
        if (watched){
            this.setWatchDate(Calendar.getInstance().getTime());
        }
        else{
            this.setWatchDate(null);
        }
    }

    public Date getWatchDate(){
        return this.WatchDate;
    }

    public void setWatchDate(Date date){
        this.WatchDate = date;
    }

    public Episode(){

    }

    public Episode(int id){
        this();
        this.setId(id);
    }
}
