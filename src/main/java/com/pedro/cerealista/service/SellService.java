package com.pedro.cerealista.service;


import com.pedro.cerealista.models.entities.ClientModel;
import com.pedro.cerealista.models.entities.ProductModel;
import com.pedro.cerealista.models.entities.SellItemModel;
import com.pedro.cerealista.models.entities.SellModel;
import com.pedro.cerealista.repositories.ClientRepository;
import com.pedro.cerealista.repositories.ProductRepository;
import com.pedro.cerealista.repositories.SellRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class SellService {

    private final SellRepository sellRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public SellService(SellRepository sellRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.sellRepository = sellRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    public SellModel criarVenda(SellModel sell) {

        sell.setData(java.time.LocalDate.now());
        ClientModel cliente = clientRepository.findById(sell.getCliente().getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado: " + sell.getCliente().getIdCliente()));
        sell.setCliente(cliente);

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (SellItemModel item : sell.getItens()) {
            ProductModel product = productRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + item.getProduto().getIdProduto()));
            if (product.getQuantidadeEstoque().compareTo(item.getQuantidade()) < 0) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + product.getNome());
            }
            product.setQuantidadeEstoque(product.getQuantidadeEstoque().subtract(item.getQuantidade()));
            productRepository.save(product);
            item.setPrecoUnitario(product.getPreco());
            item.setProduto(product);
            BigDecimal precoTotal = item.getPrecoUnitario().multiply(item.getQuantidade());
            valorTotal = valorTotal.add(precoTotal);
        }
        sell.setValorTotal(valorTotal);
        sell.getItens().forEach(item -> item.setVenda(sell));
        return sellRepository.save(sell);
    }

    public SellModel getVenda(Long id) {
        return sellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada: " + id));
    }

    public List<SellModel> GetAllSells() {
        return sellRepository.findAll();
    }
}

