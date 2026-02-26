package rm.tech.ecommerce.module.account.services.interfaces;

import java.util.Optional;

import rm.tech.ecommerce.module.account.api.dtos.request.StoreByAdminPost;
import rm.tech.ecommerce.module.account.api.dtos.response.StoreResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.store.StoreAccount;

public interface IAccountStoreService {

    StoreAccount findByIdWithThrow(Long id);

    Optional<StoreAccount> findById(Long id);

    Object findStoreResponseById(Long id);

    StoreResponse createStoreByAdmin(StoreByAdminPost request);
}
