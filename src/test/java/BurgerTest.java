import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private final float DELTA = 0.001f;
    Burger burger;
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getBurgerBun() {
        burger.setBuns(bun);
    }

    @Test
    public void addIngredient() {

        burger.addIngredient(ingredient);
        assertNotNull(burger.ingredients);
        int size = burger.ingredients.size();
        System.out.println("Количество ингредиентов " + size);
    }

    @Test
    public void removeIngredient() {

        burger.addIngredient(ingredient);
        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);
        System.out.println("Удалены ли все ингредиенты из списка " + burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient() {

        burger.addIngredient(ingredient);
        int indexFirstIngredient = burger.ingredients.indexOf(ingredient);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Говяжий метеорит", 0.85F);
        burger.addIngredient(secondIngredient);
        int indexSecondIngredient = burger.ingredients.indexOf(secondIngredient);
        burger.moveIngredient(indexSecondIngredient, indexFirstIngredient);
        assertEquals("Данные не совпадают", secondIngredient, burger.ingredients.get(0));

    }

    @Test
    public void countBurgerPrice() {

        float priceBun = 0.55f;
        float priceIngredient = 0.85f;
        burger.setBuns(bun);
        when(bun.getPrice()).thenReturn(priceBun);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Говяжий метеорит", 0.85f);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        when(ingredient.getPrice()).thenReturn(priceIngredient);
        float expected = priceIngredient + priceBun * 2 + 0.85f;
        assertEquals("Проверка полной стоимости бургера", expected, burger.getPrice(), DELTA);
        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(ingredient, Mockito.times(1)).getPrice();
    }

    @Test
    public void getBurgerReceipt() {

        float priceBun = 0.55f;
        float priceIngredient = 0.5f;
        Ingredient secondIngredient = new Ingredient(SAUCE, "Соус традиционный галактический", 0.35f);
        when(bun.getName()).thenReturn("Марсианская красная булка");
        when(bun.getPrice()).thenReturn(priceBun);
        when(ingredient.getType()).thenReturn(FILLING);
        when(ingredient.getName()).thenReturn("Говяжий метеорит");
        when(ingredient.getPrice()).thenReturn(priceIngredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        System.out.println(burger.getReceipt());
        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(ingredient, Mockito.times(1)).getType();
        Mockito.verify(ingredient, Mockito.times(1)).getName();
    }
}
