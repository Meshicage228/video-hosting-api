package ru.clevertec.repository;

import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.entity.CategoryEntity;
import ru.clevertec.entity.ChannelEntity;
import static ru.clevertec.entity.ChannelEntity.Fields.*;
import ru.clevertec.enums.Language;
import java.util.ArrayList;
import java.util.Optional;

@UtilityClass
public class ChannelSpecificationService {

    public Specification<ChannelEntity> createSpecification(ChannelFilter channelFilter) {
        return (root, query, builder) -> {
            String channelTitle = channelFilter.getTitle();
            Language filterLanguage = channelFilter.getLanguage();
            Integer categoryId = channelFilter.getCategoryId();
            String categoryTitle = channelFilter.getCategoryTitle();
            ArrayList<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(channelTitle)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(builder.like(root.get(CategoryEntity.Fields.title), "%" + value.trim() + "%")));

            Optional.ofNullable(filterLanguage)
                    .ifPresent(value -> predicates.add(builder.like(root.get(language), "%" + value + "%")));

            Optional.ofNullable(categoryTitle)
                    .filter(StringUtils::isNotBlank)
                    .ifPresent(value -> predicates.add(builder.like(root.get(category).get(CategoryEntity.Fields.title), "%" + value.trim() + "%")));

            Optional.ofNullable(categoryId)
                    .ifPresent(value -> predicates.add(builder.equal(root.get(category).get(CategoryEntity.Fields.id), value)));

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
