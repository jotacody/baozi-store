package com.baozoi.baozoi.controllers;

import com.baozoi.baozoi.model.Pedido;
import com.baozoi.baozoi.model.Produto;
import com.baozoi.baozoi.repository.ClienteRepository;
import com.baozoi.baozoi.repository.PedidoRepository;
import com.baozoi.baozoi.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidos;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;

    public PedidoController(PedidoRepository pedidos, ClienteRepository clientes, ProdutoRepository produtos) {
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido registrar(@Valid @RequestBody Pedido pedido) {
        if (!clientes.existsById(pedido.getClienteId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado");
        }
        Produto produto = produtos.findById(pedido.getProdutoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
        if (!produto.getEstoque()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto sem estoque");
        }
        return pedidos.save(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidos.findAll();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidos.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long id) {
        if (!pedidos.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        pedidos.deleteById(id);
    }
}
