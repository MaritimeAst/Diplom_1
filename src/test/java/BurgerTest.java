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

    Burger burger;
    private final float DELTA = 0.001f;
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
        System.out.println("В списке содержится " + size + " ингредиент(а)");
    }

    @Test
    public void removeIngredient() {

        burger.addIngredient(ingredient);
        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);
        System.out.println("Список ингредиентов пуст - " + burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient() {

        burger.addIngredient(ingredient);
        int indexFirstIngredient = burger.ingredients.indexOf(ingredient);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Говяжий метеорит", 0.35F);
        burger.addIngredient(secondIngredient);
        int indexSecondIngredient = burger.ingredients.indexOf(secondIngredient);
        burger.moveIngredient(indexSecondIngredient, indexFirstIngredient);
        assertEquals("Данные не совпадают", secondIngredient, burger.ingredients.get(0));
        System.out.println("Теперь в бургере первый ингредиент это - " + burger.ingredients.get(0).getName());
    }
}
