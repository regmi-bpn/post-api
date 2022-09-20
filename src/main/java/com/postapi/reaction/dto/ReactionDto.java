package com.postapi.reaction.dto;


import com.postapi.user.dto.UserDto;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReactionDto {
    private HashMap<String, List<UserDto>> reaction;
}
