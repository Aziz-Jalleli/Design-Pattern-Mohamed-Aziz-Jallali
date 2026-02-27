public class InStockSpecification implements Specification<Product> {

    @Override
    public boolean isSatisfiedBy(Product p) {
        return p.isInStock();
    }
}