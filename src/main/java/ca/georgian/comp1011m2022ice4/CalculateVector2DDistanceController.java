package ca.georgian.comp1011m2022ice4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculateVector2DDistanceController implements Initializable
{

    @FXML
    private TextField ResultsTextField;

    @FXML
    private Spinner<Double> X1Spinner;

    @FXML
    private Spinner<Double> X2Spinner;

    @FXML
    private Spinner<Double> Y1Spinner;

    @FXML
    private Spinner<Double> Y2Spinner;

    @FXML
    void OnCalculateButtonClicked(ActionEvent event)
    {

        // Setup variables
        try
        {
            float x1 = X1Spinner.getValue().floatValue();
            float y1 = Y1Spinner.getValue().floatValue();
            float x2 = X2Spinner.getValue().floatValue();
            float y2 = Y2Spinner.getValue().floatValue();

            Vector2D point1 = new Vector2D(x1, y1);
            int insertPoint1 = DBManager.Instance().insertVector2D(point1);

            Vector2D point2 = new Vector2D(x2, y2);
            int insertPoint2 = DBManager.Instance().insertVector2D(point2);

            float distance = Utility.Instance().Distance(point1, point2);
            ResultsTextField.setText(String.valueOf(distance));
        }

        catch (Exception e)
        {
            ResultsTextField.setText("Please enter valid numbers.");
        }
    }

    @FXML
    void OnResetButtonClicked(ActionEvent event)
    {
        X1Spinner.getValueFactory().setValue(0.0);
        Y1Spinner.getValueFactory().setValue(0.0);
        X2Spinner.getValueFactory().setValue(0.0);
        Y2Spinner.getValueFactory().setValue(0.0);
        ResultsTextField.clear();

        X1Spinner.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Utility.Instance().ConfigureVector2DSpinner(X1Spinner, -1000.0, 1000.0, 0.0, 5.0);
        Utility.Instance().ConfigureVector2DSpinner(Y1Spinner, -1000.0, 1000.0, 0.0, 5.0);
        Utility.Instance().ConfigureVector2DSpinner(X2Spinner, -1000.0, 1000.0, 0.0, 5.0);
        Utility.Instance().ConfigureVector2DSpinner(Y2Spinner, -1000.0, 1000.0, 0.0, 5.0);



    }
}
