// Age >= 18
public class AgeSpecification implements Specification<Candidate> {
    @Override
    public boolean isSatisfiedBy(Candidate c) {
        return c.getAge() >= 18;
    }
}