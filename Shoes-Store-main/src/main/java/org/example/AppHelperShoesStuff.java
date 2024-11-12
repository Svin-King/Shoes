package org.example;

import org.example.interfaces.AppHelper;
import org.example.interfaces.Input;
import org.example.interfaces.FileRepository;
import org.example.model.ShoesStuff;

import java.util.List;

public class AppHelperShoesStuff implements AppHelper<ShoesStuff> {
    private final FileRepository<ShoesStuff> shoesStuffRepository;
    private final Input inputProvider;

    public AppHelperShoesStuff(FileRepository<ShoesStuff> shoesStuffRepository, Input inputProvider) {
        this.shoesStuffRepository = shoesStuffRepository;
        this.inputProvider = inputProvider;
    }

    @Override
    public ShoesStuff create() {
        ShoesStuff shoesStuff = new ShoesStuff();
        System.out.print("Введите название товара: ");
        ShoesStuff ShoesStuff = null;
        shoesStuff.setName(inputProvider.getInput());
        System.out.print("Введите описание товара: ");
        shoesStuff.setDescription(inputProvider.getInput());

        double price;
        while (true) {
            try {
                System.out.print("Введите цену товара: ");
                price = Double.parseDouble(inputProvider.getInput());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное числовое значение для цены.");
            }
        }
        shoesStuff.setPrice(price);

        System.out.print("Есть ли скидка на товар? (да/нет): ");
        String discountInput = inputProvider.getInput();
        if (discountInput.equalsIgnoreCase("да")) {
            shoesStuff.setHasDiscount(true);
            double discountedPrice;
            while (true) {
                try {
                    System.out.print("Введите цену со скидкой: ");
                    discountedPrice = Double.parseDouble(inputProvider.getInput());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Пожалуйста, введите корректное числовое значение для цены со скидкой.");
                }
            }
            shoesStuff.setDiscountedPrice(discountedPrice);
        } else {
            shoesStuff.setHasDiscount(false);
        }

        return shoesStuff;
    }

    @Override
    public void printList(List<ShoesStuff> shoesStuffs) {
        if (shoesStuffs.isEmpty()) {
            System.out.println("Список товаров пуст.");
            return;
        }
        System.out.println("------ Список товаров ------");
        for (int i = 0; i < shoesStuffs.size(); i++) {
            ShoesStuff shoesStuff = shoesStuffs.get(i);
            System.out.printf("%d. Название: %s, Описание: %s, Цена: %.2f%s%n",
                    i + 1,
                    shoesStuff.getName(),
                    shoesStuff.getDescription(),
                    shoesStuff.getPrice(),
                    shoesStuff.isHasDiscount() ? ", Цена со скидкой: " + shoesStuff.getDiscountedPrice() : ""
            );
        }
    }

    @Override
    public FileRepository<ShoesStuff> getRepository() {
        return shoesStuffRepository;
    }
}
