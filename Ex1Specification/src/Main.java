import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200, "Electronics", true),
                new Product("Phone", 800, "Electronics", false),
                new Product("Book", 30, "Books", true),
                new Product("TV", 500, "Electronics", true),
                new Product("Notebook", 5, "Stationery", true)
        );

        Specification<Product> priceSpec =
                new PriceSpecification(100);
        Specification<Product> newSpec =
                new CheapSpecification(800);

        Specification<Product> categorySpec =
                new CategorySpecification("Electronics");

        Specification<Product> stockSpec =
                new InStockSpecification();

        Specification<Product> finalSpec =
                new AndSpecification<>(
                        priceSpec,
                        new AndSpecification<>(stockSpec, newSpec)
                );

        BetterFilter filter = new BetterFilter();
        List<Product> result = filter.filter(products, finalSpec);

        System.out.println("Filtered products:");
        for (Product p : result) {
            System.out.println(
                    "- " + p.getName()
                            + " | " + p.getPrice()
                            + " | " + p.getCategory()
                            + " | inStock=" + p.isInStock()
            );
        }
    }
}