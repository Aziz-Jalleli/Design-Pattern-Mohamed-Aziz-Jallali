// Boursier uniquement
public class ScholarshipSpecification implements Specification<Candidate> {
    @Override
    public boolean isSatisfiedBy(Candidate c) {
        return c.isHasScholarship();
    }
}