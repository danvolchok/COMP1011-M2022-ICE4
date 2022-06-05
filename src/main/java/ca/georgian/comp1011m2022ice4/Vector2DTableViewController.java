package ca.georgian.comp1011m2022ice4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        var vectors = DBManager.Instance().readVectorTable();
        System.out.println("Vector2D Data: ");
        for (var vector: vectors)
        {
            System.out.println(vector);
        }
    }
}
