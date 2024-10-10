package ru.clevertec.service;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationService <T, F> {
    Specification<T> createSpecification(F t);
}
