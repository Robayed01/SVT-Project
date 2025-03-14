import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

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
}