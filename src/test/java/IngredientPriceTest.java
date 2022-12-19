import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientPriceTest {

    private final float priceIngredient;
    private final float expectedPrice;
    private final float DELTA = 0.001f;

    public IngredientPriceTest (float priceIngredient, float expectedPrice) {
        this.priceIngredient = priceIngredient;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] priceParam() {
        return new Object[][] {
                {0.1f, 0.1f},
                {1.99f, 1.99f},
                {10, 10}
        };
    }

    @Test
    public void getIngredientPrice() {
        Ingredient ingredient = new Ingredient(SAUCE, "Мясо бессмертных моллюсков Protostomia", priceIngredient);
        float actualPrice = ingredient.getPrice();
        assertEquals("Проверка корректности подсчета стоимости ингредиента", expectedPrice, actualPrice, DELTA);
    }
}