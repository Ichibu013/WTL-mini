package com.miniproject.wtlmini.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * The type Entity to dto converter.
 */
@Component
public class GenericDtoMapper {

	private final ModelMapper modelMapper;

	public GenericDtoMapper() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
	}


	public <D, T> D map(final T source, Class<D> destinationClass, Consumer<D> customMapping) {
		try {
			D destination = modelMapper.map(source, destinationClass);
			if (customMapping != null) {
				customMapping.accept(destination); // Apply the custom mappings here
			}
			return destination;
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
					"Failed to map " + source.getClass().getSimpleName() + " to " + destinationClass.getSimpleName(),
					e);
		}
	}

	// Overloaded method without the custom mapping
	public <D, T> D map(final T source, Class<D> destinationClass) {
		return map(source, destinationClass, null); // Calls the method with custom
		// mapping, passing null
	}

	public <D, T> List<D> mapAll(final List<T> sourceList, Class<D> destinationClass) {
		return sourceList.stream().map(entity -> map(entity, destinationClass)).collect(Collectors.toList());
	}

	public <S, D> D map(final S source, D destination) {
		try {
			modelMapper.map(source, destination);
			return destination;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Failed to map " + source.getClass().getSimpleName() + " to "
					+ destination.getClass().getSimpleName(), e);
		}
	}

}