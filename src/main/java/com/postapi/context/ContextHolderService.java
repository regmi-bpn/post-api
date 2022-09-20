package com.postapi.context;

import com.postapi.user.constants.UserType;
import com.postapi.user.entity.Users;
import com.postapi.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ContextHolderService {

    private UserRepository userRepository;

    @Autowired
    public ContextHolderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Context getContext() {
        return ContextHolder.get();
    }

    public void setContext(String username, String userType, List<String> permissions) {
        if (userType.equalsIgnoreCase(UserType.USER.name())) {
            this.setContextForClient(username);
        }else if(userType.equalsIgnoreCase(UserType.ADMIN.name())){
            this.setContextForAdmin(username);
        }
    }

    private void setContextForClient(String username) {
        Optional<Users> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresent(user -> {
            ContextHolder thread = new ContextHolder(user.getId(), user.getPhoneNumber(), UserType.USER.name());
            thread.run();
        });
    }

    private void setContextForAdmin(String username) {
        Optional<Users> adminOptional = userRepository.findByUsername(username);
        adminOptional.ifPresent(admin -> {
            ContextHolder thread = new ContextHolder(admin.getId(), admin.getPhoneNumber(), UserType.ADMIN.name());
            thread.run();
        });
    }


}
