public class CategorySpecification implements Specification < Product > {
    private String category ;

    public CategorySpecification(String cat) {
        this.category = cat;
    }

    public boolean isSatisfiedBy ( Product p ) {
        return p . getCategory () . equals ( category ) ;
    }
}