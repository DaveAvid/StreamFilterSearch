package com.david.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField filterMovieTextField;
    @FXML
    private TextField filterActorTextField;
    @FXML
    private TextField filterDirectorTextField;
    @FXML
    private TextField filterGenreTextField;
    @FXML
    private TextField filterDateTextField;
    @FXML
    private TableView<MediaType> mediaTable;
    @FXML
    private TableColumn<String, MediaType> titleColumn;
    @FXML
    private TableColumn<String, MediaType> dateReleasedColumn;
    @FXML
    private TableColumn<ArrayList<Person>, MediaType> actorColumn;
    @FXML
    private TableColumn<ArrayList<Person>, MediaType> directorColumn;
    @FXML
    private TableColumn<ArrayList<String>, MediaType> genreColumn;
    @FXML
    private TableColumn<String, MediaType> runTimeColumn;
    @FXML
    private TableColumn<String, MediaType> typeColumn;
    @FXML
    private TableColumn<String, MediaType> imdbColumn;
    @FXML
    private TableColumn<String, MediaType> mpaaColumn;
    @FXML
    private TableColumn<String, MediaType> yearColumn;
    @FXML
    private TableColumn<String, MediaType> descriptionColumn;
    List<MediaType> allMedia = MediaUtils.populateMediaList("NetFlixMovies.json", "NetFlixTVShows.json");
    private ObservableList<MediaType> masterData = FXCollections.observableArrayList(allMedia);
    private ObservableList<MediaType> filteredData = FXCollections.observableArrayList();

    /**
     * The constructor is called before the initialize() method.
     *
     * @throws IOException if cannot read file
     */
    public Controller() throws IOException {

        //Add data to filtered data
        filteredData.addAll(masterData);
        //Listen for changes in master data
        //Whenever the master data changes we must also update the filtered data.
        masterData.addListener(new ListChangeListener<MediaType>() {
            @Override
            public void onChanged(Change<? extends MediaType> c) {
                updateFilteredData();
            }
        });
    }

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        //Initialize the person table
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateReleasedColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        actorColumn.setCellValueFactory(new PropertyValueFactory<>("actors"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("directors"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
        runTimeColumn.setCellValueFactory(new PropertyValueFactory<>("runTime"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        imdbColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        mpaaColumn.setCellValueFactory(new PropertyValueFactory<>("mpaaRating"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        mediaTable.setItems(filteredData);

        //Listen for text changes in the title text field
        filterMovieTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }
        });

        //Listen for text changes in the actor text field
        filterActorTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }
        });
        //Listen for text changes in the director text field
        filterDirectorTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }
        });
        //Listen for text changes in the director text field
        filterGenreTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }
        });
        //Listen for text changes in the director text field
        filterDateTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateFilteredData();
            }
        });
    }

    /**
     * Updates the filteredTitleData to contain all data from the masterData that matches the current filter.
     *
     */
    private void updateFilteredData() {
        filteredData.clear();

        for (MediaType m : masterData) {
            if (matchesFilter(m)) {
                filteredData.add(m);
            }
        }
        //Must re-sort table after items changed
        reapplyTableSortOrder();
    }

    /**
     * Returns true if the media matches the current filter. Lower/Upper case ignored.
     *
     * @param mediaType Object of type MediaType
     * @return boolean true if a match is found and false if it is not
     */
    private boolean matchesFilter(MediaType mediaType) {
        if (StringUtils.containsIgnoreCase(mediaType.getTitle(), filterMovieTextField.getText()) || filterMovieTextField.getText().isEmpty()) {
            if (StringUtils.containsIgnoreCase(mediaType.getReleaseDate(), filterDateTextField.getText()) || filterDateTextField.getText().isEmpty()) {
                for (Person actor : mediaType.getActors()) {
                    if (StringUtils.containsIgnoreCase(actor.getFullName(), filterActorTextField.getText()) || filterActorTextField.getText().isEmpty()) {
                        for (Person director : mediaType.getDirectors()) {
                            if (StringUtils.containsIgnoreCase(director.getFullName(), filterDirectorTextField.getText()) || filterDirectorTextField.getText().isEmpty()) {
                                for (String genre : mediaType.getGenres()) {
                                    if (StringUtils.containsIgnoreCase(genre, filterGenreTextField.getText()) || filterGenreTextField.getText().isEmpty()) {
                                        return true;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return false; //Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<MediaType, ?>> sortOrder = new ArrayList<>(mediaTable.getSortOrder());
        mediaTable.getSortOrder().clear();
        mediaTable.getSortOrder().addAll(sortOrder);
    }
}