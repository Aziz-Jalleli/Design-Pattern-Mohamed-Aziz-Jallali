// Note >= 12
public class GradeSpecification implements Specification<Candidate> {
    @Override
    public boolean isSatisfiedBy(Candidate c) {
        return c.getGrade() >= 12.0;
    }
}