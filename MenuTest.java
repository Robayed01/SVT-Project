import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void MenuTest() {
        Menu menu = new Menu();

        assertFalse(menu.getBiriyanis().isEmpty());
        assertFalse(menu.getKebabs().isEmpty());
        assertFalse(menu.getFishes().isEmpty());
        assertFalse(menu.getDrinks().isEmpty());
    }


}