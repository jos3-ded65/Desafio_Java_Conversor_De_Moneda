/*
La clase Map de Java, que se usa para almacenar pares clave-valor
 */
import java.util.Map;

public record Monedas(String base_code, Map<String, Double> conversion_rates) { }
