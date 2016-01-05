package seriestacker.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rkorff on 2015-07-11.
 */
public class Season {
    private int Id;
    private int SeasonNo;
    private Collection<Episode> Episodes;

    public int getId(){
        return this.Id;
    }

    private void setId(int id){
        this.Id = id;
    }

    public int getSeasonNo(){
        return this.SeasonNo;
    }

    public void setSeasonNo(int seasonNo){
        this.SeasonNo = seasonNo;
    }

    public Collection<Episode> getEpisodes(){
        return this.Episodes;
    }

    public void setEpisodes(Collection<Episode> episodes){
        this.Episodes = episodes;
    }

    public Season(){

    }

    public Season(int id){
        this();
        this.setId(id);
    }
}
