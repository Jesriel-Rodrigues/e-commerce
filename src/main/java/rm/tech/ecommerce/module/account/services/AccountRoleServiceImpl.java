package rm.tech.ecommerce.module.account.services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rm.tech.ecommerce.exceptions.ResourceBadRequestException;
import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.entities.AccountRole;
import rm.tech.ecommerce.module.account.domain.enums.TypeRole;
import rm.tech.ecommerce.module.account.domain.repositories.AccountRepository;
import rm.tech.ecommerce.module.account.domain.repositories.AccountRoleRepository;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountRoleService;

@Service
@RequiredArgsConstructor
public class AccountRoleServiceImpl implements IAccountRoleService {
    
    private final AccountRoleRepository roleRepository;
    private final AccountRepository accountRepository;


    @Override
    public Optional<AccountRole> findByTypeRole( TypeRole typeRole){
        return roleRepository.findByTypeRole(typeRole);
    }

    @Override
    public AccountRole findByTypeRoleWithThrow( TypeRole typeRole){
        return findByTypeRole(typeRole)
            .orElseThrow(() -> new ResourceBadRequestException("Permissão não encontrada"));
    }

    @Override
    public void addRoleInAccount(Account account, TypeRole typeRole){

        Set<AccountRole> roles = account.getRoles();
        AccountRole role = findByTypeRoleWithThrow(typeRole);
        
        roles.add(role);
        account.setRoles(roles);
        accountRepository.save(account);
    }


    @Override
    public String claimRolesAuthorityByAccount(Account account){
        
        Long maxRoleNumberAccount = getMaxRoleSize(account); 

        Set<String> rolesAuthorized = new HashSet<>();

        for (TypeRole role : TypeRole.values()) {
            
            if (role.getSize() <= maxRoleNumberAccount) {
                rolesAuthorized.add(role.name());
            }
        }

        // Criar a string com as roles filtradas
        return  rolesAuthorized.stream()
            .collect(Collectors.joining(" "));        
    }

    public Long getMaxRoleSize(Account account){
        Optional<Long> maxRoleSize = account.getRoles().stream()
            .map(AccountRole::getTypeRole)
            .map(TypeRole::getSize)
            .max(Comparator.naturalOrder());

        return maxRoleSize.orElse(0L); // Retorna 0 se não houver roles na conta
    }
}
