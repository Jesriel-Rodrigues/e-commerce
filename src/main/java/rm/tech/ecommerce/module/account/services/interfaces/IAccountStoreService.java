package rm.tech.ecommerce.module.account.services.interfaces;

import java.util.Optional;

import rm.tech.ecommerce.module.account.api.dtos.request.StoreByAdminPost;
import rm.tech.ecommerce.module.account.api.dtos.response.StoreResponse;
import rm.tech.ecommerce.module.account.domain.entities.AccountStore;

public interface IAccountStoreService {

    AccountStore findByIdWithThrow(Long id);

    Optional<AccountStore> findById(Long id);

    Object findStoreResponseById(Long id);

    StoreResponse createStoreByAdmin(StoreByAdminPost request);
}
