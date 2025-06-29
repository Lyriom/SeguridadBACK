package com.alerta.servicio;

import com.alerta.config.TwilioConfig;
import com.alerta.entidades.Reporte;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    public void enviarAlertaWhatsApp(Reporte reporte) {
        Twilio.init(TwilioConfig.ACCOUNT_SID, TwilioConfig.AUTH_TOKEN);

        // Tipo de suceso (manejo de nulls)
        String tipoSuceso = (reporte.getCategoria() != null && reporte.getCategoria().getTiposuceso() != null)
                ? reporte.getCategoria().getTiposuceso()
                : "No especificado";

        // Enlace de Google Maps con coordenadas
        String enlaceUbicacion = "https://www.google.com/maps?q=" + reporte.getLatitud() + "," + reporte.getLongitud();

        // Mensaje completo
        String mensaje = "🚨 Nueva alerta de seguridad #" + reporte.getId() + "\n"
                + "Tipo: " + tipoSuceso + "\n"
                + "Descripción: " + reporte.getDescripcion() + "\n"
                + "Ubicación: " + reporte.getLatitud() + ", " + reporte.getLongitud() + "\n"
                + "📍 Ver en mapa: " + enlaceUbicacion + "\n"
                + "Estado: " + reporte.getEstado() + "\n"
                + "Fecha: " + reporte.getFecha() + "\n\n"
                + "Responda este mensaje si requiere más información.";

        // Enviar mensaje por WhatsApp
        Message message = Message.creator(
                new PhoneNumber(TwilioConfig.TO),
                new PhoneNumber(TwilioConfig.FROM),
                mensaje
        ).create();

        System.out.println("✅ Alerta enviada con SID: " + message.getSid());
    }
}

