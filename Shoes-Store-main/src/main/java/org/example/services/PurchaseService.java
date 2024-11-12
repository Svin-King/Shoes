package org.example.services;

import org.example.interfaces.AppHelper;
import org.example.interfaces.Service;
import org.example.interfaces.Input;
import org.example.model.Customer;
import org.example.model.ShoesStuff;
import org.example.model.Purchase;

import java.util.List;

public class PurchaseService implements Service<Purchase> {
    private final List<Purchase> purchases;
    private final AppHelper<Purchase> appHelperPurchase;
    private final AppHelper<Customer> appHelperCustomer;
    private AppHelper<ShoesStuff> appHelperShoesStuff = null;
    private final Input inputProvider;

    public PurchaseService(List<Purchase> purchases, AppHelper<Purchase> appHelperPurchase,
                           AppHelper<Customer> appHelperCustomer, AppHelper<ShoesStuff> appHelperShoesStuff,
                           Input inputProvider) {
        this.purchases = purchases;
        this.appHelperPurchase = appHelperPurchase;
        this.appHelperCustomer = appHelperCustomer;
        this.appHelperShoesStuff = this.appHelperShoesStuff;
        this.inputProvider = inputProvider;
    }

    @Override
    public boolean add() {
        Customer customer = selectCustomer();
        ShoesStuff shoesStuff = selectShoesStuff();

        if (customer != null && shoesStuff != null) {
            boolean useDiscount = false;

            if (shoesStuff.isHasDiscount()) {
                System.out.print("Товар имеет скидку. Купить по скидке? (да/нет): ");
                String response = inputProvider.getInput().trim().toLowerCase();
                useDiscount = response.equals("да");
            }

            Purchase purchase = new Purchase(customer, shoesStuff, useDiscount);
            purchases.add(purchase);
            appHelperPurchase.getRepository().save(purchases);
            System.out.println("Покупка успешно добавлена.");
            return true;
        }

        System.out.println("Ошибка при добавлении покупки.");
        return false;
    }

    private ShoesStuff selectShoesStuff() {
        return null;
    }

    @Override
    public void print() {
        if (purchases.isEmpty()) {
            System.out.println("Список покупок пуст.");
        } else {
            System.out.println("------ Список покупок ------");
            for (int i = 0; i < purchases.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, purchases.get(i));
            }
        }
    }

    @Override
    public List<Purchase> list() {
        return purchases;
    }

    @Override
    public boolean edit(Purchase purchase) {
        System.out.println("Редактирование покупки недоступно.");
        return false;
    }

    @Override
    public boolean remove(Purchase purchase) {
        print();
        System.out.print("Введите номер покупки для удаления: ");
        int indexToRemove = Integer.parseInt(inputProvider.getInput()) - 1;

        if (indexToRemove >= 0 && indexToRemove < purchases.size()) {
            purchases.remove(indexToRemove);
            appHelperPurchase.getRepository().save(purchases);
            System.out.println("Покупка успешно удалена.");
            return true;
        } else {
            System.out.println("Покупка не найдена.");
            return false;
        }
    }

    private Customer selectCustomer() {
        appHelperCustomer.printList(appHelperCustomer.getRepository().load());
        System.out.print("Введите номер покупателя для покупки: ");
        int customerIndex = Integer.parseInt(inputProvider.getInput()) - 1;

        if (customerIndex >= 0 && customerIndex < appHelperCustomer.getRepository().load().size()) {
            return appHelperCustomer.getRepository().load().get(customerIndex);
        }
        System.out.println("Некорректный выбор покупателя.");
        return null;
    }

}
