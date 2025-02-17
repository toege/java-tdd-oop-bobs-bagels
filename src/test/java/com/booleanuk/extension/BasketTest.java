package com.booleanuk.extension;

import com.booleanuk.core.Basket;
import com.booleanuk.core.Discount;
import com.booleanuk.core.Inventory;
import com.booleanuk.core.Receipt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BasketTest {

    // Add functionality
    @Test
    public void addMultipleValidItems() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        boolean result = basket.addItem("Bagel Onion", 1);
        Assertions.assertTrue(result);

        result = basket.addItem("Filling Bacon", 1);
        Assertions.assertTrue(result);

        result = basket.addItem("Coffee Black", 1);
        Assertions.assertTrue(result);

        result = basket.addItem("Bagel Onion", 3);
        Assertions.assertTrue(result);

        Assertions.assertEquals("Bagel Onion", basket.getBasketItem("BGLO").getNametype());
        Assertions.assertEquals("Filling Bacon", basket.getBasketItem("FILB").getNametype());
        Assertions.assertEquals("Coffee Black", basket.getBasketItem("COFB").getNametype());
        Assertions.assertEquals(4, basket.getBasketItem("BGLO").getQuantity());

    }

    @Test
    public void addMultipleInValidItems() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        boolean result = basket.addItem("Choclate", 1);
        Assertions.assertFalse(result);

        result = basket.addItem("Bagel Black", 1);
        Assertions.assertFalse(result);

        result = basket.addItem("Filling Bagel", 1);
        Assertions.assertFalse(result);

        Assertions.assertEquals(0, basket.getBasketSize());
    }

    // Remove
    @Test
    public void removeItemsValid() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        boolean result = basket.addItem("Bagel Onion", 1);
        Assertions.assertTrue(result);

        Assertions.assertEquals(1, basket.getBasketSize());

        result = basket.removeItem("Bagel Onion", 1);
        Assertions.assertTrue(result);

        Assertions.assertEquals(0, basket.getBasketSize());
    }

    // 3.
    @Test
    public void removeItemsNotInBasket() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        boolean result = basket.addItem("Bagel Onion", 1);
        Assertions.assertTrue(result);

        Assertions.assertEquals(1, basket.getBasketSize());

        result = basket.removeItem("Bagel onion", 1);
        Assertions.assertFalse(result);

        Assertions.assertEquals(1, basket.getBasketSize());
    }

    @Test
    public void removeOnlyOneValidItem() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        boolean result = basket.addItem("Bagel Onion", 1);
        result = basket.addItem("Filling Bacon", 1);
        result = basket.addItem("Bagel Onion", 1);
        result = basket.addItem("Coffee Black", 1);

        Assertions.assertEquals(4, basket.getBasketSize());

        result = basket.removeItem("Bagel Onion", 1);
        Assertions.assertTrue(result);

        Assertions.assertEquals(3, basket.getBasketSize());
    }

    // Capacity
    @Test
    public void updateBasketCapacityTo5() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        int newCapacity = 5;
        int result = basket.changeCapacity(newCapacity);
        Assertions.assertEquals(5, result);
    }

    @Test
    public void updateBasketCapacityTo10() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        int newCapacity = 10;
        int result = basket.changeCapacity(newCapacity);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void addBeyondCapacity() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        int newCapacity = 2;
        int intResult = basket.changeCapacity(newCapacity);
        boolean result = basket.addItem("Bagel Onion", 1);
        result = basket.addItem("Filling Bacon", 1);
        Assertions.assertTrue(result);
        result = basket.addItem("Bagel Onion", 1);
        Assertions.assertFalse(result);

        Assertions.assertEquals(2, basket.getBasketSize());
    }


    // Overall Test after first five user stories
    @Test
    public void overallTest() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        int intResult = basket.changeCapacity(3);
        // Add 4 items to a max 3 items basket. Fours add should return false, the rest true
        Assertions.assertTrue(basket.addItem("Bagel Plain", 1));
        Assertions.assertTrue(basket.addItem("Bagel Sesame", 1));
        Assertions.assertTrue(basket.addItem("Coffee Capuccino", 1));
        Assertions.assertFalse(basket.addItem("Filling Cheese", 1));

        // Remove banana to add cheese and salami. Salami should return fail, the rest true
        Assertions.assertTrue(basket.removeItem("Bagel Sesame", 1));
        Assertions.assertTrue(basket.addItem("Bagel Everything", 1));
        Assertions.assertFalse(basket.addItem("Bagel Onion", 1));

        // Change capacity to 5 and add the extra bagels
        Assertions.assertEquals(5, basket.changeCapacity(5));
        Assertions.assertTrue(basket.addItem("Bagel Everything", 1));
        Assertions.assertTrue(basket.addItem("Bagel Everything", 1));

        // Remove ham bagel, that has now been added
        Assertions.assertTrue(basket.removeItem("Bagel Everything", 1));
    }

    // Check total cost in basket

    @Test
    public void testTotalCost() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Discount discount = new com.booleanuk.core.Discount();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory, discount);
        Assertions.assertEquals(0.0, basket.getTotal());

        Assertions.assertTrue(basket.addItem("Bagel Plain", 1));
        Assertions.assertTrue(basket.addItem("Bagel Sesame", 1));
        Assertions.assertTrue(basket.addItem("Coffee Capuccino", 1));
        Assertions.assertTrue(basket.addItem("Filling Cheese", 1));

        Assertions.assertEquals(1.76, basket.getTotal());
    }

    // Get price of one item
    @Test
    public void testGetPrize() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);

        Assertions.assertEquals(0.39, basket.getPrice("Bagel Plain"));
        Assertions.assertEquals(0.49, basket.getPrice("Bagel Sesame"));
        Assertions.assertEquals(0.49, basket.getPrice("Bagel Everything"));
        Assertions.assertEquals(0.49, basket.getPrice("Bagel Onion"));
        Assertions.assertEquals(0.12, basket.getPrice("Filling Cheese"));
        Assertions.assertEquals(1.29, basket.getPrice("Coffee Capuccino"));
    }

    // Build bagel
    @Test
    public void testBuildBagelAllFillings() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory);
        String[] fillings = {"Bacon", "Egg", "Cheese", "Cream Cheese", "Smoked Salmon", "Ham"};
        boolean result = basket.buildBagel(2, fillings);
        Assertions.assertTrue(result);
        String ans = basket.getBasketItem("BGLB1").getNametype();
        Assertions.assertEquals("Bagel Bacon, Egg, Cheese, Cream Cheese, Smoked Salmon, Ham", ans);
        Assertions.assertEquals(2, basket.getBasketSize());

        String[] fillings1 = {"Egg", "Cheese", "Cream Cheese", "Ham"};
        result = basket.buildBagel(1, fillings1);
        Assertions.assertTrue(result);
        Assertions.assertEquals("Bagel Egg, Cheese, Cream Cheese, Ham", basket.getBasketItem("BGLB2").getNametype());
        Assertions.assertEquals(3, basket.getBasketSize());

        String[] fillings2 = {"Tomato", "Eggs"};
        result = basket.buildBagel(3, fillings2);
        Assertions.assertFalse(result);
        Assertions.assertEquals(3, basket.getBasketSize());
    }

    // Test for not allowing customers is incorporated with the adding testing.

    // Checking the price for bagel and/or filling, is all in one method and tested together further up.

    // Extensions
    // Discounts

    @Test
    public void testDiscountSixBagels() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Discount discount = new com.booleanuk.core.Discount();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory, discount);
        boolean result = basket.addItem("Bagel Onion", 6);
        Assertions.assertTrue(result);

        Assertions.assertEquals(2.49, basket.getTotal());
    }

    @Test
    public void testDiscountTwelveBagels() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Discount discount = new com.booleanuk.core.Discount();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory, discount);
        boolean result = basket.addItem("Bagel Everything", 12);
        Assertions.assertTrue(result);

        Assertions.assertEquals(3.99, basket.getTotal());
    }

    @Test
    public void testDiscountSixteenBagels() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Discount discount = new com.booleanuk.core.Discount();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory, discount);
        boolean result = basket.addItem("Bagel Plain", 16);
        Assertions.assertTrue(result);

        Assertions.assertEquals(5.55, basket.getTotal());
    }

    @Test
    public void testDiscountBagelAndCoffee() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Discount discount = new com.booleanuk.core.Discount();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory, discount);
        boolean result = basket.addItem("Coffee Black", 1);
        result = basket.addItem("Bagel Plain", 1);
        Assertions.assertTrue(result);

        Assertions.assertEquals(1.25, basket.getTotal());
    }

    @Test
    public void testDiscountExtensionBigOrder() {
        com.booleanuk.core.Inventory inventory = new com.booleanuk.core.Inventory();
        com.booleanuk.core.Discount discount = new Discount();
        com.booleanuk.core.Basket basket = new com.booleanuk.core.Basket(inventory, discount);
        boolean result = basket.addItem("Bagel Onion", 2);
        result = basket.addItem("Bagel Plain", 12);
        result = basket.addItem("Bagel Everything", 6);
        result = basket.addItem("Coffee Black", 3);
        Assertions.assertTrue(result);

        Assertions.assertEquals(9.97, basket.getTotal());
    }

    @Test
    public void restReceipt() {
        com.booleanuk.core.Inventory inventory = new Inventory();
        com.booleanuk.core.Basket basket = new Basket(inventory);
        boolean result = basket.addItem("Bagel Onion", 2);
        result = basket.addItem("Bagel Plain", 12);
        result = basket.addItem("Bagel Everything", 6);
        result = basket.addItem("Coffee Black", 3);
        Assertions.assertTrue(result);


        com.booleanuk.core.Receipt receipt = new Receipt(basket.getBasket());
        receipt.createReceipt();
        ArrayList<String> receiptList = receipt.getReceipt();
        Assertions.assertEquals("    ~~~ Bob's Bagels ~~~    ", receiptList.get(1));
        Assertions.assertEquals("Plain Bagel        12  $4.68", receiptList.get(6));
        Assertions.assertEquals("Onion Bagel        2   $0.98", receiptList.get(7));
        Assertions.assertEquals("Total                 $11.57", receiptList.get(11));
        Assertions.assertEquals("      for your order!       ", receiptList.get(14));
    }
}
