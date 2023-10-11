package edu.ifrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import io.quarkus.test.Mock;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {
    
// 1 - Crie um mock da classe ItemPedido

// 2 - injete um mock da classe Pedido
    @org.mockito.Mock
    DescontoService descontoService;

    @InjectMocks
    Pedido pedido;


    @Test
    public void calcularValorTotal(){
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(50));
        itens.add(new ItemPedido(50));

        pedido = new Pedido(itens, descontoService);

        when(descontoService.calcularDesconto(100)).thenReturn(10.0);

        double valorTotal = pedido.calcularValorTotal();
        
        assertEquals(90,valorTotal);
    }
    @Test
    public void calcularValorTotalSemDesconto(){
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(50));
        itens.add(new ItemPedido(50));

        pedido = new Pedido(itens, descontoService);

        when(descontoService.calcularDesconto(100)).thenReturn(0.0);

        double valorTotal = pedido.calcularValorTotal();
        
        assertEquals(100,valorTotal);
    }
    @Test
    public void calcularValorTotalDescontoMaior(){
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(50));
        itens.add(new ItemPedido(50));

        pedido = new Pedido(itens, descontoService);

        when(descontoService.calcularDesconto(100)).thenReturn(120.0);
        
        assertThrows(IllegalArgumentException.class,()->pedido.calcularValorTotal());
    }

    @Test
    public void calcularValorTotalComListaVazia(){
        List<ItemPedido> itens = new ArrayList<>();

        pedido = new Pedido(itens, descontoService);

        double valorTotal = pedido.calcularValorTotal();
        
        assertEquals(00,valorTotal);
    }

    @Test
    public void calcularValorTotalPorItem(){
        List<ItemPedido> itens = new ArrayList<>();
        itens.add(new ItemPedido(100));
        itens.add(new ItemPedido(100));

        when(descontoService.calcularDesconto(anyDouble())).thenReturn(90.0);

        pedido = new Pedido(itens, 10.0);

        double valorTotal = pedido.calcularValorTotalPorItem();
        
        assertEquals(00,valorTotal);
    }



}
