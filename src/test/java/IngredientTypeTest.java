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
    private final String expectedType;

    public IngredientTypeTest(IngredientType ingredientType, String expectedType) {
        this.ingredientType = ingredientType;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Object[][] ingredientTypeParam() {
        return new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        };
    }

    @Test
    public void getIngredientType() {
        assertEquals("Проверка корректности типа ингредиента" + ingredientType.toString(), ingredientType, IngredientType.valueOf(expectedType));
    }
}