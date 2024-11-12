package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.Service;
import org.example.interfaces.Input;
import org.example.model.ShoesStuff;

import java.util.List;

public class ShoesStuffService implements Service<ShoesStuff> {
    private final List<ShoesStuff> shoesStuffs;
    private AppHelper<ShoesStuff> appHelperShoesStuff;
    private final Input inputProvider;

    public ShoesStuffService(List<ShoesStuff> shoesStuffs, AppHelper<ShoesStuff> appHelperPetStuff, Input inputProvider) {
        this.shoesStuffs = shoesStuffs;
        this.appHelperShoesStuff = appHelperShoesStuff;
        this.inputProvider = inputProvider;
    }

    @Override
    public boolean add() {
        ShoesStuff shoesStuff = appHelperShoesStuff.create();
        if (shoesStuff != null) {
            shoesStuffs.add(shoesStuff);
            appHelperShoesStuff.getRepository().save(shoesStuffs);
            System.out.println("Товар успешно добавлен.");
            return true;
        }
        System.out.println("Ошибка при добавлении товара.");
        return false;
    }

    @Override
    public void print() {
        appHelperShoesStuff.printList(shoesStuffs);
    }

    @Override
    public List<ShoesStuff> list() {
        return shoesStuffs;
    }

    @Override
    public boolean edit(ShoesStuff petStuff) {
        if (shoesStuffs.isEmpty()) {
            System.out.println("Список товаров пуст. Нечего редактировать.");
            return false;
        }

        print();
        System.out.print("Введите номер товара для редактирования: ");
        int indexToEdit = Integer.parseInt(inputProvider.getInput()) - 1;

        if (indexToEdit >= 0 && indexToEdit < shoesStuffs.size()) {
            ShoesStuff updatedShoesStuff = appHelperShoesStuff.create();
            shoesStuffs.set(indexToEdit, updatedShoesStuff);
            appHelperShoesStuff.getRepository().save(shoesStuffs);
            System.out.println("Товар успешно отредактирован.");
            return true;
        } else {
            System.out.println("Некорректный выбор товара.");
            return false;
        }
    }

    @Override
    public boolean remove(ShoesStuff shoesStuff) {
        if (shoesStuffs.isEmpty()) {
            System.out.println("Список товаров пуст. Нечего удалять.");
            return false;
        }

        print();
        System.out.print("Введите номер товара для удаления: ");
        int indexToRemove = Integer.parseInt(inputProvider.getInput()) - 1;

        if (indexToRemove >= 0 && indexToRemove < shoesStuffs.size()) {
            shoesStuffs.remove(indexToRemove);
            appHelperShoesStuff.getRepository().save(shoesStuffs);
            System.out.println("Товар успешно удален.");
            return true;
        } else {
            System.out.println("Некорректный выбор товара.");
            return false;
        }
    }
}
