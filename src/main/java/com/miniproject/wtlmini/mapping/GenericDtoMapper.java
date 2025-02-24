package com.miniproject.wtlmini.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Component
public class GenericDtoMapper {

	private static final Logger logger = LoggerFactory.getLogger(GenericDtoMapper.class);
	private final ModelMapper modelMapper;

	public GenericDtoMapper() {
		logger.info("Initializing GenericDtoMapper...");
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setSkipNullEnabled(true);
		logger.info("GenericDtoMapper initialized successfully with STRICT matching strategy and null-skipping enabled.");
	}

	public <D, E> D toDto(E entity, Class<D> dtoClass) {
		logger.debug("Mapping entity of type {} to DTO of type {}",
				entity != null ? entity.getClass().getName() : "null",
				dtoClass != null ? dtoClass.getName() : "null");
		if (entity == null || dtoClass == null) {
			logger.warn("Null entity or dtoClass passed to toDto method, returning null.");
			return null;
		}
		return modelMapper.map(entity, dtoClass);
	}

	public <D, E> E toEntity(D dto, Class<E> entityClass) {
		logger.debug("Mapping DTO of type {} to entity of type {}",
				dto != null ? dto.getClass().getName() : "null",
				entityClass != null ? entityClass.getName() : "null");
		if (dto == null || entityClass == null) {
			logger.warn("Null dto or entityClass passed to toEntity method, returning null.");
			return null;
		}
		return modelMapper.map(dto, entityClass);
	}

	public <D, E> List<D> toDtoList(List<E> entities, Class<D> dtoClass) {
		logger.debug("Mapping list of entities to list of DTOs of type {}",
				dtoClass != null ? dtoClass.getName() : "null");
		if (entities == null || dtoClass == null) {
			logger.warn("Null entities list or dtoClass passed to toDtoList method, returning null or empty list.");
			return entities == null ? null : List.of();
		}
		return entities.stream()
				.map(entity -> modelMapper.map(entity, dtoClass))
				.collect(Collectors.toList());
	}

	public <D, E> List<E> toEntityList(List<D> dtos, Class<E> entityClass) {
		logger.debug("Mapping list of DTOs to list of entities of type {}",
				entityClass != null ? entityClass.getName() : "null");
		if (dtos == null || entityClass == null) {
			logger.warn("Null DTOs list or entityClass passed to toEntityList method, returning null or empty list.");
			return dtos == null ? null : List.of();
		}
		return dtos.stream()
				.map(dto -> modelMapper.map(dto, entityClass))
				.collect(Collectors.toList());
	}
}