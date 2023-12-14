package com.cybersoft.cozastore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputResponse {
    private int pages;
    private int totalPages;
    private int totalItems;
    private Object data;
}
