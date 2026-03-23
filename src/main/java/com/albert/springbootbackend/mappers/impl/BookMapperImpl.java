package com.albert.springbootbackend.mappers.impl;

import com.albert.springbootbackend.domain.AuthorEntity;
import com.albert.springbootbackend.domain.BookEntity;
import com.albert.springbootbackend.domain.dto.BookDto;
import com.albert.springbootbackend.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {this.modelMapper = modelMapper;}

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
