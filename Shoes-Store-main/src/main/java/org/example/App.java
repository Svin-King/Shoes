package org.example;

import org.example.interfaces.Input;
import org.example.services.CustomerService;
import org.example.services.ShoesStuffService;
import org.example.services.PurchaseService;

public class App {
    private final CustomerService customerService;
    private final ShoesStuffService shoesStuffService;
    private final PurchaseService purchaseService;
    private final Input inputProvider;

    public App(CustomerService customerService, ShoesStuffService shoesStuffService, PurchaseService purchaseService, Input inputProvider) {
        this.customerService = customerService;
        this.shoesStuffService = shoesStuffService;
        this.purchaseService = purchaseService;
        this.inputProvider = inputProvider;
    }

    public void run() {
        System.out.println("------ Магазин товаров для домашних животных ------");
        boolean repeat = true;
        do {
            System.out.println("Меню:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить товар");
            System.out.println("2. Список товаров");
            System.out.println("3. Редактировать товар");
            System.out.println("4. Удалить товар");
            System.out.println("5. Добавить покупателя");
            System.out.println("6. Список покупателей");
            System.out.println("7. Редактировать покупателя");
            System.out.println("8. Удалить покупателя");
            System.out.println("9. Купить товар");
            System.out.println("10. История покупок");
            System.out.print("Введите номер задачи: ");

            int task = Integer.parseInt(inputProvider.getInput());

            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    shoesStuffService.add();
                    break;
                case 2:
                    shoesStuffService.print();
                    break;
                case 3:
                    shoesStuffService.edit(null);  // Вызов метода edit, который сам покажет список
                    break;
                case 4:
                    shoesStuffService.remove(null);
                    break;
                case 5:
                    customerService.add();
                    break;
                case 6:
                    customerService.print();
                    break;
                case 7:
                    customerService.edit(null);
                    break;
                case 8:
                    customerService.remove(null);
                    break;
                case 9:
                    purchaseService.add();
                    break;
                case 10:
                    purchaseService.print();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        } while (repeat);

        System.out.println("До свидания!");
    }
}
