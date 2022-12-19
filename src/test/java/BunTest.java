import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String nameBun;
    private final String expected;

    public BunTest(String name, String expected) {
        this.nameBun = name;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] nameParam() {
        return new Object[][] {
                {"Марсианская красная булка R22", "Марсианская красная булка R22"},
                {"MartianRedBun R23", "MartianRedBun R23"},
                {"Краторная R24", "Краторная R24"},
        };
    }

    @Test
    public void getBunName() {
        Bun bun = new Bun(nameBun, 1360);
        String actual = bun.getName();
        assertEquals("Проверка корректности наименования при создании булки", expected, actual);
    }
}
