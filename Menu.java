import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<FoodName> biriyanis;
    private List<FoodName> kebabs;
    private List<FoodName> fishes;
    private List<FoodName> drinks;

    public Menu() {
        biriyanis = new ArrayList<>();
        kebabs = new ArrayList<>();
        fishes = new ArrayList<>();
        drinks = new ArrayList<>();

        biriyanis.add(new FoodName(1, "Chicken Biriyanis", 160));
        biriyanis.add(new FoodName(2, "Kacchi Biriyani", 250));

        kebabs.add(new FoodName(3, "Beef Kebab", 380));
        kebabs.add(new FoodName(4, "Chicken Kebab", 260));

        fishes.add(new FoodName(5, "Salmon Fish", 200));
        fishes.add(new FoodName(6, "Rupchada Fish", 450));
        
        drinks.add(new FoodName(5, "Mojo", 20));
        drinks.add(new FoodName(6, "Mountain Dew", 20));
    }

    public List<FoodName> getBiriyanis() {
        return biriyanis;
    }

    public List<FoodName> getKebabs() {
        return kebabs;
    }

    public List<FoodName> getFishes() {
        return fishes;
    }
    
    public List<FoodName> getDrinks() {
        return drinks;
    }
}
