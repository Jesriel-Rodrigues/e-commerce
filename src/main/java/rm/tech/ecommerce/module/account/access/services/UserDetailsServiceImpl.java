package rm.tech.ecommerce.module.account.access.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.entities.AccountRole;
import rm.tech.ecommerce.module.account.domain.repositories.AccountRepository;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> userOpt = accountRepository.findByEmail(username);
        Account user = userOpt.get();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (AccountRole role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTypeRole().getName()));
        }

        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
