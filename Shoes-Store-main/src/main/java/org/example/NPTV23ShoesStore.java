package org.example;

import org.example.interfaces.AppHelper;
import org.example.interfaces.Input;
import org.example.interfaces.repository.InMemoryRepository;
import org.example.services.CustomerService;
import org.example.services.ShoesStuffService;
import org.example.services.PurchaseService;
import org.example.model.Customer;
import org.example.model.ShoesStuff;
import org.example.model.Purchase;

import java.util.ArrayList;
import java.util.List;

public class NPTV23ShoesStore {
    public static void main(String[] args) {
        Input inputProvider = new AppHelper.ConsoleInput();

        InMemoryRepository<Customer> customerRepository = new InMemoryRepository<>();
        InMemoryRepository<ShoesStuff> shoesStuffRepository = new InMemoryRepository<>();
        InMemoryRepository<Purchase> purchaseRepository = new InMemoryRepository<>();

        AppHelper<Customer> appHelperCustomer = new AppHelperCustomer(customerRepository, inputProvider);
        AppHelper<ShoesStuff> appHelperShoesStuff = new AppHelperShoesStuff(shoesStuffRepository, inputProvider);
        AppHelper<Purchase> appHelperPurchase = new AppHelperPurchase(purchaseRepository, inputProvider, appHelperCustomer, appHelperShoesStuff);

        List<Customer> customers = new ArrayList<>(customerRepository.load());
        List<ShoesStuff> shoesStuffs = new ArrayList<>(shoesStuffRepository.load());
        List<Purchase> purchases = new ArrayList<>(purchaseRepository.load());

        CustomerService customerService = new CustomerService(customers, appHelperCustomer, inputProvider);
        ShoesStuffService shoesStuffService = new ShoesStuffService(shoesStuffs, appHelperShoesStuff, inputProvider);
        PurchaseService purchaseService = new PurchaseService(purchases, appHelperPurchase, appHelperCustomer, appHelperShoesStuff, inputProvider);

        App app = new App(customerService, shoesStuffService, purchaseService, inputProvider);
        app.run();
    }
}
