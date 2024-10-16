package ru.clevertec.repository;

import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.enums.Language;
import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class ChannelSpecificationService {

    public Specification<ChannelEntity> createSpecification(ChannelFilter channelFilter) {
        return (root, query, builder) -> {
            String title = channelFilter.getTitle();
            Language language = channelFilter.getLanguage();
            CategoryDto category = channelFilter.getCategory();
            ArrayList<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(title)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(builder.like(root.get("title"), "%" + value.trim() + "%")));

            Optional.ofNullable(language)
                    .ifPresent(value -> predicates.add(builder.like(root.get("language"), "%" + value + "%")));

            Optional.ofNullable(category)
                    .map(CategoryDto::getTitle)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(builder.like(root.get("category").get("title"), "%" + value.trim() + "%")));

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
