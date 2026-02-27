public class PriceSpecification implements Specification < Product > {
    private double minPrice ;

    public PriceSpecification(int i) {
        minPrice = i;
    }

    public boolean isSatisfiedBy ( Product p ) {
        return p . getPrice () >= minPrice ;
    }
}
