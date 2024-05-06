package rm.tech.ecommerce.module.account.services.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.entities.AccountRole;
import rm.tech.ecommerce.module.account.domain.enums.TypeRole;

public interface IAccountRoleService {

    Optional<AccountRole> findByTypeRole(TypeRole typeRole);

    AccountRole findByTypeRoleWithThrow(TypeRole typeRole);

    void addRoleInAccount(Account account, TypeRole typeRole);

    List<Map<String, String>> claimRolesAuthorityByAccount(Account account);
}