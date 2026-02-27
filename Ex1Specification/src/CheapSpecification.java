public class CheapSpecification implements Specification < Product >{
    private int price ;

    public CheapSpecification(int p) {
        this.price = p;
    }

    public boolean isSatisfiedBy ( Product p ) {
        return p.getPrice()<=this.price;
    }
}
