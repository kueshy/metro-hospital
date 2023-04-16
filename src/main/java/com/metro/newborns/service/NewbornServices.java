package com.metro.newborns.service;

import com.metro.newborns.dto.NewBornDto;
import com.metro.newborns.entities.NewBorn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewbornServices {

    public NewBornDto createNew(NewBornDto childDto);

    public NewBornDto update(Long uniqueIdentifier, NewBornDto childDto);

    public List<NewBornDto> searchChild(String name);

    public Page<NewBorn> getAllChildren(Pageable pageRequest);


}
