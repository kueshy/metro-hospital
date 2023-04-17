package com.metro.newborns.service;

import com.metro.newborns.dto.NewBornDto;
import com.metro.newborns.entities.NewBorn;
import com.metro.newborns.enums.MartialStatus;
import com.metro.newborns.enums.NewBornGender;
import com.metro.newborns.exceptions.EntityNotFoundException;
import com.metro.newborns.repository.NewBornRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewBornServeImp implements NewbornServices {

    private final NewBornRepository newBornRepository;

    @Override
    public NewBornDto createNew(NewBornDto childDto) {
        NewBorn newBorn = toEntity(childDto);
        newBorn = newBornRepository.save(newBorn);
        return toDto(newBorn);
    }

    @Override
    public NewBornDto update(Long uniqueIdentifier, NewBornDto childDto) {
        Optional<NewBorn> newBorn = newBornRepository.findById(uniqueIdentifier);
        if (newBorn.isPresent()) {
            NewBorn previous = newBorn.get();
            previous.setChild_gender(childDto.getChild_gender().name());
            previous.setChild_name(childDto.getChild_name());
            previous.setChild_place_of_birth(childDto.getChild_place_of_birth());
            previous.setChild_registration_number(childDto.getChild_registration_number());
            previous.setChild_date_of_birth(childDto.getChild_date_of_birth());
            previous.setChild_weight(childDto.getChild_weight());
            previous.setMothers_name(childDto.getMothers_name());
            previous.setMothers_age(childDto.getMothers_age());
            previous.setMothers_occupation(childDto.getMothers_occupation());
            previous = newBornRepository.save(previous);
            return toDto(previous);
        } else {
            throw new EntityNotFoundException("Newborn", "id", uniqueIdentifier);
        }

    }

    @Override
    public List<NewBornDto> searchChild(String name) {
        List<NewBorn> data = newBornRepository.findByChild_nameContainingIgnoreCase(name);
        return data.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public Page<NewBorn> getAllChildren(Pageable pageRequest) {
        return newBornRepository.findAll(pageRequest);
    }

    private NewBorn toEntity(NewBornDto childDto) {
        NewBorn child = NewBorn.builder().
                child_name(childDto.getChild_name())
                .child_place_of_birth(childDto.getChild_place_of_birth())
                .child_registration_number(childDto.getChild_registration_number())
                .child_date_of_birth(childDto.getChild_date_of_birth())
                .child_gender(childDto.getChild_gender().name())
                .child_weight(childDto.getChild_weight())
                .mothers_name(childDto.getMothers_name())
                .mothers_age(childDto.getMothers_age())
                .mothers_occupation(childDto.getMothers_occupation())
                .mothers_martial_status(childDto.getMothers_martial_status().name())
                .build();
        return child;

    }


    private NewBornDto toDto(NewBorn child) {
        NewBornDto childDto = NewBornDto.builder()
                .child_id(child.getChild_id())
                .child_name(child.getChild_name())
                .child_place_of_birth(child.getChild_place_of_birth())
                .child_registration_number(child.getChild_registration_number())
                .child_date_of_birth(child.getChild_date_of_birth())
                .child_gender(NewBornGender.valueOf(child.getChild_gender()))
                .child_weight(child.getChild_weight())
                .mothers_name(child.getMothers_name())
                .mothers_age(child.getMothers_age())
                .mothers_occupation(child.getMothers_occupation())
                .mothers_martial_status(MartialStatus.valueOf(child.getMothers_martial_status()))
                .build();
        return childDto;

    }
}
