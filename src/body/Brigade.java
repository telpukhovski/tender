package body;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class Brigade {
    private final ArrayList<Employee> workers;
    private final BigDecimal cost;

    public Brigade(BigDecimal cost, ArrayList<Employee> employees) {
        this.cost = cost;
        workers = employees;
    }

    public ArrayList<Employee> getWorkers() {
        return workers;
    }


    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brigade brigade)) return false;
        return Objects.equals(workers, brigade.workers) && Objects.equals(cost, brigade.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workers, cost);
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "workers=" + workers +
                ", cost=" + cost +
                '}';
    }
}
