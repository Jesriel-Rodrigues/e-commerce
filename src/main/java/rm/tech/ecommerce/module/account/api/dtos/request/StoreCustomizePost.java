package rm.tech.ecommerce.module.account.api.dtos.request;


public record StoreCustomizePost(

    Long id,

    String urlCustomize,
    
    String colorPrimary,

    String colorSecundary
) {
    
}
