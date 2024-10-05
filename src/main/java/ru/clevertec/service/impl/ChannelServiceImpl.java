package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.ChannelDto;
import ru.clevertec.dto.PaginatedChannelDto;
import ru.clevertec.dto.request.ChannelFilter;
import ru.clevertec.entity.ChannelEntity;
import ru.clevertec.enums.Category;
import ru.clevertec.enums.Language;
import ru.clevertec.exception.ChannelNotFoundException;
import ru.clevertec.mapper.ChannelMapper;
import ru.clevertec.repository.ChannelRepository;
import ru.clevertec.service.ChannelService;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelMapper mapper;

    @Override
    public ChannelDto saveChannel(ChannelDto channelDto) {
        ChannelEntity entity = mapper.toEntity(channelDto);
        ChannelEntity saved = channelRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public List<PaginatedChannelDto> searchChannel(Integer page, Integer size, ChannelFilter channelFilter) {
        Specification<ChannelEntity> specification = createSpecification(channelFilter);

        return channelRepository.findAll(specification, PageRequest.of(page, size))
                .stream()
                .map(mapper::paginatedSearchResult)
                .toList();
    }

    private Specification<ChannelEntity> createSpecification(ChannelFilter channelFilter) {
        return (root, query, builder) -> {
            String title = channelFilter.getTitle();
            Language language = channelFilter.getLanguage();
            Category category = channelFilter.getCategory();
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (nonNull(title)) {
                if(isNotBlank(title)) {
                    predicates.add(builder.like(root.get("title"), "%" + title.toLowerCase().trim() + "%"));
                }
            }
            if (nonNull(language)) {
                predicates.add(builder.like(root.get("language"), language.toString()));
            }
            if (nonNull(category)) {
                predicates.add(builder.like(root.get("category"), category.toString()));
            }

            Predicate[] array = predicates.toArray(Predicate[]::new);

            return builder.and(array);
        };
    }
}
