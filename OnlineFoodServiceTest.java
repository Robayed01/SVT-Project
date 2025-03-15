import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class OnlineFoodServiceTest {
    private OnlineFoodService onlineFoodService;

    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Setting up before all tests...");
    }

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("Tearing down after all tests...");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Setting up before each test...");
        onlineFoodService = new OnlineFoodService();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down after each test...");
        onlineFoodService = null;
    }

    @Test
    void InitializationTest() {
        assertNotNull(onlineFoodService);
    }

    @Test
    void AddItemToOrderTest() {
        FoodName biriyani = new FoodName(2, "Kacchi Biriyani", 250);
        onlineFoodService.addItemToOrder(biriyani);

        String orderSummary = onlineFoodService.getOrderSummary();
        assertTrue(orderSummary.contains("Kacchi Biriyani - Tk250"));

        FoodName kebab = new FoodName(3, "Beef Kebab", 380);
        assertNotEquals(biriyani.getPrice(), kebab.getPrice());

        FoodName sameBiriyani = biriyani;
        assertSame(biriyani, sameBiriyani);

        assertNotSame(biriyani, kebab);

        assertTimeout(Duration.ofMillis(100), () -> {
            onlineFoodService.addItemToOrder(biriyani);
        });
    }

    @Test
    void ClearOrderTest() {
        FoodName biriyani = new FoodName(1, "Chicken Biriyani", 160);
        onlineFoodService.addItemToOrder(biriyani);

        onlineFoodService.clearOrder();
        String orderSummary = onlineFoodService.getOrderSummary();
        assertTrue(orderSummary.isEmpty());
    }

    @Test
    void ShowCustomerDetailsWithValidInputTest() {
        assertDoesNotThrow(() -> {
            onlineFoodService.showCustomerDetails();
        });
    }

    @Test
    void UpdateOrderSummaryTest() {
        FoodName biriyani = new FoodName(1, "Chicken Biriyani", 160);
        onlineFoodService.addItemToOrder(biriyani);

        String orderSummary = onlineFoodService.getOrderSummary();
        assertNotNull(orderSummary);
        assertTrue(orderSummary.contains("Total Price: Tk160"));
    }

    @Test
    void MenuItemsAdditionTest() {
        List<FoodName> biriyanis = onlineFoodService.getMenu().getBiriyanis();
        assertNotNull(biriyanis);
        assertFalse(biriyanis.isEmpty());
    }

    @Test
    void FinishOrderButtonActionWithInvalidDataTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            onlineFoodService.finishOrderButtonAction();
        });
        try {
            onlineFoodService.finishOrderButtonAction();
            fail();
        } catch (IllegalArgumentException e) {}


    }

    @Test
    void ClearOrderButtonActionTest() {
        assertDoesNotThrow(() -> {
            onlineFoodService.clearOrderButtonAction();
        });
    }

    @Test
    void TotalPriceInitialStateTest() {
        int totalPrice = onlineFoodService.getTotalPrice();
        assertEquals(0, totalPrice);
    }

    @Test
    void TotalPriceAfterAddingItemsTest() {
        FoodName fish = new FoodName(6, "Rupchada Fish", 450);
        FoodName kebab = new FoodName(4, "Chicken Kebab", 260);

        onlineFoodService.addItemToOrder(fish);
        onlineFoodService.addItemToOrder(kebab);

        int[] expected = {450, 260};
        int[] actual = {450, 260};

        assertArrayEquals(expected, actual);

        int totalPrice = onlineFoodService.getTotalPrice();
        assertEquals(710, totalPrice);
    }

    @Test
    void OrderSummaryInitialStateTest() {
        String orderSummary = onlineFoodService.getOrderSummary();
        assertTrue(orderSummary.isEmpty());
    }

    @Test
    void OrderSummaryAfterAddingItemsTest() {
        FoodName biriyani = new FoodName(1, "Chicken Biriyani", 160);
        FoodName kebab = new FoodName(3, "Beef Kebab", 380);

        onlineFoodService.addItemToOrder(biriyani);
        onlineFoodService.addItemToOrder(kebab);

        String orderSummary = onlineFoodService.getOrderSummary();
        assertTrue(orderSummary.contains("Chicken Biriyani - Tk160"));
        assertTrue(orderSummary.contains("Beef Kebab - Tk380"));
        assertTrue(orderSummary.contains("Total Price: Tk540"));

        List<String> expectedLines = List.of("Chicken Biriyani", "Beef Kebab", "Total: Tk450");
        List<String> actualLines = List.of("Chicken Biriyani", "Beef Kebab", "Total: Tk450");

        assertLinesMatch(expectedLines, actualLines);

    }

    @ParameterizedTest
    @ValueSource(ints = {160, 250, 380, 450})
    void FoodPricePositiveTest(int price) {
        assertTrue(price > 0);
    }

    @ParameterizedTest
    @CsvSource({
            "160, 250, 410",
            "20, 20, 40"
    })
    void TotalPriceCalculationTest(int price1, int price2, int total) {
        assertEquals(total, price1 + price2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/customerInfo.csv", numLinesToSkip = 0)
    void CustomerInfoTest(String name, String address, String mobileNumber) {
        assertNotNull(name);
        assertNotNull(address);
        assertTrue(mobileNumber.contains("+880"));
    }

    static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(160, 250, 410),
                Arguments.of(450, 200, 650)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void TotalofPriceTest(int cost1, int cost2, int total) {
        assertEquals(total, cost1 + cost2);
    }

}