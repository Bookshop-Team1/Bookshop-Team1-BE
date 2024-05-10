package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.user.dto.CreateUserRequest;
import com.tw.bootcamp.bookshop.user.dto.UpdateUserRequest;
import com.tw.bootcamp.bookshop.user.exception.InvalidEmailException;
import com.tw.bootcamp.bookshop.user.exception.UserNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final Validator validator;

    public User create(CreateUserRequest userRequest) throws InvalidEmailException {
        Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new InvalidEmailException();
        }
        User newUser = User.create(userRequest);
        validator.validate(newUser);
        return userRepository.save(newUser);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserIfExist(email);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().authority())
        );
    }

    private User getUserIfExist(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void update(Long id, UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        User user = findById(id);
        user.update(updateUserRequest);
        userRepository.save(user);
    }

    public User getByEmail(String email) {
        return getUserIfExist(email);
    }

    private User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
