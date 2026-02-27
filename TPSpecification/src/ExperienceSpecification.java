// Expérience >= 2 ans
public class ExperienceSpecification implements Specification<Candidate> {
    @Override
    public boolean isSatisfiedBy(Candidate c) {
        return c.getExperienceYears() >= 2;
    }
}