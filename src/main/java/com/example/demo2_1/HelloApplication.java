package com.example.demo2_1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.Random;

import static com.example.demo2_1.GameModeDialog.playWithBot;


public class HelloApplication extends Application {
    protected int count;
    private int Xpos;
    private int Ypos;
    private final Players player1 = new Players(1, "C:\\Users\\ABOBUS2006\\IdeaProjects\\demo2.1\\idef\\krestic.png");
    private final Players player2 = new Players(2, "C:\\Users\\ABOBUS2006\\IdeaProjects\\demo2.1\\idef\\nolic.png");
    private final int[][] pole = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    public Pane root = new Pane();
    private GameLogic gameLogic;


    int currentPlayer = (new Random()).nextBoolean() ? player1.getId() : player2.getId();
    public HelloApplication() {
    }

    public void start(Stage primaryStage) {
        gameLogic = new GameLogic(pole);
        Group arena = getGroup();
        boolean playWithBot = GameModeDialog.show();
        // Эту херню не трогай, а то все поламаешь. Если двигать ее ниже выбора, то будет гг
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(arena, root);
        Scene scene = new Scene(stackPane, 300.0, 300.0);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Крестики-нолики");
        primaryStage.show();
        //Здесь идет проверка на то как играть с ботом или без
        if(playWithBot){
                BotStap(primaryStage);
                Click(primaryStage);
        }
        else {
            Click(primaryStage);
        }


    }
    private void Click(Stage primaryStage) {
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent) -> {
            ++count;
            Xpos = (int) Math.floor(MouseEvent.getX() / 100.0);
            Ypos = (int) Math.floor(MouseEvent.getY() / 100.0);
            stap(primaryStage);
            if(playWithBot)
                BotStap(primaryStage);
        });
    }

    private void Chenge(Stage primaryStage){
        for (int i = 0; i< pole.length; i++)
            System.out.println(pole[i][i]);
        if (gameLogic.checkWin(currentPlayer))
            showGameResult(primaryStage, "Победитель: игрок " + currentPlayer);
        if (gameLogic.allCellsFilled())
            showGameResult(primaryStage, "Ничья!");

        if(currentPlayer == player1.getId())
            currentPlayer = player2.getId();
        else
            currentPlayer = player1.getId();
    }
    public void BotStap(Stage primaryStage){
            int proverka = count;
            System.out.println(count);
            ++count;
            Xpos = (int) (Math.random()*3);
            Ypos = (int) (Math.random()*3);
            stap(primaryStage);
            System.out.println(count);
            if(proverka == count)
                BotStap(primaryStage);
    }

    private void stap(Stage primaryStage) {
        if (pole[Xpos][Ypos] == 0) {
            ImageView imageView = new ImageView(currentPlayer == player1.getId() ? player1.getImage() : player2.getImage());
            pole[Xpos][Ypos] = currentPlayer;
            imageView.setX((25 + 100 * Xpos));
            imageView.setY((25 + 100 * Ypos));
            imageView.setFitWidth(50.0);
            imageView.setFitHeight(50.0);
            root.getChildren().add(imageView);
            Chenge(primaryStage);
        } else {
            count--;
        }
    }


    private void showGameResult(Stage primaryStage, String resultText) {
        Stage winner = new Stage();
        Label label = new Label();
        Scene win = new Scene(label, 200.0, 200.0);
        winner.setScene(win);
        winner.setResizable(false);
        label.setText(resultText);
        label.setTextFill(Color.RED);
        primaryStage.close();
        winner.show();
    }

    private Group getGroup() {
        Line line = new Line();
        line.setStartX(100.0);
        line.setStartY(300.0);
        line.setEndX(100.0);
        line.setEndY(0.0);
        Line line1 = new Line();
        line1.setStartX(200.0);
        line1.setStartY(300.0);
        line1.setEndX(200.0);
        line1.setEndY(0.0);
        Line line2 = new Line();
        line2.setStartX(0.0);
        line2.setStartY(200.0);
        line2.setEndX(300.0);
        line2.setEndY(200.0);
        Line line3 = new Line();
        line3.setStartX(0.0);
        line3.setStartY(100.0);
        line3.setEndX(300.0);
        line3.setEndY(100.0);
        return new Group(line, line1, line2, line3);
    }
    public static void main(String[] args) {launch();}
}