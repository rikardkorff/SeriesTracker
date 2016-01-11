package seriestracker.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seriestracker.dao.GenreDAO;
import seriestracker.dao.sqlserver.GenreSqlServerDAO;
import seriestracker.models.Genre;

/**
 * Created by User on 08/01/2016.
 */
public class editGenreController {
    @FXML
    private TableView<Genre> genreTable;

    @FXML
    private TableColumn<Genre, String> genreColumn;

    @FXML
    private AnchorPane root;

    private GenreDAO dao = new GenreSqlServerDAO();

    @FXML
    private void initialize(){
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        genreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Genre> genres = FXCollections.observableArrayList();
        genres.addAll(dao.findAll());
        genreTable.setItems(genres);
        addRow();
    }

    @FXML
    private void onEdited(TableColumn.CellEditEvent<Genre, String> e){
        e.getRowValue().setName(e.getNewValue());
        int rowIdx =e.getTablePosition().getRow();
        if (rowIdx + 1 == genreTable.getItems().size()){
            addRow();
        }else {
            requestFocus(rowIdx + 1);
        }
    }

    private void addRow(){
        genreTable.getItems().add(new Genre());
        int rowIdx = genreTable.getItems().size() - 1;
        requestFocus(rowIdx);
    }

    @FXML
    private void onSave(){
        // add new
        for (Genre genre : genreTable.getItems().filtered(g -> g.getId() == 0)
             ) {
            dao.create(genre);

        }

        //update existing
        for (Genre genre : genreTable.getItems().filtered(g -> g.getHasChanged())
             ) {
            dao.update(genre);
        }
    }

    @FXML
    private void onClose(){
        ((Stage)root.getScene().getWindow()).close();
    }

    private void requestFocus(int rowIdx){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                genreTable.requestFocus();
                genreTable.getSelectionModel().select(rowIdx);
                genreTable.getFocusModel().focus(rowIdx);
                genreTable.edit(rowIdx, genreColumn);
            }
        });
    }
}
