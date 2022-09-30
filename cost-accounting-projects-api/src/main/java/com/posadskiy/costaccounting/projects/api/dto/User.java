package com.posadskiy.costaccounting.projects.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String defaultCurrency;
    private List<Category> purchaseCategories;
    private List<Category> incomeCategories;
}
