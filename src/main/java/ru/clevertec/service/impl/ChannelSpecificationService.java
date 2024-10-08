package ru.clevertec.service.impl;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CategoryDto;
import ru.clevertec.dto.filter.ChannelFilter;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.enums.Language;
import ru.clevertec.service.SpecificationService;

import java.util.ArrayList;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class ChannelSpecificationService implements SpecificationService<ChannelEntity, ChannelFilter> {

    @Override
    public Specification<ChannelEntity> createSpecification(ChannelFilter channelFilter) {
        return (root, query, builder) -> {
            String title = channelFilter.getTitle();
            Language language = channelFilter.getLanguage();
            CategoryDto category = channelFilter.getCategory();
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (nonNull(title)) {
                if(isNotBlank(title)) {
                    predicates.add(builder.like(root.get("title"), "%" + title.trim() + "%"));
                }
            }
            if (nonNull(language)) {
                predicates.add(builder.like(root.get("language"), language.toString()));
            }
            if (nonNull(category)) {
                if(nonNull(category.getTitle())){
                    predicates.add(builder.like(root.get("category").get("title"), "%" + category.getTitle().trim() + "%"));
                }
            }

            Predicate[] array = predicates.toArray(Predicate[]::new);

            return builder.and(array);
        };
    }
}
