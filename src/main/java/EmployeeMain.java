import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeMain {

    static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(
                new Employee("Rukshesh", "Talwar", 8000.0, List.of("Project 1", "Project 2", "Project 3"))
        );

        employeeList.add(
                new Employee("Himanshi", "Talwar", 80000.0, List.of("Project 4", "Project 5", "Project 6"))
        );

        employeeList.add(
                new Employee("Netik", "Talwar", 9000.0, List.of("Project 7", "Project 8", "Project 9"))
        );

        employeeList.add(
                new Employee("Trijal", "Talwar", 10000.0, List.of("Project 10", "Project 11", "Project 12"))
        );
    }

    public static void main(String[] args) {
        //forEach
        employeeList.stream()
                .forEach(employee -> System.out.println(employee));

        /**
         * Increasing the salary of all employees by some percent by using map()
         * map() operates on all the stream elements and returns a new stream of different element types
         * Syntax of map below
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper)
         */
        List<Employee> increasedSal = employeeList.stream()
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()))
                .collect(Collectors.toList());
        System.out.println(increasedSal);

        /**
         * Filter the employee whose salary is greater than 5000 and increase the salary by some percentage
         */
        List<Employee> filterEmployee = employeeList.stream()
                .filter(employee -> employee.getSalary() > 5000)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .collect(Collectors.toList());
        System.out.println(filterEmployee);

        Employee firstEmployee = employeeList.stream()
                .filter(employee -> employee.getSalary() > 6000)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                ))
                .findFirst()
                .orElse(null);
        System.out.println(firstEmployee);

        String projects = employeeList
                .stream()
                .map(Employee::getProjects)
                .flatMap(strings -> strings.stream())
                .collect(Collectors.joining(","));
        System.out.println(projects);

        //Short circuit operators
        List<Employee> tryingShortCircuit = employeeList
                .stream()
                .skip(1)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(tryingShortCircuit);

        /**
         * Reduction methods sum(), reduce(), max(), min()
         */

        //Finite Data -> Making the data finite using limit()
        Stream
                .generate(Math::random)
                .limit(5)
                .forEach(value -> System.out.println(value));

        //sorting using sorted() method which takes the Comparator as an argument
        List<Employee> sortedEmployeesByFirstName = employeeList
                .stream()
                .sorted((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()))
                .collect(Collectors.toList());
        System.out.println(sortedEmployeesByFirstName);

        //finding the min or max
        employeeList
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        //reduce
        Double totalSal = employeeList
                .stream()
                .map(employee -> employee.getSalary())
                .reduce(0.0, Double::sum);
        System.out.println(totalSal);


    }
}
