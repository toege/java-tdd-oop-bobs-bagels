package com.booleanuk.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketTest {

    // Add functionality
    @Test
    public void addMultipleValidItems() {
        Basket basket = new Basket();
        boolean result = basket.addItem("Bagel Onion");
        Assertions.assertTrue(result);

        result = basket.addItem("Filling Bacon");
        Assertions.assertTrue(result);

        result = basket.addItem("Coffee Black");
        Assertions.assertTrue(result);

        result = basket.addItem("Bagel Onion");
        Assertions.assertTrue(result);

        Assertions.assertEquals("BGLO", basket.basket.get(0));
        Assertions.assertEquals("FILB", basket.basket.get(1));
        Assertions.assertEquals("COFB", basket.basket.get(2));
        Assertions.assertEquals("BGLO", basket.basket.get(3));

    }

    @Test
    public void addMultipleInValidItems() {
        Basket basket = new Basket();
        boolean result = basket.addItem("Choclate");
        Assertions.assertFalse(result);

        result = basket.addItem("Bagel Black");
        Assertions.assertFalse(result);

        result = basket.addItem("Filling Bagel");
        Assertions.assertFalse(result);

        Assertions.assertEquals(0, basket.basket.size());
    }

    // Remove
    @Test
    public void removeItemsValid() {
        Basket basket = new Basket();
        boolean result = basket.addItem("Bagel Onion");
        Assertions.assertTrue(result);

        Assertions.assertEquals(1, basket.basket.size());

        result = basket.removeItem("Bagel Onion");
        Assertions.assertTrue(result);

        Assertions.assertEquals(0, basket.basket.size());
    }

    // 3.
    @Test
    public void removeItemsNotInBasket() {
        Basket basket = new Basket();
        boolean result = basket.addItem("Bagel Onion");
        Assertions.assertTrue(result);

        Assertions.assertEquals(1, basket.basket.size());

        result = basket.removeItem("Bagel onion");
        Assertions.assertFalse(result);

        Assertions.assertEquals(1, basket.basket.size());
    }

    @Test
    public void removeOnlyOneValidItem() {
        Basket basket = new Basket();
        boolean result = basket.addItem("Bagel Onion");
        result = basket.addItem("Filling Bacon");
        result = basket.addItem("Bagel Onion");
        result = basket.addItem("Coffee Black");

        Assertions.assertEquals(4, basket.basket.size());

        result = basket.removeItem("Bagel Onion");
        Assertions.assertTrue(result);

        Assertions.assertEquals(3, basket.basket.size());
    }

    // Capacity
    @Test
    public void updateBasketCapacityTo5() {
        Basket basket = new Basket();
        int newCapacity = 5;
        int result = basket.changeCapacity(newCapacity);
        Assertions.assertEquals(5, result);
    }

    @Test
    public void updateBasketCapacityTo10() {
        Basket basket = new Basket();
        int newCapacity = 10;
        int result = basket.changeCapacity(newCapacity);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void addBeyondCapacity() {
        Basket basket = new Basket();
        int newCapacity = 2;
        int intResult = basket.changeCapacity(newCapacity);
        boolean result = basket.addItem("Bagel Onion");
        result = basket.addItem("Filling Bacon");
        Assertions.assertTrue(result);
        result = basket.addItem("Bagel Onion");
        Assertions.assertFalse(result);

        Assertions.assertEquals(2, basket.basket.size());
    }


    // Overall Test after first five user stories
    @Test
    public void overallTest() {
        Basket basket = new Basket();
        int intResult = basket.changeCapacity(3);
        // Add 4 items to a max 3 items basket. Fours add should return false, the rest true
        Assertions.assertTrue(basket.addItem("Bagel Plain"));
        Assertions.assertTrue(basket.addItem("Bagel Sesame"));
        Assertions.assertTrue(basket.addItem("Coffee Capuccino"));
        Assertions.assertFalse(basket.addItem("Filling Cheese"));

        // Remove banana to add cheese and salami. Salami should return fail, the rest true
        Assertions.assertTrue(basket.removeItem("Bagel Sesame"));
        Assertions.assertTrue(basket.addItem("Bagel Everything"));
        Assertions.assertFalse(basket.addItem("Bagel Onion"));

        // Change capacity to 5 and add the extra bagels
        Assertions.assertEquals(5, basket.changeCapacity(5));
        Assertions.assertTrue(basket.addItem("Bagel Everything"));
        Assertions.assertTrue(basket.addItem("Bagel Everything"));

        // Remove ham bagel, that has now been added
        Assertions.assertTrue(basket.removeItem("Bagel Everything"));
    }
}
