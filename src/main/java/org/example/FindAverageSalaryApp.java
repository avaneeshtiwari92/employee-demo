package org.example;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

class FindAverageSalaryApp{

    public void findAverageSalary(List<Employee> employees){

        ConcurrentMap<String, ConcurrentMap<String, Double>> locationToDesignationMap = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee::getOfficeLocation,
                        Collectors.groupingByConcurrent(Employee::getDesignation,
                                Collectors.averagingDouble(Employee::getSalary))));

        locationToDesignationMap.forEach((location, designationMap) -> {
            designationMap.forEach((designation, avgSalary) -> {
                System.out.printf("%s --> %s --> %.2f\n", location, designation, avgSalary);
            });
        });
    }
}

