package com.example.demo2_1;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    Stage StartMenu = new Stage();
    ImageView FalsePass = new ImageView("C:\\Users\\ABOBUS2006\\IdeaProjects\\demo2.1\\idef\\hazard-sign.png");
    PasswordField  Password = new PasswordField ();
    PasswordField  ReturnPassword = new PasswordField ();


    int currentPlayer = (new Random()).nextBoolean() ? player1.getId() : player2.getId();


    public HelloApplication() {
    }

    public void start(Stage StartMenu) {//здесь теперь вызов только мейн меню
        gameLogic = new GameLogic(pole);
        StarMenu();




    }
    public void GameStart(){//игра теперь тут, не теряй
        Stage primaryStage = new Stage();
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

    private void StarMenu(){
        Group startmain = getStartMenu();
        Group stackPane = new Group();
        //Гиперссылка на создание нового аккаунта
        Hyperlink NewAccount = new Hyperlink("Не имеете аккаут?");
        NewAccount.setLayoutY(380);
        NewAccount.setLayoutX(0);
        stackPane.getChildren().addAll(startmain, NewAccount);
        Scene StartMen = new Scene(stackPane, 400, 400);
        StartMenu.setScene(StartMen);
        StartMenu.setResizable(false);
        StartMenu.setTitle("Tic Tac Toe вход");//Здесь заканчивается тем, что следующая строка вызывает окно
        StartMenu.show();
        NewAccount.setOnAction(event -> {//переход на создание акка
            stackPane.getChildren().clear();
            Register();
        });//Нажатие на надпись, что нет акка
        //        Enter.setOnAction(actionEvent -> GameStart());//Нажатие на кнопку войти
    }

    private void Register(){//очередная инициализация, только уже регистрации, если чел нажал, что акка нет
        Group RegisterStak = getRegister();
        Group StartReg = new Group();
        Password.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)->Cheker(Password, ReturnPassword, FalsePass));

        // Добавляем слушатель изменения текста для поля ввода повторного пароля
        ReturnPassword.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> Cheker(Password, ReturnPassword, FalsePass));
        //Кнопка информации
        ImageView InfButton = new ImageView("C:\\Users\\ABOBUS2006\\IdeaProjects\\demo2.1\\idef\\info.png");
        Button WhatWord = new Button("", InfButton);
        InfButton.setFitWidth(WhatWord.getWidth());
        InfButton.setFitHeight(WhatWord.getHeight());
        InfButton.setPreserveRatio(false);
        WhatWord.setStyle("-fx-padding: -15;");
        WhatWord.setPrefWidth(30); // Установка предпочтительной ширины кнопки
        WhatWord.setPrefHeight(30); // Установка предпочтительной высоты кнопки
        WhatWord.setLayoutY(10);
        WhatWord.setLayoutX(10);
        //Кнопка для подтверждения регистрации
        ImageView RegIm = new ImageView("C:\\Users\\ABOBUS2006\\IdeaProjects\\demo2.1\\idef\\entry-door.png");
        Button Reg = new Button("", RegIm);
        RegIm.setFitWidth(Reg.getWidth());
        RegIm.setFitHeight(Reg.getHeight());
        RegIm.setPreserveRatio(false);
        Reg.setStyle("-fx-padding: -15;");
        Reg.setPrefWidth(15); // Установка предпочтительной ширины кнопки
        Reg.setPrefHeight(20); // Установка предпочтительной высоты кнопки
        Reg.setLayoutY(260);
        Reg.setLayoutX(180);
        //продолжение основного блока
        StartReg.getChildren().addAll(RegisterStak, WhatWord, Reg, FalsePass);
        Scene Register = new Scene(StartReg, 400, 400);
        StartMenu.setScene(Register);
        StartMenu.setResizable(false);
        StartMenu.setTitle("Окно регистрации");//следующая строка уже открытие окна
        StartMenu.show();
        WhatWord.setOnMouseEntered((MouseEvent event) -> WhatWord.setTooltip(new Tooltip("Секретное слово нужно для изменения пароля в случае непредвиденных ситуаций!")));
        Reg.setOnAction(actionEvent -> {
            Password.clear();
            ReturnPassword.clear();
            StartReg.getChildren().clear();
            StarMenu();
        });


    }

    private void Cheker(PasswordField password, PasswordField returnPassword, ImageView falsePass){
        String password1 = Password.getText();
        String confirmPassword = ReturnPassword.getText();

        if (!password1.equals(confirmPassword) || password1.isEmpty() || confirmPassword.isEmpty()) {
            FalsePass.setVisible(true);
            Tooltip tooltip = new Tooltip("Пароли не совпадают или одно из полей пустое!");
            Tooltip.install(FalsePass, tooltip);
        } else {
            FalsePass.setVisible(false);
        }
    }

    private Group getStartMenu(){
        ImageView EnterButton = new ImageView("C:\\Users\\ABOBUS2006\\IdeaProjects\\demo2.1\\idef\\entry-door.png");
        Button Enter = new Button("",EnterButton);//Отсюда начинается херня для регистрации и входа
        TextField Nick = new TextField();
        PasswordField  Password = new PasswordField ();
        Hyperlink ForgotPassword = new Hyperlink("Забыли пароль?");
        Label Error = new Label("*Введен неверный логин или пароль");
        //Кнопка входа
        EnterButton.setFitWidth(Enter.getWidth());
        EnterButton.setFitHeight(Enter.getHeight());
        EnterButton.setPreserveRatio(false);
        Enter.setStyle("-fx-padding: -15;");
        Enter.setLayoutY(260);
        Enter.setLayoutX(180);
        Enter.setPrefWidth(15); // Установка предпочтительной ширины кнопки
        Enter.setPrefHeight(20); // Установка предпочтительной высоты кнопки
        //Поле ввода имени
        Nick.setPromptText("Введите имя");
        Nick.setLayoutY(70);
        Nick.setLayoutX(120);
        //Поле ввода пароля
        Password.setPromptText("Введите пароль");
        Password.setLayoutY(100);
        Password.setLayoutX(120);
        //Гиперссылка на забытый пароль
        ForgotPassword.setLayoutY(120);
        ForgotPassword.setLayoutX(175);
        //Ошибка ввода
        Error.setTextFill(Color.RED);
        Error.setVisible(false);
        Error.setLayoutY(220);
        Error.setLayoutX(95);
        return new Group(Enter,Nick,Password,ForgotPassword, Error);
    }

    private Group getRegister(){
        TextField Nick = new TextField();
        TextField SecretWord = new TextField();
        //Поле ввода имени
        Nick.setPromptText("Введите имя");
        Nick.setLayoutY(70);
        Nick.setLayoutX(120);
        //Поле ввода пароля
        Password.setPromptText("Введите пароль");
        Password.setLayoutY(100);
        Password.setLayoutX(120);
        //Поле ввода пароля
        ReturnPassword.setPromptText("Повторите ввод пароля");
        ReturnPassword.setLayoutY(130);
        ReturnPassword.setLayoutX(120);
        //Поле Секретного слова
        SecretWord.setPromptText("Слово для защиты");
        SecretWord.setLayoutY(160);
        SecretWord.setLayoutX(120);
        //Иконка несовпадения
        FalsePass.setVisible(false);
        FalsePass.setLayoutY(103);
        FalsePass.setLayoutX(270);
        return new Group(Nick, Password, SecretWord, ReturnPassword, FalsePass);

    }

    public static void main(String[] args) {launch();}
}