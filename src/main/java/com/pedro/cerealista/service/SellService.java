package com.pedro.cerealista.service;


import com.pedro.cerealista.dtos.ItemSellRecordDto;
import com.pedro.cerealista.dtos.SellRecordDto;
import com.pedro.cerealista.models.entities.ClientModel;
import com.pedro.cerealista.models.entities.ProductModel;
import com.pedro.cerealista.models.entities.SellItemModel;
import com.pedro.cerealista.models.entities.SellModel;
import com.pedro.cerealista.repositories.ClientRepository;
import com.pedro.cerealista.repositories.ProductRepository;
import com.pedro.cerealista.repositories.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellService {

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    public SellModel createSell(SellRecordDto sellDto) {
        // 1. Buscar o cliente
        ClientModel client = clientRepository.findById(sellDto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // 2. Criar a venda
        SellModel sell = new SellModel();
        sell.setData(sellDto.data());
        sell.setValorTotal(sellDto.valorTotal());
        sell.setCliente(client);

        // 3. Criar os itens da venda
        List<SellItemModel> items = new ArrayList<>();
        for (ItemSellRecordDto itemDto : sellDto.itens()) {
            // Buscar o produto
            ProductModel product = productRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            // Verificar estoque (opcional)
            if (product.getQuantidadeEstoque() < itemDto.quantidade()) {
                throw new RuntimeException("Estoque insuficiente para " + product.getNome());
            }

            // Atualizar estoque (opcional)
            product.setQuantidadeEstoque(product.getQuantidadeEstoque() - itemDto.quantidade());
            productRepository.save(product);

            // Criar item da venda
            SellItemModel item = new SellItemModel();
            item.setProduto(product);
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoUnitario(itemDto.precoUnitario());
            item.setVenda(sell);

            items.add(item);
        }

        // 4. Adicionar itens à venda
        sell.setItens(items);

        // 5. Salvar a venda
        return sellRepository.save(sell);
    }

    public boolean delete(Long id) {
        Optional<SellModel> vendaOptional = sellRepository.findById(id);
        if (vendaOptional.isPresent()) {
            sellRepository.delete(vendaOptional.get());
            return true;
        } else {
            return false;
        }

}

    public SellModel getSellById(Long id) {
        return sellRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, // 🔁 Retorna HTTP 404
                        "Venda não encontrada"
                ));
    }

    public List<SellModel> getAllSells() {
        return sellRepository.findAll();
    }


}
