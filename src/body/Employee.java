package body;

import java.util.HashSet;
import java.util.Objects;

public class Employee {
    private final HashSet<Skills> skills;

    public Employee(HashSet<Skills> skill) {
        skills = skill;
    }

    public HashSet<Skills> getSkills() {
        return skills;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "skills=" + skills +
                '}';
    }
}

