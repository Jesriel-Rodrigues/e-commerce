package rm.tech.ecommerce.module.account.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.exceptions.ResourceBadRequestException;
import rm.tech.ecommerce.module.account.api.dtos.request.StoreByAdminPost;
import rm.tech.ecommerce.module.account.api.dtos.response.StoreResponse;
import rm.tech.ecommerce.module.account.api.mappers.AccountStoreMapper;
import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.entities.AccountStore;
import rm.tech.ecommerce.module.account.domain.repositories.AccountStoreRepository;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountService;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountStoreService;

@Service
@AllArgsConstructor
public class AccountStoreServiceImpl implements IAccountStoreService {
    
    private final AccountStoreRepository storeRepository;

    private final AccountStoreMapper storeMapper;

    private final IAccountService accountService;


    @Override
    public AccountStore findByIdWithThrow( Long id){
        return findById(id)
            .orElseThrow(() -> new ResourceBadRequestException("Loja não encontrada"));
    }

    @Override
    public Optional<AccountStore> findById( Long id){
        return storeRepository.findById(id);
    }

    @Override
    public Object findStoreResponseById( Long id){
        return findByIdWithThrow(id);
    }

    @Override
    public StoreResponse createStoreByAdmin( StoreByAdminPost request){

        Account account =  accountService.findByIdWithThrow(request.getAccountId());
        
        AccountStore store = storeMapper.convertRequestInEntity(request);

        addIdsAccountInStore(store, account);

        store.setAccount(account);

        store = storeRepository.save(store);
        return storeMapper.convertEntityInResponse(store);
    }

    private void addIdsAccountInStore(AccountStore store, Account account){

        if (account.getStore() != null) {
            store.setId(account.getStore().getId());

            if (account.getStore().getStoreAddress() != null) {
                store.getStoreAddress().setId(account.getStore().getStoreAddress().getId());
            }

            if (account.getStore().getStoreCustomize() != null) {
                store.getStoreCustomize().setId(account.getStore().getStoreCustomize().getId());
            }
        }
    }
}
