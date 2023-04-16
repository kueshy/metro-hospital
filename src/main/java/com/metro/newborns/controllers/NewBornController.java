package com.metro.newborns.controllers;

import com.metro.newborns.dto.NewBornDto;
import com.metro.newborns.dto.SearchResult;
import com.metro.newborns.entities.NewBorn;
import com.metro.newborns.exceptions.EntityNotFoundException;
import com.metro.newborns.service.NewbornServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController

public class NewBornController {

    private final NewbornServices newBornServe;


    @PostMapping("/addChild")
    public ResponseEntity<NewBornDto> createNewBorn(@RequestBody NewBornDto newBornDto) {
        try {
            NewBornDto newBorn = newBornServe.createNew(newBornDto);
            return ResponseEntity.ok(newBorn);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/editChild/{id}")
    public ResponseEntity<NewBornDto> createNewBorn(@PathVariable long id, @RequestBody NewBornDto newBornDto) {
        try {
            NewBornDto newBorn = newBornServe.update(id, newBornDto);
            return ResponseEntity.ok(newBorn);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }


    @GetMapping("/allChildren")
    public ResponseEntity<Page<NewBorn>> getAllChildren(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        Pageable pageable = PageRequest.of(0, 10);
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        }
        try {
            Page<NewBorn> pages = newBornServe.getAllChildren(pageable);
            return ResponseEntity.ok(pages);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchChild(@RequestParam String name) {
        try {
            List<NewBornDto> list = newBornServe.searchChild(name);
            SearchResult searchResult = SearchResult.builder()
                    .data(list)
                    .build();
            return ResponseEntity.ok(searchResult);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
