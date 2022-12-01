import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;

@RunWith(Parameterized.class)
public class IngredientNameTest {

    private final String nameIngredient;
    private final String expected;

    public IngredientNameTest(String nameIngredient, String expected) {
        this.nameIngredient = nameIngredient;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] nameParam() {
        return new Object[][] {
                {"Соус Spicy-X", "Соус Spicy-X"},
                {"Мини-салат Экзо-Плантаго", "Мини-салат Экзо-Плантаго"},
                {"Сыр с астероидной плесенью", "Сыр с астероидной плесенью"},
                {"Плоды Фалленианского дерева", "Плоды Фалленианского дерева"}
        };
    }

    @Test
    public void getIngredientName() {
        Ingredient ingredient = new Ingredient(FILLING, nameIngredient, 0.6f);
        String actual = ingredient.getName();
        assertEquals("Проверка названия ингредиента", expected, actual);
    }
}
