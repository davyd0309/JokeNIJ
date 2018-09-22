package pl.dawydiuk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.repository.RoleRepository;
import pl.dawydiuk.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Created by Judyta on 18.08.2018.
 */

@Component
public class RunAtStart {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public RunAtStart(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void runAtStart() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User admin = User.builder()
                .active(true)
                .email("konrad@dawydiuk.pl")
                .name("Konrad")
                .lastName("Dawydiuk")
                .password(passwordEncoder.encode("konrad"))
                .build();

        User user = User.builder()
                .active(true)
                .email("michal@dawydiuk.pl")
                .name("Michał")
                .lastName("Nawrocki")
                .password(passwordEncoder.encode("michal"))
                .build();


        Role roleAdmin = Role.builder()
                .role("ROLE_ADMIN")
                .build();

        Role roleUser = Role.builder()
                .role("ROLE_USER")
                .build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        admin.getRoles().add(roleAdmin);
        admin.getRoles().add(roleUser);

        user.getRoles().add(roleUser);



        userRepository.save(admin);
        userRepository.save(user);

    }
}
