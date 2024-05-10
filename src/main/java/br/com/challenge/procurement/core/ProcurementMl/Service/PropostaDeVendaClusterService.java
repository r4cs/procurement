package br.com.challenge.procurement.core.ProcurementMl.Service;

import br.com.challenge.procurement.core.api.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.api.repositories.PropostaDeVendaRepo;
import br.com.challenge.procurement.core.ProcurementMl.Models.PropostaDeVendaClusterModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaDeVendaClusterService {

    private final PropostaDeVendaRepo repo;
    private final PropostaDeVendaClusterModel clusterModel;

    @Autowired
    public PropostaDeVendaClusterService(PropostaDeVendaRepo repo, PropostaDeVendaClusterModel clusterModel) {
        this.repo = repo;
        this.clusterModel = clusterModel;
    }

    public void clusterizarPropostasDeVenda() {
        SparkSession sparkSession = SparkSession.builder().appName("ProcurementML").master("local[*]").getOrCreate();

        System.out.println("Iniciando clusterização de propostas de venda...");

        List<PropostaDeVenda> propostasDeVenda = repo.findAll(); // Recupera todas as propostas de venda
        Dataset<Row> data = sparkSession.createDataFrame(propostasDeVenda, PropostaDeVenda.class);

        System.out.println("Dados das propostas de venda carregados com sucesso...");

        sparkSession.stop(); // Parar a SparkSession após o uso

        clusterModel.trainModel(data);

        System.out.println("Clusterização das propostas de venda concluída.");

        // Realizar previsões ou outras operações com o modelo
    }
}
