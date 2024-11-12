package org.example.model;

import java.util.UUID;
import java.time.LocalDate;

public class Purchase {
    private UUID id;
    private Customer customer;
    private ShoesStuff shoesStuff;
    private LocalDate purchaseDate;
    private boolean useDiscount;

    public Purchase(Customer customer, ShoesStuff shoesStuff, boolean useDiscount) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.shoesStuff = shoesStuff;
        this.purchaseDate = LocalDate.now();
        this.useDiscount = useDiscount;
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShoesStuff getShoesStuff() {
        return shoesStuff;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isUseDiscount() {
        return useDiscount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Покупатель: ").append(customer);
        sb.append(", Товар: ").append(shoesStuff);

        if (useDiscount) {
            sb.append(" (приобретен по скидке за: ").append(shoesStuff.getDiscountedPrice()).append(")");
        } else {
            sb.append(" (приобретен по полной цене: ").append(shoesStuff.getPrice()).append(")");
        }

        sb.append(", Дата покупки: ").append(purchaseDate);
        return sb.toString();
    }
}
