import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType ingredientType;
    private final IngredientType expectedType;

    public IngredientTypeTest(IngredientType ingredientType, IngredientType expectedType) {
        this.ingredientType = ingredientType;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Object[][] ingredientTypeParam() {
        return new Object[][]{
                {SAUCE, SAUCE},
                {FILLING, FILLING}
        };
    }

    @Test
    public void getIngredientType() {
        Ingredient ingredient = new Ingredient(ingredientType, "Мясо бессмертных моллюсков Protostomia", 0.6f);
        IngredientType actualType = ingredient.getType();
        assertEquals("Проверка корректности типа ингредиента", expectedType, actualType);
    }
}