package com.ecohaulconnect.clientesservicos.domain.endereco;
import com.ecohaulconnect.clientesservicos.domain.servico.Servico;
import com.ecohaulconnect.clientesservicos.domain.transportador.Transportador;


public class CalculoDeProximidadeService {

    public static boolean filtrarServicosDentroDaArea(Servico servico, Transportador transportador) {
        var estaDentroDoRaioDeServico = false;

        var raioDeServicoEmKm = transportador.getRaioDeServicoEmKm().doubleValue();
        var latTransportador = transportador.getEndereco().getLatitude();
        var longTransportador = transportador.getEndereco().getLongitude();

        var latServico = servico.getEndereco().getLatitude();
        var longServico = servico.getEndereco().getLongitude();

        var distanciaEntreServicoETransportador = haversineDistance(
                latTransportador, latServico, longTransportador, longServico);

       if (Double.compare(distanciaEntreServicoETransportador, raioDeServicoEmKm) <= 0) {
          estaDentroDoRaioDeServico = true;
       }

        return estaDentroDoRaioDeServico;
    }

    private static Double haversineDistance (Double latA, Double latB, Double lonA, Double lonB) {
        final int R = 6371; //raio da terra

        Double latDistance = toRad(latA - latB);
        Double lonDistance = toRad(lonA - lonB);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(latA)) * Math.cos(toRad(latB)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }

    private static Double toRad(Double valor) {
        return valor * Math.PI / 180;
    }
}
