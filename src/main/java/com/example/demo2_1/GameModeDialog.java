//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo2_1;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameModeDialog {
    protected static boolean playWithBot;

    public GameModeDialog() {
    }

    public static boolean show() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Выбор режима игры");
        stage.setMinWidth(251.0);
        Button playWithBotButton = new Button("Играть с ботом");
        Button playWithoutBotButton = new Button("Играть без бота");
        playWithBotButton.setOnAction((e) -> {
            playWithBot = true;
            stage.close();
        });
        playWithoutBotButton.setOnAction((e) -> {
            playWithBot = false;
            stage.close();
        });
        VBox layout = new VBox(10.0);
        layout.getChildren().addAll(new Node[]{playWithBotButton, playWithoutBotButton});
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
        return playWithBot;
    }
}
