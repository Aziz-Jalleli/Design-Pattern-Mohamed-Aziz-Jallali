public class Candidate {
    private String name;
    private int age;
    private double grade;
    private int experienceYears;
    private boolean hasScholarship;

    public Candidate(String name, int age, double grade, int experienceYears, boolean hasScholarship) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.experienceYears = experienceYears;
        this.hasScholarship = hasScholarship;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGrade() { return grade; }
    public int getExperienceYears() { return experienceYears; }
    public boolean isHasScholarship() { return hasScholarship; }

    @Override
    public String toString() {
        return name + " | Âge: " + age + " | Note: " + grade +
                " | Expérience: " + experienceYears + " ans | Boursier: " + (hasScholarship ? "Oui" : "Non");
    }
}