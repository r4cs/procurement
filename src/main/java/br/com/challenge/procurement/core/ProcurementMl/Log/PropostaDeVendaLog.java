package br.com.challenge.procurement.core.ProcurementMl.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PropostaDeVendaLog {

    private static final Logger logger = LoggerFactory.getLogger(PropostaDeVendaLog.class);

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
