import org.junit.Assert;
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
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.*;
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
    @Mock
    List<Ingredient>ingredients;
    @Mock
    Ingredient cutlet;
    @Mock
    Ingredient ketchup;
    @Mock
    Ingredient cheese;
    @Before
    public void setUp() {
        burger = new Burger();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getBurgerBun() {
        burger.setBuns(bun);
        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient);
        assertTrue("Список ингридиентов содержит ингридиент", burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredient() {

        Mockito.when(ingredients.remove(1)).thenReturn(ingredient);
        burger.ingredients = ingredients;
        burger.removeIngredient(1);
        Mockito.verify(burger.ingredients).remove(1);
    }

    @Test
    public void moveIngredient() {

        burger.addIngredient(ingredient);
        int indexFirstIngredient = burger.ingredients.indexOf(ingredient);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Говяжий метеорит", 0.85F);
        burger.addIngredient(secondIngredient);
        int indexSecondIngredient = burger.ingredients.indexOf(secondIngredient);
        burger.moveIngredient(indexSecondIngredient, indexFirstIngredient);
        assertEquals("Проверка перемещания ингредиента", secondIngredient, burger.ingredients.get(0));

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

        burger = new Burger();
        burger.addIngredient(cheese);
        burger.addIngredient(cutlet);
        burger.addIngredient(ketchup);

        Mockito.when(bun.getPrice()).thenReturn(1.5f);
        Mockito.when(cheese.getPrice()).thenReturn(2.0f);
        Mockito.when(cutlet.getPrice()).thenReturn(3.0f);
        Mockito.when(ketchup.getPrice()).thenReturn(0.5f);

        Mockito.when(bun.getName()).thenReturn("КосмоБулка");
        Mockito.when(cheese.getName()).thenReturn("КосмоСыр");
        Mockito.when(cutlet.getName()).thenReturn("КосмоКотлета");
        Mockito.when(ketchup.getName()).thenReturn("КосмоКетчуп");

        Mockito.when(cheese.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ketchup.getType()).thenReturn(IngredientType.SAUCE);

        String expectedReceipt = "(==== КосмоБулка ====)\r\n" +
                "= filling КосмоСыр =\r\n" +
                "= filling КосмоКотлета =\r\n" +
                "= sauce КосмоКетчуп =\r\n" +
                "(==== КосмоБулка ====)\r\n" +
                "\r\n" +
                "Price: 8,500000\r\n";

        burger.setBuns(bun);
        Assert.assertEquals("Проверка вывода чека", expectedReceipt, burger.getReceipt());
    }
}
