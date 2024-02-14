package com.denzel.reactExport.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record Person(@Id Long id,String firstName,String lastName,String idNumber) {
}
