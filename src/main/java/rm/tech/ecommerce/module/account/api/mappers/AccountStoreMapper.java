package rm.tech.ecommerce.module.account.api.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import rm.tech.ecommerce.module.account.api.dtos.request.StoreByAdminPost;
import rm.tech.ecommerce.module.account.domain.entities.AccountStore;

@Component
@RequiredArgsConstructor
public class AccountStoreMapper {
    
    private final ModelMapper modelMapper;

    public AccountStore convertRequestInEntity( StoreByAdminPost storePost){
        return modelMapper.map(storePost, AccountStore.class);
    }
}
