package seriestracker.models;

import javafx.beans.property.*;

/**
 * Created by rkorff on 2015-07-12.
 */
public class Genre {
    private final IntegerProperty Id;
    private final StringProperty Name;
    private final BooleanProperty HasChanged;

    public int getId(){
        return this.Id.get();
    }

    public void setId(int id){
        this.Id.set(id);
    }

    public String getName(){
        return this.Name.get();
    }

    public void setName(String name){
        this.Name.set(name);
        this.HasChanged.set(true);
    }

    public boolean getHasChanged(){
        return this.HasChanged.get();
    }

    public void setHasChanged(boolean hasChanged){
        this.HasChanged.set(hasChanged);
    }

    public StringProperty NameProperty(){
        return Name;
    }

    public Genre(){
        this(0, null, false);
    }

    public Genre(int id){
        this(id, null, false);
    }

    public Genre(int id, String name){
        this(id, name, false);
    }

    public Genre(int id, String name, boolean hasChanged){
        this.Id = new SimpleIntegerProperty(id);
        this.Name = new SimpleStringProperty(name);
        this.HasChanged = new SimpleBooleanProperty(hasChanged);
    }
}
