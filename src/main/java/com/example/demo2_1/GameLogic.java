package com.example.demo2_1;


public class GameLogic extends HelloApplication {
    private final int[][] pole;
    public boolean allCellsFilled;

    public GameLogic(int[][] pole) {
        this.pole = pole;
    }
    public  boolean allCellsFilled() {
        allCellsFilled = true; // Изначально считаем, что все ячейки заполнены

        // Проверка всех ячеек
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pole[i][j] == 0) {
                    allCellsFilled = false; // Если хоть одна ячейка не заполнена, изменяем значение на false
                    return false; // Нет смысла продолжать проверку, если найдена хоть одна незаполненная ячейка
                }
            }
        }
        return !checkWin(currentPlayer);// Если все ячейки заполнены, возвращаем true
    }

    public boolean checkWin(int player) {
        // Проверка строк
        for (int i = 0; i < 3; i++) {
            if (pole[i][0] == player && pole[i][1] == player && pole[i][2] == player) {
                return true;
            }
        }
        // Проверка столбцов
        for (int j = 0; j < 3; j++) {
            if (pole[0][j] == player && pole[1][j] == player && pole[2][j] == player) {
                return true;
            }
        }
        // Проверка диагоналей
        return (pole[0][0] == player && pole[1][1] == player && pole[2][2] == player) ||
                (pole[0][2] == player && pole[1][1] == player && pole[2][0] == player); // Ни одно из условий победы не выполнено
    }
}