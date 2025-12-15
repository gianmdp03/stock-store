package com.stockstore.stockstore.shared.controller;

import com.stockstore.stockstore.shared.dto.category.CategoryDetailDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryListDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryRequestDTO;
import com.stockstore.stockstore.shared.dto.category.CategoryUpdateDTO;
import com.stockstore.stockstore.shared.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDetailDTO> addCategory(@Valid @RequestBody CategoryRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDetailDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(id, dto));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryListDTO>> listCategories(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.listCategories(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Page<CategoryListDTO>> searchCategories(
            @PathVariable String name,
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.searchCategories(name, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
