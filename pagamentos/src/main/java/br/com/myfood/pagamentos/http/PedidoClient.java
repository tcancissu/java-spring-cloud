package br.com.myfood.pagamentos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("pedidos")
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/pago")
    void atualizaPagamento(@PathVariable Long id);

}
