package it.ko2.spring.example.spnego.model;

import java.util.List;

public record PageModel<C>(int pageNumber,
                           int totalPages,
                           long totalRecords,
                           List<C> records) {
}