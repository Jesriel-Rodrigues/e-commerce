package rm.tech.ecommerce.module.account.services.interfaces;

import java.util.Optional;

import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.entities.AccountRole;
import rm.tech.ecommerce.module.account.domain.enums.TypeRole;

public interface IAccountRoleService {

    Optional<AccountRole> findByTypeRole(TypeRole typeRole);

    AccountRole findByTypeRoleWithThrow(TypeRole typeRole);

    void addRoleInAccount(Account account, TypeRole typeRole);

    String claimRolesAuthorityByAccount(Account account);
}