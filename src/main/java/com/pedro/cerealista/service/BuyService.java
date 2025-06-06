package com.pedro.cerealista.service;

import com.pedro.cerealista.dtos.BuyItemRecordDto;
import com.pedro.cerealista.dtos.BuyRecordDto;
import com.pedro.cerealista.models.entities.BuyItemModel;
import com.pedro.cerealista.models.entities.BuyModel;
import com.pedro.cerealista.models.entities.ProductModel;
import com.pedro.cerealista.models.entities.SupplierModel;
import com.pedro.cerealista.repositories.BuyRepository;
import com.pedro.cerealista.repositories.ProductRepository;
import com.pedro.cerealista.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BuyService {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyRepository buyRepository;

    @Transactional
    public BuyModel criarCompra(BuyRecordDto dto) {
        BuyModel compra = new BuyModel();
        compra.setData(LocalDate.now());

        // Busca fornecedor
        SupplierModel fornecedor = supplierRepository.findById(dto.fornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        compra.setSupplier(fornecedor);

        // Processa itens
        for (BuyItemRecordDto itemDto : dto.itens()) {
            ProductModel produto = productRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BuyItemModel item = new BuyItemModel();
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoUnitario(itemDto.precoUnitario());

            compra.adicionarItem(item);

            // Atualiza estoque (aumenta)
            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque().add(BigDecimal.valueOf(itemDto.quantidade()))
            );
            productRepository.save(produto); // Garante persistência do estoque
        }

        // Calcula total
        compra.calcularValorTotal();

        return buyRepository.save(compra); // Corrigido aqui
    }
}