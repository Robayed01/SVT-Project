import org.junit.jupiter.api.*;

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
        FoodName biriyani = new FoodName(1, "Kacchi Biriyani", 250);
        onlineFoodService.addItemToOrder(biriyani);

        String orderSummary = onlineFoodService.getOrderSummary();
        assertNotNull(orderSummary);
        assertTrue(orderSummary.contains("Kacchi Biriyani - Tk250"));
    }

}