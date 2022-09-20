package com.postapi.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Context {
    private Long userId;
    private String userName;
    private String userType;
    private List<String> permissions;


    public Context() {
        this.permissions = new ArrayList<>();
    }
}
