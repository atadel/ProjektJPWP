package com.example.parapet;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    int ususzone = 0;
    int wszystkie = 0;
    int max = 10;

 //funkcjonalność metod
 public void ususz(ImageView view){
     Effect currentEffect = view.getEffect();
     if (currentEffect!=null&&!(currentEffect instanceof SepiaTone)&&!(currentEffect instanceof Blend)){
         Blend blend = new Blend();
         blend.setMode(BlendMode.MULTIPLY);
         blend.setBottomInput(currentEffect);
         blend.setTopInput(new SepiaTone(0.6));
         view.setEffect(blend);
         //view.setEffect(new SepiaTone(0.8));
         ususzone +=1;
     }
     else if ((currentEffect instanceof SepiaTone)&&!(currentEffect instanceof Blend)){
         view.setEffect(null);
         ususzone-=1;       }
     else if (currentEffect == null){
         view.setEffect(new SepiaTone(0.8));
         ususzone +=1;
     }
     else if ((currentEffect instanceof Blend)){
         Reflection reflection = new Reflection();
         reflection.setTopOffset(0);
         reflection.setTopOpacity(0.75);
         reflection.setBottomOpacity(0.10);
         reflection.setFraction(0.7);
         view.setEffect(reflection);
         ususzone -=1;
     }
 }

    public void zmianaKwiatka(ImageView view, String nazwa) throws IOException {
        Image image = new Image(getClass().getResource(nazwa+".png").openStream());
        view.setImage(image);
    }

    //public void dodajKwiatek()
    public void nowyKwiatek(HBox hbox){
        if (wszystkie < max) {
            ImageView widok = new ImageView();
            wszystkie +=1;
            widok.setFitWidth(180);
            //widok.setFitHeight(200);
            widok.setPreserveRatio(true);
            ComboBox<String> combo = new ComboBox<>();

            combo.getItems().addAll("kaktus", "bratek", "chryzantemy", "lawenda", "irys", "hortensja");
            combo.setOnAction(event -> {
                String selectedItem = combo.getSelectionModel().getSelectedItem();
                try {
                    zmianaKwiatka(widok, selectedItem);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Region spacer = new Region();
            Region bottom = new Region();
            VBox vbox = new VBox();
            Button sepiaButton = new Button("Ususz/Uratuj");
            sepiaButton.setOnAction(event -> ususz(widok));

            //vbox.getChildren().addAll(combo, spacer, widok, sepiaButton);
            vbox.getChildren().addAll(combo, sepiaButton, spacer, widok, bottom);
            //vbox.getChildren().addAll(combo, widok, sepiaButton);
            VBox.setVgrow(spacer, Priority.ALWAYS);
            //vbox.setPadding(new Insets(8));
            hbox.getChildren().add(vbox);
            hbox.setPadding(new Insets(0,0,50,0));}
        else {
            zbytWiele();
        }
    }
    public void wyrzucKwiatka(HBox hbox){
        int lastIndex = hbox.getChildren().size() - 1;
        if (lastIndex >= 0) {
            hbox.getChildren().remove(lastIndex);
            wszystkie -= 1;
        }
    }
    public void wykresKolowy(){
        // Drugie okno
        Stage secondaryStage = new Stage();
        // Tworzenie wykresu
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("ususzone kwiatki", ususzone),
                        new PieChart.Data("nieususzone kwiatki",wszystkie-ususzone));
        final PieChart chart = new PieChart(pieChartData);

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.pieValueProperty().intValue()
                        )
                )
        );

        Scene scene2 = new Scene(new Group());
        scene2.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        ((Group) scene2.getRoot()).getChildren().add(chart);

        secondaryStage.setTitle("Ususzone Kwiatki");
        secondaryStage.setScene(scene2);
        secondaryStage.show();
    }

    public void wykresBar(HBox hbox){
        Map<String, Integer> valueCounts = new HashMap<>();
        for (Node node : hbox.getChildren()) {
            if (node instanceof VBox vbox) {
                ComboBox<String> comboBox = (ComboBox<String>) vbox.getChildren().get(0);
                String selectedValue = comboBox.getSelectionModel().getSelectedItem();
                if (selectedValue != null) {
                    valueCounts.put(selectedValue, valueCounts.getOrDefault(selectedValue, 0) + 1);
                }
            }
        }

        // Tworzenie osi dla wykresu
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(1); // ustawienie kroku osi na 1
        yAxis.setLowerBound(0); // ustawienie minimalnej wartości osi na 0
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);


        // Tworzenie danych dla wykresu
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (String value : valueCounts.keySet()) {
            int count = valueCounts.get(value);
            series.getData().add(new XYChart.Data<>(value, count));
        }

        chart.getData().add(series);
        // Tworzenie sceny i okna dla wykresu
        Scene scene = new Scene(chart, 600, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
    public void umyjParapet(HBox hbox){
        Reflection reflection = new Reflection();
        reflection.setTopOffset(0);
        reflection.setTopOpacity(0.75);
        reflection.setBottomOpacity(0.10);
        reflection.setFraction(0.7);
        for (Node node : hbox.getChildren()) {
            if (node instanceof VBox vbox) {
                ImageView view = (ImageView) vbox.getChildren().get(3);
                Effect currentEffect = view.getEffect();
                if (currentEffect!=null&&!(currentEffect instanceof Reflection)&&!(currentEffect instanceof Blend)) {

                    //ImageView image = (ImageView) vbox.getChildren().get(2);

                    Blend blend = new Blend();
                    blend.setMode(BlendMode.MULTIPLY);
                    blend.setBottomInput(currentEffect);
                    blend.setTopInput(reflection);
                    //ImageView image = (ImageView) vbox.getChildren().get(1);
                    view.setEffect(blend);
                }
                else if ((currentEffect instanceof Reflection)&&!(currentEffect instanceof Blend)){
                    view.setEffect(null);
                }
                else if (currentEffect == null){
                    view.setEffect(reflection);
                }
                else if ((currentEffect instanceof Blend)){
                    view.setEffect(new SepiaTone(0.8));
                }
            }
        }
    }


    public void zbytWiele(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("UWAGA!");
        alert.setContentText("Osiągnąłeś limit 10 kwiatków!");
        alert.setHeaderText("Za dużo kwiatków!");
        alert.show();
    }

    //guziki
    @FXML
    Button ususzoneButton;
    @FXML
    Button dodajButton;
    @FXML
    Button gatunkiButton;
    @FXML
    Button usunButton;
    @FXML
    Button umyjButton;

    //panele
    @FXML
    HBox hbox = new HBox();
    @FXML
    HBox parapet = new HBox();

    //metody
    public void handleWykresKolowy(ActionEvent actionEvent) {
        wykresKolowy();
    }
    public void handleWykresBar(ActionEvent actionEvent) {
        wykresBar(hbox);
    }
    public void handlenowyKwiatek(ActionEvent actionEvent) {
        nowyKwiatek(hbox);
    }
    public void handleumyjParapet(ActionEvent actionEvent) {
        umyjParapet(hbox);
    }
    public void handlewyrzucKwiatka(ActionEvent actionEvent) {
        wyrzucKwiatka(hbox);
    }
}