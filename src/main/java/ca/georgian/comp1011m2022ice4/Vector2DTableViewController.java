package ca.georgian.comp1011m2022ice4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Vector2DTableViewController implements Initializable {


    @FXML
    private TableView<Vector2D> tableView;

    @FXML
    private TableColumn<Vector2D, Integer> VectorIDColumn;

    @FXML
    private TableColumn<Vector2D, Float> XColumn;

    @FXML
    private TableColumn<Vector2D, Float> YColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        VectorIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        XColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        YColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));

        tableView.getItems().addAll(DBManager.Instance().readVectorTable());

        // mock data
        /*
        ObservableList<Vector2D> data = FXCollections.observableArrayList(
                new Vector2D(0, 0.0f, 0.0f),
                new Vector2D(1, 10, 20),
                new Vector2D(2, 45, 20),
                new Vector2D(3, 10, 20),
                new Vector2D(4, 310, 20),
                new Vector2D(5, 10, 20),
                new Vector2D(6, 10, 20)
        );

        tableView.getItems().addAll(data);

         */
    }
}
