package br.com.challenge.procurement.core.ProcurementMl.Models;

import br.com.challenge.procurement.core.api.model.entities.PropostaDeVenda;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Component;

@Component
public class PropostaDeVendaClusterModel {


    public PropostaDeVendaClusterModel() {
    }

    public KMeansModel trainModel(Dataset<Row> data) {
        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"id", "pedido_compra", "valor_unitario", "valor_total", "fornecedor"})
                .setOutputCol("features");

        Dataset<Row> dataset = assembler.transform(data);

        KMeans kmeans = new KMeans().setK(3).setSeed(1L);
        return kmeans.fit(dataset);
    }

    // Método para fazer previsões
    // public DataFrame predict(DataFrame newData, KMeansModel model) {}
}
