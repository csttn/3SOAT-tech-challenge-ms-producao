package br.com.tech.challenge.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = {"src/test/resources/features"}
)
public class ListarFilaPedidosSteps {

    @Dado("que estou autenticado no sistema")
    public void que_estou_autenticado_no_sistema() {
        // Implementação para autenticação no sistema
    }

    @Quando("eu listar a fila de pedidos")
    public void eu_listar_a_fila_de_pedidos() {
        // Implementação para listar a fila de pedidos
    }

    @Então("devo receber uma lista de pedidos não vazia")
    public void devo_receber_uma_lista_de_pedidos_nao_vazia() {
        // Implementação para verificar se a lista de pedidos não está vazia
    }
}
