package br.com.myfood.pedidos.amqp;

import br.com.myfood.pedidos.dto.PagamentoDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDto pagamento) {
        String mensagem = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s 
                """.formatted(pagamento.getId(),
                                pagamento.getPedidoId(),
                                pagamento.getValor(),
                                pagamento.getStatus());

        System.out.println("Recebi a mensagem " + mensagem);
    }

}
